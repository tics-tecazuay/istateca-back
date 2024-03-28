package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.TerceroRepository;
import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.models.Tercero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerceroImpl extends BaseServiceImpl<Tercero, Integer> implements TerceroService {

    @Autowired
    private TerceroRepository repository;

    public TerceroImpl(BaseRepository<Tercero, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Tercero buscarxCedula(String cedula) {
        return repository.findByCedula(cedula);
    }
}
