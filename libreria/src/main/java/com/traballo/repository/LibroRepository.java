package com.traballo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traballo.entity.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    
}
