

package model;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author Novitus
 */
public class Usuarios {
    private int id_Usuario;
    private String dni_Usuario;
    private String nombre_Usuario;
    private int tipo_Usuario;
    private String ntipo_Usuario;
    private String correo;
    private String telefono;
    
    
     private static final Logger LOG = Logger.getLogger(Usuarios.class.getName());

    public Usuarios(int id_Usuario, String dni_Usuario, String nombre_Usuario,
            int tipo_Usuario, String ntipo_Usuario, String correo,  String telefono) {
        LOG.info("Se creo un objeto de la clase Usuarios");
        this.id_Usuario = id_Usuario;
        this.dni_Usuario = dni_Usuario;
        this.nombre_Usuario = nombre_Usuario;
        this.tipo_Usuario = tipo_Usuario;
        this.ntipo_Usuario = ntipo_Usuario;
        this.correo = correo;
        this.telefono = telefono;
        
    }
    
    public Usuarios(){
}
    
    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setIdUsuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getdni_Usuario() {
        return dni_Usuario;
    }

    public void setdniUsuario(String dni_Usuario) {
        this.dni_Usuario = dni_Usuario;
    }
        
    
    public String getnombre_Usuario() {
        return nombre_Usuario;
    }

    public void setnombreUsuario(String nombre_Usuario) {
        this.nombre_Usuario = nombre_Usuario;
    }

    public int gettipo_Usuario() {
        return tipo_Usuario;
    }

    public void settipo_Usuario(int tipo_Usuario) {
        this.tipo_Usuario = tipo_Usuario;
    }
//=========
    
    public String getntipo_Usuario() {
        return ntipo_Usuario;
    }

    public void setntipo_Usuario(String ntipo_Usuario) {
        this.ntipo_Usuario = ntipo_Usuario;
    }
    
//========    
    public String getcorreo() {
        return correo;
    }

    public void setcorreo(String correo) {
        this.correo = correo;
    }

    public String gettelefono() {
        return telefono;
    }

    public void settelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
      @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id_Usuario;
        hash = 61 * hash + Objects.hashCode(this.dni_Usuario);
        hash = 61 * hash + Objects.hashCode(this.nombre_Usuario);
        hash = 61 * hash + this.tipo_Usuario;
        hash = 61 * hash + Objects.hashCode(this.ntipo_Usuario);
        hash = 61 * hash + Objects.hashCode(this.correo);
        hash = 61 * hash + Objects.hashCode(this.telefono);
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
        final Usuarios other = (Usuarios) obj;
        if (this.id_Usuario != other.id_Usuario) {
            return false;
        }
        if (!Objects.equals(this.dni_Usuario, other.dni_Usuario)) {
            return false;
        }       
        if (!Objects.equals(this.nombre_Usuario, other.nombre_Usuario)) {
            return false;
        }
        if (this.tipo_Usuario != other.tipo_Usuario) {
            return false;
        }
        
        if (!Objects.equals(this.ntipo_Usuario, other.ntipo_Usuario)) {
            return false;
        }
        
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        
        return Objects.equals(this.telefono, other.telefono);
    }
   
    
}
