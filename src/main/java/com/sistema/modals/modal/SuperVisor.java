package com.sistema.modals.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "supervisor")
@Entity
public class SuperVisor {

    @Id
    @Column(name = "id_supervisor")
    private int id_supervisor;
    @Column(name = "nombre_super")
    private String nombre_super;
    @Column(name = "apellido_super")
    private String apellido_super;
    @Column(name = "email_super")
    private String email_super;



    @ManyToOne(fetch = FetchType.EAGER)// fetch para atraer datos = eager traera el objeto hijo(tipo get) y lazy es de no traer el get(que no traiga la info)
    @JoinColumn(name = "id_region", referencedColumnName = "id_region")
    private Region region;

    @OneToMany(mappedBy = "superVisor", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private List<Vendedor> vendedors;





}
