package com.istateca.app.fenix.fservices;

import com.istateca.app.fenix.fmodels.MateriaCarrera;
import java.util.List;

public interface CarreraMateriaService {

    public List<MateriaCarrera> findAllMaterias();

    public List<MateriaCarrera> findAllCarreras();

    public List<MateriaCarrera> findAllMateriasxNombre(String nombre,Integer idCarrera);

    public List<MateriaCarrera> findAll();
}
