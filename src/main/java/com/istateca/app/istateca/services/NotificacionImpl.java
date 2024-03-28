package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.daos.NotificacionRepository;
import com.istateca.app.istateca.models.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NotificacionImpl extends BaseServiceImpl<Notificacion,Integer> implements NotificacionService{

    @Autowired
    private NotificacionRepository repository;

    public NotificacionImpl(BaseRepository<Notificacion, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Notificacion> notificacionesxusuario(Integer idSolicitante) {
        return repository.findTop10ByPrestamoIdSolicitanteIdAndMensajeInOrderByVistoAscIdDesc(idSolicitante,Arrays.asList(2,3,5,6,7));
    }

    @Override
    public List<Notificacion> notificacionesxdocente() {
        return repository.findTop15ByMensajeInOrderByVistoAscIdDesc(Arrays.asList(1,4));
    }
}
