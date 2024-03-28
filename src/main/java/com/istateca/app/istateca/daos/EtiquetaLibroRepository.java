package com.istateca.app.istateca.daos;

import com.istateca.app.istateca.models.EtiquetaLibro;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtiquetaLibroRepository extends BaseRepository<EtiquetaLibro,Integer>{

    List<EtiquetaLibro> findByLibroId(Integer id);

    void deleteById(Integer id);

}
