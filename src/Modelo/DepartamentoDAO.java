package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
public class DepartamentoDAO {
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        List<String> datos = new ArrayList<>();
        String consulta = "select nombre from departamento";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {               
                datos.add(rs.getString(1));
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error");
        }
        return datos;
    }
    
    public int buscar(String departamento) {
        int id_departamento = -1;
        String consulta = "select departamento.id from departamento where departamento.nombre = (?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, departamento);
            rs = ps.executeQuery();
            while (rs.next()) {               
                id_departamento = rs.getInt(1);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error");
        }
        return id_departamento;
    }
    
    public String buscar(int id_departamento) {
        String nombreDepartamento = null;
        String consulta = "select nombre from departamento where id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id_departamento);
            rs = ps.executeQuery();
            while (rs.next()) {               
                nombreDepartamento = rs.getString(1);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error");
        }
        return nombreDepartamento;
    }
        
}
