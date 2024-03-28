package com.istateca.app.istateca.daos;

import com.istateca.app.istateca.models.Tercero;
import org.springframework.stereotype.Repository;

@Repository
public interface TerceroRepository extends BaseRepository<Tercero,Integer>{

    Tercero findByCedula(String cedula);

}
