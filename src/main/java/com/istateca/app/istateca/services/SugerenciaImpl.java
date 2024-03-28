package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.daos.SugerenciaRepository;
import com.istateca.app.istateca.models.Sugerencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SugerenciaImpl extends BaseServiceImpl<Sugerencia, Integer> implements SugerenciaService {

    @Autowired
    private SugerenciaRepository repository;

    public SugerenciaImpl(BaseRepository<Sugerencia, Integer> baseRepository) {
        super(baseRepository);
    }

}
