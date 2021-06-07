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
public class ProductoDAO {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
       
    public List listar() {
        List<Producto> datos = new ArrayList<>();
        String consulta = "select * from producto";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Producto producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setClave(rs.getInt(2));
                producto.setNombre(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPrecio(rs.getDouble(5));
                datos.add(producto);
            }
            
        } catch (java.sql.SQLException e) {
            System.out.println("Error listado");
        }
        return datos;
    }
    
    public int agregar(Producto producto) {
        String consulta = "insert into producto (clave, nombre, descripcion, "
                + "precio) values (?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, producto.getClave());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public int actualizar(Producto producto) {
        String consulta = "update producto set clave = ?, nombre = ?, descripcion = ?, "
                + "precio = ? where id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, producto.getClave());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getId());
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public int eliminar(int id) {
        String consulta = "delete from producto where id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public Producto buscar(int id) {
        Producto producto = new Producto();
        String consulta = "select * from producto where id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {                
                producto.setId(rs.getInt(1));
                producto.setClave(rs.getInt(2));
                producto.setNombre(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPrecio(rs.getDouble(5));
            }
            
        } catch (java.sql.SQLException e) {
            System.out.println("Error busqueda");
        }
        return producto;
    }
    
    
    public int buscarID(String producto) {
        int id_producto = -1;
        String consulta = "select producto.id from producto where producto.nombre = (?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, producto);
            rs = ps.executeQuery();
            while (rs.next()) {               
                id_producto = rs.getInt(1);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error");
        }
        return id_producto;
    }
    
}
