package com.sistema.dao.implement;

import com.sistema.modals.modal.SuperVisor;
import com.sistema.modals.modal.Vendedor;

import java.util.List;

public interface ISuperVisorSerImpl {
    public List<SuperVisor> findAll();
    public SuperVisor findById (int idSupervisor);
    public SuperVisor save (SuperVisor superVisor);
    public void delete (SuperVisor superVisor);
}
