
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.Pedido;
import Modelo.Producto;
import Modelo.Usuario;
import Modelo.ProductoDAO;
import Modelo.ProductoDepartamentoDAO;
import Modelo.DetallePedido;
import Modelo.PedidoDAO;
import Modelo.DetallePedidoDAO;
import Vista.SolicitarProducto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class ControladorPedido implements ActionListener {

    Pedido pedido = new Pedido();
    SolicitarProducto solicitarProducto = new SolicitarProducto();
    Usuario usuario = new Usuario();
    ProductoDAO productoDAO = new ProductoDAO();
    PedidoDAO pedidoDAO = new PedidoDAO();
    ProductoDepartamentoDAO productoDepartamentoDAO = new ProductoDepartamentoDAO();
    DefaultTableModel tablaModelo = new DefaultTableModel();
    List<DetallePedido> detallePedidos = new ArrayList<>();
    List<Integer> idProductos = new ArrayList<>();

    public ControladorPedido(SolicitarProducto solicitarProducto, Usuario usuario) {
        this.solicitarProducto = solicitarProducto;
        this.usuario = usuario;
        this.solicitarProducto.lbUsuario.setText("Hola usuario: " + usuario.getNombreUsuario());
        List<Producto> listaProducto = productoDepartamentoDAO.listar(usuario.getDepartamento().getId());
         for (int i = 0; i < listaProducto.size(); i++) {
            this.solicitarProducto.cbProducto.addItem(listaProducto.get(i).getNombre());
        }
        this.solicitarProducto.btnSolicitar.addActionListener(this);
        this.solicitarProducto.btnEliminar.addActionListener(this);
        this.solicitarProducto.btnGenerar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == solicitarProducto.btnSolicitar) {
            solicitar();
            limpiarCampos();
        }
        if(e.getSource() == solicitarProducto.btnGenerar) {
            generar();
            borrarFilas(tablaModelo);
            detallePedidos.clear();
        }
        if(e.getSource() == solicitarProducto.btnEliminar) {
            eliminarProducto();
        }
    }
    
    public void solicitar() { 
        try {
            String producto = solicitarProducto.cbProducto.getSelectedItem().toString();
            String cantidadS = solicitarProducto.txtCantidad.getText();
        
            if("SELECCIONE".equals(producto) || cantidadS.isEmpty()) {
                JOptionPane.showMessageDialog(solicitarProducto, "Por favor llene todos los campos");
                return;
            }
            
            int id_producto = productoDAO.buscarID(producto);
            int cantidad = Integer.parseInt(cantidadS);
                        
            if(idProductos.contains(id_producto)) {
                JOptionPane.showMessageDialog(solicitarProducto, "Este producto ya ha sido seleccionado");
                return;
            } else {
                idProductos.add(id_producto);
            }
            
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setId_producto(id_producto);
            detallePedido.setCantidad(cantidad);
            detallePedidos.add(detallePedido);
            
            listar(solicitarProducto.tablaSolicitud, detallePedido);
           
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(solicitarProducto, "Por favor ingrese los datos correctamente");
        }
        
    }
    
    public void listar(JTable tabla, DetallePedido detallePedido) {
        tablaModelo = (DefaultTableModel) tabla.getModel();
        solicitarProducto.tablaSolicitud.setModel(tablaModelo);
        Producto producto = productoDAO.buscar(detallePedido.getId_producto());
        Object[] object = new Object[3];
        object[0] = producto.getNombre();
        object[1] = producto.getDescripcion();
        object[2] = detallePedido.getCantidad();
        tablaModelo.addRow(object);
        solicitarProducto.tablaSolicitud.setModel(tablaModelo);
    }
    
    public void eliminarProducto() {
        String nombreProducto;
        int fila = solicitarProducto.tablaSolicitud.getSelectedRow();
        if(fila == -1) {
            JOptionPane.showMessageDialog(solicitarProducto, "Debe seleccionar una fila");
        } else {
            nombreProducto = solicitarProducto.tablaSolicitud.getValueAt(fila, 0).toString();
            tablaModelo.removeRow(fila);
            JOptionPane.showMessageDialog(solicitarProducto, "Producto solicitado eliminado con exito");
            int id_producto = productoDAO.buscarID(nombreProducto);
            for (int i = 0; i < detallePedidos.size(); i++) {
                if(id_producto == detallePedidos.get(i).getId_producto()) {
                    detallePedidos.remove(i);
                }
            }
            for (int i = 0; i < idProductos.size(); i++) {
                if(id_producto == idProductos.get(i)) {
                    idProductos.remove(i);
                }
            }  
        }
    }
    
    public void generar() {
       int id_pedido = pedidoDAO.agregar(usuario.getId());
       if(id_pedido == -1) {
           JOptionPane.showMessageDialog(solicitarProducto, "Hubo un problema");
           return;
       }
       DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAO();
       if(detallePedidoDAO.generar(detallePedidos, id_pedido) == -1) {
           JOptionPane.showMessageDialog(solicitarProducto, "Hubo un problema");
       } else{
           JOptionPane.showMessageDialog(solicitarProducto, "Pedido de productos generado con exito");
       }
    }
    
    public void borrarFilas(DefaultTableModel tabla) {
        int filas = tabla.getRowCount();
        for (int i = filas - 1; i >= 0; i--) {
            tabla.removeRow(i);
        }
    }
    
    public void limpiarCampos() {
        solicitarProducto.txtCantidad.setText("");
        solicitarProducto.cbProducto.setSelectedIndex(0);
    }
    
}
