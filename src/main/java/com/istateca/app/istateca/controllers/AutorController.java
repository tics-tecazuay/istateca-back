package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.Autor;
import com.istateca.app.istateca.services.AutorService;
import com.istateca.app.istateca.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/autor")
public class AutorController extends BaseController<Autor> {

    /*
    controladores basicos estan en controlador general
    crear: /autor/crear -> pasar requestbody
    listar: /autor/listar
    buscar: /autor/buscar/{id} -> pasar PathVariable("id")
    editar: /autor/editar/{id} -> pasar requestbody
    */

    @Autowired
    private AutorService service;

    @Override
    protected BaseService<Autor, Integer> getService() {
        return service;
    }

    @GetMapping("/listarautoresxnombre/{nombre}")
    public ResponseEntity<List<Autor>> autor_libro(@PathVariable String nombre) {
        List<Autor> autores = service.autoresxNombre(nombre);
        if (autores.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(autores);
        }
    }

    @GetMapping("/listarautorxnombre/{nombre}")
    public ResponseEntity<Autor> autor_libro_2(@PathVariable String nombre) {
        Autor autor = service.autorxNombre(nombre);
        if (autor==null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(autor);
        }
    }

}
