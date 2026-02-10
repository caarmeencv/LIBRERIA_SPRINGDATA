package com.traballo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.traballo.service.AutorService;
import com.traballo.service.CategoriaService;
import com.traballo.service.EditorialService;
import com.traballo.service.LibroService;

@SpringBootApplication
public class App {
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner run(AutorService autorService, CategoriaService categoriaService, 
                                 EditorialService editorialService, LibroService libroService) {
        return args -> {
            
        };
    }
}


