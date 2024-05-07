package com.sistema.dao;

import com.sistema.modals.modal.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVendedorDAO extends JpaRepository<Vendedor, Object> {
}
