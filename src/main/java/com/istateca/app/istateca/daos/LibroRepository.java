package com.istateca.app.istateca.daos;

import com.istateca.app.istateca.models.Etiqueta;
import com.istateca.app.istateca.models.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends BaseRepository<Libro,Integer>{

    List<Libro> findAllByTituloContainingIgnoreCase(String titulo);

    List<Libro> findAllByTipoId(Integer id);

    /*
    @Query("SELECT l FROM Libro l WHERE " +
            "(:parametro IS NULL OR LOWER(l.titulo) LIKE %:parametro%) OR " +
            "(:parametro IS NULL OR LOWER(l.descripcion) LIKE %:parametro%) OR " +
            "(:parametro IS NULL OR LOWER(l.subtitulo) LIKE %:parametro%)")
    List<Libro> librosxcoincidencias(@Param("parametro") String parametro);   */

    List<Libro> findAllByTituloContainingIgnoreCaseOrSubtituloContainingIgnoreCaseOrDescripcionContainingIgnoreCaseOrAreaContainingIgnoreCaseOrEtiquetasLibroEtiquetaNombreContainingIgnoreCase(String titulo, String subtitulo, String descripcion,String area, String nombre);

    boolean existsByDisponibilidadIsTrueAndId(Integer id);

}
