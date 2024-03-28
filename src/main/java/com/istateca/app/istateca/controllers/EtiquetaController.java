package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.Etiqueta;
import com.istateca.app.istateca.services.EtiquetaService;
import com.istateca.app.istateca.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/etiqueta")
public class EtiquetaController extends BaseController<Etiqueta> {

    /*
    controladores basicos estan en controlador general
    crear: /etiqueta/crear -> pasar requestbody
    listar: /etiqueta/listar
    buscar: /etiqueta/buscar/{id} -> pasar PathVariable("id")
    editar: /etiqueta/editar/{id} -> pasar requestbody
    */

    @Autowired
    private EtiquetaService service;

    @Override
    protected BaseService<Etiqueta, Integer> getService() {
        return service;
    }

    @GetMapping("/etiquetaxLibro")
    public ResponseEntity<List<Etiqueta>> etiquetaxLibro (@RequestParam(value = "idLibro") Integer idLibro) {
        List<Etiqueta> etiquetas = service.etiquetasxLibros(idLibro);
        if (etiquetas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(etiquetas);
        }
    }

    @GetMapping("/etiquetaxNombre")
    public ResponseEntity<List<Etiqueta>> etiquetaxNombre (@RequestParam(value = "nombre") String nombre) {
        List<Etiqueta> etiquetas = service.etiquetasxNombre(nombre);
        if (etiquetas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(etiquetas);
        }
    }

    //comentado por culpa de renato >:V
}
