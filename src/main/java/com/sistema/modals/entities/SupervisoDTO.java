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
public class SupervisoDTO implements Serializable {

    @NotEmpty(message = "Este campo debe de estar lleno ")
    private String nombre_super;
    @NotNull(message = "Debes de agregar el cambio de region")
    private Integer id_region;
    @NotEmpty(message = "Debes de agregar el apellido que quiere cambiar")
    private String apellido_super;

}
