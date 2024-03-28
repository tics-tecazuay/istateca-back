package com.istateca.app.fenix.fservices;


import com.istateca.app.fenix.fdaos.UsuarioFenixRepository;
import com.istateca.app.fenix.fmodels.UsuarioFenix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UsuarioFenixImpl implements UsuarioFenixService{

    @Autowired
    private UsuarioFenixRepository usuarioFenixRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioFenix> findAll() {
        return (List<UsuarioFenix>) usuarioFenixRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioFenix findByCedula(String cedula) {
        return (UsuarioFenix) usuarioFenixRepository.findByCedula(cedula).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioFenix findByCedulaEstudiante(String cedula) {
        return (UsuarioFenix) usuarioFenixRepository.findByCedulaAndTipo(cedula,1).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioFenix> findByDocentes(){
        return (List<UsuarioFenix>) usuarioFenixRepository.findByTipo(2).orElse(null);
    }

    @Override
    public UsuarioFenix findByCorreo(String correo) {
        return usuarioFenixRepository.findByCorreo(correo).orElse(null);
    }

    @Override
    public UsuarioFenix findByNombresAndApellidosQuery(String parametro) {
        return usuarioFenixRepository.findByNombresAndApellidosQuery(parametro).orElse(null);
    }

    @Override
    public boolean existeCedula(String cedula) {
        return usuarioFenixRepository.existsByCedula(cedula);
    }

}
