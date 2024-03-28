package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.daos.DonanteRepository;
import com.istateca.app.istateca.models.Donante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonanteImpl extends BaseServiceImpl<Donante, Integer> implements DonanteService{

    @Autowired
    private DonanteRepository repository;

    public DonanteImpl(BaseRepository<Donante, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Donante> adonantesxNombre(String nombre) {
        return repository.findAllByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Donante adonantexNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
}
