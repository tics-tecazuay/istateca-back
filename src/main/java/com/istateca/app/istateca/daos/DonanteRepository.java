package com.istateca.app.istateca.daos;

import com.istateca.app.istateca.models.Donante;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonanteRepository extends BaseRepository<Donante,Integer>{

    List<Donante> findAllByNombreContainingIgnoreCase(String nombre);

    Donante findByNombre(String nombre);

}
