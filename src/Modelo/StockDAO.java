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
public class StockDAO {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;    
    
    public List listar() {
        List<Stock> datos = new ArrayList<>();
        String consulta = "select stock.id, stock.cantidadActual, stock.cantidadMinima, producto.nombre from stock, producto "
                + "where stock.id_producto = producto.id";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Stock stock = new Stock();
                stock.setId(rs.getInt(1));
                stock.setCantidadActual(rs.getInt(2));
                stock.setCantidadMinima(rs.getInt(3));
                stock.setProducto(new Producto(rs.getString(4)));
                datos.add(stock);
            }
            
        } catch (java.sql.SQLException e) {
            System.out.println("Error listado");
        }
        return datos;
    }
    
    public int agregar(Stock stock) {
        String consulta = "insert into stock (cantidadActual, cantidadMinima, "
                + "id_producto) values (?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, stock.getCantidadActual());
            ps.setInt(2, stock.getCantidadMinima());
            ps.setInt(3, stock.getProducto().getId());
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public int actualizar(Stock stock) {
        String consulta = "update stock set cantidadActual=?, cantidadMinima=?, "
                + "id_producto=? where id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, stock.getCantidadActual());
            ps.setInt(2, stock.getCantidadMinima());
            ps.setInt(3, stock.getProducto().getId());
            ps.setInt(4, stock.getId());
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public int eliminar(int id) {
        String consulta = "delete from stock where id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public int cantidadActual(int id_producto) {
        String consulta = "select cantidadActual from stock where id_producto = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id_producto);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public int actualizar(int cantidad, int id_producto) {
        try {
            con = conectar.getConnection();
            String consulta = "update stock set cantidadActual = ? where id_producto = ?";
            ps = con.prepareStatement(consulta);
            ps.setInt(1, cantidad);
            ps.setInt(2, id_producto);
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public Stock buscar(int id_producto) {
        String consulta = "select * from stock where id_producto = ?";
        Stock stock = new Stock();
        ProductoDAO productoDAO = new ProductoDAO();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id_producto);
            rs = ps.executeQuery();
            while(rs.next()) {
                stock.setId(rs.getInt(1));
                stock.setCantidadActual(rs.getInt(2));
                stock.setCantidadMinima(rs.getInt(3));
                stock.setProducto(productoDAO.buscar(rs.getInt(4)));
            }
            
        } catch (java.sql.SQLException e) {
            return null;
        }
        return stock;
    }
    
    
    public boolean enStock(int id_producto) {
        String consulta = "select * from stock where id_producto = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id_producto);
            return ps.executeQuery().next();
        } catch (java.sql.SQLException e) {
            return false;
        }
    }
    
    public List bajoStock() {
        List<Stock> listaStock = new ArrayList<>();
        String consulta = "select * from stock where cantidadActual <= cantidadMinima";
        ProductoDAO productoDAO = new ProductoDAO();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setId(rs.getInt(1));
                stock.setCantidadActual(rs.getInt(2));
                stock.setCantidadMinima(rs.getInt(3));
                stock.setProducto(productoDAO.buscar(rs.getInt(4)));
                listaStock.add(stock);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error busqueda bajo stock");
        }
        return listaStock;
    }
    
}
