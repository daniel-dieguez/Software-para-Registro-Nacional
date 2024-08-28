package com.sistema.dao.Services.rrhh;

import com.sistema.dao.implement.rrhh.IContratacionSerImp;
import com.sistema.dao.repository.rrhh.IContratacionDAO;
import com.sistema.modals.modal.rrhh.Contrataciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratacionService implements IContratacionSerImp {

    @Autowired
    private IContratacionDAO contratacionDAO;



    @Override
    public List<Contrataciones> findAll() {
        return this.contratacionDAO.findAll();
    }

    @Override
    public Contrataciones findById(int id) {
        return this.contratacionDAO.findById(id).orElse(null);
    }

    @Override
    public Contrataciones save(Contrataciones contratacion) {
        return this.contratacionDAO.save(contratacion);
    }

    @Override
    public void delete(Contrataciones contratacion) {
        this.contratacionDAO.delete(contratacion);

    }
}
