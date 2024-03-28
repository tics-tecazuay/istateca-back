package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.Autor;

import java.util.List;

public interface AutorService extends BaseService<Autor, Integer> {

    List<Autor> autoresxNombre(String nombre);

    Autor autorxNombre(String nombre);

}
