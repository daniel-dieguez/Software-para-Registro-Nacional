package com.sistema.modals.entities.rrhh;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContratacionDTO implements Serializable {

    private int id_contrato;

    private Date fecha;

    private String detalles;

    private int id_vendedor;
}
