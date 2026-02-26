package com.traballo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.traballo.entity.Autor;
import com.traballo.entity.Categoria;
import com.traballo.entity.Editorial;
import com.traballo.entity.Libro;
import com.traballo.service.AutorService;
import com.traballo.service.CategoriaService;
import com.traballo.service.EditorialService;
import com.traballo.service.LibroService;

@SpringBootApplication
public class Libreria {

    public static void main(String[] args) {
        SpringApplication.run(Libreria.class, args);
    }

    @Bean
    public CommandLineRunner run(CategoriaService categoriaService, EditorialService editorialService,
                                  AutorService autorService, LibroService libroService) {

        return args -> {

            System.out.println("\nPRUEBAS COMPLETAS DE RELACIONES DE LA LIBRERÍA\n");

            //Crear categorías
            System.out.println("1. Creando categorías...");
            Categoria cat1 = categoriaService.save(new Categoria("Fantasía", "Libros de fantasía"));
            Categoria cat2 = categoriaService.save(new Categoria("Drama", "Libros de drama"));
            Categoria cat3 = categoriaService.save(new Categoria("Historia", "Libros de historia"));
            System.out.println("   - " + cat1);
            System.out.println("   - " + cat2);
            System.out.println("   - " + cat3);
            System.out.println();

            //Crear editoriales
            System.out.println("2. Creando editoriales...");
            Editorial e1 = editorialService.save(new Editorial("Planeta", "España"));
            Editorial e2 = editorialService.save(new Editorial("OReilly", "USA"));
            Editorial e3 = editorialService.save(new Editorial("Anaya", "España"));
            System.out.println("   - " + e1);
            System.out.println("   - " + e2);
            System.out.println("   - " + e3);
            System.out.println();

            //Crear autores
            System.out.println("3. Creando autores...");
            Autor au1 = autorService.save(new Autor("J. K.", "Rowling", LocalDate.of(1965, 7, 31)));
            Autor au2 = autorService.save(new Autor("Miguel", "de Cervantes", LocalDate.of(1547, 9, 29)));
            Autor au3 = autorService.save(new Autor("Stephen", "King", LocalDate.of(1947, 12, 18)));
            Autor au4 = autorService.save(new Autor("Cristina", "Morales", LocalDate.of(1920, 1, 2)));
            System.out.println("   - " + au1);
            System.out.println("   - " + au2);
            System.out.println("   - " + au3);
            System.out.println("   - " + au4);
            System.out.println();

            //Crear libros (y enlazar categoría/editorial/autores)
            System.out.println("4. Creando libros y enlazando relaciones con categoría, editorial y autores...");

            Libro l1 = new Libro("ISBN-111", "Harry Potter y la piedra filosofal", 1997, 29.99);
            l1.setCategoria(cat1);
            l1.setEditorial(e1);
            l1.addAutor(au1);

            Libro l2 = new Libro("ISBN-222", "El Quijote", 1605, 34.50);
            l2.setCategoria(cat2);
            l2.setEditorial(e2);
            l2.addAutor(au2);

            Libro l3 = new Libro("ISBN-333", "IT", 1986, 45.00);
            l3.setCategoria(cat2);
            l3.setEditorial(e2);
            l3.addAutor(au3);

            Libro l4 = new Libro("ISBN-444", "Los combatientes", 2013, 19.95);
            l4.setCategoria(cat3);
            l4.setEditorial(e3);
            l4.addAutor(au4);

            //Prueba de libros con varios autores
            Libro l5 = new Libro("ISBN-555", "Harry Potter y la cámara secreta", 2002, 24.90);
            l5.setCategoria(cat1);
            l5.setEditorial(e1);
            l5.addAutor(au1);
            l5.addAutor(au3);

            l1 = libroService.save(l1);
            l2 = libroService.save(l2);
            l3 = libroService.save(l3);
            l4 = libroService.save(l4);
            l5 = libroService.save(l5);

            System.out.println("   - " + l1);
            System.out.println("   - " + l2);
            System.out.println("   - " + l3);
            System.out.println("   - " + l4);
            System.out.println("   - " + l5);
            System.out.println();

            //Verificar que Libro carga autores siempre (carga ansiosa o EAGER)
            System.out.println("5. Verificando que Libro carga autores (EAGER)...");
            Optional<Libro> libroOpt = libroService.findById(l5.getId());
            if (libroOpt.isPresent()) {
                Libro libroRec = libroOpt.get();
                System.out.println("   Libro: " + libroRec.getTitulo());
                System.out.println("   Autores: " + libroRec.getAutores().size());
                libroRec.getAutores().forEach(a -> System.out.println("      - " + a.getNombre() + " " + a.getApellidos()));
            }
            System.out.println();

            //pruebas de búsquedas
            System.out.println("PRUEBA 1: BÚSQUEDAS EN LIBROS");

            System.out.println("6. Buscar libros por año 2000...");
            List<Libro> librosDesde2000 = libroService.buscarDesdeAno(2000);
            librosDesde2000.forEach(x -> System.out.println("   - " + x.getTitulo() + " (" + x.getAnoPublicacion() + ")"));
            System.out.println();

            System.out.println("7. Buscar libro por ISBN 'ISBN-222'...");
            Optional<Libro> isbn222 = libroService.buscarPorIsbn("ISBN-222");
            System.out.println("   - " + isbn222.orElse(null));
            System.out.println();

            System.out.println("8. Buscar libros por nombre de editorial ('reIL' ignore case)...");
            List<Libro> porEditorial = libroService.buscarPorEditorial("reIL");
            porEditorial.forEach(x -> System.out.println("   - " + x.getTitulo() + " (Editorial: " + x.getEditorial().getNombre() + ")"));
            System.out.println();

            System.out.println("9. Buscar libros por nombre de autor ('ste' ignore case)...");
            List<Libro> porAutorNombre = libroService.buscarPorAutor("ste");
            porAutorNombre.forEach(x -> System.out.println("   - " + x.getTitulo()));
            System.out.println();

            System.out.println("10. Buscar libros por id de categoría (Drama)...");
            List<Libro> porCategoriaId = libroService.buscarPorCategoria(cat2.getId());
            porCategoriaId.forEach(x -> System.out.println("   - " + x.getTitulo() + " (Cat: " + x.getCategoria().getNombre() + ")"));
            System.out.println();

            System.out.println("11. Buscar libros por id de autor (au1 = J. K. Rowling)...");
            List<Libro> porAutorId = libroService.buscarPorAutorId(au1.getId());
            porAutorId.forEach(x -> System.out.println("   - " + x.getTitulo()));
            System.out.println();

            //pruebas de búsquedas en autores
            System.out.println("PRUEBA 2: BÚSQUEDAS EN AUTORES");

            System.out.println("12. Traer autores con libros cargados (JPQL JOIN FETCH)...");
            List<Autor> autoresConLibros = autorService.traerTodosConLibros();
            autoresConLibros.forEach(a -> System.out.println("   - " + a.getNombre() + " " + a.getApellidos()
                    + " -> libros=" + a.getLibros().size()));
            System.out.println();

            System.out.println("13. Buscar autores por nombre ('tin')...");
            List<Autor> autoresPorNombre = autorService.buscarPorNombre("tin");
            autoresPorNombre.forEach(a -> System.out.println("   - " + a.getNombre() + " " + a.getApellidos()));
            System.out.println();

            System.out.println("14. Buscar autores nacidos entre 1950 y 1970...");
            List<Autor> autoresFechas = autorService.buscarPorFechas(LocalDate.of(1950, 1, 1), LocalDate.of(1970, 12, 31));
            autoresFechas.forEach(a -> System.out.println("   - " + a.getNombre() + " (" + a.getFechaNacimiento() + ")"));
            System.out.println();

            //pruebas de borrado en cascada
            System.out.println("PRUEBA 3: BORRADO EN CASCADA (Categoría-Libros)");

            long totalAntes = libroService.count();
            System.out.println("15. Total de libros antes de borrar categoría: " + totalAntes);

            System.out.println("16. Eliminando categoría 'Fantasía'...");
            categoriaService.deleteById(cat1.getId());
            System.out.println("   - Categoría eliminada");

            long totalDespues = libroService.count();
            System.out.println("17. Total de libros después de borrar categoría: " + totalDespues);
            System.out.println();

            System.out.println("FIN DE LAS PRUEBAS\n");
        };
    }
}