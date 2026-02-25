package com.traballo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.traballo.entity.Autor;
import com.traballo.repository.AutorRepository;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // CRUD
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    public Optional<Autor> findById(Long id) {
        return autorRepository.findById(id);
    }

    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    public void deleteById(Long id) {
        autorRepository.deleteById(id);
    }

    // ENUNCIADO
    public List<Autor> traerTodosConLibros() {
        return autorRepository.findAllWithLibros();
    }

    public List<Autor> buscarPorNombre(String nombre) {
        return autorRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Autor> buscarPorFechas(LocalDate inicio, LocalDate fin) {
        return autorRepository.findByFechaNacimientoBetween(inicio, fin);
    }
}