
package controller;

import model.Libros;
import bd.ConnectionDB;

import java.util.List;
import java.util.ArrayList;
import bd.ConnectionDB;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Novitus
 */
public class LibrosDAO {
    
    
   public List<Object> getAll(){
       List<Object> listado = new ArrayList<>();
       //String queryLibros = "SELECT * FROM LIBROS;";
       String queryLibros = "select l.id_libro, l.titulo_libro, l.id_categoria, c.nombre_categoria,l.edicion,l.editorial,l.fecha_publicacion from libros AS l inner join categorias  AS c on l.id_categoria = c.id_categoria ORDER BY L.id_libro ASC;";
       try (Connection con = ConnectionDB.getConnection()){
           Statement stmt = con.createStatement();
           stmt.executeQuery(queryLibros);
           ResultSet lista = stmt.executeQuery(queryLibros);
           while(lista.next()){
                listado.add(new Libros(lista.getInt("id_Libro")
                        ,lista.getString("titulo_Libro")
                        ,lista.getInt("id_Categoria")
                        ,lista.getString("nombre_Categoria")
                        ,lista.getInt("edicion")
                        ,lista.getString("editorial")
                        ,lista.getDate("fecha_Publicacion")));
               
           }
       
       }catch(SQLException ex){
           System.err.println("Error al listar Libros" + ex.getMessage());
       }
       return listado;
   }
   
   public boolean insert(Object object){
       Libros libros = (Libros) object;
       String sql = "INSERT INTO libros (titulo_libro,id_categoria,edicion,editorial,fecha_publicacion) VALUES (?,?,?,?,?);";
       try(Connection con = ConnectionDB.getConnection()){
           PreparedStatement pst = con.prepareStatement(sql);
           pst.setString(1, libros.getTitulo_Libro());
           pst.setInt(2, libros.getId_Categoria());
           pst.setInt(3, libros.getEdicion());
           pst.setString(4, libros.getEditorial());                    
           pst.setDate(5, libros.getFecha_Publicacion());
           
           return pst.executeUpdate() > 0;
           
       }catch(SQLException ex){
           System.err.println("Error al insertar Libro" + ex.getMessage());
           return false;
       }
   }
   
   public boolean update(Object object){
       Libros libros = (Libros) object;
       
       String sql = "UPDATE libros SET titulo_libro = ?, id_categoria = ?, edicion = ?, editorial = ?, fecha_publicacion = ? WHERE id_libro = ?";

       try(Connection con = ConnectionDB.getConnection()){
           PreparedStatement pst = con.prepareStatement(sql);
           
           pst.setString(1, libros.getTitulo_Libro());
            pst.setInt(2, libros.getId_Categoria());
            pst.setInt(3, libros.getEdicion());
            pst.setString(4, libros.getEditorial());
            pst.setDate(5, libros.getFecha_Publicacion());
            pst.setInt(6, libros.getId_Libro());
           
           return pst.executeUpdate() > 0;
           
       }catch(SQLException ex){
           System.err.println("Error al actualizar Libro" + ex.getMessage());
           return false;
       }
   }
   
   public boolean delete(int id_libro){       
       String sql = "DELETE FROM libros WHERE id_libro = ?;";
       try(Connection con = ConnectionDB.getConnection()){
           PreparedStatement pst = con.prepareStatement(sql);           
           pst.setInt(1, id_libro);
           return pst.executeUpdate() > 0;
           
       }catch(SQLException ex){
           System.err.println("Error al eliminar Libro" + ex.getMessage());
           return false;
       }
   }
   
   public Object getById(int id_libro){            
       //String queryLibros = "SELECT * FROM LIBROS WHERE id_libro = ?;";
       String queryLibros = "SELECT l.id_libro, l.titulo_libro, l.id_categoria, c.nombre_categoria, l.edicion, l.editorial, l.fecha_publicacion " +
                         "FROM libros l " +
                         "INNER JOIN categorias c ON l.id_categoria = c.id_categoria " +
                         "WHERE l.id_libro = ?;";
       Libros libro = new Libros();
       try (Connection con = ConnectionDB.getConnection()){
           PreparedStatement pst = con.prepareStatement(queryLibros);
           pst.setInt(1, id_libro);
           
           ResultSet resultado = pst.executeQuery();
           while(resultado.next()){                
               libro.setIdLibro(resultado.getInt("id_Libro"));
               libro.setTituloLibro(resultado.getString("titulo_libro"));
               libro.setId_Categoria(resultado.getInt("id_categoria"));
               libro.setnombre_Categoria(resultado.getString("nombre_categoria"));
               libro.setEdicion(resultado.getInt("edicion"));
            libro.setEditorialLibro(resultado.getString("editorial"));
            libro.setFecha_Publicacion(resultado.getDate("fecha_publicacion"));
           }
       
       }catch(SQLException ex){
           System.err.println("Error al listar Libros" + ex.getMessage());
       }
       return libro;
   }
   
   
   public List<String> obtenerCategoriasCombo() {
    List<String> lista = new ArrayList<>();
    String sql = "SELECT id_categoria, nombre_categoria FROM categorias ORDER BY id_categoria ASC";
    try (Connection con = ConnectionDB.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            int id = rs.getInt("id_categoria");
            String nombre = rs.getString("nombre_categoria");
            lista.add(id + " - " + nombre);  // Formato para mostrar en el combo
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}

   
}
