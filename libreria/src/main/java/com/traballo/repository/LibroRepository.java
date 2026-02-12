package com.traballo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traballo.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByAnoPublicacionGreaterThanEqual(int ano);

    Libro findByISBN(String isbn);

    List<Libro> findByEditorialNombreIgnoreCaseContaining(String nombre);

    List<Libro> findByAutoresNombreIgnoreCaseContaining(String nombre);

    List<Libro> findByCategoriaId(Long id);

    List<Libro> findByAutoresId(Long id);
}
