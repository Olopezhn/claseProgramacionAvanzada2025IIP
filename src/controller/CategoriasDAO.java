package controller;

import bd.ConnectionDB;
import model.Categorias;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDAO {

    public List<Categorias> getAll() {
        List<Categorias> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias ORDER BY id_categoria";
        try (Connection con = ConnectionDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(new Categorias(
                    rs.getInt("id_categoria"),
                    rs.getString("nombre_categoria")
                    
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar categorias: " + e.getMessage());
        }
        return lista;
    }

    public boolean insert(Object obj) {
        Categorias a = (Categorias) obj;
        String sql = "INSERT INTO categorias (nombre_categoria) VALUES (?)";
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, a.getNombre_categoria());
            

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar categoria: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Object obj) {
        Categorias a = (Categorias) obj;
        String sql = "UPDATE categorias SET nombre_categoria = ? WHERE id_categoria = ?";
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, a.getNombre_categoria());            
            pst.setInt(2, a.getId_categoria());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar categoria: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id_categoria) {
        String sql = "DELETE FROM categorias WHERE id_categoria = ?";
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, id_categoria);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar autor: " + e.getMessage());
            return false;
        }
    }

    public Categorias getById(int id_categoria) {
        Categorias categoria = new Categorias();
        String sql = "SELECT * FROM categorias WHERE id_categoria = ?";
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, id_categoria);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setNombre_categoria(rs.getString("nombre_categoria"));
                
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener categoria por ID: " + e.getMessage());
        }
        return categoria;
    }
}
