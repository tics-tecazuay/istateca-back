package com.istateca.app.istateca.daos;

import com.istateca.app.istateca.models.Prestamo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrestamoRepository extends BaseRepository<Prestamo,Integer>{

    List<Prestamo> findAllByEstadoPrestamo(Integer estado);

    List<Prestamo> findAllByIdSolicitanteCedula(String cedula);

    List<Prestamo> findByFechaMaximaLessThanAndEstadoPrestamo(Date fechaActual,Integer estado);

    List<Prestamo> findByTipoPrestamoAndCarreraIdAndEstadoPrestamoAndFechaFinBetween
                                        (Integer tipo,Integer carreraId,Integer estado,Date inicio,Date fin);

    List<Prestamo> findByTipoPrestamoAndCarreraIdAndFechaFinBetween
                                        (Integer tipo,Integer carreraId,Date inicio,Date fin);

    List<Prestamo> findByCarreraIdAndFechaFinBetween(Integer carreraId,Date inicio,Date fin);

    List<Prestamo> findByFechaFinBetween(Date inicio,Date fin);

    Integer countByEstadoPrestamoInAndIdSolicitanteId(List<Integer> estados, Integer personaId);

    List<Prestamo> findAllByIdSolicitanteCedulaAndActivoIsTrue(String cedula);

}
