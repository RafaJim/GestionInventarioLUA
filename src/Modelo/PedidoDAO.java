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
public class PedidoDAO {
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        List<Pedido> listaPedido = new ArrayList<>();
        String consulta = "select * from pedido";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while(rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt(1));
                pedido.setHoraCreacion(rs.getDate(2));
                pedido.setId_usuario(rs.getInt(3));
                listaPedido.add(pedido);
            }
            
        } catch (java.sql.SQLException e) {
             System.out.println("Error listado");
        }
        return listaPedido;
    }
    
    public int agregar(int id_usuario) {
        String nombreColumna[] = new String[]{"id"};
        int id_pedido = -1;
        String consulta = "insert into pedido (horaCreacion, id_usuario) values (NOW(), ?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta, nombreColumna);
            ps.setInt(1, id_usuario);
            if(ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    id_pedido = rs.getInt(1);
                }
            }
        } catch (java.sql.SQLException e) {
            return -1;
        }
        return id_pedido;
    }
      
}
