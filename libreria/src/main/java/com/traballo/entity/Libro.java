package com.traballo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ISBN;
    private String titulo;
    private int anoPublicacion;
    private float precio;

    // MANY TO ONE -> Categoria
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // MANY TO ONE -> Editorial
    @ManyToOne
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    // MANY TO MANY -> Autor
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    public Libro() {
    }

    public Libro(String ISBN, String titulo, int anoPublicacion, float precio) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.anoPublicacion = anoPublicacion;
        this.precio = precio;
    }

    public Long getId() { return id; }

    public String getISBN() { return ISBN; }

    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getAnoPublicacion() { return anoPublicacion; }

    public void setAnoPublicacion(int anoPublicacion) { this.anoPublicacion = anoPublicacion; }

    public float getPrecio() { return precio; }

    public void setPrecio(float precio) { this.precio = precio; }

    public Categoria getCategoria() { return categoria; }

    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Editorial getEditorial() { return editorial; }

    public void setEditorial(Editorial editorial) { this.editorial = editorial; }

    public List<Autor> getAutores() { return autores; }

    public void setAutores(List<Autor> autores) { this.autores = autores; }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anoPublicacion=" + anoPublicacion +
                ", precio=" + precio +
                '}';
    }
}
