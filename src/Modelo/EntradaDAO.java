package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
public class EntradaDAO {
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        List<Entrada> entradas = new ArrayList<>();
        String consulta = "select * from entrada";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while(rs.next()) {
                Entrada entrada = new Entrada();
                entrada.setId(rs.getInt(1));
                entrada.setCantidad(rs.getInt(2));
                entrada.setFecha(rs.getDate(3));
                entrada.setId_producto(rs.getInt(4));
                entradas.add(entrada);
            }
            
        } catch (java.sql.SQLException e) {
             System.out.println("Error listado");
        }
        return entradas;
    }
    
    public int agregar(Entrada entrada) {
        String consulta = "insert into entrada (cantidad, id_producto, fecha) values (?,?,CURDATE())";
        try {
            if(enStock(entrada.getId_producto())) {
                con = conectar.getConnection();
                if(sumar(entrada) == 1) {
                    ps = con.prepareStatement(consulta);
                    ps.setInt(1, entrada.getCantidad());
                    ps.setInt(2, entrada.getId_producto());
                    return ps.executeUpdate();
                }
            } else {
                return 2;
            }
        } catch (java.sql.SQLException e) {
            return -1;
        }
        return -1;
    }
 
    public int sumar(Entrada entrada) {
        try {
            String consulta = "select * from stock where id_producto = ?";
            int cantidadActual = 0;
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, entrada.id_producto);
            rs = ps.executeQuery();
            if(rs.next()) {
                cantidadActual = rs.getInt("cantidadActual");
            }
            int cantidad = entrada.cantidad + cantidadActual;
            consulta = "update stock set cantidadActual = ? where id_producto = ?";
            ps = con.prepareStatement(consulta);
            ps.setInt(1, cantidad);
            ps.setInt(2, entrada.id_producto);
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public boolean enStock(int id_producto) {
        String consulta = "select * from stock where id_producto = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id_producto);
            rs = ps.executeQuery();
            return rs.next();
        } catch (java.sql.SQLException e) {
            return false;
        }
    }
    
    public List entradasMes(int mes, int ano) {
        List<Entrada> entradas = new ArrayList<>();
        String consulta = "select * from entrada where MONTH(fecha) = ? AND YEAR(fecha) = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, mes);
            ps.setInt(2, ano);
            rs = ps.executeQuery();
            while (rs.next()) {
                Entrada entrada = new Entrada();
                entrada.setId(rs.getInt(1));
                entrada.setCantidad(rs.getInt(2));
                entrada.setFecha(rs.getDate(3));
                entrada.setId_producto(rs.getInt(4));
                entradas.add(entrada);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error listar entradas del mes");
        }
        return entradas;
    }
    
}
