package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.EtiquetaRepository;
import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.models.Etiqueta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtiquetaImpl extends BaseServiceImpl<Etiqueta, Integer> implements EtiquetaService {

    @Autowired
    private EtiquetaRepository repository;

    public EtiquetaImpl(BaseRepository<Etiqueta, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Etiqueta> etiquetasxLibros(Integer id) {
        return repository.findAllByLibrosetiquetadosLibroId(id);
    }

    @Override
    public List<Etiqueta> etiquetasxNombre(String nombre) {
        return repository.findAllByNombreContainingIgnoreCase(nombre);
    }
}
