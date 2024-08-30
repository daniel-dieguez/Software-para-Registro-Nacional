package com.sistema.dao.implement.rrhh;

import com.sistema.modals.modal.rrhh.Renuncia;

import java.util.List;

public interface IRenunicaSerImp {

    public List<Renuncia> findAll();
    public Renuncia findById(int id_renuncia);
    public Renuncia save(Renuncia renuncia);
    public void delete(Renuncia renuncia);
}
