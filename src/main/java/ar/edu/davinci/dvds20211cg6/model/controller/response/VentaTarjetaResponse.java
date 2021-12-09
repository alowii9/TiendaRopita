package ar.edu.davinci.dvds20211cg6.model.controller.response;


import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaTarjetaResponse extends VentaResponse {

    private Integer cantidadCuotas;

    private BigDecimal coeficienteTarjeta;
}

