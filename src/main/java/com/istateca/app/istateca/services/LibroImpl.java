package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.LibroRepository;
import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.models.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroImpl extends BaseServiceImpl<Libro, Integer> implements LibroService {

    @Autowired
    private LibroRepository repository;

    public LibroImpl(BaseRepository<Libro, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Libro> librosxTitulo(String titulo) {
        return repository.findAllByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<Libro> librosxTipo(Integer tipo) {
        return repository.findAllByTipoId(tipo);
    }

    @Override
    public List<Libro> librosxcoincidencias(String parametro) {
        return repository.findAllByTituloContainingIgnoreCaseOrSubtituloContainingIgnoreCaseOrDescripcionContainingIgnoreCaseOrAreaContainingIgnoreCaseOrEtiquetasLibroEtiquetaNombreContainingIgnoreCase(parametro,parametro,parametro,parametro,parametro);
    }

    @Override
    public boolean Disponible(Integer idLibro) {
        return repository.existsByDisponibilidadIsTrueAndId(idLibro);
    }
}
