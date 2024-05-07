package com.sistema.dao.Services;

import com.sistema.dao.IVendedorDAO;
import com.sistema.modals.entities.VendedorDto;
import com.sistema.modals.modal.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VendedorService implements IVendedorServImpl{

    @Autowired
    private IVendedorDAO iVendedorDAO;

    @Override
    public List<Vendedor> findAll() {
        return this.iVendedorDAO.findAll();
    }

    @Override
    public Vendedor findById(String idVendedor) {
        return this.iVendedorDAO.findById(idVendedor).orElse(null);
    }

    @Override
    public Vendedor save(Vendedor vendedor) {
        return this.iVendedorDAO.save(vendedor);
    }

    @Override
    public void delete(Vendedor vendedor) {
        this.iVendedorDAO.delete(vendedor);

    }
}
