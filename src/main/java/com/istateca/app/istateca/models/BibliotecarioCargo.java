package com.istateca.app.istateca.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "blibliotecario_cargo")
public class BibliotecarioCargo implements Serializable, Actualizable<BibliotecarioCargo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cgo_id")
    private Integer id;

    @Column(name = "cgo_fecha_inicio")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @Column(name = "cgo_fecha_fin")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;

    @Column(name = "cgo_activo_bibliotecario")
    private Boolean activoBibliotecario;

    // Foreign Key - Relationships

    @ManyToOne
    @JoinColumn(name = "per_id", referencedColumnName = "per_id")
    private Persona persona;

    @Override
    public void actualizarDatos(BibliotecarioCargo entity) {
        if (entity.getFechaInicio() != null) {
            this.setFechaInicio(entity.getFechaInicio());
        }
        if (entity.getFechaFin() != null) {
            this.setFechaFin(entity.getFechaFin());
        }
        if (entity.getActivoBibliotecario() != null) {
            this.setActivoBibliotecario(entity.getActivoBibliotecario());
        }
        if (entity.getPersona() != null) {
            this.setPersona(entity.getPersona());
        }
    }
}
