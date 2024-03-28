package com.istateca.app.fenix.fcontrollers;


import com.istateca.app.fenix.fmodels.MateriaCarrera;
import com.istateca.app.fenix.fservices.CarreraMateriaService;
import com.istateca.app.istateca.models.Carrera;
import com.istateca.app.istateca.services.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrerafenix")
public class MateriaCarrerasFenixController {

    @Autowired
    private CarreraMateriaService service;


    @Autowired
    private CarreraService carreraService;

    @GetMapping("/listarcarreras")
    public ResponseEntity<?> listarCarreras() {
        Optional<List<MateriaCarrera>> current = Optional.ofNullable(service.findAllCarreras());
        if(current.isPresent()) {
            return ResponseEntity.ok().body(current.get());
        }
        return ResponseEntity.notFound().build();
    }
    /*
    @GetMapping("/listarmaterias")
    public ResponseEntity<?> listarMaterias() {
        Optional<List<MateriaCarrera>> current = Optional.ofNullable(service.findAllMaterias());
        if(current.isPresent()) {
            return ResponseEntity.ok().body(current.get());
        }
        return ResponseEntity.notFound().build();
    }*/
    /*
    @GetMapping("/listarmateriasxnombre/{nombre}/{idcarrera}")
    public ResponseEntity<?> listarMateriasxNombre(@PathVariable("nombre") String nombre,
                                                   @PathVariable("idcarrera") Integer idCarrera) {
        Optional<List<MateriaCarrera>> current = Optional.ofNullable(service.findAllMateriasxNombre(nombre,idCarrera));
        if(current.isPresent()) {
            return ResponseEntity.ok().body(current.get());
        }
        return ResponseEntity.notFound().build();
    }*/

    @GetMapping("/llenarcarreras")
    public String llenarDatos(){

        List<MateriaCarrera> listacarrera=service.findAllCarreras();
        List<MateriaCarrera> listamateria=service.findAllMaterias();

        if(listacarrera.size()==0 || listamateria.size()==0){
            return "Algo Malio Sal";
        }else{
            for (MateriaCarrera fenix: listacarrera) {
                boolean ban=carreraService.findByIdFenix(fenix.getCarreraMateriaId());
                if(ban){
                    Carrera nueva = new Carrera();
                    nueva.setIdFenix(fenix.getCarreraMateriaId());
                    nueva.setNombre(fenix.getNombre());
                    nueva.setActivo(true);
                    carreraService.save(nueva);
                }
            }/*
            List<Carrera> carreras= carreraService.findAll();
            for (MateriaCarrera fenix: listamateria) {
                boolean ban=materiaService.findByIdMateria(fenix.getCarreraMateriaId());
                if(ban && fenix.getCiclo()>3){
                    Carrera existe = new Carrera();
                    for (Carrera car: carreras) {
                        if(car.getIdCarrera()==fenix.getCarreraId())existe=car;
                    }
                    Materia nueva=new Materia();
                    nueva.setIdMateria(fenix.getCarreraMateriaId());
                    nueva.setNombre(fenix.getNombre());
                    nueva.setCarrera(existe);
                    materiaService.save(nueva);
                }
            }*/
            return "Todo bien todo correcto y yo que me alegro";
        }
    }

}
