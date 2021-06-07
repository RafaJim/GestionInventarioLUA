package Controlador;

import Modelo.DepartamentoDAO;
import Modelo.Usuario;
import Modelo.Producto;
import Modelo.UsuarioDAO;
import Modelo.ProductoDAO;
import Modelo.ProductoDepartamentoDAO;
import Vista.RestriccionProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class ControladorRestriccionProductos implements ActionListener {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    RestriccionProductos restriccionProductos = new RestriccionProductos();
    Usuario usuario = new Usuario();
    DefaultTableModel tablaModelo = new DefaultTableModel();
    DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    ProductoDAO productoDAO = new ProductoDAO();
    ProductoDepartamentoDAO productoDepartamentoDAO = new ProductoDepartamentoDAO();

    public ControladorRestriccionProductos(RestriccionProductos restriccionProductos) {
        this.restriccionProductos = restriccionProductos;
        restriccionProductos.cbDepartamento.addActionListener(this);
        List<String> listaDepartamento = departamentoDAO.listar();
        for (int i = 0; i < listaDepartamento.size(); i++) {
            this.restriccionProductos.cbDepartamento.addItem(listaDepartamento.get(i));
        }
        List<Producto> listaProducto = productoDAO.listar();
        for (int i = 0; i < listaProducto.size(); i++) {
            this.restriccionProductos.cbProducto.addItem(listaProducto.get(i).getNombre());
        }
        this.restriccionProductos.btnAgregar.addActionListener(this);
        this.restriccionProductos.btnEliminar.addActionListener(this);
        this.restriccionProductos.btnListar.addActionListener(this);
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == restriccionProductos.btnListar) {
            borrarFilas(tablaModelo);
            listar(restriccionProductos.tablaProducto);
        } 
        if(e.getSource() == restriccionProductos.btnAgregar) {
            agregar();
            limpiarCampos();
            borrarFilas(tablaModelo);
            listar(restriccionProductos.tablaProducto);          
        } 
        if(e.getSource() == restriccionProductos.btnEliminar) {
            eliminar();
            borrarFilas(tablaModelo);
            listar(restriccionProductos.tablaProducto); 
        } 
    }
    
    public void agregar() {
                   
        String valorProducto = restriccionProductos.cbProducto.getSelectedItem().toString();
        String valorDepartamento = restriccionProductos.cbDepartamento.getSelectedItem().toString();
            
        if("SELECCIONE".equals(valorProducto) || "SELECCIONE".equals(valorDepartamento)) {
            JOptionPane.showMessageDialog(restriccionProductos, "Por favor llene todos los campos");
            return;
        }
            
        int id_departamento = departamentoDAO.buscar(valorDepartamento);
        int id_producto = productoDAO.buscarID(valorProducto);
        
        if(productoDepartamentoDAO.existe(id_departamento, id_producto)) {
            JOptionPane.showMessageDialog(restriccionProductos, "Producto existente en la lista");
            return;
        }
            
        if(productoDepartamentoDAO.agregar(id_departamento, id_producto) == 1) {
            JOptionPane.showMessageDialog(restriccionProductos, "Producto ingresado a la lista correctamente");
        } else {
            JOptionPane.showMessageDialog(restriccionProductos, "Error al ingresar los datos");
        }       
    }
    
    public void listar(JTable tabla) {
        tablaModelo = (DefaultTableModel) tabla.getModel();
        restriccionProductos.tablaProducto.setModel(tablaModelo);
        String valorDepartamento = restriccionProductos.cbDepartamento.getSelectedItem().toString();
        if("SELECCIONE".equals(valorDepartamento)) {
            JOptionPane.showMessageDialog(restriccionProductos, "Por favor eliga un departamento");
            return;
        }
        int id_departamento = departamentoDAO.buscar(valorDepartamento);
        List<Producto> lista = productoDepartamentoDAO.listar(id_departamento);
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getClave();
            object[2] = lista.get(i).getNombre();
            object[3] = lista.get(i).getDescripcion();
            object[4] = lista.get(i).getPrecio();
            tablaModelo.addRow(object);
        }
        restriccionProductos.tablaProducto.setModel(tablaModelo);
    }
    
    public void eliminar() {
        int fila = restriccionProductos.tablaProducto.getSelectedRow();
        if(fila == -1) {
            JOptionPane.showMessageDialog(restriccionProductos, "Debe seleccionar una fila");
        } else {
            int id = Integer.parseInt(restriccionProductos.tablaProducto.getValueAt(fila, 0).toString());
            if(productoDepartamentoDAO.eliminar(id) == 1) {
                JOptionPane.showMessageDialog(restriccionProductos, "Producto borrado correctamente");
            } else {
                JOptionPane.showMessageDialog(restriccionProductos, "Error al eliminar los datos");
            }
        }
    }
    
    public void borrarFilas(DefaultTableModel tabla) {
        int filas = tabla.getRowCount();
        for (int i = filas - 1; i >= 0; i--) {
            tabla.removeRow(i);
        }
    }
    
    public void limpiarCampos() {
        restriccionProductos.cbProducto.setSelectedIndex(0);
    }
        
}
