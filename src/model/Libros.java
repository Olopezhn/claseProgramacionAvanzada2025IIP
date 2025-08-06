


package model;

import java.sql.Date;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author Novitus
 */
public class Libros {
    private int id_Libro;
    private String titulo_Libro;
    private int id_Categoria;
    private String nombre_Categoria;
    private int edicion;
    private String editorial;
    private Date fecha_Publicacion;
    
     private static final Logger LOG = Logger.getLogger(Libros.class.getName());

    public Libros(int id_Libro, String titulo_Libro, int id_Categoria, String nombre_Categoria
            , int edicion, String editorial, Date fecha_Publicacion) {
        LOG.info("Se creo un objeto de la clase Libros");
        this.id_Libro = id_Libro;
        this.titulo_Libro = titulo_Libro;
        this.id_Categoria = id_Categoria;
        this.nombre_Categoria = nombre_Categoria;
        this.edicion = edicion;
        this.editorial = editorial;
        this.fecha_Publicacion = fecha_Publicacion;
    }
    
    public Libros(){
}
    
    public int getId_Libro() {
        return id_Libro;
    }

    public void setIdLibro(int id_Libro) {
        this.id_Libro = id_Libro;
    }

    public String getTitulo_Libro() {
        return titulo_Libro;
    }

    public void setTituloLibro(String titulo_Libro) {
        this.titulo_Libro = titulo_Libro;
    }

    public int getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    
    public String getnombre_Categoria() {
        return nombre_Categoria;
    }

    public void setnombre_Categoria(String nombre_Categoria) {
        this.nombre_Categoria = nombre_Categoria;
    }
    
    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorialLibro(String editorial) {
        this.editorial = editorial;
    }

    public Date getFecha_Publicacion() {
        return fecha_Publicacion;
    }

    public void setFecha_Publicacion(Date fecha_Publicacion) {
        this.fecha_Publicacion = fecha_Publicacion;
    }
    
    
      @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id_Libro;
        hash = 61 * hash + Objects.hashCode(this.titulo_Libro);
        hash = 61 * hash + this.id_Categoria;
        hash = 61 * hash + Objects.hashCode(this.nombre_Categoria);
        hash = 61 * hash + this.edicion;
        hash = 61 * hash + Objects.hashCode(this.editorial);
        hash = 61 * hash + Objects.hashCode(this.fecha_Publicacion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libros other = (Libros) obj;
        if (this.id_Libro != other.id_Libro) {
            return false;
        }
        if (this.id_Categoria != other.id_Categoria) {
            return false;
        }
        if (this.edicion != other.edicion) {
            return false;
        }
        if (!Objects.equals(this.titulo_Libro, other.titulo_Libro)) {
            return false;
        }
        if (!Objects.equals(this.nombre_Categoria, other.nombre_Categoria)) {
            return false;
        }
        
        if (!Objects.equals(this.editorial, other.editorial)) {
            return false;
        }
        return Objects.equals(this.fecha_Publicacion, other.fecha_Publicacion);
    }
   
    
}
