package com.sistema.dao.Services;

import com.sistema.modals.modal.Region;
import com.sistema.modals.modal.SuperVisor;

import java.util.List;

public interface ISuperVisorImpl {
    public List<SuperVisor> findAll();
    public SuperVisor findById (String idSupervisor);
    public SuperVisor save (SuperVisor superVisor);
    public void delete (SuperVisor superVisor);
}
