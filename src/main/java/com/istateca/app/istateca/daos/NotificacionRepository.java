package com.istateca.app.istateca.daos;

import com.istateca.app.istateca.models.Notificacion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends BaseRepository<Notificacion,Integer>{

    List<Notificacion> findTop10ByPrestamoIdSolicitanteIdAndMensajeInOrderByVistoAscIdDesc(Integer idSolicitante,List<Integer> mensaje);

    List<Notificacion> findTop15ByMensajeInOrderByVistoAscIdDesc(List<Integer> mensaje);
}

