package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.Sugerencia;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.SugerenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sugerencia")
public class SugerenciaController extends BaseController<Sugerencia> {

        /*
    controladores basicos estan en controlador general
    crear: /persona/crear -> pasar requestbody
    listar: /persona/listar
    buscar: /persona/buscar/{id} -> pasar PathVariable("id")
    editar: /persona/editar/{id} -> pasar requestbody
    */

    @Autowired
    private SugerenciaService service;

    @Override
    protected BaseService<Sugerencia, Integer> getService() {
        return service;
    }
}
