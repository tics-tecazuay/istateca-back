package com.istateca.app.istateca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "etiqueta")
public class Etiqueta implements Serializable,Actualizable<Etiqueta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eti_id")
    private Integer id;

    @NotEmpty(message = "Nombre requerido.")
    @Column(name = "eti_nombre")
    private String nombre;

    @Column(name = "eti_activo")
    private Boolean activo;

    // Bidirectional Relationships

    @JsonIgnore
    @OneToMany(mappedBy = "etiqueta", fetch = FetchType.LAZY)
    private List<EtiquetaLibro> librosetiquetados;


    @Override
    public void actualizarDatos(Etiqueta entity) {
        if (entity.getNombre() != null) {
            this.setNombre(entity.getNombre());
        }
        if (entity.getActivo() != null) {
            this.setActivo(entity.getActivo());
        }
    }
}
