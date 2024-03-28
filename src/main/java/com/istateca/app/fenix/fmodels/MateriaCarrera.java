package com.istateca.app.fenix.fmodels;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "vistateca_carreras_materias")
public class MateriaCarrera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer carreraMateriaId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "carreraid")
    private Integer carreraId;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "ciclo")
    private Integer ciclo;


}
