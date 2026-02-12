package com.traballo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.traballo.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();

    List<Autor> findByNombreIgnoreCaseContaining(String nombre);

    List<Autor> findByFechaNacimientoBetween(Date inicio, Date fin);
}
