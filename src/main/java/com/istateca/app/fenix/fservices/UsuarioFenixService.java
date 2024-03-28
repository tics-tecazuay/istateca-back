package com.istateca.app.fenix.fservices;



import com.istateca.app.fenix.fmodels.UsuarioFenix;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioFenixService {

    List<UsuarioFenix> findAll();

    UsuarioFenix findByCedula(String cedula);

    UsuarioFenix findByCedulaEstudiante(String cedula);

    List<UsuarioFenix> findByDocentes();
    UsuarioFenix findByCorreo(String correo);

    UsuarioFenix findByNombresAndApellidosQuery(@Param("parametro") String parametro);

    boolean existeCedula(String cedula);

}
