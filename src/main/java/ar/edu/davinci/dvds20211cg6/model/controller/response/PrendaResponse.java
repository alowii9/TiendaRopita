package ar.edu.davinci.dvds20211cg6.model.controller.response;

import ar.edu.davinci.dvds20211cg6.domain.TipoPrenda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrendaResponse {

    private Long id;

    private BigDecimal precioBase;

    private TipoPrenda tipo;

    private String descripcion;


}
