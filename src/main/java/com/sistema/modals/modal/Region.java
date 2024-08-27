package com.sistema.modals.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "region")
@Entity
public class Region implements Serializable {

    @Id
    @Column (name = "id_region")
    private int id_region;

    @Column(name = "nombre_region")
    private String nombre_region;


    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private List<SuperVisor> superVisors;



}
