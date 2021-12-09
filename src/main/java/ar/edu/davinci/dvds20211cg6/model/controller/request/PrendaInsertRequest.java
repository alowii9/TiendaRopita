package ar.edu.davinci.dvds20211cg6.model.controller.request;

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
public class PrendaInsertRequest {

    private BigDecimal precioBase;

    private TipoPrenda tipo;

    private String descripcion;

}
