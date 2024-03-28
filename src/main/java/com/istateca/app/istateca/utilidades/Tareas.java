package com.istateca.app.istateca.utilidades;

import com.istateca.app.istateca.models.Notificacion;
import com.istateca.app.istateca.models.Persona;
import com.istateca.app.istateca.models.Prestamo;
import com.istateca.app.istateca.services.NotificacionService;
import com.istateca.app.istateca.services.PersonaService;
import com.istateca.app.istateca.services.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class Tareas {

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private NotificacionService notificacionService;

    // Tarea para el cambio de estado en caso de sobrepasar fecha maxima del prestamo
    //se ejecuta a la media noche
    @Scheduled(cron = "00 00 00 * * *")
    public void prestamosPasados() {
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);
        for (Prestamo prestamo: prestamoService.prestamopasados(date)) {
                prestamo.setEstadoPrestamo(5);
                prestamoService.update(prestamo, prestamo.getId());
        }
    }
    //notificacion de retraso en la devolucion, se ejecuta al dia siguiente de la fecha maxima de devolucion a las 08:30
    @Scheduled(cron = "00 30 08 * * *")
    public void notificacionPasados() {
        List<Persona> biblios = personaService.bibliotecarioDevice();
        for (Prestamo prestamo: prestamoService.prestamoxestadoprestamo(5)) {
            if(restarUnDia(prestamo.getFechaMaxima().toString(),false) && prestamo.getTipoPrestamo()!=3) {
                NotificacionDevice.enviarNotificacion(prestamo.getIdSolicitante().getDevice(), "DEVOLUCION ATRASADA", prestamo.mensaje(false));
                if (biblios != null) {
                    for (Persona biblio : biblios) {
                        NotificacionDevice.enviarNotificacion(biblio.getDevice(), "DEVOLUCION ATRASADA", prestamo.mensaje(true));
                    }
                }
            }
        }
    }

    //notificacion de que la fecha maxima de devolucion esta proxima, se ejecuta un dia antes de la misma a las 18:00
    @Scheduled(cron = "00 30 18 * * *")
    public void notificacionPorPasarse() {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        for (Prestamo prestamo: prestamoService.prestamoxestadoprestamo(2)) {
            if(restarUnDia(prestamo.getFechaMaxima().toString(),true) && prestamo.getTipoPrestamo()!=3) {
                Notificacion notificacion=new Notificacion();
                notificacion.setPrestamo(prestamo);
                notificacion.setMensaje(7);
                notificacion.setVisto(false);
                notificacionService.save(notificacion);
                NotificacionDevice.enviarNotificacion(prestamo.getIdSolicitante().getDevice(), "DEVOLUCION PROXIMA",
                        "No olvides devolver el libro " + prestamo.getLibro().getTitulo() + " hasta el " + formato.format(prestamo.getFechaMaxima()));
            }
        }
    }

    /**
     *Metodo que suma o resta un dia a una fehca
     *entregada y la compara con la actual
     * @param estado Si esta en true suma un dia , en false resta un dia
     * @param dateString Fecha en formato string para la conversion correcta
     *@Type Boolean
     */
    public static boolean restarUnDia(String dateString,boolean estado) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            LocalDate fechaTransformada = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (estado)fechaTransformada = fechaTransformada.minusDays(1);
            else fechaTransformada = fechaTransformada.plusDays(1);
            LocalDate fechaSistema = LocalDateTime.now().toLocalDate();
            return fechaSistema.equals(fechaTransformada);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}
