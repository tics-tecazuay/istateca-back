package com.istateca.app.istateca.controllers;

import com.istateca.app.fenix.fmodels.UsuarioFenix;
import com.istateca.app.fenix.fservices.UsuarioFenixService;
import com.istateca.app.istateca.models.Carrera;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrera")
public class CarreraController extends BaseController<Carrera> {

    /*
    controladores basicos estan en controlador general
    crear: /carrera/crear -> pasar requestbody
    listar: /carrera/listar
    buscar: /carrera/buscar/{id} -> pasar PathVariable("id")
    editar: /carrera/editar/{id} -> pasar requestbody
    */

    @Autowired
    private CarreraService service;

    @Autowired
    private UsuarioFenixService usuarioFenixService;

    @Override
    protected BaseService<Carrera, Integer> getService() {
        return service;
    }

    @GetMapping("/carreraest/{cedula}")
    public ResponseEntity<Carrera> carreraest(@PathVariable String cedula) {
        UsuarioFenix usuarioFenix = usuarioFenixService.findByCedula(cedula);
        Carrera carrera = service.carreraest(usuarioFenix.getCarreraid());
        if (carrera == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(carrera);
        }
    }
}
