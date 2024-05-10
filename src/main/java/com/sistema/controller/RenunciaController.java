package com.sistema.controller;

import com.sistema.dao.Services.IRenunciaServImpl;
import com.sistema.modals.modal.Region;
import com.sistema.modals.modal.Renuncia;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping(value = "/renuncia")
public class RenunciaController {


        @Autowired
        private IRenunciaServImpl iRenunciaImp;

        private Logger logger = LoggerFactory.getLogger(Region.class);

        @GetMapping
        public ResponseEntity<?> renuncia() {
            Map<String, Object> response = new HashMap<>();
            this.logger.debug("inica consulta en renuncia");
            try {
                List<Renuncia> renuncias = this.iRenunciaImp.findAll();
                if (renuncias == null && renuncias.isEmpty()) {
                    logger.warn("No existe registro de entidad");
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    logger.info("se ejecuta la consulta para renuncia");
                    return new ResponseEntity<List<Renuncia>>(renuncias, HttpStatus.OK);
                }
            } catch (CannotCreateTransactionException e) {
                response = this.getTransactionExepcion(response, e);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
            } catch (DataAccessException e) {
                response = this.getDataAccessException(response, e);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

            }

        }


        @PostMapping
        public ResponseEntity<?> create(@Valid @RequestBody Renuncia value, BindingResult result) {
            Map<String, Object> response = new HashMap<>();
            if (result.hasErrors() == true) {
                List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage())
                        .collect(Collectors.toList());
                response.put("errores", errores);
                logger.info("se encontraron errores al momento de validarr");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            try {
                Date fechaRecibida = value.getFecha();
                if (fechaRecibida == null) {
                    response.put("mensaje", "Fecha es un campo obligatorio");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
                Renuncia renuncia = new Renuncia();
                renuncia.setId_renuncia(value.getId_renuncia());
                renuncia.setId_vendedor(value.getId_vendedor());
                renuncia.setFecha(fechaRecibida);
                this.iRenunciaImp.save(renuncia);
                logger.info("Se acaba de agregar nueva renuncia");
                response.put("mensaje", "Una nueva region se ingreso");
                response.put("Region", renuncia);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
            } catch (CannotCreateTransactionException e) {
                response = this.getTransactionExepcion(response, e);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

            } catch (DataAccessException e) {
                response = this.getDataAccessException(response, e);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

            }

        }

        @DeleteMapping("/{idRegion}")
        public ResponseEntity<?> delete(@PathVariable String idRenuncia) {
            Map<String, Object> response = new HashMap<>();
            try {
                Renuncia renuncia = this.iRenunciaImp.findById(idRenuncia);
                if (renuncia == null) {
                    response.put("mensaje", "el id ".concat(idRenuncia).concat("no existe"));
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                } else {
                    this.iRenunciaImp.delete(renuncia);
                    response.put("mensaje", "el id con el id".concat(idRenuncia).concat("fue eliminado "));
                    response.put("listado", renuncia);
                    logger.info("El id fue eliminada con exito");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                }

            } catch (CannotCreateTransactionException e) {
                response = this.getTransactionExepcion(response, e);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

            } catch (DataAccessException e) {
                response = this.getDataAccessException(response, e);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

            }
        }


        private Map<String, Object> getTransactionExepcion(Map<String, Object> response, CannotCreateTransactionException e) {
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensajee", "error al moneotno de contectarse a la");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return response;
        }

        private Map<String, Object> getDataAccessException(Map<String, Object> response, DataAccessException e) {
            logger.error("El error al momento de ejecutlar la consulta ea  la base d adatos");
            response.put("mensaje", "Error al momenot de ejecutar ola consulta a la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return response;

        }
    }

