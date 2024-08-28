package com.sistema.modals.modal.rrhh;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sistema.modals.modal.Vendedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contrataciones")
public class Contrataciones implements Serializable {

    @Id
    @Column(name = "id_contrato")
    private int id_Contrato;



    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "detalles")
    private String detalles;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vendedor", referencedColumnName = "id_vendedor")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Vendedor vendedor;




}
