package com.istateca.app.istateca.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "sugerencia")
public class Sugerencia implements Serializable,Actualizable<Sugerencia>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sur_id")
    private Integer id;

    @NotEmpty(message = "Descripcion obligatoria, si no ¿para que realiza la sugerencia?.")
    @Column(name = "sur_descripcion")
    private String descripcion;

    @Column(name = "sur_estado")
    private Boolean estado;

    @Column(name = "sur_fecha")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    // Bidirectional Relationships

    @ManyToOne
    @JoinColumn(name = "per_id", referencedColumnName = "per_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private Carrera carrera;

    @Override
    public void actualizarDatos(Sugerencia entity) {
        if (entity.getDescripcion() != null) {
            this.setDescripcion(entity.getDescripcion());
        }
        if (entity.getFecha() != null) {
            this.setFecha(entity.getFecha());
        }
        if (entity.getPersona() != null) {
            this.setPersona(entity.getPersona());
        }
        if (entity.getCarrera() != null) {
            this.setCarrera(entity.getCarrera());
        }
        if (entity.getEstado() != null) {
            this.setEstado(entity.getEstado());
        }
    }
}
