package ar.edu.davinci.dvds20211cg6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ar.edu.davinci.dvds20211cg6.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.davinci.dvds20211cg6.model.controller.request.ClienteInsertRequest;
import ar.edu.davinci.dvds20211cg6.model.controller.request.ClienteUpdateRequest;
import ar.edu.davinci.dvds20211cg6.model.controller.request.ItemInsertRequest;
import ar.edu.davinci.dvds20211cg6.model.controller.request.ItemUpdateRequest;
import ar.edu.davinci.dvds20211cg6.model.controller.request.PrendaInsertRequest;
import ar.edu.davinci.dvds20211cg6.model.controller.request.PrendaUpdateRequest;
import ar.edu.davinci.dvds20211cg6.model.controller.request.VentaEfectivoRequest;
import ar.edu.davinci.dvds20211cg6.model.controller.request.VentaTarjetaRequest;
import ar.edu.davinci.dvds20211cg6.model.controller.response.ClienteResponse;
import ar.edu.davinci.dvds20211cg6.model.controller.response.ItemResponse;
import ar.edu.davinci.dvds20211cg6.model.controller.response.PrendaResponse;
import ar.edu.davinci.dvds20211cg6.model.controller.response.VentaEfectivoResponse;
import ar.edu.davinci.dvds20211cg6.model.controller.response.VentaTarjetaResponse;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;


@Configuration
public class OrikaConfiguration {


    private final Logger LOGGER = LoggerFactory.getLogger(OrikaConfiguration.class);

    private final ObjectMapper objectMapper;

    public OrikaConfiguration() {
        objectMapper = new ObjectMapper();
    }

    @Bean
    public MapperFacade mapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        // PRENDA

        mapperFactory.classMap(Prenda.class, PrendaInsertRequest.class).byDefault().register();
        mapperFactory.classMap(Prenda.class, PrendaUpdateRequest.class).byDefault().register();
        mapperFactory.classMap(Prenda.class, PrendaResponse.class)
                .customize(new CustomMapper<Prenda, PrendaResponse>() {
                    public void mapAtoB(final Prenda prenda, final PrendaResponse prendaResponse, final MappingContext context) {
                        LOGGER.info(" #### Custom mapping for Prenda --> PrendaResponse #### ");
                        prendaResponse.setId(prenda.getId());
                        prendaResponse.setDescripcion(prenda.getDescripcion());
                        prendaResponse.setTipo(prenda.getTipo().getDescripcion());
                        prendaResponse.setPrecioBase(prenda.getPrecioBase());
                    }
                }).register();

        // CLIENTE

        mapperFactory.classMap(Cliente.class, ClienteInsertRequest.class).byDefault().register();
        mapperFactory.classMap(Cliente.class, ClienteUpdateRequest.class).byDefault().register();
        mapperFactory.classMap(Cliente.class, ClienteResponse.class).byDefault().register();


        // ITEM

        mapperFactory.classMap(ItemInsertRequest.class, Item.class)
                .customize(new CustomMapper<ItemInsertRequest, Item>() {
                    public void mapAtoB(final ItemInsertRequest itemInsertRequest, final Item item, final MappingContext context) {
                        LOGGER.info(" #### Custom mapping for itemInsertRequest --> Item #### ");
                        Prenda prenda = Prenda.builder()
                                .id(itemInsertRequest.getPrendaId())
                                .build();
                        item.setPrenda(prenda);
                        item.setCantidad(itemInsertRequest.getCantidad());
                    }
                }).register();

        mapperFactory.classMap(ItemUpdateRequest.class, Item.class)
                .customize(new CustomMapper<ItemUpdateRequest, Item>() {
                    public void mapAtoB(final ItemUpdateRequest itemUpdateRequest, final Item item, final MappingContext context) {
                        LOGGER.info(" #### Custom mapping for itemUpdateRequest --> Item #### ");
                        item.setCantidad(itemUpdateRequest.getCantidad());
                    }
                }).register();

        mapperFactory.classMap(Item.class, ItemResponse.class)
                .customize(new CustomMapper<Item, ItemResponse>() {
                    public void mapAtoB(final Item item, final ItemResponse itemResponse, final MappingContext context) {
                        LOGGER.info(" #### Custom mapping for Item --> ItemResponse #### ");
                        PrendaResponse prendaResponse = PrendaResponse.builder()
                                .id(item.getPrenda().getId())
                                .descripcion(item.getPrenda().getDescripcion())
                                .tipo(item.getPrenda().getTipo().getDescripcion())
                                .precioBase(item.getPrenda().getPrecioBase())
                                .build();
                        itemResponse.setId(item.getId());
                        itemResponse.setCantidad(item.getCantidad());
                        itemResponse.setPrenda(prendaResponse);
                        itemResponse.setImporte(item.importe());
                    }
                }).register();

        // VENTA EFECTIVO

