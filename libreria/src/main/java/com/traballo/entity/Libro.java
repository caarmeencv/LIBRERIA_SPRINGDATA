package com.traballo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ISBN;
    private String titulo;
    private int AnoPublicacion;
    private float precio;

    // Constructores
    public Libro() {
    }

    public Libro(String ISBN, String titulo, int AnoPublicacion, float precio) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.AnoPublicacion = AnoPublicacion;
        this.precio = precio;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPublicacion() {
        return AnoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        AnoPublicacion = anoPublicacion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", titulo='" + titulo + '\'' +
                ", AnoPublicacion=" + AnoPublicacion +
                ", precio=" + precio +
                '}';
    }
}

