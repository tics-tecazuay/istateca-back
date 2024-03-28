package com.istateca.app.istateca.daos;

import com.istateca.app.istateca.models.Tipo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoRepository extends BaseRepository<Tipo,Integer>{

    List<Tipo> findAllByNombreContainingIgnoreCase(String nombre);

}
