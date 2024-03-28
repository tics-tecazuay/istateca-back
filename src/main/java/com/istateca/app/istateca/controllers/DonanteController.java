package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.Donante;
import com.istateca.app.istateca.services.DonanteService;
import com.istateca.app.istateca.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donante")
public class DonanteController extends BaseController<Donante> {

    /*
    controladores basicos estan en controlador general
    crear: /donante/crear -> pasar requestbody
    listar: /donante/listar
    buscar: /donante/buscar/{id} -> pasar PathVariable("id")
    editar: /donante/editar/{id} -> pasar requestbody
    */

    @Autowired
    private DonanteService service;

    @Override
    protected BaseService<Donante, Integer> getService() {
        return service;
    }


    @GetMapping("/listarxnombre/{nombre}")
    public ResponseEntity<List<Donante>> donante_libro(@PathVariable String nombre) {
        List<Donante> donantes = service.adonantesxNombre(nombre);
        if (donantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(donantes);
        }
    }

    @GetMapping("/listarxnombreindividual/{nombre}")
    public ResponseEntity<Donante> donante_libro_2(@PathVariable String nombre) {
        Donante donante = service.adonantexNombre(nombre);
        if (donante==null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(donante);
        }
    }

}
