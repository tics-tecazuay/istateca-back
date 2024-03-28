package com.istateca.app.istateca.daos;


import com.istateca.app.istateca.models.Authority;
import com.istateca.app.istateca.models.Persona;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorityRepository extends BaseRepository<Authority, Integer> {

    // This method will be util if you want a user with more than a single ROLE_
    Set<Authority> findByPersona(Persona persona);

    List<Authority> findByName(String name);

    Authority findByPersonaCedula(String cedula);

}
