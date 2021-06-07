
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
public class ProductoDepartamentoDAO {
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(int id_departamento) {
        List<Producto> datos = new ArrayList<>();
        List<Integer> idProductos = new ArrayList<>();
        String consultaID = "select id_producto from producto_departamento where id_departamento = ?";
        String consultaProductos = "select clave, nombre, descripcion, precio from producto where id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consultaID);
            ps.setInt(1, id_departamento);
            rs = ps.executeQuery();
            while (rs.next()) {                
                idProductos.add(rs.getInt(1));
            }
            for (int i = 0; i < idProductos.size(); i++) {
                int idProducto = idProductos.get(i);
                ps = con.prepareStatement(consultaProductos);
                ps.setInt(1, idProducto);
                rs = ps.executeQuery();
                while (rs.next()) {                
                    Producto producto = new Producto();
                    producto.setId(idProducto);
                    producto.setClave(rs.getInt(1));
                    producto.setNombre(rs.getString(2));
                    producto.setDescripcion(rs.getString(3));
                    producto.setPrecio(rs.getDouble(4));
                    datos.add(producto);
                }
            }
            
        } catch (java.sql.SQLException e) {
            System.out.println("Error listado");
        }
        return datos;
    }
    
    public boolean existe(int id_departamento, int id_producto) {
        String consulta = "select id from producto_departamento where id_departamento = ? AND id_producto = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id_departamento);
            ps.setInt(2, id_producto);
            rs= ps.executeQuery();
            return rs.next();
        } catch (java.sql.SQLException e) {
            System.out.println("Error existencia");;
        }
        return false;
    }
    
    public int agregar(int id_departamento, int id_producto) {
        String consulta = "insert into producto_departamento (id_departamento, id_producto) values (?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id_departamento);
            ps.setInt(2, id_producto);
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public int eliminar(int id) {
        String consulta = "delete from producto_departamento where id_producto = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    
}
