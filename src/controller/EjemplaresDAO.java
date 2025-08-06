
package controller;
import bd.ConnectionDB;
import model.Ejemplares;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Sac
 */
public class EjemplaresDAO {
    public List<Object> getAll() {
        List<Object> lista = new ArrayList<>();
        String sql = "SELECT * FROM EJEMPLARES";

        try (Connection con = ConnectionDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Ejemplares(
                        rs.getInt("id_ejemplar"),                        
                        rs.getInt("id_libro"),
                        rs.getInt("piso"),
                        rs.getInt("pasillo"),
                        rs.getInt("existencia")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar Ejemplares: " + e.getMessage());
        }
        return lista;
    }

    public boolean insert(Object obj) {
        Ejemplares d = (Ejemplares) obj;
        String sql = "INSERT INTO ejemplares (id_libro, piso, pasillo, existencia) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, d.getId_Libro());
            pst.setInt(2, d.getPiso());
            pst.setInt(3, d.getPasillo());
            pst.setInt(4, d.getExistencia());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar ejemplar: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM ejemplares WHERE id_ejemplar = ?";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar ejemplar: " + e.getMessage());
            return false;
        }
    }

    public Object getById(int id) {
        Ejemplares d = new Ejemplares();
        String sql = "SELECT * FROM ejemplares WHERE id_ejemplar = ?";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                d.setId_Ejemplar(rs.getInt("id_ejemplar"));
                d.setId_Libro(rs.getInt("id_libro"));
                d.setPiso(rs.getInt("piso"));
                d.setPasillo(rs.getInt("pasillo"));
                d.setExistencia(rs.getInt("existencia"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener ejemplar por ID: " + e.getMessage());
        }

        return d;
    }

    public boolean update(Object obj) {
        Ejemplares d = (Ejemplares) obj;
        String sql = "UPDATE ejemplares SET id_libro = ?, piso = ?, pasillo = ?, existencia = ? WHERE id_ejemplar = ?";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, d.getId_Libro());
            pst.setInt(2, d.getPiso());
            pst.setInt(3, d.getPasillo());
            pst.setInt(4, d.getExistencia());
            pst.setInt(5, d.getId_Ejemplar());
            
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar ejemplar: " + e.getMessage());
            return false;
        }
    }
    
    public Ejemplares getByIdEjemplar(int idEjemplar) {
    Ejemplares d = null;
    try {
        Connection conn = ConnectionDB.getConnection();
        String sql = "SELECT * FROM ejemplares WHERE id_ejemplar = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idEjemplar);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            d = new Ejemplares();
            d.setId_Ejemplar(rs.getInt("id_ejemplar"));
            d.setId_Libro(rs.getInt("id_libro"));
            d.setPiso(rs.getInt("piso"));
            d.setPasillo(rs.getInt("pasillo"));
            d.setExistencia(rs.getInt("Existencia"));
        }
        conn.close();
    } catch (Exception e) {
        System.out.println("Error al obtener ejemplar por ID: " + e.getMessage());
    }
    return d;
}
    
}
