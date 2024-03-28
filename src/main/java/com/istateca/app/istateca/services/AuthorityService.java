package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.Authority;
import com.istateca.app.istateca.models.Persona;

public interface AuthorityService extends BaseService<Authority, Integer>{

    public Authority rol(String cedula);

}