        mapperFactory.classMap(VentaEfectivoRequest.class, VentaEfectivo.class)
                .customize(new CustomMapper<VentaEfectivoRequest, VentaEfectivo>() {
                    public void mapAtoB(final VentaEfectivoRequest ventaEfectivoRequest, final VentaEfectivo venta, final MappingContext context) {
                        LOGGER.info(" #### Custom mapping for VentaEfectivoRequest --> VentaEfectivo #### ");
                        Cliente cliente = Cliente.builder()
                                .id(ventaEfectivoRequest.getClienteId())
                                .build();
                        venta.setCliente(cliente);
                    }
                }).register();

        mapperFactory.classMap(VentaEfectivo.class, VentaEfectivoResponse.class)
                .customize(new CustomMapper<VentaEfectivo, VentaEfectivoResponse>() {
                    public void mapAtoB(final VentaEfectivo venta, final VentaEfectivoResponse ventaResponse, final MappingContext context) {
                        LOGGER.info(" #### Custom mapping for VentaEfectivo --> VentaEfectivoResponse #### ");

                        ClienteResponse cliente = ClienteResponse.builder()
                                .id(venta.getCliente().getId())
                                .nombre(venta.getCliente().getNombre())
                                .apellido(venta.getCliente().getApellido())
                                .build();

                        ventaResponse.setId(venta.getId());
                        ventaResponse.setCliente(cliente);

                        DateFormat formatearFecha = new SimpleDateFormat(Constantes.FORMATO_FECHA);
                        String fechaStr = formatearFecha.format(venta.getFecha());

                        ventaResponse.setFecha(fechaStr);
                        ventaResponse.setImporteFinal(venta.importeFinal());

                        ventaResponse.setItems(new ArrayList<ItemResponse>());
                        for (Item item : venta.getItems()) {
                            PrendaResponse prendaResponse = PrendaResponse.builder()
                                    .id(item.getPrenda().getId())
                                    .descripcion(item.getPrenda().getDescripcion())
                                    .tipo(item.getPrenda().getTipo().getDescripcion())
                                    .precioBase(item.getPrenda().getPrecioBase())
                                    .build();
                            ItemResponse itemResponse = ItemResponse.builder()
                                    .id(item.getId())
                                    .cantidad(item.getCantidad())
                                    .prenda(prendaResponse)
                                    .importe(item.importe())
                                    .build();

                            ventaResponse.getItems().add(itemResponse);
                        }
                    }
                }).register();


        // VENTA TARJETA

        mapperFactory.classMap(VentaTarjetaRequest.class, VentaTarjeta.class)
                .customize(new CustomMapper<VentaTarjetaRequest, VentaTarjeta>() {
                    public void mapAtoB(final VentaTarjetaRequest ventaTarjetaRequest, final VentaTarjeta venta, final MappingContext context) {
                        LOGGER.info(" #### Custom mapping for VentaTarjetaRequest --> VentaTarjeta #### ");
                        Cliente cliente = Cliente.builder()
                                .id(ventaTarjetaRequest.getClienteId())
                                .build();
                        venta.setCliente(cliente);
                        venta.setCantidadCuotas(ventaTarjetaRequest.getCantidadCuotas());
                    }
                }).register();
        //mapperFactory.classMap(Cliente.class, ClienteUpdateRequest.class).byDefault().register();
        mapperFactory.classMap(VentaTarjeta.class, VentaTarjetaResponse.class)
                .customize(new CustomMapper<VentaTarjeta, VentaTarjetaResponse>() {
                    public void mapAtoB(final VentaTarjeta venta, final VentaTarjetaResponse ventaResponse, final MappingContext context) {
                        LOGGER.info(" #### Custom mapping for VentaTarjeta --> VentaTarjetaResponse #### ");

                        ClienteResponse cliente = ClienteResponse.builder()
                                .id(venta.getCliente().getId())
                                .nombre(venta.getCliente().getNombre())
                                .apellido(venta.getCliente().getApellido())
                                .build();

                        ventaResponse.setId(venta.getId());
                        ventaResponse.setCliente(cliente);

                        DateFormat formatearFecha = new SimpleDateFormat(Constantes.FORMATO_FECHA);
                        String fechaStr = formatearFecha.format(venta.getFecha());

                        ventaResponse.setFecha(fechaStr);
                        ventaResponse.setImporteFinal(venta.importeFinal());

                        ventaResponse.setItems(new ArrayList<ItemResponse>());
                        for (Item item : venta.getItems()) {
                            PrendaResponse prendaResponse = PrendaResponse.builder()
                                    .id(item.getPrenda().getId())
                                    .descripcion(item.getPrenda().getDescripcion())
                                    .tipo(item.getPrenda().getTipo().getDescripcion())
                                    .precioBase(item.getPrenda().getPrecioBase())
                                    .build();
                            ItemResponse itemResponse = ItemResponse.builder()
                                    .id(item.getId())
                                    .cantidad(item.getCantidad())
                                    .prenda(prendaResponse)
                                    .importe(item.importe())
                                    .build();

                            ventaResponse.getItems().add(itemResponse);
                        }

                        ventaResponse.setCantidadCuotas(venta.getCantidadCuotas());
                        ventaResponse.setCoeficienteTarjeta(venta.getCoeficienteTarjeta());
                    }
                }).register();



        // RETORNAMOS LA INSTANCIA MAPPER FACTORY
        return mapperFactory.getMapperFacade();
    }


}
