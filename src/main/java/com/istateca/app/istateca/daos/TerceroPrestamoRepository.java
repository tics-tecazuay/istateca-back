package com.istateca.app.istateca.daos;

import com.istateca.app.istateca.models.TerceroPrestamo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerceroPrestamoRepository extends BaseRepository<TerceroPrestamo,Integer>{

    List<TerceroPrestamo> findAllByTerceroCedula(String cedula);

}
