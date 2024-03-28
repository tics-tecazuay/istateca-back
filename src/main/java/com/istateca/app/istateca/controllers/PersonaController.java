package com.istateca.app.istateca.controllers;

import com.istateca.app.fenix.fmodels.UsuarioFenix;
import com.istateca.app.fenix.fservices.UsuarioFenixService;
import com.istateca.app.istateca.daos.AuthorityRepository;
import com.istateca.app.istateca.models.Authority;
import com.istateca.app.istateca.models.Persona;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController extends BaseController<Persona> {

    /*
    controladores basicos estan en controlador general
    crear: /persona/crear -> pasar requestbody
    listar: /persona/listar
    buscar: /persona/buscar/{id} -> pasar PathVariable("id")
    editar: /persona/editar/{id} -> pasar requestbody
    */

    @Autowired
    private PersonaService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UsuarioFenixService usuarioFenixService;

    @Override
    protected BaseService<Persona, Integer> getService() {
        return service;
    }

    @GetMapping("/personaxcedula/{cedula}")
    public ResponseEntity<Persona> personaxcedula(@PathVariable String cedula) {
        Persona persona = service.personaxCedula(cedula);
        if (persona == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(persona);
        }
    }

    @GetMapping("/personadocente/{cedula}")
    public ResponseEntity<Persona> personadocente(@PathVariable String cedula) {
        Persona persona=null;
        try {
            persona = service.personaxCedula(cedula);
        }catch (Exception e){
        }
        if (persona == null) {
            UsuarioFenix usuario = usuarioFenixService.findByCedula(cedula);
            if (usuario==null||usuario.getTipo()==1)return ResponseEntity.notFound().build();
            else{
                Persona nueva= new Persona();
                nueva.setCedula(usuario.getCedula());
                nueva.setNombres(usuario.getNombres());
                nueva.setApellidos(usuario.getApellidos());
                nueva.setCorreo(usuario.getCorreo());
                nueva.setDireccion(usuario.getDireccion());
                nueva.setFenixId(usuario.getAlumno_docenteId());
                nueva.setCelular(usuario.getCelular());
                nueva.setCalificacion(5);
                nueva.setActivo(true);
                return ResponseEntity.ok(nueva);
            }
        } else {
            if (persona.getTipo()==1) return ResponseEntity.notFound().build();
            else return ResponseEntity.ok(persona);
        }
    }

    //registro si existe, crear cuando no existe
    @PostMapping("/registrardocenteadmin")
    public ResponseEntity<String> registrardocenteadmin(@RequestBody Persona persona,@RequestParam String rol) {
        Authority role=authorityRepository.findByPersonaCedula(persona.getCedula());
        if (role==null) role= new Authority();
        role.setName(rol);
        if (persona.getPassword()==null){
            persona.setPassword
                    (passwordEncoder.encode(persona.getNombres()+" "+persona.getApellidos()+persona.getCorreo()));
        }
        service.save(persona);
        role.setPersona(persona);
        authorityRepository.save(role);
        return ResponseEntity.ok("Registro exitoso para "+persona.getCedula()+" con rol "+role.getName());
    }

    /*public List<Persona> bibliotecariosyAdmins(){
        return service.bibliotecarioDevice();
    }*/
}
