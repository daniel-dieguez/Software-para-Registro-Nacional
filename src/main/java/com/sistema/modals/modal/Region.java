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
@Table(name = "region")
@Entity
public class Region implements Serializable {

    @Id
    @Column (name = "id_region")
    private int id_region;
    @Column (name = "nombre_region")
    private String nombre_region;





}
