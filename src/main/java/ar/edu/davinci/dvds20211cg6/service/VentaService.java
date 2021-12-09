package ar.edu.davinci.dvds20211cg6.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.edu.davinci.dvds20211cg6.domain.Item;
import ar.edu.davinci.dvds20211cg6.domain.Venta;
import ar.edu.davinci.dvds20211cg6.domain.VentaEfectivo;
import ar.edu.davinci.dvds20211cg6.domain.VentaTarjeta;
import ar.edu.davinci.dvds20211cg6.exception.BusinessException;


public interface VentaService {

    public List<Venta> listAll();
    public Page<Venta> list(Pageable pageable);

    public Venta findById(Long id) throws BusinessException;

    public VentaEfectivo save(VentaEfectivo venta) throws BusinessException;
    public VentaEfectivo save(VentaEfectivo ventaEfectivo, Item item) throws BusinessException;
    public VentaTarjeta save(VentaTarjeta venta) throws BusinessException;
    public VentaTarjeta save(VentaTarjeta ventaTarjeta, Item item) throws BusinessException;

    public void delete(Venta venta);
    public void delete(Long id);

    public long count();

    public Venta addItem(Long id, Item item) throws BusinessException;
    public Venta updateItem(Long ventaId, Long itemId, Item item) throws BusinessException;
    public Venta deleteItem(Long ventaId, Long itemId) throws BusinessException;
}

