package controller;

import bd.ConnectionDB;
import model.Prestamos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public List<Object> getAll() {
    List<Object> listado = new ArrayList<>();
    String query = """
SELECT p.id_prestamo, 
       l.titulo_libro AS nombre_libro, 
       u.nombre_usuario AS nombre_usuario, 
       e.nombre_empleado AS nombre_empleado,
       p.cantidad,
       p.fecha_prestamo,
       p.fecha_limite
FROM prestamos p
INNER JOIN libros l ON p.id_libro = l.id_libro
INNER JOIN usuarios u ON p.id_usuario = u.id_usuario
INNER JOIN empleados e ON p.id_empleado = e.id_empleado;
""" ;

    try (Connection con = ConnectionDB.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            Prestamos p = new Prestamos();
            p.setId_Prestamo(rs.getInt("id_prestamo"));
            p.setTituloLibro(rs.getString("nombre_libro"));
            p.setNombreUsuario(rs.getString("nombre_usuario"));
            p.setNombreEmpleado(rs.getString("nombre_empleado"));
            p.setCantidad(rs.getInt("cantidad"));
            p.setFecha_Prestamo(rs.getDate("fecha_prestamo"));
            p.setFecha_limite(rs.getDate("fecha_limite"));
            listado.add(p);
        }
    } catch (SQLException ex) {
        System.err.println("Error al listar préstamos con join: " + ex.getMessage());
    }

    return listado;
}

    public boolean insert(Object object) {
        Prestamos prestamo = (Prestamos) object;
        String sql = "INSERT INTO prestamos (id_libro, cantidad, id_usuario, id_empleado, fecha_prestamo, fecha_limite) VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection con = ConnectionDB.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, prestamo.getId_Libro());
            pst.setInt(2, prestamo.getCantidad());
            pst.setInt(3, prestamo.getId_Usuario());
            pst.setInt(4, prestamo.getId_Empleado());
            pst.setDate(5, prestamo.getFecha_Prestamo());
            pst.setDate(6, prestamo.getFecha_limite());
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error al insertar préstamo: " + ex.getMessage());
            return false;
        }
    }
    
     public boolean existePrestamo(int idPrestamo) {
        String sql = "SELECT COUNT(*) FROM prestamos WHERE id_prestamo = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPrestamo);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean update(Object object) {
        Prestamos prestamo = (Prestamos) object;
        String sql = "UPDATE prestamos SET id_libro = ?, cantidad = ?, id_usuario = ?, id_empleado = ?, fecha_prestamo = ?, fecha_limite = ? WHERE id_prestamo = ?";
        try (Connection con = ConnectionDB.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, prestamo.getId_Libro());
            pst.setInt(2, prestamo.getCantidad());
            pst.setInt(3, prestamo.getId_Usuario());
            pst.setInt(4, prestamo.getId_Empleado());
            pst.setDate(5, prestamo.getFecha_Prestamo());
            pst.setDate(6, prestamo.getFecha_limite());
            pst.setInt(7, prestamo.getId_Prestamo());
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error al actualizar préstamo: " + ex.getMessage());
            return false;
        }
    }

    public boolean delete(int idPrestamo) {
        String sql = "DELETE FROM prestamos WHERE id_prestamo = ?;";
        try (Connection con = ConnectionDB.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idPrestamo);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error al eliminar préstamo: " + ex.getMessage());
            return false;
        }
    }

    public Prestamos getById(int id) {
    String sql = "SELECT * FROM prestamos WHERE id_prestamo = ?";
    try (Connection conn = ConnectionDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Prestamos p = new Prestamos();
            p.setId_Prestamo(rs.getInt("id_prestamo"));
            p.setId_Libro(rs.getInt("id_libro"));
            p.setCantidad(rs.getInt("cantidad"));
            p.setId_Usuario(rs.getInt("id_usuario"));
            p.setId_Empleado(rs.getInt("id_empleado"));
            p.setFecha_Prestamo(rs.getDate("fecha_prestamo"));
            p.setFecha_limite(rs.getDate("fecha_limite"));
            return p;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
        public boolean tieneDevolucionRelacionada(int idPrestamo) {
    String sql = "SELECT COUNT(*) FROM devolucion WHERE id_prestamo = ?";
    try (Connection con = ConnectionDB.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, idPrestamo);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; // true si hay al menos una devolución
        }
    } catch (SQLException ex) {
        System.err.println("Error al verificar devolución relacionada: " + ex.getMessage());
    }
    return false;
        
        
    }
}