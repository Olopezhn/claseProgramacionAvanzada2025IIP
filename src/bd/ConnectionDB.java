package bd;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Novitus
 */
public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/dbbiblioteca";
    private static final String USUARIO = "empresa_app";
    private static final String PASSWORD = "E12345678*";
    
    private static HikariDataSource dataSource;
    
    static{
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(URL);
            config.setUsername(USUARIO);
            config.setPassword(PASSWORD);            
            config.setMaximumPoolSize(10); //Maximo conexiones simultaneas
            config.setMinimumIdle(5);         //Minimo de conexiones en espera
            config.setIdleTimeout(3000); //Timpo de inactividad antes de colocar la conexion en espera
            config.setMaxLifetime(20000); // Timepo de vida maximo de una conexion
            config.setConnectionTimeout(10000);
            
            dataSource = new HikariDataSource(config);
           }
    
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
    
    public static void closePool(){
        if(dataSource != null){
            dataSource.close();
        }
    }
}
