package com.istateca.app.istateca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "autor")
public class Autor implements Serializable,Actualizable<Autor> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ato_id")
    private Integer id;

    @NotEmpty(message = "Campo obligatorio.")
    @Column(name = "ato_nombre")
    private String nombre;

    // Bidirectional Relationships

    @JsonIgnore
    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<AutorLibro> autoresLibros;

    @Override
    public void actualizarDatos(Autor entity) {
        if (entity.getNombre()!=null)
        this.setNombre(entity.getNombre());
    }
}
