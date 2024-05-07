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
@Table(name = "Region")
@Entity
public class Region implements Serializable {

    @Id
    @Column (name = "idRegion")
    private int idRegion;
    @Column (name = "Nombre")
    private int Nombre;





}
