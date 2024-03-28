package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.Notificacion;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notificacion")
public class NotificacionController extends BaseController<Notificacion>{

    /*
    controladores basicos estan en controlador general
    crear: /persona/crear -> pasar requestbody
    listar: /persona/listar
    buscar: /persona/buscar/{id} -> pasar PathVariable("id")
    editar: /persona/editar/{id} -> pasar requestbody
    */

    @Autowired
    private NotificacionService service;

    @Override
    protected BaseService<Notificacion, Integer> getService() {
        return service;
    }

    @GetMapping("/notificacionesxpersona")
    public ResponseEntity<List<Notificacion>> notificacionesxpersona(@RequestParam(value = "idsolicitante") Integer idsolicitante) {
        List<Notificacion> notificaciones = service.notificacionesxusuario(idsolicitante);
        if (notificaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notificaciones);
        }
    }

    @GetMapping("/notificacionesbibliotecarios")
    public ResponseEntity<List<Notificacion>> notificacionesbibliotecarios() {
        List<Notificacion> notificaciones = service.notificacionesxdocente();
        if (notificaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notificaciones);
        }
    }

}
