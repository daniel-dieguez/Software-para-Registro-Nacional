package com.sistema.dao.Services;

import com.sistema.modals.modal.SuperVisor;

import java.util.List;

public interface ISuperVisorSerImpl {
    public List<SuperVisor> findAll();
    public SuperVisor findById (String idSupervisor);
    public SuperVisor save (SuperVisor superVisor);
    public void delete (SuperVisor superVisor);
}
