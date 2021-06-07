/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DetallePedido;
import Modelo.DetallePedidoDAO;
import Modelo.Pedido;
import Modelo.PedidoDAO;
import Modelo.ProductoDAO;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.PedidoPendiente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class ControladorPedidoPendiente implements ActionListener {
    PedidoPendiente pedidoPendiente = new PedidoPendiente();
    PedidoDAO pedidoDAO = new PedidoDAO();
    DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAO();
    ProductoDAO productoDAO = new ProductoDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    DefaultTableModel tablaModelo = new DefaultTableModel();
    
    public ControladorPedidoPendiente(PedidoPendiente pedidoPendiente) {
        this.pedidoPendiente = pedidoPendiente;
        this.pedidoPendiente.btnLiberar.addActionListener(this);
        this.pedidoPendiente.btnEliminar.addActionListener(this);
        listar(pedidoPendiente.tablaPedido);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == pedidoPendiente.btnLiberar) {
            liberar();
            borrarFilas(tablaModelo);
            listar(pedidoPendiente.tablaPedido);
        }
        if(e.getSource() == pedidoPendiente.btnEliminar) {
            eliminar();
            borrarFilas(tablaModelo);
            listar(pedidoPendiente.tablaPedido);
        }
    }
    
    public void liberar() {
        int fila = pedidoPendiente.tablaPedido.getSelectedRow();
        if(fila == -1) {
            JOptionPane.showMessageDialog(pedidoPendiente, "Debe seleccionar una fila");
        } else {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setCantidad(Integer.parseInt(pedidoPendiente.tablaPedido.getValueAt(fila, 4).toString()));
            int id_pedido = Integer.parseInt(pedidoPendiente.tablaPedido.getValueAt(fila, 2).toString());
            detallePedido.setId_producto(productoDAO.buscarID(pedidoPendiente.tablaPedido.getValueAt(fila, 5).toString()));
            if(detallePedidoDAO.existeStock(detallePedido) >= 0) {
                detallePedidoDAO.liberar(detallePedido, id_pedido);
                JOptionPane.showMessageDialog(pedidoPendiente, "Pedido liberado correctamente");
            } else {
                JOptionPane.showMessageDialog(pedidoPendiente, "No es posible liberar el pedido, aun no hay stock");
            }
        }
    }
    
    public void eliminar() {
        int fila = pedidoPendiente.tablaPedido.getSelectedRow();
        if(fila == -1) {
            JOptionPane.showMessageDialog(pedidoPendiente, "Debe seleccionar una fila");
        } else {
            int id_pedido = Integer.parseInt(pedidoPendiente.tablaPedido.getValueAt(fila, 2).toString());
            int id_producto = productoDAO.buscarID(pedidoPendiente.tablaPedido.getValueAt(fila, 5).toString());
            if(detallePedidoDAO.eliminar(id_pedido, id_producto) != -1) {
                JOptionPane.showMessageDialog(pedidoPendiente, "Pedido pendiente eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(pedidoPendiente, "Error al eliminar los datos");
            }
        }
    }
    
    
    public void listar(JTable tabla) {
        tablaModelo = (DefaultTableModel) tabla.getModel();
        pedidoPendiente.tablaPedido.setModel(tablaModelo);
        List<Pedido> listaPedidos = pedidoDAO.listar();
        for (int i = 0; i < listaPedidos.size(); i++) {
            Pedido pedido = listaPedidos.get(i);
            int id_pedido = pedido.getId();
            Date horaCreacion = pedido.getHoraCreacion();
            Usuario usuario = usuarioDAO.buscar(pedido.getId_usuario());
            Object[] object = new Object[7];
            List<DetallePedido> listaDetallePedidos = detallePedidoDAO.listar(4, id_pedido);
            for (int j = 0; j < listaDetallePedidos.size(); j++) {
                object[0] = usuario.getNombreUsuario();
                object[1] = usuario.getDepartamento().getNombre();
                object[2] = id_pedido;
                object[3] = horaCreacion.toString();
                object[4] = listaDetallePedidos.get(j).getCantidad();
                object[5] = productoDAO.buscar(listaDetallePedidos.get(j).getId_producto()).getNombre();
                object[6] = "pendiente";
                tablaModelo.addRow(object);
            }
            pedidoPendiente.tablaPedido.setModel(tablaModelo);
        }
    }
    
    public void borrarFilas(DefaultTableModel tabla) {
        int filas = tabla.getRowCount();
        for (int i = filas - 1; i >= 0; i--) {
            tabla.removeRow(i);
        }
    }

}
