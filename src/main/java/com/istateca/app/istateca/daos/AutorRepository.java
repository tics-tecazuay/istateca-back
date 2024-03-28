package com.istateca.app.istateca.daos;

import com.istateca.app.istateca.models.Autor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends BaseRepository<Autor,Integer>{

    List<Autor> findAllByNombreContainingIgnoreCase(String nombre);

    Autor findByNombre(String nombre);

}
