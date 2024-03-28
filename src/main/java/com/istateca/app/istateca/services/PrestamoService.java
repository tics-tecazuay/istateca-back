package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.Prestamo;

import java.util.Date;
import java.util.List;

public interface PrestamoService extends BaseService<Prestamo, Integer> {

    /**
     *Servicio que devuelve un listado de prestamo de acuerdo al atributo prestamoEstado
     * @param estado Estado que deseamos buscar
     * @Type Prestamo
     */
    List<Prestamo> prestamoxestadoprestamo(Integer estado);

    List<Prestamo> prestamoxcedula(String cedula);

    List<Prestamo> prestamopasados(Date fechaActual);

    List<Prestamo> reporteprestamo(Integer tipo,Integer carreraId,Integer estado,Date inicio, Date fin);

    List<Prestamo> reporteprestamosinestado(Integer tipo,Integer carreraId,Date inicio, Date fin);

    List<Prestamo> reporteprestamosincarrera(Date inicio, Date fin);

    List<Prestamo> reporteprestamoconcarrera(Integer carreraId,Date inicio, Date fin);

    Integer numeroPrestamosActivos(List<Integer> estado,Integer idSolicitante);

    List<Prestamo> prestamoActivoxCedula(String cedula);
}
