package com.sistema.dao.Services;

import com.sistema.modals.modal.Renuncia;

import java.util.List;

public interface IRenunciaServImpl {
    public List<Renuncia> findAll();
    public Renuncia findById (String idRenincia);
    public Renuncia save (Renuncia renuncia);
    public void delete (Renuncia renuncia);
}
