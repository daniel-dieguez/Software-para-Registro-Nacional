package com.sistema.controller.rrhh;


import com.sistema.dao.Services.VendedorService;
import com.sistema.dao.Services.rrhh.ContratacionService;
import com.sistema.dao.implement.rrhh.IContratacionSerImp;
import com.sistema.dao.repository.rrhh.IContratacionDAO;
import com.sistema.modals.entities.rrhh.ContratacionDTO;
import com.sistema.modals.modal.Region;
import com.sistema.modals.modal.Vendedor;
import com.sistema.modals.modal.rrhh.Contrataciones;
import jakarta.validation.Valid;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/contrataciones")
public class ContratacionController {

    @Autowired
    private IContratacionSerImp iContratacionSerImp;

    @Autowired
    private VendedorService vendedorService;


    private Logger logger = LoggerFactory.getLogger(Contrataciones.class);

    @GetMapping("/viewAll")
    public ResponseEntity<?> viewAll (){

        Map<String, Object> response = new HashMap<>();
        this.logger.debug("inicia consulta de contrataciones");
        try {
            List<Contrataciones> contrataciones = this.iContratacionSerImp.findAll();
            logger.info("Se ejecuto consulta de contrataciones");
            return new ResponseEntity<List<Contrataciones>>(contrataciones, HttpStatus.OK);

        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }catch(DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @PostMapping("/newContrato")
    public ResponseEntity<?> newContrato(@Valid  @RequestBody ContratacionDTO value, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        try {

            if (iContratacionSerImp.findById(value.getId_contrato()) != null) {
                response.put("error", "El ID de contrato ya existe: " + value.getId_contrato());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            Contrataciones contrataciones = new Contrataciones();
            contrataciones.setId_contrato(value.getId_contrato());
            contrataciones.setFecha(value.getFecha());
            contrataciones.setDetalles(value.getDetalles());

           Vendedor vendedor = vendedorService.findById(String.valueOf(value.getId_vendedor()));

            contrataciones.setVendedor(vendedor);
            this.iContratacionSerImp.save(contrataciones);


            logger.info("se agrego nuevo vendedor");
            response.put("mensaje", "Nuevo contrato para vendedor".concat(vendedor.getNombre_vendedor()));
            response.put("Contrata", contrataciones);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);


        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }catch(DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    private Map<String, Object> getTransactionExepcion(Map<String,Object> response, CannotCreateTransactionException e){
        logger.error("Error al momento de conectarse a la base de datos");
        response.put("mensajee", "error al moneotno de contectarse a la");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;
    }

    private Map<String, Object> getDataAccessException(Map<String, Object> response, DataAccessException e){
        logger.error("El error al momento de ejecutlar la consulta ea  la base d adatos");
        response.put("mensaje", "Error al momenot de ejecutar ola consulta a la base de datos");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;

    }
}
