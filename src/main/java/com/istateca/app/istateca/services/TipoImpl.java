package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.TipoRepository;
import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.models.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoImpl extends BaseServiceImpl<Tipo, Integer> implements TipoService {

    @Autowired
    private TipoRepository repository;

    public TipoImpl(BaseRepository<Tipo, Integer> baseRepository) {
        super(baseRepository);
    }


    @Override
    public List<Tipo> tipoxnombre(String nombre) {
        return repository.findAllByNombreContainingIgnoreCase(nombre);
    }
}
