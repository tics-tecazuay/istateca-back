package com.istateca.app.istateca.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.istateca.app.istateca.utilidades.NotificacionDevice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "prestamo")
public class Prestamo implements Serializable,Actualizable<Prestamo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pre_id")
    private Integer id;

    @Column(name = "pre_fecha_solicitud")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin; //  error en nombre, correcto fecha solicitud no se cambia por implementacion

    @NotNull(message = "Campo estado libro obligatorio.")
    @Column(name = "pre_estado_libro")
    private Integer estadoLibro;

    @NotNull(message = "Campo estado del prestamo obligatorio.")
    @Column(name = "pre_estado_prestamo")
    private Integer estadoPrestamo;

    @Column(name = "pre_fecha_entrega")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaEntrega;

    @Column(name = "pre_documento_habilitante")
    private Integer documentoHabilitante;

    @Column(name = "pre_fecha_devolucion")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaDevolucion;

    //@NotNull(message = "Se nesesita fecha maxima de devolucion.")
    @Column(name = "pre_fecha_maxima")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaMaxima;

    @Column(name = "pre_activo", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean activo;

    @Column(name = "pre_escaneo_matriz")
    private String escaneoMatriz;

    @NotNull(message = "Requiere tipo.")
    @Column(name = "pre_tipo_prestamo")
    private Integer tipoPrestamo;

    // Foreign Key - Relationships

    @ManyToOne
    @JoinColumn(name = "per_id_solicitante", referencedColumnName = "per_id")
    private Persona idSolicitante;

    @ManyToOne
    @JoinColumn(name = "per_id_entrega", referencedColumnName = "per_id")
    private Persona idEntrega;

    @ManyToOne
    @JoinColumn(name = "per_id_recibido", referencedColumnName = "per_id")
    private Persona idRecibido;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "lib_id", referencedColumnName = "lib_id")
    private Libro libro;

    @ManyToOne
    private TerceroPrestamo terceroPrestamo;

    // Bidirectional Relationships

    @JsonIgnore
    @OneToMany(mappedBy = "prestamo", fetch = FetchType.LAZY)
    private List<Notificacion> notificaciones;

    @Override
    public void actualizarDatos(Prestamo entity) {
        if (entity.getFechaFin() != null) {
            this.setFechaFin(entity.getFechaFin());
        }
        if (entity.getEstadoLibro() != null) {
            this.setEstadoLibro(entity.getEstadoLibro());
        }
        if (entity.getEstadoPrestamo() != null) {
            this.setEstadoPrestamo(entity.getEstadoPrestamo());
        }
        if (entity.getFechaEntrega() != null) {
            this.setFechaEntrega(entity.getFechaEntrega());
        }
        if (entity.getDocumentoHabilitante() != null) {
            this.setDocumentoHabilitante(entity.getDocumentoHabilitante());
        }
        if (entity.getFechaDevolucion() != null) {
            this.setFechaDevolucion(entity.getFechaDevolucion());
        }
        if (entity.getFechaMaxima() != null) {
            this.setFechaMaxima(entity.getFechaMaxima());
        }
        if (entity.getActivo() != null) {
            this.setActivo(entity.getActivo());
        }
        if (entity.getEscaneoMatriz() != null) {
            this.setEscaneoMatriz(entity.getEscaneoMatriz());
        }
        if (entity.getTipoPrestamo() != null) {
            this.setTipoPrestamo(entity.getTipoPrestamo());
        }
        if (entity.getLibro() != null) {
            this.setLibro(entity.getLibro());
        }
        if (entity.getCarrera() != null) {
            this.setCarrera(entity.getCarrera());
        }
        if (entity.getIdSolicitante() != null) {
            this.setIdSolicitante(entity.getIdSolicitante());
        }
        if (entity.getIdEntrega() != null) {
            this.setIdEntrega(entity.getIdEntrega());
        }
        if (entity.getIdRecibido()!= null) {
            this.setIdRecibido(entity.getIdRecibido());
        }
        if (entity.getTerceroPrestamo()!= null) {
            this.setTerceroPrestamo(entity.getTerceroPrestamo());
        }
    }

    @JsonIgnore
    @Transient
    private Integer estadoPrestamoAnterior;

    @PostLoad
    private void setEstadoPrestamoAnterior() {
        this.estadoPrestamoAnterior = this.estadoPrestamo;
    }
    @PostUpdate
    public void postUpdate() {
        if (!Objects.equals(this.estadoPrestamo, this.estadoPrestamoAnterior)&&this.tipoPrestamo!=3) {
            if(this.idSolicitante.getDevice()!=null){
                if(this.estadoPrestamo == 3 || this.estadoPrestamo == 6)
                    NotificacionDevice.enviarNotificacion(this.idSolicitante.getDevice(),"FINALIZACION DE PRESTAMO",mensaje(null));
                if(this.estadoPrestamo == 7)
                    NotificacionDevice.enviarNotificacion(this.idSolicitante.getDevice(),"PRESTAMO RECHAZADO",mensaje(false));
            }
        }
    }

    /**
     *Metodo que devuelve un mensaje para enviar a dispositivos moviles
     *en base al cambio de estadoPrestamo del modelo
     * @param version Destinado al estadoPrestamo 5, siendo true para bibliotecarios y false para usuarios
     * @Type String
     */
    public String mensaje(Boolean version){
        switch (this.estadoPrestamo){
            case 1:{
                return "El usuario "+this.idSolicitante.getNombres()+" "+this.idSolicitante.getApellidos()+
                        " ha solicitado el libro "+ this.libro.getTitulo();
            }
            case 5:{
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                String fechaFormateada = formato.format(this.fechaMaxima);
                if (version){
                    return "El prestamo del libro "+this.libro.getTitulo() +" al usuario "+
                            this.idSolicitante.getNombres()+" "+this.idSolicitante.getApellidos()+
                            " ha superado la fecha de devolucion "+ fechaFormateada;
                }
                else{
                    return "Su prestamo del libro "+this.libro.getTitulo()+
                            " ha superado la fecha de devolucion "+ fechaFormateada;
                }
            }
            case 3,6:{
                return "Su prestamo del libro "+this.libro.getTitulo()+
                        " ha concluido exitosamente";
            }
            case 7:{
                return "Su prestamo del libro "+this.libro.getTitulo()+
                        " ha sido rechazado";
            }
            default: return null;
        }
    }

}
