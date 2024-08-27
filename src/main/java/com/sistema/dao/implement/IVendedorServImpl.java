package com.sistema.dao.implement;

import com.sistema.modals.modal.Vendedor;

import java.util.List;

public interface IVendedorServImpl {

    public List<Vendedor> findAll();
    public Vendedor findById (String idVendedor);
    public Vendedor save (Vendedor vendedor);
    public void delete (Vendedor vendedor);
}
