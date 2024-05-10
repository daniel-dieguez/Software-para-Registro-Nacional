package com.sistema.modals.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "supervisor")
@Entity
public class SuperVisor {

    @Id
    @Column(name = "id_supervisor")
    private int id_supervisor;
    @Column(name = "nombre-super")
    private String nombre_super;
    @Column(name = "apellido_super")
    private String apellido_super;
    @Column(name = "email_super")
    private String email_super;
    @Column (name = "id_region")
    private int id_region;

}
