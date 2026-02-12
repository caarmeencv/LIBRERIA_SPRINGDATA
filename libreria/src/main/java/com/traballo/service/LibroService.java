package com.traballo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.traballo.entity.Libro;
import com.traballo.repository.LibroRepository;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    public List<Libro> buscarPorAno(int ano) {
        return libroRepository.findByAnoPublicacionGreaterThanEqual(ano);
    }

    public Libro buscarPorISBN(String isbn) {
        return libroRepository.findByISBN(isbn);
    }

    public List<Libro> buscarPorEditorial(String nombre) {
        return libroRepository.findByEditorialNombreIgnoreCaseContaining(nombre);
    }

    public List<Libro> buscarPorAutor(String nombre) {
        return libroRepository.findByAutoresNombreIgnoreCaseContaining(nombre);
    }

    public List<Libro> buscarPorCategoria(Long id) {
        return libroRepository.findByCategoriaId(id);
    }

    public List<Libro> buscarPorAutorId(Long id) {
        return libroRepository.findByAutoresId(id);
    }
}
