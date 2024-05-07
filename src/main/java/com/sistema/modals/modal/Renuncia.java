package com.sistema.modals.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Renunca")
@Entity
public class Renuncia {

    @Id
    @Column(name = "idRenuncia")
    private String idRenuncia;
    @Column(name = "idVendedor")
    private String idVendedor;
    @Column(name = "Fecha")
    private String Fecha;
    @Column(name = "Motivo")
    private String Motivo;

}
