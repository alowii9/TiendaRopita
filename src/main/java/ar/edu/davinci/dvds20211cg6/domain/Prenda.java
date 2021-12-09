package ar.edu.davinci.dvds20211cg6.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.math.BigDecimal;

//Lombok
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

//JPA
@Entity
@Table(name="prendas")

//se agrego el implemento de Serializable.
public class Prenda implements Serializable {


    //y tmb se agrego este Long.
    private static final long serialVersionUID = 3202089571512147315L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "prd_id")
    private long id;

    @Column(name = "prd_descripcion")
    private String descripcion;

    @Column(name = "prd_tipo")
    @Enumerated(EnumType.STRING)
    private TipoPrenda tipo;

    @Column(name = "prd_precio_base")
    private BigDecimal precioBase;


    //se agrego este metodo que va de la mano con la clase item
    public BigDecimal getPrecioFinal() {
        return precioBase;
    }


}
