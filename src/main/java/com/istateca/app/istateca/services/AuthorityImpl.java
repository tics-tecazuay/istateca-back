package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.AuthorityRepository;
import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.models.Authority;
import com.istateca.app.istateca.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityImpl extends BaseServiceImpl<Authority, Integer> implements AuthorityService {
    public AuthorityImpl(BaseRepository<Authority, Integer> baseRepository) {
        super(baseRepository);
    }

    @Autowired
    private AuthorityRepository repository;

    @Override
    public Authority rol(String cedula) {
        return repository.findByPersonaCedula(cedula);
    }
}
