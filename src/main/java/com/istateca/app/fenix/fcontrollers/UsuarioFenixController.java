package com.istateca.app.fenix.fcontrollers;


import com.istateca.app.fenix.fmodels.UsuarioFenix;
import com.istateca.app.fenix.fservices.UsuarioFenixService;
import com.istateca.app.istateca.models.Persona;
import com.istateca.app.istateca.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/usuariofenix")
public class UsuarioFenixController {

    @Autowired
    private UsuarioFenixService service;

    @Autowired
    private PersonaService personaService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/buscarusuario/{cedula}")
    public ResponseEntity<?> buscarCedula(@PathVariable("cedula") String cedula) {
        Optional<UsuarioFenix> current = Optional.ofNullable(service.findByCedula(cedula));
        if (current.isPresent()) {
            return ResponseEntity.ok().body(current.get());
        } else {
            String errorMessage = "La cedula proporcionada no se encuentra en el sistema Fenix o no posee " +
                    "una matricula activa: " + cedula;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping("/buscarpersonacedula/{cedula}")
    public ResponseEntity<?> buscarCedulaEstidiantes(@PathVariable("cedula") String cedula) {
        Optional<UsuarioFenix> current = Optional.ofNullable(service.findByCedulaEstudiante(cedula));
        Optional<Persona> existe = Optional.ofNullable(personaService.personaxCedula(cedula));
        if(current.isPresent()) {
            if (existe.isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("Mensaje", "Ya existe esa cedula"));
            }
            else return ResponseEntity.ok().body(current.get());
        }
        return ResponseEntity.badRequest()
                .body(Collections
                        .singletonMap("Mensaje","No se encontro registro para la cédula " + cedula));
    }

    @GetMapping("/listardocentes")
    public ResponseEntity<?> listarDocentes() {
        return ResponseEntity.ok().body(service.findByDocentes());
    }

    @GetMapping("/actualizarUsuarios")
    public ResponseEntity<?> desactivarUsuarios() {
        List<Persona> per = new ArrayList<>();//listado para datos en futuro
        personaService.findAll().forEach(persona -> {
            if(persona.getTipo()<3 && persona.getActivo()){
                persona.setActivo(service.existeCedula(persona.getCedula()));
                personaService.save(persona);
                per.add(persona);
            }
        });
        return ResponseEntity.ok().body("Se desactivaron "+per.size()+" usuarios");
    }
}
