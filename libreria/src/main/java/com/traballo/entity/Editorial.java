package com.traballo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String pais;

    @OneToMany(mappedBy = "editorial")
    private List<Libro> libros;

    public Editorial() {
    }

    public Editorial(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    public Long getId() { return id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }

    public void setPais(String pais) { this.pais = pais; }

    public List<Libro> getLibros() { return libros; }

    public void setLibros(List<Libro> libros) { this.libros = libros; }

    @Override
    public String toString() {
        return "Editorial{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
