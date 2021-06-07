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
public class DetallePedidoDAO {
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(int id_estatus, int id_pedido) {
        List<DetallePedido> datos = new ArrayList<>();
        String consulta = "select cantidad, id_producto from detallepedido where id_pedido = ? AND id_estatus = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id_pedido);
            ps.setInt(2, id_estatus);
            rs = ps.executeQuery();
            while (rs.next()) {                
                DetallePedido detallePedido = new DetallePedido();
                detallePedido.setCantidad(rs.getInt(1));
                detallePedido.setId_producto(rs.getInt(2));
                datos.add(detallePedido);
            }
            
        } catch (java.sql.SQLException e) {
            System.out.println("Error listado detalle pedido");
        }
        return datos;
    }
        
    public int generar(List<DetallePedido> detallePedidos, int id_pedido) {
        for (int i = 0; i < detallePedidos.size(); i++) {
            DetallePedido detallePedido = detallePedidos.get(i);
            int cantidadNueva = existeStock(detallePedido);
            if(cantidadNueva >= 0) {
                try {
                    con = conectar.getConnection();
                    String consulta = "insert into detallepedido (cantidad, id_pedido, id_producto, id_estatus) values (?, ?, ?, ?)";
                    ps = con.prepareStatement(consulta);
                    ps.setInt(1, detallePedido.getCantidad());
                    ps.setInt(2, id_pedido);
                    ps.setInt(3, detallePedido.getId_producto());
                    ps.setInt(4, 3);
                    ps.executeUpdate();
                } catch (java.sql.SQLException e) {
                    return -1;
                } 
                restar(detallePedido, id_pedido, cantidadNueva);
            } else {
                generarEntregaYPendiente(detallePedido, id_pedido, cantidadNueva);
            }
        }
        return 1;
    }
    
    
    public int liberar(DetallePedido detallePedido, int id_pedido) {
        try {            
            StockDAO stockDAO = new StockDAO();
            int cantidadActual = stockDAO.cantidadActual(detallePedido.getId_producto());
            stockDAO.actualizar(cantidadActual - detallePedido.getCantidad(), detallePedido.getId_producto());
            
            con = conectar.getConnection();
            String consulta = "update detallepedido set id_estatus = ? where id_producto = ? AND id_pedido = ?";
            ps = con.prepareStatement(consulta);
            ps.setInt(1, 3);
            ps.setInt(2, detallePedido.getId_producto());
            ps.setInt(3, id_pedido);
            
            return ps.executeUpdate();
            
        } catch (java.sql.SQLException e) {
            return -1;
        } 
    }
    
    public int restar(DetallePedido detallePedido, int id_pedido, int cantidadNueva) {
        try {
            StockDAO stockDAO = new StockDAO();
            stockDAO.actualizar(cantidadNueva, detallePedido.getId_producto());
            con = conectar.getConnection();
            String consulta = "update detallepedido set id_estatus = ? where id_producto = ? AND id_pedido = ?";
            ps = con.prepareStatement(consulta);
            ps.setInt(1, 3);
            ps.setInt(2, detallePedido.getId_producto());
            ps.setInt(3, id_pedido);
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        } 
    }
  
    public int generarEntregaYPendiente(DetallePedido detallePedido, int id_pedido, int cantidadNueva) {
        try {
            StockDAO stockDAO = new StockDAO();
            int cantidadActual = stockDAO.cantidadActual(detallePedido.getId_producto());
            String consulta;
            con = conectar.getConnection();
            if(cantidadActual != 0) {
                consulta = "insert into detallepedido (cantidad, id_pedido, id_producto, id_estatus) values (?, ?, ?, ?)";
                ps = con.prepareStatement(consulta);
                ps.setInt(1, cantidadActual);
                ps.setInt(2, id_pedido);
                ps.setInt(3, detallePedido.getId_producto());
                ps.setInt(4, 3);
                ps.executeUpdate();
                stockDAO.actualizar(0, detallePedido.getId_producto());
            }
                
            consulta = "insert into detallepedido (cantidad, id_pedido, id_producto, id_estatus) values (?, ?, ?, ?)";
            ps = con.prepareStatement(consulta);
            ps.setInt(1, Math.abs(cantidadNueva));
            ps.setInt(2, id_pedido);
            ps.setInt(3, detallePedido.getId_producto());
            ps.setInt(4, 4);
            return ps.executeUpdate();
              
        } catch (java.sql.SQLException e) {
            return -1;
        }     
        
    }
    
    
    public int existeStock(DetallePedido detallePedido) {
        StockDAO stockDAO = new StockDAO();
        int cantidadActual = stockDAO.cantidadActual(detallePedido.getId_producto());
        return cantidadActual - detallePedido.getCantidad();      
    }
    
    public int eliminar(int id_pedido, int id_producto) {
        String consulta = "delete from detallepedido where id_pedido = ? AND id_producto = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id_pedido);
            ps.setInt(2, id_producto);
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
}
