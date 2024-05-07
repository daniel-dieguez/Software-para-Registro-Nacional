package com.sistema.modals.modal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Vendedor")
@Entity
public class Vendedor implements Serializable {


    @Id
    @Column(name = "idVendedor")
    private int idVendedor;
    @Column (name = "Nombre")
    private String Nombre;
    @Column(name = "Apellido")
    private String Apellido;
    @Column(name = "Emal")
    private String Email;
    @Column(name = "idSupervisor")
    private String idSupervisor;
    @Column(name = "idRegion")
    private String idRegion;
}
