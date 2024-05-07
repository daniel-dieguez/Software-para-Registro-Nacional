package com.sistema.dao.Services;

import com.sistema.dao.ISupervisorDAO;
import com.sistema.modals.modal.SuperVisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperVisorServ implements ISuperVisorImpl{

    @Autowired
    private ISupervisorDAO iSupervisorDAO;


    @Override
    public List<SuperVisor> findAll() {
        return this.iSupervisorDAO.findAll();
    }

    @Override
    public SuperVisor findById(String idSupervisor) {
        return this.iSupervisorDAO.findById(idSupervisor).orElse(null);
    }

    @Override
    public SuperVisor save(SuperVisor superVisor) {
        return this.iSupervisorDAO.save(superVisor);
    }

    @Override
    public void delete(SuperVisor superVisor) {
        this.iSupervisorDAO.delete(superVisor);

    }
}
