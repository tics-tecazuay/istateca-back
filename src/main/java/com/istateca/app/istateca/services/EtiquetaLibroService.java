package com.istateca.app.istateca.services;

import com.istateca.app.istateca.models.EtiquetaLibro;

import java.util.List;

public interface EtiquetaLibroService extends BaseService<EtiquetaLibro, Integer>{

    List<EtiquetaLibro> etiquetasxLibro(Integer id);

    boolean borrarEtiquetaLibro(Integer id);

}
