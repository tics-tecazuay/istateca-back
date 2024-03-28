package com.istateca.app.fenix.fservices;

import com.istateca.app.fenix.fdaos.MateriaCarreraRepository;
import com.istateca.app.fenix.fmodels.MateriaCarrera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MateriaCarreraImp implements CarreraMateriaService {

    @Autowired
    private MateriaCarreraRepository materiaCarreraRepository;


    @Override
    @Transactional(readOnly = true)
    public List<MateriaCarrera> findAllMaterias() {
        return (List<MateriaCarrera>) materiaCarreraRepository.findAllByTipo(2).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MateriaCarrera> findAllCarreras() {
        return (List<MateriaCarrera>) materiaCarreraRepository.findAllByTipo(1).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MateriaCarrera> findAllMateriasxNombre(String nombre,Integer idCarrera) {
        return (List<MateriaCarrera>) materiaCarreraRepository.findAllByNombreContainingAndCarreraIdAndTipo(nombre,idCarrera,2).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MateriaCarrera> findAll() {
        return (List<MateriaCarrera>) materiaCarreraRepository.findAll();
    }

}
