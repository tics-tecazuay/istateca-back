package com.istateca.app.istateca.services;


import com.istateca.app.istateca.daos.CarreraRepository;
import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.models.Carrera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarreraImpl extends BaseServiceImpl<Carrera, Integer> implements CarreraService {

    @Autowired
    private CarreraRepository repository;

    public CarreraImpl(BaseRepository<Carrera, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public boolean findByIdFenix(Integer idCarrera) {
        return (repository.findByIdFenix(idCarrera).orElse(null)==null);
    }

    @Override
    public Carrera carreraest(Integer idCarrera) {
        return repository.findByIdFenix(idCarrera).orElse(null);
    }


}
