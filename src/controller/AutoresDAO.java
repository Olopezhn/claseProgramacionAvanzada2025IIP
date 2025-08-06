package controller;

import bd.ConnectionDB;
import model.Autores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoresDAO {

    public List<Autores> getAll() {
        List<Autores> lista = new ArrayList<>();
        String sql = "SELECT * FROM autores ORDER BY id_autor";
        try (Connection con = ConnectionDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(new Autores(
                    rs.getInt("id_autor"),
                    rs.getString("nombre_autor"),
                    rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar autores: " + e.getMessage());
        }
        return lista;
    }

    public boolean insert(Object obj) {
        Autores a = (Autores) obj;
        String sql = "INSERT INTO autores (nombre_autor, telefono) VALUES (?, ?)";
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, a.getNombre_autor());
            pst.setString(2, a.getTelefono());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar autor: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Object obj) {
        Autores a = (Autores) obj;
        String sql = "UPDATE autores SET nombre_autor = ?, telefono = ? WHERE id_autor = ?";
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, a.getNombre_autor());
            pst.setString(2, a.getTelefono());
            pst.setInt(3, a.getId_autor());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar autor: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id_autor) {
        String sql = "DELETE FROM autores WHERE id_autor = ?";
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, id_autor);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar autor: " + e.getMessage());
            return false;
        }
    }

    public Autores getById(int id_autor) {
        Autores autor = new Autores();
        String sql = "SELECT * FROM autores WHERE id_autor = ?";
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, id_autor);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                autor.setId_autor(rs.getInt("id_autor"));
                autor.setNombre_autor(rs.getString("nombre_autor"));
                autor.setTelefono(rs.getString("telefono"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener autor por ID: " + e.getMessage());
        }
        return autor;
    }
}
