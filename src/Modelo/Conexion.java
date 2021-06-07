package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author daniel
 */
public class Conexion {
    
    Connection con;
    
    public Connection getConnection(){
        String url = "jdbc:mysql://localhost/DBProyecto";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("No es posible conectarse con la base de datos");
        }
        return con;
    }
    
}
