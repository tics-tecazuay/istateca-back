package com.istateca.app.istateca.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "etiqueta_libro")
public class EtiquetaLibro implements Serializable,Actualizable<EtiquetaLibro> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "elb_id")
    private Integer id;

    // Foreign Key - Relationships

    @ManyToOne
    @JoinColumn(name = "lib_id", referencedColumnName = "lib_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "eti_id", referencedColumnName = "eti_id")
    private Etiqueta etiqueta;

    @Override
    public void actualizarDatos(EtiquetaLibro entity) {
        if (entity.getLibro()!=null)
            this.setLibro(entity.getLibro());
        if (entity.getEtiqueta()!=null)
            this.setEtiqueta(entity.getEtiqueta());
    }
}
