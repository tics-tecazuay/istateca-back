package com.istateca.app.istateca.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "notificacion")
public class Notificacion implements Serializable,Actualizable<Notificacion> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "not_id")
    private Integer id;

    @Column(name = "not_mensaje")
    private Integer mensaje;

    @Column(name = "not_visto")
    private Boolean visto;

    // Foreign Key - Relationships

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pre_id", referencedColumnName = "pre_id")
    private Prestamo prestamo;

    //solo editable el campo visto
    @Override
    public void actualizarDatos(Notificacion entity) {
        if (entity.visto != null) {
            this.setVisto(entity.getVisto());
        }
    }


}
