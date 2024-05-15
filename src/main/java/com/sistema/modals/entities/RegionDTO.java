package com.sistema.modals.entities;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegionDTO implements Serializable {

    @NotEmpty(message = "Este campo de nombre debe de estar lleno")
    private String nombre_region;

}
