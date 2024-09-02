package com.sistema.controller;


import com.sistema.dao.Services.RegionService;
import com.sistema.dao.Services.SuperVisorServSer;
import com.sistema.dao.implement.IRegionServImpl;
import com.sistema.dao.implement.IVendedorServImpl;
import com.sistema.dao.repository.IVendedorDAO;
import com.sistema.modals.entities.VendedorDto;
import com.sistema.modals.modal.Region;
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
@RequestMapping(value = "/Vendedor")
@CrossOrigin(origins = "*")
public class VendedorController {

    @Autowired
    private IVendedorServImpl iVendedorServImp;

    @Autowired
    private SuperVisorServSer superVisorServSer;

    @Autowired
    private RegionService regionService;

    @Autowired
    private IVendedorDAO iVendedorDAO;

    private Logger logger = LoggerFactory.getLogger(Vendedor.class);


    @GetMapping("/viewWorkers")
    public ResponseEntity<?> Vendedor(){
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("inica consulta para vendedor");
        try{
            List<Vendedor> vendedors =  this.iVendedorServImp.findAll();
            if(vendedors == null || vendedors.size() == 0){
                logger.warn("No existe registro de entidad");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                logger.info("se ejecuta la consulta para vendedor ");
                return new ResponseEntity<List<Vendedor>>(vendedors, HttpStatus.OK);
            }
        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }catch(DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }

    @GetMapping("/vendedor/activos")
    public ResponseEntity<?> VendedorActivos(){
        Map<String, Object> response = new HashMap<>();
        try {
            List<Vendedor> vendedors = this.iVendedorDAO.findVendedoActivos();
            logger.info("se ejecuta la consulta para vendedor activos ");
            return new ResponseEntity<List<Vendedor>>(vendedors, HttpStatus.OK);

        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }catch(DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }


    @PostMapping("/newWorker")
    public ResponseEntity<?> create(@Valid @RequestBody VendedorDto value, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors() == true){
            List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            logger.info("se encontraron errores al momento de validarr");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            Vendedor vendedor = new Vendedor();
            vendedor.setId_vendedor(value.getId_vendedor());
            vendedor.setNombre_vendedor(value.getNombre_vendedor());
            vendedor.setApellido_vendedor(value.getApellido_vendedor());
            vendedor.setEmail_vendedor(value.getEmail_vendedor());


            //Asocio el id del supervisor y el id de la region
            SuperVisor superVisor = superVisorServSer.findById(value.getId_supervisor());
            vendedor.setSuperVisor(superVisor);
            Region region = regionService.findById(String.valueOf(value.getId_region()));
            vendedor.setRegion(region);

            this.iVendedorServImp.save(vendedor);
            logger.info("Se acaba de agregar nuevo vendedor");
            response.put("mensaje", "Una nuevo vendedor se ingreso");
            response.put("Region", vendedor);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }catch (DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }

    @DeleteMapping("/delete/{idVendedor}")
    public ResponseEntity<?> delete(@PathVariable String idVendedor){
        Map<String, Object> response = new HashMap<>();
        try{
           Vendedor vendedor = this.iVendedorServImp.findById(idVendedor);
            if(vendedor == null){
                response.put("mensaje","el id ".concat(idVendedor).concat("no existe"));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
            }else {
                this.iVendedorServImp.delete(vendedor);
                response.put("mensaje","el id con el id".concat(idVendedor).concat( "  fue eliminado "));
                response.put("listado", vendedor);
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

    @PutMapping("/upDate/{idVendedor}")
    public ResponseEntity<?>update(@Valid @RequestBody VendedorDto value, BindingResult result, @PathVariable String idVendedor){
        Map<String, Object> response = new HashMap<>();
        try{


            Vendedor vendedor = this.iVendedorServImp.findById(String.valueOf(idVendedor));
            vendedor.setNombre_vendedor(value.getNombre_vendedor());
            vendedor.setApellido_vendedor(value.getApellido_vendedor());
            vendedor.setEmail_vendedor(value.getEmail_vendedor());

            this.iVendedorServImp.save(vendedor);


            response.put("Mensaje", "los datos de vendedor se actualizaron");
            response.put("El vendedor cambio de datos: ", vendedor);
            logger.info("Se actualizo el dato del vendedor");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

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
