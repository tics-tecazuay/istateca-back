package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.TerceroPrestamo;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.TerceroPrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/terceroprestamo")
public class TerceroPrestamoController extends BaseController<TerceroPrestamo> {
        /*
    controladores basicos estan en controlador general
    crear: /terceroprestamo/crear -> pasar requestbody
    listar: /terceroprestamo/listar
    buscar: /terceroprestamo/buscar/{id} -> pasar PathVariable("id")
    editar: /terceroprestamo/editar/{id} -> pasar requestbody
    */

    @Autowired
    private TerceroPrestamoService service;

    @Override
    protected BaseService<TerceroPrestamo, Integer> getService() {
        return service;
    }

    @GetMapping("/terceroxcedula/{cedula}")
    public ResponseEntity<List<TerceroPrestamo>> terceroxcedula(@PathVariable String cedula) {
        List<TerceroPrestamo> terceroPrestamo = service.tercerosxCedula(cedula);
        if (terceroPrestamo == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(terceroPrestamo);
        }
    }

}
