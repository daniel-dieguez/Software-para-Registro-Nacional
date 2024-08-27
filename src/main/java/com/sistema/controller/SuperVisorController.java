package com.sistema.controller;

import com.sistema.dao.Services.SuperVisorServSer;
import com.sistema.dao.implement.ISuperVisorSerImpl;
import com.sistema.modals.entities.SupervisoDTO;
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
@RequestMapping(value = "/supervisor")
public class SuperVisorController {


    @Autowired
    private ISuperVisorSerImpl iSuperVisorSerImpl;

    @Autowired
    private SuperVisorServSer superVisorServSer;

    private Logger logger = LoggerFactory.getLogger(SuperVisor.class);


    @GetMapping
    public ResponseEntity<?> supervisor(){
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("inica consulta para supervisor");
        try{
            List<SuperVisor> superVisor =  this.iSuperVisorSerImpl.findAll();
            if(superVisor == null || superVisor.size() == 0){
                logger.warn("No existe registro de entidad");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                logger.info("se ejecuta la consulta para supervisor");
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
            superVisor.setId_supervisor(value.getId_supervisor());
            superVisor.setNombre_super(value.getNombre_super());
            superVisor.setApellido_super(value.getApellido_super());
            superVisor.setEmail_super(value.getEmail_super());
            superVisor.setRegion(value.getRegion());
            this.iSuperVisorSerImpl.save(superVisor);
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

    @PutMapping("{id_supervisor}")
    public ResponseEntity<?>update(@Valid @RequestBody SupervisoDTO value, BindingResult result, @PathVariable String id_supervisor){
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("eorrres", errores);
            logger.info("Se encotraron errores en la peticion de supervisor ");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            SuperVisor superVisor = this.iSuperVisorSerImpl.findById(Integer.parseInt(id_supervisor));
            if(superVisor == null){
                response.put("mensaje", "la region con el id".concat(id_supervisor).concat("no existe"));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }else {

                superVisor.setNombre_super(value.getNombre_super());
                superVisor.setApellido_super(value.getApellido_super());
                this.iSuperVisorSerImpl.save(superVisor);
                response.put("mensaje","El supervisor fue actualizado");
                response.put("listado", superVisor);
                logger.info("El supervisor fue actualizada con exito ");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }

        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }catch (DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    /*

    @DeleteMapping("/{idVendedor}")
    public ResponseEntity<?> delete(@PathVariable String idSupervisor){
        Map<String, Object> response = new HashMap<>();
        try{
            SuperVisor superVisor = this.iSuperVisorSerImpl.findById(idSupervisor);
            if(superVisor == null){
                response.put("mensaje","el id ".concat(idSupervisor).concat("no existe"));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
            }else {
                this.iSuperVisorSerImpl.delete(superVisor);
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

*/

    @GetMapping("/{idSuperVisor}/vendedores")
    public ResponseEntity<List<Vendedor>> VendedoresySupervisores(@PathVariable int idSuperVisor){
        Map<String, Object> response = new HashMap<>();
        List<Vendedor> vendedors  = superVisorServSer.vendedorYSupervisor(idSuperVisor);
        if (vendedors == null || vendedors.isEmpty()) {
            logger.info("No se encontraron vendedores para el supervisor con id " + idSuperVisor);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        logger.info("Se ejecuta la consulta para el supervisor con id " + idSuperVisor);
        return ResponseEntity.ok(vendedors);
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
