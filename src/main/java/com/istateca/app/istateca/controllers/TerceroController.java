package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.Persona;
import com.istateca.app.istateca.models.Tercero;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.TerceroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tercero")
public class TerceroController extends BaseController<Tercero> {

    /*
    controladores basicos estan en controlador general
    crear: /tercero/crear -> pasar requestbody
    listar: /tercero/listar
    buscar: /tercero/buscar/{id} -> pasar PathVariable("id")
    editar: /tercero/editar/{id} -> pasar requestbody
    */

    @Autowired
    private TerceroService service;


    @Override
    protected BaseService<Tercero, Integer> getService() {
        return service;
    }

    @GetMapping("/terceroxcedula/{cedula}")
    public ResponseEntity<Tercero> terceroxcedula(@PathVariable String cedula) {
        Tercero tercero = service.buscarxCedula(cedula);
        if (tercero == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tercero);
        }
    }
}
