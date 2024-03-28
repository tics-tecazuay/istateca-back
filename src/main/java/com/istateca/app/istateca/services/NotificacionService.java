package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.Notificacion;

import java.util.List;

public interface NotificacionService extends BaseService<Notificacion, Integer>{

    List<Notificacion> notificacionesxusuario(Integer idSolicitante);

    List<Notificacion> notificacionesxdocente();

}
