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
@Table(name = "vendedor")
@Entity
public class Vendedor implements Serializable {


    @Id
    @Column(name = "id_vendedor")
    private int id_vendedor;
    @Column (name = "nombre_vendedor")
    private String nombre_vendedor;
    @Column(name = "apellido_vendedor")
    private String apellido_vendedor;
    @Column(name = "email_vendedor")
    private String email_vendedor;
    @Column(name = "id_supervisor")
    private int id_supervisor;
    @Column(name = "id_region")
    private int id_region;
}
