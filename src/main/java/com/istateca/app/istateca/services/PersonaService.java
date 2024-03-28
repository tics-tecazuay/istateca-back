package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.Persona;

import java.util.List;

public interface PersonaService extends BaseService<Persona, Integer> {

    Persona personaxCedula(String cedula);

    Persona findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    List<Persona> bibliotecarioDevice();

    boolean Habilitado(Integer idPersona,Integer calificacion);

}
