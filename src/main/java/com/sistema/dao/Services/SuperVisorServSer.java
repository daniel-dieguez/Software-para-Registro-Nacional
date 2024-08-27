package com.sistema.dao.Services;

import com.sistema.dao.implement.ISuperVisorSerImpl;
import com.sistema.dao.repository.ISupervisorDAO;
import com.sistema.modals.modal.SuperVisor;
import com.sistema.modals.modal.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SuperVisorServSer implements ISuperVisorSerImpl {

    @Autowired
    private ISupervisorDAO iSupervisorDAO;


    @Override
    public List<SuperVisor> findAll() {
        return this.iSupervisorDAO.findAll();
    }

    @Override
    public SuperVisor findById(int idSupervisor) {
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

    public List<Vendedor> vendedorYSupervisor(int idSupervisor) {
        SuperVisor superVisor = this.findById(idSupervisor);
        if (superVisor != null) {
            return superVisor.getVendedors();
        } else {
            return null;
        }
    }




}
