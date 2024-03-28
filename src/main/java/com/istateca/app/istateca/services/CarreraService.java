package com.istateca.app.istateca.services;


import com.istateca.app.istateca.models.Carrera;

public interface CarreraService extends BaseService<Carrera, Integer> {

    public boolean findByIdFenix(Integer idCarrera);

    public Carrera carreraest(Integer idCarrera);

}
