package com.istateca.app.istateca.controllers;


import com.istateca.app.istateca.models.Actualizable;
import com.istateca.app.istateca.services.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class BaseController<T extends Actualizable<T>> {
    protected abstract BaseService<T,Integer> getService();

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody T entity) {
        try {
            T savedEntity = getService().save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
        } catch (Exception e) {
            String errorMessage = "Error al crear la entidad: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarID(@PathVariable("id") Integer id) {
        try {
            Optional<T> current = getService().findById(id);
            if (current.isPresent()) {
                return ResponseEntity.ok().body(current.get());
            } else {
                String errorMessage = "No se encontró ninguna entidad con el ID: " + id;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
        } catch (Exception e) {
            String errorMessage = "Error al buscar la entidad: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar() {
        try {
            List<?> entities = getService().findAll();
            return ResponseEntity.ok().body(entities);
        } catch (Exception e) {
            List<String> errorMessage = new ArrayList<>();
            errorMessage.add("Error al obtener la lista de entidades: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Integer id, @RequestBody T entity) {
        Optional<T> optional = getService().findById(id);
        if (optional.isPresent()) {
            T current = optional.get();
            current.actualizarDatos(entity);
            T savedEntity = getService().save(current);
            return ResponseEntity.ok().body(savedEntity);
        } else {
            String errorMessage = "No existe entidad con el ID: " + id;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

}
