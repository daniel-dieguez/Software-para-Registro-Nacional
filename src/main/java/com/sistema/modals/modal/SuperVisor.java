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
@Table(name = "Supervisor")
@Entity
public class SuperVisor {

    @Id
    @Column(name = "idSupervisor")
    private int idSupervisor;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Apellido")
    private String Apellido;
    @Column(name = "Email")
    private String Email;
}
