package com.istateca.app.istateca.controllers;

import com.istateca.app.constant.Validate;
import com.istateca.app.fenix.fmodels.UsuarioFenix;
import com.istateca.app.fenix.fservices.UsuarioFenixService;
import com.istateca.app.istateca.daos.AuthorityRepository;
import com.istateca.app.istateca.models.Authority;
import com.istateca.app.istateca.models.Persona;
import com.istateca.app.istateca.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
public class LogInUp {

    @Autowired
    PersonaService personaService;

    @Autowired
    UsuarioFenixService usuarioFenixService;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityRepository authorityRepository;


    @PostMapping("/credentials")
    public ResponseEntity<?> verificar(@RequestParam String email,@RequestParam String nombres) {
        if(!Validate.verifyEmail(email)) {
            return ResponseEntity.badRequest().body("No estas ligado al ISTA");
        }
        if (!personaService.existsByCorreo(email)) {
            if(logUp(email,nombres)) return ResponseEntity.ok().body("Registro exitoso");
            return ResponseEntity.badRequest().body("Usted ya esta salio del ISTA");
        }
        return ResponseEntity.ok().body("Pesona registrada");
    }

    @RequestMapping("/ingresar")
    public Persona getUserDetailsAfterLogin(Authentication authentication) {
        return personaService.findByCorreo(authentication.getName());
    }

    public boolean logUp(String email,String nombres) {
        UsuarioFenix usuario = usuarioFenixService.findByCorreo(email);
        if (usuario == null)
            usuario = usuarioFenixService.findByNombresAndApellidosQuery(nombres);
        if (usuario != null) {
            Persona fenix = new Persona();
            System.out.println(nombres+email);
            String hashPwd = passwordEncoder.encode(nombres+email);
            String ps = nombres+email;
            fenix.setCorreo(email);
            fenix.setPassword(hashPwd);
            fenix.setApellidos(usuario.getApellidos());
            fenix.setNombres(usuario.getNombres());
            fenix.setDireccion(usuario.getDireccion());
            fenix.setCedula(usuario.getCedula());
            fenix.setFenixId(usuario.getAlumno_docenteId());
            fenix.setTipo(usuario.getTipo());
            fenix.setCalificacion(5);
            fenix.setActivo(true);
            Persona persona = personaService.save(fenix);
            Authority role = new Authority();
            switch(usuario.getTipo()){
                case 1:
                    role.setName("ROLE_STUD");
                    break;
                case 2:
                    role.setName("ROLE_DOCEN");
                    break;
                case 3:
                    role.setName("ROLE_BLIB");
                    break;
                case 4:
                    role.setName("ROLE_ADMIN");
                    break;
            }
            role.setPersona(persona);
            authorityRepository.save(role);
            return true;
        }
        return false;
    }

}
