package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.EtiquetaLibro;
import com.istateca.app.istateca.models.Libro;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.EtiquetaLibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class EtiquetaLibroController extends BaseController<EtiquetaLibro> {

    /*
    controladores basicos estan en controlador general
    crear: /autor/crear -> pasar requestbody
    listar: /autor/listar
    buscar: /autor/buscar/{id} -> pasar PathVariable("id")
    editar: /autor/editar/{id} -> pasar requestbody
    */

    @Autowired
    private EtiquetaLibroService service;

    @Override
    protected BaseService<EtiquetaLibro, Integer> getService() {
        return service;
    }

    @DeleteMapping("/eliminaretiqueta")
    public ResponseEntity<String> librosxCoincidencia(@RequestParam(value = "parametro") Integer parametro) {
        if (service.borrarEtiquetaLibro(parametro))return ResponseEntity.ok("Borrado "+parametro);
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/etiquetasxlibro")
    public ResponseEntity<List<EtiquetaLibro>> etiquetasxlibro(@RequestParam(value = "parametro") Integer parametro) {
        List<EtiquetaLibro> etiquetaLibros = service.etiquetasxLibro(parametro);
        if (etiquetaLibros.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(etiquetaLibros);
        }
    }

}
