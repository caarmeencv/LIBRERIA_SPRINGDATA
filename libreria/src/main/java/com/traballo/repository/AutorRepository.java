package com.traballo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.traballo.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();

    List<Autor> findByNombreContainingIgnoreCase(String nombre);

    List<Autor> findByFechaNacimientoBetween(LocalDate inicio, LocalDate fin);
}