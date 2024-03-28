package com.istateca.app.istateca.services;

import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.daos.EtiquetaLibroRepository;
import com.istateca.app.istateca.models.EtiquetaLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtiquetaLibroImpl extends BaseServiceImpl<EtiquetaLibro, Integer> implements EtiquetaLibroService {

    @Autowired
    private EtiquetaLibroRepository repository;

    public EtiquetaLibroImpl(BaseRepository<EtiquetaLibro, Integer> baseRepository) {
        super(baseRepository);
    }


    @Override
    public List<EtiquetaLibro> etiquetasxLibro(Integer id) {
        return repository.findByLibroId(id);
    }

    @Override
    public boolean borrarEtiquetaLibro(Integer id) {
        try {
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }
}
