package com.sistema.modals.modal;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sistema.modals.modal.rrhh.Contrataciones;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_supervisor",referencedColumnName = "id_supervisor")
    private SuperVisor superVisor;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_region", referencedColumnName = "id_region")
    @JsonIgnore
    private Region region; //SI TUITLIZO ESTE ME MOSTRARA LA REGION ADEMAS DE



    @OneToOne(mappedBy = "vendedor", fetch = FetchType.LAZY)
    @JsonIgnore
    private Contrataciones contrataciones;


}
