package com.istateca.app.fenix.fdaos;


import com.istateca.app.fenix.fmodels.UsuarioFenix;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioFenixRepository extends CrudRepository<UsuarioFenix,Integer> {

    Optional<UsuarioFenix> findByCedula(String cedula);

    Optional<UsuarioFenix> findByCedulaAndTipo(String cedula,Integer tipo);

    Optional<List<UsuarioFenix>> findByTipo(Integer tipo);

    Optional<UsuarioFenix> findByCorreo(String correo);

    @Query("SELECT u FROM UsuarioFenix u WHERE UPPER(u.nombres || ' ' || u.apellidos) = UPPER(:parametro)")
    Optional<UsuarioFenix> findByNombresAndApellidosQuery(@Param("parametro") String parametro);
/*
    @Query("SELECT CASE WHEN EXISTS (SELECT 1 FROM Persona p WHERE UPPER(p.nombres || ' ' || p.apellidos) = UPPER(:parametro)) THEN true ELSE false END FROM Persona p")
    boolean existsByNombresAndApellidosQuery(@Param("parametro") String parametro);
*/
    boolean existsByCedula(String cedula);

}
