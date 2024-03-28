package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.Tipo;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/tipo")
public class TipoController extends BaseController<Tipo> {

    /*
    controladores basicos estan en controlador general
    crear: /tipo/crear -> pasar requestbody
    listar: /tipo/listar
    buscar: /tipo/buscar/{id} -> pasar PathVariable("id")
    editar: /tipo/editar/{id} -> pasar requestbody
    */

    @Autowired
    private TipoService service;

    @Override
    protected BaseService<Tipo, Integer> getService() {
        return service;
    }
    //ejemplo para vistas u entidades mescladas
    /*@GetMapping("/vista")
    public ResponseEntity<?> obtenerVista() {
        List<Libro> libros = libroService.findAll();
        List<Tipo> tipos = service.findAll();

        if (libro != null && usuario != null) {
            Map<String, Object> vista = new HashMap<>();
            vista.put("idLibro", libro.getId());
            vista.put("tituloLibro", libro.getTitulo());
            vista.put("autorLibro", libro.getAutor());
            vista.put("idUsuario", usuario.getId());
            vista.put("nombreUsuario", usuario.getNombre());

            Gson gson = new Gson();
            String vistaJson = gson.toJson(vista);

            return ResponseEntity.ok().body(vistaJson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping("/buscarxnombre")
    public ResponseEntity<List<Tipo>> tiposxnombre(@RequestParam(value = "nombre") String nombre) {
        List<Tipo> tipos = service.tipoxnombre(nombre);
        if (tipos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tipos);
        }
    }

}
