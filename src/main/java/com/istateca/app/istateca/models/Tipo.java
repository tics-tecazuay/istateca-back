package com.istateca.app.istateca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "tipo")
public class Tipo implements Serializable,Actualizable<Tipo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tip_id")
    private Integer id;

    @NotEmpty(message = "Requiere un tipo.")
    @Column(name = "tip_nombre")
    private String nombre;

    @Column(name = "tip_activo")
    private Boolean activo;

    // Bidirectional Relationships

    @JsonIgnore
    @OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY)
    private List<Libro> libros;

    @Override
    public void actualizarDatos(Tipo entity) {
        if (entity.getNombre() != null) {
            this.setNombre(entity.getNombre());
        }
        if (entity.getActivo() != null) {
            this.setActivo(entity.getActivo());
        }
    }

}
