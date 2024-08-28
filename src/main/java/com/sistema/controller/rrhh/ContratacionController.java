package com.sistema.controller.rrhh;


import com.sistema.dao.Services.rrhh.ContratacionService;
import com.sistema.dao.implement.rrhh.IContratacionSerImp;
import com.sistema.dao.repository.rrhh.IContratacionDAO;
import com.sistema.modals.modal.Region;
import com.sistema.modals.modal.rrhh.Contrataciones;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/contrataciones")
public class ContratacionController {

    @Autowired
    private IContratacionSerImp iContratacionSerImp;

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
