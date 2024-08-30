package com.sistema.modals.modal.rrhh;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sistema.modals.modal.Vendedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "renuncia")
public class Renuncia implements Serializable {

    @Id
    @Column(name = "id_renuncia")
    public int id_renuncia;
    @Column(name = "fecha")
    public Date  fecha;
    @Column(name = "motivo")
    public String motivo;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vendedor", referencedColumnName = "id_vendedor")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Vendedor vendedor;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_contrato", referencedColumnName = "id_contrato")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Contrataciones contratacion;


}
