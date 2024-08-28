package com.sistema.modals.entities.rrhh;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContratacionDTO implements Serializable {

    @NotEmpty(message = "Este campo debe de estar lleno ")
    private String fecha;
}
