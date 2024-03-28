package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.LibroRepository;
import com.istateca.app.istateca.daos.PersonaRepository;
import com.istateca.app.istateca.daos.PrestamoRepository;
import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.models.Prestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PrestamoImpl extends BaseServiceImpl<Prestamo, Integer> implements PrestamoService {

    @Autowired
    private PrestamoRepository repository;

    public PrestamoImpl(BaseRepository<Prestamo, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Prestamo> prestamoxestadoprestamo(Integer estado) {
        return repository.findAllByEstadoPrestamo(estado);
    }

    @Override
    public List<Prestamo> prestamoxcedula(String cedula) {
        return repository.findAllByIdSolicitanteCedula(cedula);
    }

    @Override
    public List<Prestamo> prestamopasados(Date fechaActual) {
        return repository.findByFechaMaximaLessThanAndEstadoPrestamo(fechaActual,2);
    }

    @Override
    public List<Prestamo> reporteprestamo(Integer tipo, Integer carreraId, Integer estado,Date inicio, Date fin) {
        return repository.findByTipoPrestamoAndCarreraIdAndEstadoPrestamoAndFechaFinBetween(tipo,carreraId,estado,inicio,fin);
    }

    @Override
    public List<Prestamo> reporteprestamosinestado(Integer tipo, Integer carreraId,Date inicio, Date fin) {
        return repository.findByTipoPrestamoAndCarreraIdAndFechaFinBetween(tipo,carreraId,inicio,fin);
    }

    @Override
    public List<Prestamo> reporteprestamosincarrera(Date inicio, Date fin) {
        return repository.findByFechaFinBetween(inicio,fin);
    }

    @Override
    public List<Prestamo> reporteprestamoconcarrera(Integer carreraId, Date inicio, Date fin) {
        return repository.findByCarreraIdAndFechaFinBetween(carreraId, inicio, fin);
    }

    @Override
    public Integer numeroPrestamosActivos(List<Integer> estado, Integer idSolicitante) {
        return repository.countByEstadoPrestamoInAndIdSolicitanteId(estado,idSolicitante);
    }

    @Override
    public List<Prestamo> prestamoActivoxCedula(String cedula) {
        return repository.findAllByIdSolicitanteCedulaAndActivoIsTrue(cedula);
    }

}
