package com.sistema.modals.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "renuncia")
@Entity
public class Renuncia {

    @Id
    @Column(name = "id_renuncia")
    private int id_renuncia;
    @Column(name = "id_vendedor")
    private int id_vendedor;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "motivo")
    private String motivo;

}
