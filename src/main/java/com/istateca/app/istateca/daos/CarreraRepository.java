package com.istateca.app.istateca.daos;

import com.istateca.app.istateca.models.Carrera;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarreraRepository extends BaseRepository<Carrera,Integer>{
    Optional<Carrera> findByIdFenix(Integer idCarrera);

}
