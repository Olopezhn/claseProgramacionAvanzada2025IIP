package model;

import java.util.logging.Logger;

/**
 * Modelo para la tabla Autores.
 * id_autor: clave primaria
 * nombre_autor: nombre del autor (hasta 100 caracteres)
 * telefono: número de teléfono (8 caracteres)
 * 
 * @author Ethan
 */
public class Autores {
    private int id_autor;
    private String nombre_autor;
    private String telefono;

    private static final Logger LOG = Logger.getLogger(Autores.class.getName());

    // Constructor completo
    public Autores(int id_autor, String nombre_autor, String telefono) {
        LOG.info("Se creó un objeto de la clase Autores");
        this.id_autor = id_autor;
        this.nombre_autor = nombre_autor;
        this.telefono = telefono;
    }

    // Constructor vacío
    public Autores() {}

    // Getters y Setters
    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNombre_autor() {
        return nombre_autor;
    }

    public void setNombre_autor(String nombre_autor) {
        this.nombre_autor = nombre_autor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
