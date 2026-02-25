package com.traballo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.traballo.entity.Libro;
import com.traballo.repository.LibroRepository;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // CRUD
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    public Optional<Libro> findById(Long id) {
        return libroRepository.findById(id);
    }

    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    public void deleteById(Long id) {
        libroRepository.deleteById(id);
    }

    public long count() {
        return libroRepository.count();
    }

    // ENUNCIADO
    public List<Libro> buscarDesdeAno(Integer ano) {
        return libroRepository.findByAnoPublicacionGreaterThanEqual(ano);
    }

    public Optional<Libro> buscarPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn);
    }

    public List<Libro> buscarPorEditorial(String nombre) {
        return libroRepository.findByEditorialNombreContainingIgnoreCase(nombre);
    }

    public List<Libro> buscarPorAutor(String nombre) {
        return libroRepository.findByAutoresNombreContainingIgnoreCase(nombre);
    }

    public List<Libro> buscarPorCategoria(Long idCategoria) {
        return libroRepository.findByCategoriaId(idCategoria);
    }

    public List<Libro> buscarPorAutorId(Long idAutor) {
        return libroRepository.findByAutoresId(idAutor);
    }
}