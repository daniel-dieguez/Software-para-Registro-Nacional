package com.sistema.dao.Services.rrhh;

import com.sistema.dao.implement.rrhh.IRenunicaSerImp;
import com.sistema.dao.repository.rrhh.IRenunciaDAO;
import com.sistema.modals.modal.rrhh.Renuncia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenunciaServiServ implements IRenunicaSerImp {

    @Autowired
    private IRenunciaDAO iRenunciaDAO;


    @Override
    public List<Renuncia> findAll() {
        return this.iRenunciaDAO.findAll();
    }

    @Override
    public Renuncia findById(int id_renuncia) {
        return this.iRenunciaDAO.findById(id_renuncia).orElse(null);
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
