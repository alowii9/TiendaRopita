package ar.edu.davinci.dvds20211cg6.domain;

import java.util.LinkedList;
import java.util.List;

public enum TipoPrenda {

    REMERA("Remera"),
    PANTALON("Pantalón"),
    SHORTS("Shorts"),
    BOXER("Boxer"),
    HODDIE("Hoddie"),
    MEDIA("Media"),
    CAMISA("Camisa"),
    SACO("Saco"),
    TAPADO("Tapado");

    private String descripcion;

    private TipoPrenda(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoPrenda getDescripcion() {
        return descripcion;
    }

    public String getDescripcion2() {
        return descripcion;
    }

    public static List<TipoPrenda> getTiposPrenda() {
        List<TipoPrenda> tiposPrenda = new LinkedList<>();
        tiposPrenda.add(TipoPrenda.REMERA);
        tiposPrenda.add(TipoPrenda.PANTALON);
        tiposPrenda.add(TipoPrenda.SHORTS);
        tiposPrenda.add(TipoPrenda.BOXER);
        tiposPrenda.add(TipoPrenda.HODDIE);
        tiposPrenda.add(TipoPrenda.MEDIA);
        tiposPrenda.add(TipoPrenda.CAMISA);
        tiposPrenda.add(TipoPrenda.SACO);
        tiposPrenda.add(TipoPrenda.TAPADO);



        return tiposPrenda;
    }


}
