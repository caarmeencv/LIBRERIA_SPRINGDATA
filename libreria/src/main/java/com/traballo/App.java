package com.traballo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import com.traballo.repository.LibroRepository;
import com.traballo.service.LibroService;

@SpringBootApplication
public class App implements CommandLineRunner {

    private final LibroService libroService;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;
    private final EditorialRepository editorialRepository;
    private final LibroRepository libroRepository;

    public App(LibroService libroService,
               AutorRepository autorRepository,
               CategoriaRepository categoriaRepository,
               EditorialRepository editorialRepository,
               LibroRepository libroRepository) {
        this.libroService = libroService;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
        this.editorialRepository = editorialRepository;
        this.libroRepository = libroRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("\n=== CREANDO DATOS ===");

        // Categorías
        Categoria catProg = categoriaRepository.save(new Categoria("Programación", "Libros técnicos"));
        Categoria catNovela = categoriaRepository.save(new Categoria("Novela", "Narrativa"));

        System.out.println("Categoria creada: " + catProg);
        System.out.println("Categoria creada: " + catNovela);

        // Editoriales
        Editorial edAnaya = editorialRepository.save(new Editorial("Anaya", "España"));
        Editorial edPlaneta = editorialRepository.save(new Editorial("Planeta", "España"));

        System.out.println("Editorial creada: " + edAnaya);
        System.out.println("Editorial creada: " + edPlaneta);

        // Autores
        Autor a1 = autorRepository.save(new Autor("Juan", "Pérez", sdf.parse("1980-05-10")));
        Autor a2 = autorRepository.save(new Autor("Ana", "Gómez", sdf.parse("1990-02-15")));

        System.out.println("Autor creado: " + a1);
        System.out.println("Autor creado: " + a2);

        // Libros
        Libro l1 = new Libro("123456", "Spring Boot", 2023, 29.99f);
        l1.setCategoria(catProg);
        l1.setEditorial(edAnaya);
        l1.setAutores(Arrays.asList(a1, a2));
        libroService.save(l1);

        Libro l2 = new Libro("999888", "Hibernate Pro", 2019, 19.99f);
        l2.setCategoria(catNovela);
        l2.setEditorial(edPlaneta);
        l2.setAutores(Arrays.asList(a2));
        libroService.save(l2);

        System.out.println("Libro creado: " + l1);
        System.out.println("Libro creado: " + l2);

        // =============================
        // PRUEBAS LIBRO
        // =============================
        System.out.println("\n=============================");
        System.out.println("PRUEBAS LIBRO");
        System.out.println("=============================");

        System.out.println("\nBuscar libros desde año 2020:");
        libroService.buscarPorAno(2020).forEach(System.out::println);

        System.out.println("\nBuscar por ISBN 123456:");
        System.out.println(libroService.buscarPorISBN("123456"));

        System.out.println("\nBuscar por editorial 'anaya' (ignore case):");
        libroService.buscarPorEditorial("anaya").forEach(System.out::println);

        System.out.println("\nBuscar por nombre de autor contiene 'ju' (ignore case):");
        libroService.buscarPorAutor("ju").forEach(System.out::println);

        System.out.println("\nBuscar por id de categoría (catProg): " + catProg.getId());
        libroService.buscarPorCategoria(catProg.getId()).forEach(System.out::println);

        System.out.println("\nBuscar por id de autor (a1): " + a1.getId());
        libroService.buscarPorAutorId(a1.getId()).forEach(System.out::println);

        // =============================
        // PRUEBAS AUTOR
        // =============================
        System.out.println("\n=============================");
        System.out.println("PRUEBAS AUTOR");
        System.out.println("=============================");

        System.out.println("\nTraer autores con libros cargados (JPQL fetch):");
        List<Autor> autoresConLibros = autorRepository.findAllWithLibros();
        for (Autor a : autoresConLibros) {
            System.out.println(a);
            System.out.println("  Libros: " + a.getLibros());
        }

        System.out.println("\nBuscar autor por nombre contiene 'ana':");
        autorRepository.findByNombreIgnoreCaseContaining("ana").forEach(System.out::println);

        System.out.println("\nBuscar autores nacidos entre 1979-01-01 y 1991-01-01:");
        Date ini = sdf.parse("1979-01-01");
        Date fin = sdf.parse("1991-01-01");
        autorRepository.findByFechaNacimientoBetween(ini, fin).forEach(System.out::println);

        // =============================
        // BORRADO CATEGORIA (CASCADE)
        // =============================
        System.out.println("\n=============================");
        System.out.println("BORRAR CATEGORÍA (cascade)");
        System.out.println("=============================");

        long idCatBorrar = catProg.getId();
        System.out.println("Libros de esa categoría antes: " + libroRepository.findByCategoriaId(idCatBorrar).size());

        System.out.println("Borrando categoría Programación (id=" + idCatBorrar + ")");
        categoriaRepository.deleteById(idCatBorrar);

        System.out.println("Libros de esa categoría después: " + libroRepository.findByCategoriaId(idCatBorrar).size());
        System.out.println("✅ Categoría borrada y libros asociados borrados automáticamente.");
    }
}
