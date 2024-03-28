package com.istateca.app.istateca.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "tercero_prestamo")
public class TerceroPrestamo implements Serializable,Actualizable<TerceroPrestamo>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tps_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ter_id", referencedColumnName = "ter_id")
    private Tercero tercero;

    @ManyToOne
    @JoinColumn(name = "pre_id", referencedColumnName = "pre_id")
    private Prestamo prestamo;

    @Override
    public void actualizarDatos(TerceroPrestamo entity) {
        if (entity.getPrestamo() != null) {
            this.setPrestamo(entity.getPrestamo());
        }
        if (entity.getTercero() != null) {
            this.setTercero(entity.getTercero());
        }
    }
}
