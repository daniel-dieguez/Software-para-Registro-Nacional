package com.sistema.modals.entities.rrhh;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RenunciaDTO implements Serializable {

    public int id_renuncia;
    public Date fecha;
    public String motivo;

}
