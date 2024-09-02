package com.sistema.dao.repository;

import com.sistema.modals.modal.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IVendedorDAO extends JpaRepository<Vendedor, Object> {

    @Query("SELECT v FROM Vendedor v LEFT JOIN Renuncia r ON v.id_vendedor = r.vendedor.id_vendedor WHERE r.id_renuncia IS NULL")
    List<Vendedor> findVendedoActivos();

}
