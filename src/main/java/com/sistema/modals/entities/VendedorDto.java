package com.sistema.modals.entities;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VendedorDto implements Serializable {


    private int id_vendedor;
    private String nombre_vendedor;
    private String apellido_vendedor;
    private String email_vendedor;
    private int id_supervisor;
    private int id_region;
}
