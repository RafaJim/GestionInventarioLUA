package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Vista.ProductoCRUD;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel 
 */
public class ControladorProductoCRUD implements ActionListener {

    ProductoDAO productoDAO = new ProductoDAO();
    ProductoCRUD productoCRUD = new ProductoCRUD();
    Producto producto = new Producto();
    DefaultTableModel tablaModelo = new DefaultTableModel();
    
    public ControladorProductoCRUD(ProductoCRUD productoCRUD) {
        this.productoCRUD = productoCRUD;
        this.productoCRUD.btnListar.addActionListener(this);
        this.productoCRUD.btnAgregar.addActionListener(this);
        this.productoCRUD.btnEditar.addActionListener(this);
        this.productoCRUD.btnActualizar.addActionListener(this);
        this.productoCRUD.btnEliminar.addActionListener(this);
        listar(productoCRUD.tablaProducto);
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == productoCRUD.btnListar) {
            borrarFilas(tablaModelo);
            listar(productoCRUD.tablaProducto);
        }
        if(e.getSource() == productoCRUD.btnAgregar) {
            agregar();
            limpiarCampos();
            borrarFilas(tablaModelo);
            listar(productoCRUD.tablaProducto);
        } 
        if(e.getSource() == productoCRUD.btnEditar) {
            int fila = productoCRUD.tablaProducto.getSelectedRow();
            if(fila == -1) {
                JOptionPane.showMessageDialog(productoCRUD, "Debe seleccionar una fila");
            } else {
                String id = productoCRUD.tablaProducto.getValueAt(fila, 0).toString();
                String clave = productoCRUD.tablaProducto.getValueAt(fila, 1).toString();
                String nombre = productoCRUD.tablaProducto.getValueAt(fila, 2).toString();
                String descripcion = productoCRUD.tablaProducto.getValueAt(fila, 3).toString();
                String precio = productoCRUD.tablaProducto.getValueAt(fila, 4).toString();
                productoCRUD.txtID.setText(id);
                productoCRUD.txtClave.setText(clave);
                productoCRUD.txtNombre.setText(nombre);
                productoCRUD.txtDescripcion.setText(descripcion);
                productoCRUD.txtPrecio.setText(precio);
            }
        }
        if(e.getSource() == productoCRUD.btnActualizar) {
            actualizar();
            limpiarCampos();
            borrarFilas(tablaModelo);
            listar(productoCRUD.tablaProducto);
        }
        if(e.getSource() == productoCRUD.btnEliminar) {
            eliminar();
            borrarFilas(tablaModelo);
            listar(productoCRUD.tablaProducto);
        }
    }
    
    public void agregar() {
        
        try {
            String clave = productoCRUD.txtClave.getText();
            String nombre = productoCRUD.txtNombre.getText();
            String descripcion = productoCRUD.txtDescripcion.getText();
            String precio = productoCRUD.txtPrecio.getText();
            
            if(clave.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() || precio.isEmpty()) {
                JOptionPane.showMessageDialog(productoCRUD, "Por favor llene todos los campos");
                return;
            }
            
            producto.setClave(Integer.parseInt(clave));
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(Double.parseDouble(precio));
            
            if(productoDAO.agregar(producto) == 1) {
                JOptionPane.showMessageDialog(productoCRUD, "Producto ingresado correctamente");
            } else {
                JOptionPane.showMessageDialog(productoCRUD, "Error al ingresar los datos");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(productoCRUD, "Por favor ingrese los datos correctamente");
        }
   
    }
    
    public void actualizar() {
        try {
            String id = productoCRUD.txtID.getText();
            String clave = productoCRUD.txtClave.getText();
            String nombre = productoCRUD.txtNombre.getText();
            String descripcion = productoCRUD.txtDescripcion.getText();
            String precio = productoCRUD.txtPrecio.getText();
        
            if(clave.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() || precio.isEmpty()) {
                JOptionPane.showMessageDialog(productoCRUD, "Por favor llene todos los campos");
                return;
            }
        
            producto.setId(Integer.parseInt(id));
            producto.setClave(Integer.parseInt(clave));
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(Double.parseDouble(precio));
            
            if(productoDAO.actualizar(producto) == 1) {
                JOptionPane.showMessageDialog(productoCRUD, "Producto actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(productoCRUD, "Error al actualizar los datos");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(productoCRUD, "Por favor ingrese los datos correctamente");
        }
        
    }
    
    public void eliminar() {
        int fila = productoCRUD.tablaProducto.getSelectedRow();
        if(fila == -1) {
            JOptionPane.showMessageDialog(productoCRUD, "Debe seleccionar una fila");
        } else {
            int id = Integer.parseInt(productoCRUD.tablaProducto.getValueAt(fila, 0).toString());
            if(productoDAO.eliminar(id) == 1) {
                JOptionPane.showMessageDialog(productoCRUD, "Producto borrado correctamente");
            } else {
                JOptionPane.showMessageDialog(productoCRUD, "Error al eliminar los datos");
            }
        }
    }
    
    public void listar(JTable tabla) {
        tablaModelo = (DefaultTableModel) tabla.getModel();
        productoCRUD.tablaProducto.setModel(tablaModelo);
        List<Producto> lista = productoDAO.listar();
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getClave();
            object[2] = lista.get(i).getNombre();
            object[3] = lista.get(i).getDescripcion();
            object[4] = lista.get(i).getPrecio();
            tablaModelo.addRow(object);
        }
        productoCRUD.tablaProducto.setModel(tablaModelo);
    }
    
    public void borrarFilas(DefaultTableModel tabla) {
        int filas = tabla.getRowCount();
        for (int i = filas - 1; i >= 0; i--) {
            tabla.removeRow(i);
        }
    }
    
    public void limpiarCampos() {
        productoCRUD.txtID.setText("");
        productoCRUD.txtClave.setText("");
        productoCRUD.txtNombre.setText("");
        productoCRUD.txtDescripcion.setText("");
        productoCRUD.txtPrecio.setText("");
    }
    
}
