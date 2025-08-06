
package controller;
import bd.ConnectionDB;
import model.Devolucion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Sac
 */
public class DevolucionDAO {
    public List<Object> getAll() {
        List<Object> lista = new ArrayList<>();
        String sql = "SELECT * FROM devolucion";

        try (Connection con = ConnectionDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Devolucion(
                        rs.getInt("id_devolucion"),
                        rs.getInt("id_prestamo"),
                        rs.getInt("id_libro"),
                        rs.getDate("fecha_devolucion")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar devoluciones: " + e.getMessage());
        }
        return lista;
    }

    public boolean insert(Object obj) {
        Devolucion d = (Devolucion) obj;
        String sql = "INSERT INTO devolucion (id_prestamo, id_libro, fecha_devolucion) VALUES (?, ?, ?)";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, d.getId_prestamo());
            pst.setInt(2, d.getId_libro());
            pst.setDate(3, d.getFecha_devolucion());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar devolución: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM devolucion WHERE id_devolucion = ?";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar devolución: " + e.getMessage());
            return false;
        }
    }

    public Object getById(int id) {
        Devolucion d = new Devolucion();
        String sql = "SELECT * FROM devolucion WHERE id_devolucion = ?";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                d.setId_devolucion(rs.getInt("id_devolucion"));
                d.setId_prestamo(rs.getInt("id_prestamo"));
                d.setId_libro(rs.getInt("id_libro"));
                d.setFecha_devolucion(rs.getDate("fecha_devolucion"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener devolución por ID: " + e.getMessage());
        }

        return d;
    }

    public boolean update(Object obj) {
        Devolucion d = (Devolucion) obj;
        String sql = "UPDATE devolucion SET id_prestamo = ?, id_libro = ?, fecha_devolucion = ? WHERE id_devolucion = ?";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, d.getId_prestamo());
            pst.setInt(2, d.getId_libro());
            pst.setDate(3, d.getFecha_devolucion());
            pst.setInt(4, d.getId_devolucion());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar devolución: " + e.getMessage());
            return false;
        }
    }
    
    public Devolucion getByIdPrestamo(int idPrestamo) {
    Devolucion d = null;
    try {
        Connection conn = ConnectionDB.getConnection();
        String sql = "SELECT * FROM devolucion WHERE id_prestamo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPrestamo);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            d = new Devolucion();
            d.setId_devolucion(rs.getInt("id_devolucion"));
            d.setId_prestamo(rs.getInt("id_prestamo"));
            d.setId_libro(rs.getInt("id_libro"));
            d.setFecha_devolucion(rs.getDate("fecha_devolucion"));
        }
        conn.close();
    } catch (Exception e) {
        System.out.println("Error al obtener devolución por ID préstamo: " + e.getMessage());
    }
    return d;
}
    
}
