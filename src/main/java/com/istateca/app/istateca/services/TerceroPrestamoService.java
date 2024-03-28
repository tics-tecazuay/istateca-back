package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.TerceroPrestamo;

import java.util.List;

public interface TerceroPrestamoService extends BaseService<TerceroPrestamo, Integer>{

    List<TerceroPrestamo> tercerosxCedula(String cedula);

}
