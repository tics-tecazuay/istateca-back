package com.istateca.app.istateca.daos;

import com.istateca.app.istateca.models.Etiqueta;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtiquetaRepository extends BaseRepository<Etiqueta,Integer>{

    List<Etiqueta> findAllByLibrosetiquetadosLibroId(Integer id);

    List<Etiqueta> findAllByNombreContainingIgnoreCase(String nombre);
 }
