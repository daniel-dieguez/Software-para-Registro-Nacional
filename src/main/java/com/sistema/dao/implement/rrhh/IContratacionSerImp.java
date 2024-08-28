package com.sistema.dao.implement.rrhh;

import com.sistema.modals.entities.rrhh.ContratacionDTO;
import com.sistema.modals.modal.rrhh.Contrataciones;

import java.util.List;

public interface IContratacionSerImp {


    public List<Contrataciones> findAll();
    public Contrataciones findById(int id);
    public Contrataciones save(Contrataciones contratacion);
    public void delete(Contrataciones contratacion);
}
