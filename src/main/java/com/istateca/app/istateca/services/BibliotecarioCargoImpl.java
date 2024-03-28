package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.daos.BibliotecarioCargoRepository;
import com.istateca.app.istateca.models.BibliotecarioCargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibliotecarioCargoImpl extends BaseServiceImpl<BibliotecarioCargo, Integer> implements BibliotecarioCargoService {

    @Autowired
    private BibliotecarioCargoRepository repository;

    public BibliotecarioCargoImpl(BaseRepository<BibliotecarioCargo, Integer> baseRepository) {
        super(baseRepository);
    }
}
