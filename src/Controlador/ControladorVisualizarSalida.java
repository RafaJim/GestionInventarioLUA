package Controlador;

import Modelo.Pedido;
import Modelo.Usuario;
import Modelo.DetallePedido;
import Modelo.PedidoDAO;
import Modelo.DetallePedidoDAO;
import Modelo.UsuarioDAO;
import Modelo.ProductoDAO;
import Vista.VisualizarSalida;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class ControladorVisualizarSalida {
    VisualizarSalida visualizarPedido = new VisualizarSalida();
    PedidoDAO pedidoDAO = new PedidoDAO();
    DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAO();
    ProductoDAO productoDAO = new ProductoDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    DefaultTableModel tablaModelo = new DefaultTableModel();
    
    public ControladorVisualizarSalida(VisualizarSalida visualizarPedido) {
        this.visualizarPedido = visualizarPedido;
        listar(visualizarPedido.tablaPedido);
    }
    
    public void listar(JTable tabla) {
        tablaModelo = (DefaultTableModel) tabla.getModel();
        visualizarPedido.tablaPedido.setModel(tablaModelo);
        List<Pedido> listaPedidos = pedidoDAO.listar();
        for (int i = 0; i < listaPedidos.size(); i++) {
            Pedido pedido = listaPedidos.get(i);
            int id_pedido = pedido.getId();
            Date horaCreacion = pedido.getHoraCreacion();
            Usuario usuario = usuarioDAO.buscar(pedido.getId_usuario());
            Object[] object = new Object[7];
            List<DetallePedido> listaDetallePedidos = detallePedidoDAO.listar(3, id_pedido);
            for (int j = 0; j < listaDetallePedidos.size(); j++) {
                object[0] = usuario.getNombreUsuario();
                object[1] = usuario.getDepartamento().getNombre();
                object[2] = id_pedido;
                object[3] = horaCreacion.toString();
                object[4] = listaDetallePedidos.get(j).getCantidad();
                object[5] = productoDAO.buscar(listaDetallePedidos.get(j).getId_producto()).getNombre();
                object[6] = "entregado";
                tablaModelo.addRow(object);
            }
            visualizarPedido.tablaPedido.setModel(tablaModelo);
        }
    }
    
}
