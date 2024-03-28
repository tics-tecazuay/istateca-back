package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.AutorLibro;
import com.istateca.app.istateca.daos.AutorLibroRepository;
import com.istateca.app.istateca.daos.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorLibroImpl extends BaseServiceImpl<AutorLibro, Integer> implements AutorLibroService {

    @Autowired
    private AutorLibroRepository repository;

    public AutorLibroImpl(BaseRepository<AutorLibro, Integer> baseRepository) {
        super(baseRepository);
    }
}
