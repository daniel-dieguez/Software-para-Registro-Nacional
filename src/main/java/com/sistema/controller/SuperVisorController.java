package com.sistema.controller;

import com.sistema.dao.Services.ISuperVisorImpl;
import com.sistema.dao.Services.SuperVisorServ;
import com.sistema.modals.modal.SuperVisor;
import com.sistema.modals.modal.Vendedor;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/SuperVisor")
public class SuperVisorController {


    @Autowired
    private ISuperVisorImpl iSuperVisorImpl;

    private Logger logger = LoggerFactory.getLogger(SuperVisor.class);


    @GetMapping
    public ResponseEntity<?> supervisor(){
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("inica consulta");
        try{
            List<SuperVisor> superVisor =  this.iSuperVisorImpl.findAll();
            if(superVisor == null && superVisor.isEmpty()){
                logger.warn("No existe registro de entidad");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                logger.info("se ejecuta la consulta");
                return new ResponseEntity<List<SuperVisor>>(superVisor, HttpStatus.OK);
            }
        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }catch(DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SuperVisor value, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors() == true){
            List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            logger.info("se encontraron errores al momento de validarr");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            SuperVisor superVisor = new SuperVisor();
            superVisor.setNombre(value.getNombre());
            superVisor.setApellido(value.getApellido());
            superVisor.setEmail(value.getEmail());
            superVisor.setIdSupervisor(value.getIdSupervisor());
            this.iSuperVisorImpl.save(superVisor);
            logger.info("Se acaba de agregar nuevo supervisor");
            response.put("mensaje", "Una nuevo supervisor se ingreso");
            response.put("Supervisor", superVisor);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }catch (DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }

    @DeleteMapping("/{idVendedor}")
    public ResponseEntity<?> delete(@PathVariable String idSupervisor){
        Map<String, Object> response = new HashMap<>();
        try{
            SuperVisor superVisor = this.iSuperVisorImpl.findById(idSupervisor);
            if(superVisor == null){
                response.put("mensaje","el id ".concat(idSupervisor).concat("no existe"));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
            }else {
                this.iSuperVisorImpl.delete(superVisor);
                response.put("mensaje","el id con el id".concat(idSupervisor).concat("fue eliminado "));
                response.put("listado", superVisor);
                logger.info("El id fue eliminada con exito");
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
            }

        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }catch (DataAccessException e){
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
