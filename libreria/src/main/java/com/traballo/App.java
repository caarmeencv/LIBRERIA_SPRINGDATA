package com.traballo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.traballo.entity.Autor;
import com.traballo.entity.Categoria;
import com.traballo.entity.Editorial;
import com.traballo.entity.Libro;
import com.traballo.repository.AutorRepository;
import com.traballo.repository.CategoriaRepository;
import com.traballo.repository.EditorialRepository;
import com.traballo.service.LibroService;

@SpringBootApplication
public class App implements CommandLineRunner {

    private final LibroService libroService;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;
    private final EditorialRepository editorialRepository;

    public App(LibroService libroService,
               AutorRepository autorRepository,
               CategoriaRepository categoriaRepository,
               EditorialRepository editorialRepository) {
        this.libroService = libroService;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
        this.editorialRepository = editorialRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Categoria cat = categoriaRepository.save(
                new Categoria("Programación", "Libros técnicos"));

        Editorial ed = editorialRepository.save(
                new Editorial("Anaya", "España"));

        Autor autor1 = autorRepository.save(
                new Autor("Juan", "Pérez", sdf.parse("1980-05-10")));

        Libro libro = new Libro("123456", "Spring Boot", 2023, 29.99f);
        libro.setCategoria(cat);
        libro.setEditorial(ed);
        libro.setAutores(Arrays.asList(autor1));

        libroService.save(libro);

        System.out.println("Buscar por ISBN:");
        System.out.println(libroService.buscarPorISBN("123456"));

        System.out.println("Buscar por editorial:");
        libroService.buscarPorEditorial("anaya")
                .forEach(System.out::println);
    }
}
