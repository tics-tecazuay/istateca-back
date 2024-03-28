package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.Libro;

import java.util.List;

public interface LibroService extends BaseService<Libro, Integer> {

    List<Libro> librosxTitulo(String titulo);
    List<Libro> librosxTipo(Integer tipo);
    List<Libro> librosxcoincidencias(String parametro);

    boolean Disponible(Integer idLibro);

}
