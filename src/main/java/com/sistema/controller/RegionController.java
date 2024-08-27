package com.sistema.controller;

import com.sistema.dao.implement.IRegionServImpl;
import com.sistema.modals.entities.RegionDTO;
import com.sistema.modals.modal.Region;
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
@RequestMapping(value = "/region")
public class RegionController {

    @Autowired
    private IRegionServImpl iRegionServImp;

    private Logger logger = LoggerFactory.getLogger(Region.class);

    @GetMapping
    public ResponseEntity<?> region(){
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("inica consulta de region");
        try{
            List<Region> region =  this.iRegionServImp.findAll();
            if(region == null || region.size() == 0){
                logger.warn("No existe registro de entidad");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                logger.info("se ejecuta la consulta de region");
                return new ResponseEntity<List<Region>>(region, HttpStatus.OK);
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
    public ResponseEntity<?> create(@Valid @RequestBody Region value, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors() == true){
            List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            logger.info("se encontraron errores al momento de validarr");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            Region region = new Region();
            region.setId_region(value.getId_region());
            region.setNombre_region(value.getNombre_region());
            this.iRegionServImp.save(region);
            logger.info("Se acaba de agregar nueva region");
            response.put("mensaje", "Una nueva region se ingreso");
            response.put("Region", region);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }catch (DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }




    @PutMapping("{id_region}")
    public ResponseEntity<?>update(@Valid @RequestBody RegionDTO value, BindingResult result, @PathVariable String id_region){
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("eorrres", errores);
            logger.info("Se encotraron errores en la peticion de region ");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            Region region = this.iRegionServImp.findById(id_region);
            if(region == null){
                response.put("mensaje", "la region con el id".concat(id_region).concat("no existe"));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }else {
                region.setNombre_region(value.getNombre_region());

                this.iRegionServImp.save(region);
                response.put("mensaje","la region fue actualizado");
                response.put("listado", region);
                logger.info("La nota fue actualizada con exito ");
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







    @DeleteMapping("/{idRegion}")
    public ResponseEntity<?> delete(@PathVariable String idRegion){
        Map<String, Object> response = new HashMap<>();
        try{
            Region region = this.iRegionServImp.findById(idRegion);
            if(region == null){
                response.put("mensaje","el id ".concat(idRegion).concat("no existe"));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
            }else {
                this.iRegionServImp.delete(region);
                response.put("mensaje","el id con el id".concat(idRegion).concat("fue eliminado "));
                response.put("listado", region);
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
