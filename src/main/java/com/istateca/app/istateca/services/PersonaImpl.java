package com.istateca.app.istateca.services;

import com.istateca.app.istateca.exception.ResourceNotFoundException;
import com.istateca.app.istateca.models.Persona;
import com.istateca.app.istateca.daos.BaseRepository;
import com.istateca.app.istateca.daos.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaImpl extends BaseServiceImpl<Persona, Integer> implements PersonaService {

    @Autowired
    private PersonaRepository repository;

    public PersonaImpl(BaseRepository<Persona, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Persona personaxCedula(String cedula) {
        Optional<Persona> persona = repository.findByCedula(cedula);
        if(persona.isEmpty()) {
            throw new ResourceNotFoundException("La persona con cédula " + cedula + " no registrada");
        }
        return persona.get();
    }

    @Override
    public Persona findByCorreo(String correo) {
        Optional<Persona> persona = repository.findByCorreoAndActivoIsTrue(correo);
        if(persona.isEmpty()) {
            throw new ResourceNotFoundException("Correo " + correo + ", no registrada");
        }
        return persona.get();
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return repository.existsByCorreo(correo);
    }

    @Override
    public List<Persona> bibliotecarioDevice() {
        return repository.findByDeviceIsNotNullAndTipoIn(Arrays.asList(3,4));
    }

    @Override
    public boolean Habilitado(Integer idPersona,Integer calificacion) {
        return repository.existsByIdAndCalificacionGreaterThan(idPersona,calificacion);
    }


}
