package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.Donante;

import java.util.List;

public interface DonanteService extends BaseService<Donante, Integer> {

    List<Donante> adonantesxNombre(String nombre);

    Donante adonantexNombre(String nombre);

}
