package com.istateca.app.istateca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "donante")
public class Donante implements Serializable,Actualizable<Donante> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "don_id")
    private Integer id;

    @NotEmpty(message = "Campo obligatorio.")
    @Column(name = "don_nombre")
    private String nombre;

    // Bidirectional Relationships

    @JsonIgnore
    @OneToMany(mappedBy = "donante", fetch = FetchType.LAZY)
    private List<Libro> librosDonados;

    @Override
    public void actualizarDatos(Donante entity) {
        if (entity.getNombre()!=null)
            this.setNombre(entity.getNombre());
    }
}
