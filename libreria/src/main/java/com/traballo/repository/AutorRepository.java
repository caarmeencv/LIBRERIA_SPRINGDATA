package com.traballo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traballo.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}