package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.BibliotecarioCargo;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.BibliotecarioCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bibliotecariocargo")
public class BibliotecarioCargoController extends BaseController <BibliotecarioCargo>{

    /*
    controladores basicos estan en controlador general
    crear: /bibliotecariocargo/crear -> pasar requestbody
    listar: /bibliotecariocargo/listar
    buscar: /bibliotecariocargo/buscar/{id} -> pasar PathVariable("id")
    editar: /bibliotecariocargo/editar/{id} -> pasar requestbody
    */

    @Autowired
    private BibliotecarioCargoService service;

    @Override
    protected BaseService<BibliotecarioCargo, Integer> getService() {
        return service;
    }
}
