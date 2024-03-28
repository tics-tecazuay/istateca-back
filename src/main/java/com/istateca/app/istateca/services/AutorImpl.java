package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.Autor;
import com.istateca.app.istateca.daos.AutorRepository;
import com.istateca.app.istateca.daos.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorImpl extends BaseServiceImpl<Autor, Integer> implements AutorService{

    @Autowired
    private AutorRepository repository;

    public AutorImpl(BaseRepository<Autor, Integer> baseRepository) {
        super(baseRepository);
    }


    @Override
    public List<Autor> autoresxNombre(String nombre) {
        return repository.findAllByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Autor autorxNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
}
