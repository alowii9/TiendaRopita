package ar.edu.davinci.dvds20211cg6.model;

import  static org.junit.jupiter.api.Assertions.*;

import ar.edu.davinci.dvds20211cg6.domain.Prenda;
import ar.edu.davinci.dvds20211cg6.domain.TipoPrenda;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PrendaTest {

    @Test
    void builderTest() {

        Long id = 99L;
        String descripcion = "Remera Azul Talle M 100% Algodón";
        TipoPrenda tipo = TipoPrenda.REMERA;
        BigDecimal precioBase = new BigDecimal(795);

        Prenda prenda = Prenda.builder()
                        .id(id)
                        .descripcion(descripcion)
                        .tipo(tipo)
                        .precioBase(precioBase)
                        .build();

        assertNotNull(prenda);
        assertEquals(id, prenda.getId());
        assertEquals(descripcion, prenda.getDescripcion());
        assertEquals(tipo, prenda.getTipo());
        assertEquals(precioBase, prenda.getPrecioBase());

    }

    @Test
    void settersTest() {
        Long id = 42L;
        String descripcion = "Boxer Floral Talle M";
        TipoPrenda tipo = TipoPrenda.BOXER;
        BigDecimal precioBase = new BigDecimal(666);
        String prendaString = String.format("Prenda(id=%d, descripcion=%s, tipo=%s, precioBase=%.0f)", id, descripcion, tipo, precioBase);

        Prenda prenda = new Prenda();
        prenda.setId(id);
        prenda.setDescripcion(descripcion);
        prenda.setTipo(tipo);
        prenda.setPrecioBase(precioBase);

        assertNotNull(prenda);
        assertEquals(id, prenda.getId());
        assertEquals(descripcion, prenda.getDescripcion());
        assertEquals(tipo, prenda.getTipo());
        assertEquals(precioBase, prenda.getPrecioBase());
        assertEquals(prendaString, prenda.toString());
    }

}
