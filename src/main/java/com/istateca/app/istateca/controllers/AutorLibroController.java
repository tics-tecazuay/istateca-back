package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.AutorLibro;
import com.istateca.app.istateca.services.AutorLibroService;
import com.istateca.app.istateca.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/autorlibro")
public class AutorLibroController extends BaseController<AutorLibro> {

    /*
    controladores basicos estan en controlador general
    crear: /autorlibro/crear -> pasar requestbody
    listar: /autorlibro/listar
    buscar: /autorlibro/buscar/{id} -> pasar PathVariable("id")
    editar: /autorlibro/editar/{id} -> pasar requestbody
    */

    @Autowired
    private AutorLibroService service;

    @Override
    protected BaseService<AutorLibro, Integer> getService() {
        return service;
    }


}
