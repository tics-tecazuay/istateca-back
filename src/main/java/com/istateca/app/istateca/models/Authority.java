package com.istateca.app.istateca.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "aut_id")
    private Integer id;

    @JoinColumn(name = "aut_name")
    private String name;

    // Bidirectional Relationships

    @ManyToOne
    @JoinColumn(name = "per_id", referencedColumnName = "per_id")
    private Persona persona;

}
