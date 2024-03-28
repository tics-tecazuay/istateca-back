package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.Tercero;

public interface TerceroService extends BaseService<Tercero, Integer> {

    Tercero buscarxCedula(String cedula);

}
