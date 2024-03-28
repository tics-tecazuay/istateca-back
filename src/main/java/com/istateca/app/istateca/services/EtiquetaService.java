package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.Etiqueta;

import java.util.List;

public interface EtiquetaService  extends BaseService <Etiqueta, Integer>{

    List<Etiqueta> etiquetasxLibros(Integer id);

    List<Etiqueta> etiquetasxNombre(String nombre);

}
