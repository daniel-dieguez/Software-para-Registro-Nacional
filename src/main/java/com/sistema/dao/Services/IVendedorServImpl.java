package com.sistema.dao.Services;

import com.sistema.modals.modal.Region;
import com.sistema.modals.modal.Vendedor;

import java.util.List;

public interface IVendedorServImpl {

    public List<Vendedor> findAll();
    public Vendedor findById (String idVendedor);
    public Vendedor save (Vendedor vendedor);
    public void delete (Vendedor vendedor);
}
