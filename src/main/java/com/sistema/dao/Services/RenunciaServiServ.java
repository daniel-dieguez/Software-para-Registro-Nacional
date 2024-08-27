package com.sistema.dao.Services;

import com.sistema.dao.implement.IRenunciaServImpl;
import com.sistema.dao.repository.IRenunciaDAO;
import com.sistema.modals.modal.Renuncia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenunciaServiServ implements IRenunciaServImpl {

    @Autowired
    private IRenunciaDAO iRenunciaDAO;


    @Override
    public List<Renuncia> findAll() {
        return this.iRenunciaDAO.findAll();
    }

    @Override
    public Renuncia findById(String idRenincia) {
        return this.iRenunciaDAO.findById(idRenincia).orElse(null);
    }

    @Override
    public Renuncia save(Renuncia renuncia) {
        return this.iRenunciaDAO.save(renuncia);
    }

    @Override
    public void delete(Renuncia renuncia) {
        this.iRenunciaDAO.delete(renuncia);

    }
}
