
package model;

import java.sql.Date;
import java.util.logging.Logger;

public class Prestamos {

    private int id_Prestamo;
    private int id_Libro;
    private int cantidad;
    private int id_Usuario;
    private int id_Empleado;
    private Date fecha_Prestamo;
    private Date fecha_Limite;
    private String tituloLibro;
    private String nombreUsuario;
    private String nombreEmpleado;

    private static final Logger LOG = Logger.getLogger(Prestamos.class.getName());

    public Prestamos(int id_Prestamo, int id_Libro, int cantidad,
                     int id_Usuario, int id_Empleados,
                     Date fecha_Prestamo, Date fecha_Limite) {
        LOG.info("Se creó un objeto de la clase Prestamos");
        this.id_Prestamo = id_Prestamo;
        this.id_Libro = id_Libro;
        this.cantidad = cantidad;
        this.id_Usuario = id_Usuario;
        this.id_Empleado = id_Empleados;
        this.fecha_Prestamo = fecha_Prestamo;
        this.fecha_Limite = fecha_Limite;
    }

    public Prestamos() {
    }

    public int getId_Prestamo() {
        return id_Prestamo;
    }

    public void setId_Prestamo(int id_Prestamo) {
        this.id_Prestamo = id_Prestamo;
    }

    public int getId_Libro() {
        return id_Libro;
    }

    public void setId_Libro(int id_Libro) {
        this.id_Libro = id_Libro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public int getId_Empleado() {
        return id_Empleado;
    }

    public void setId_Empleado(int id_Empleado) {
        this.id_Empleado = id_Empleado;
    }

    public Date getFecha_Prestamo() {
        return fecha_Prestamo;
    }

    public void setFecha_Prestamo(Date fecha_Prestamo) {
        this.fecha_Prestamo = fecha_Prestamo;
    }

    public Date getFecha_limite() {
        return fecha_Limite;
    }

    public void setFecha_limite(Date fecha_limite) {
        this.fecha_Limite = fecha_limite;
    }
    
    public String getTituloLibro() {
    return tituloLibro;
}

public void setTituloLibro(String nombre_libro) {
    this.tituloLibro = nombre_libro;
}

public String getNombreUsuario() {
    return nombreUsuario;
}

public void setNombreUsuario(String nombre_usuario) {
    this.nombreUsuario = nombre_usuario;
}

public String getNombreEmpleado() {
    return nombreEmpleado;
}

public void setNombreEmpleado(String nombre_empleado) {
    this.nombreEmpleado = nombre_empleado;
}

}