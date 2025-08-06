
package controller;

import model.Usuarios;
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
public class UsuariosDAO {
    
    
   public List<Object> getAll(){
       List<Object> listado = new ArrayList<>();
       //String queryUsuarios = "SELECT * FROM USUARIOS;";
       
       String queryUsuarios = "SELECT u.id_usuario, u.dni_usuario, u.nombre_usuario, "
        + "u.tipo_usuario AS id_tipo_usuario, tu.tipo_usuario AS nombre_tipo_usuario, "
        + "u.correo, u.telefono "
        + "FROM usuarios AS u "
        + "INNER JOIN tipo_usuario AS tu ON u.tipo_usuario = tu.id_tipo_usuario "
        + "ORDER BY u.id_usuario ASC;";
       
       try (Connection con = ConnectionDB.getConnection()){
           Statement stmt = con.createStatement();
           stmt.executeQuery(queryUsuarios);
           ResultSet lista = stmt.executeQuery(queryUsuarios);
           while(lista.next()){
               listado.add(new Usuarios(lista.getInt("id_Usuario")
                       ,lista.getString("dni_Usuario")
                       ,lista.getString("nombre_Usuario")
                       ,lista.getInt("id_tipo_Usuario")
                       ,lista.getString("nombre_tipo_Usuario")
                       ,lista.getString("correo")
                       ,lista.getString("telefono")));
             
           }
       
       }catch(SQLException ex){
           System.err.println("Error al listar Libros" + ex.getMessage());
       }
       return listado;
   }
   
   public boolean insert(Object object){
       Usuarios usuarios = (Usuarios) object;
       String sql = "insert INTO usuarios (dni_usuario,nombre_usuario,tipo_usuario,correo,telefono) VALUES (?,?,?,?,?);";
       try(Connection con = ConnectionDB.getConnection()){
           PreparedStatement pst = con.prepareStatement(sql);
           
           pst.setString(1, usuarios.getdni_Usuario());
           pst.setString(2, usuarios.getnombre_Usuario());
           pst.setInt(3, usuarios.gettipo_Usuario());
           pst.setString(4, usuarios.getcorreo());                    
           pst.setString(5, usuarios.gettelefono());                    
           
           
           return pst.executeUpdate() > 0;
           
       }catch(SQLException ex){
           System.err.println("Error al insertar Usuario " + ex.getMessage());
           return false;
       }
   }
   
   public boolean update(Object object){
       Usuarios usuarios = (Usuarios) object;
       
       String sql = "UPDATE usuarios SET dni_usuario = ?, nombre_usuario = ?, tipo_usuario = ?, correo = ?, telefono = ? WHERE id_usuario = ?";

       try(Connection con = ConnectionDB.getConnection()){
           PreparedStatement pst = con.prepareStatement(sql);
           
            pst.setString(1, usuarios.getdni_Usuario());
            pst.setString(2, usuarios.getnombre_Usuario());
            pst.setInt(3, usuarios.gettipo_Usuario());            
            pst.setString(4, usuarios.getcorreo());
            pst.setString(5, usuarios.gettelefono());
            pst.setInt(6, usuarios.getId_Usuario());
           
           return pst.executeUpdate() > 0;
           
       }catch(SQLException ex){
           System.err.println("Error al actualizar Usuario" + ex.getMessage());
           return false;
       }
   }
   
   public boolean delete(int id_usuario){       
       String sql = "DELETE FROM usuarios WHERE id_usuario = ?;";
       try(Connection con = ConnectionDB.getConnection()){
           PreparedStatement pst = con.prepareStatement(sql);           
           pst.setInt(1, id_usuario);
           return pst.executeUpdate() > 0;
           
       }catch(SQLException ex){
           System.err.println("Error al eliminar Usario" + ex.getMessage());
           return false;
       }
   }
   
   public Object getById(int id_Usuario){            
       //String queryLibros = "SELECT * FROM USUARIOS WHERE id_usuario = ?;";
       
       String queryUsuarios = "SELECT u.id_usuario, u.dni_usuario, u.nombre_usuario, "
        + "u.tipo_usuario AS id_tipo_usuario, tu.tipo_usuario AS nombre_tipo_usuario, "
        + "u.correo, u.telefono "
        + "FROM usuarios AS u "
        + "INNER JOIN tipo_usuario AS tu ON u.tipo_usuario = tu.id_tipo_usuario "
        + "WHERE u.id_usuario = ?;";
       
       Usuarios usuario = new Usuarios();
       try (Connection con = ConnectionDB.getConnection()){
           PreparedStatement pst = con.prepareStatement(queryUsuarios);
           pst.setInt(1, id_Usuario);
           
           ResultSet resultado = pst.executeQuery();
           while(resultado.next()){                
               usuario.setIdUsuario(resultado.getInt("id_Usuario"));
               usuario.setdniUsuario(resultado.getString("dni_Usuario"));
               usuario.setnombreUsuario(resultado.getString("nombre_Usuario"));               
               usuario.settipo_Usuario(resultado.getInt("id_tipo_Usuario"));    
               usuario.setntipo_Usuario(resultado.getString("nombre_tipo_Usuario")); 
               usuario.setcorreo(resultado.getString("correo"));
               usuario.settelefono(resultado.getString("telefono"));
           }
       
       }catch(SQLException ex){
           System.err.println("Error al listar Usuarios" + ex.getMessage());
       }
       return usuario;
   }
   
}
