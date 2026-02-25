package com.traballo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traballo.entity.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByAnoPublicacionGreaterThanEqual(Integer ano);

    Optional<Libro> findByIsbn(String isbn);

    List<Libro> findByEditorialNombreContainingIgnoreCase(String nombre);

    List<Libro> findByAutoresNombreContainingIgnoreCase(String nombre);

    List<Libro> findByCategoriaId(Long idCategoria);

    List<Libro> findByAutoresId(Long idAutor);
}