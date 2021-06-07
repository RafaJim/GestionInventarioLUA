package Controlador;

import Modelo.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import Modelo.Stock;
import Modelo.StockDAO;
import Modelo.ProductoDAO;
import Vista.StockCRUD;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel 
 */
public class ControladorStockCRUD implements ActionListener {

    StockDAO stockDAO = new StockDAO();
    StockCRUD stockCRUD  = new StockCRUD();
    Stock stock = new Stock();
    DefaultTableModel tablaModelo = new DefaultTableModel();
    ProductoDAO productoDAO = new ProductoDAO();
    
    public ControladorStockCRUD(StockCRUD stockCRUD) {
        this.stockCRUD = stockCRUD;
        this.stockCRUD.btnListar.addActionListener(this);
        List<Producto> listaProductos = productoDAO.listar();
        for (int i = 0; i < listaProductos.size(); i++) {
            this.stockCRUD.cbProducto.addItem(listaProductos.get(i).getNombre());
        }
        this.stockCRUD.btnAgregar.addActionListener(this);
        this.stockCRUD.btnEditar.addActionListener(this);
        this.stockCRUD.btnActualizar.addActionListener(this);
        this.stockCRUD.btnEliminar.addActionListener(this);
        listar(stockCRUD.tablaStock);
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == stockCRUD.btnListar) {
            borrarFilas(tablaModelo);
            listar(stockCRUD.tablaStock);
        }
        if(e.getSource() == stockCRUD.btnAgregar) {
            agregar();
            limpiarCampos();
            borrarFilas(tablaModelo);
            listar(stockCRUD.tablaStock);
        } 
        if(e.getSource() == stockCRUD.btnEditar) {
            int fila = stockCRUD.tablaStock.getSelectedRow();
            if(fila == -1) {
                JOptionPane.showMessageDialog(stockCRUD, "Debe seleccionar una fila");
            } else {
                String id = stockCRUD.tablaStock.getValueAt(fila, 0).toString();
                String cantidadActual = stockCRUD.tablaStock.getValueAt(fila, 1).toString();
                String cantidadMinima = stockCRUD.tablaStock.getValueAt(fila, 2).toString();
                String producto = stockCRUD.tablaStock.getValueAt(fila, 3).toString();
                
                stockCRUD.txtID.setText(id);
                stockCRUD.txtCantidadActual.setText(cantidadActual);
                stockCRUD.txtCantidadMinima.setText(cantidadMinima);
                stockCRUD.cbProducto.setSelectedItem(producto);
            }
        }
        if(e.getSource() == stockCRUD.btnActualizar) {
            actualizar();
            limpiarCampos();
            borrarFilas(tablaModelo);
            listar(stockCRUD.tablaStock);
        }
        if(e.getSource() == stockCRUD.btnEliminar) {
            eliminar();
            borrarFilas(tablaModelo);
            listar(stockCRUD.tablaStock);
        }
    }
    
    public void agregar() {
        
        try {
            String cantidadActual = stockCRUD.txtCantidadActual.getText();
            String cantidadMinima = stockCRUD.txtCantidadMinima.getText();
            String valorSeleccionado = stockCRUD.cbProducto.getSelectedItem().toString();
            int id_producto = productoDAO.buscarID(valorSeleccionado);
            
            if(cantidadActual.isEmpty() || cantidadMinima.isEmpty() || valorSeleccionado.isEmpty() || "SELECCIONE".equals(valorSeleccionado)) {
                JOptionPane.showMessageDialog(stockCRUD, "Por favor llene todos los campos");
                return;
            }
            
            stock.setCantidadActual(Integer.parseInt(cantidadActual));
            stock.setCantidadMinima(Integer.parseInt(cantidadMinima));
            stock.setProducto(new Producto(id_producto));
            
            if(stockDAO.enStock(id_producto)) {
                JOptionPane.showMessageDialog(stockCRUD, "Ya existe stock de este producto.");
                return;
            }
            
            if(stockDAO.agregar(stock) == 1) {
                JOptionPane.showMessageDialog(stockCRUD, "Stock de producto ingresado correctamente");
            } else {
                JOptionPane.showMessageDialog(stockCRUD, "Error al ingresar los datos");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(stockCRUD, "Por favor ingrese los datos correctamente");
        }
   
    }
    
    public void actualizar() {
        try {
            String id = stockCRUD.txtID.getText();
            String cantidadActual = stockCRUD.txtCantidadActual.getText();
            String cantidadMinima = stockCRUD.txtCantidadMinima.getText();
            String valorSeleccionado = stockCRUD.cbProducto.getSelectedItem().toString();
            int id_producto = productoDAO.buscarID(valorSeleccionado);
        
            if(cantidadActual.isEmpty() || cantidadMinima.isEmpty() || valorSeleccionado.isEmpty() || "SELECCIONE".equals(valorSeleccionado)) {
                JOptionPane.showMessageDialog(stockCRUD, "Por favor llene todos los campos");
                return;
            }
            
            stock.setId(Integer.parseInt(id));
            stock.setCantidadActual(Integer.parseInt(cantidadActual));
            stock.setCantidadMinima(Integer.parseInt(cantidadMinima));
            stock.setProducto(new Producto(id_producto));
            
            if(stockDAO.actualizar(stock) == 1) {
                JOptionPane.showMessageDialog(stockCRUD, "Stock de producto actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(stockCRUD, "Error al actualizar los datos");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(stockCRUD, "Por favor ingrese los datos correctamente");
        }
        
    }
    
    public void eliminar() {
        int fila = stockCRUD.tablaStock.getSelectedRow();
        if(fila == -1) {
            JOptionPane.showMessageDialog(stockCRUD, "Debe seleccionar una fila");
        } else {
            int id = Integer.parseInt(stockCRUD.tablaStock.getValueAt(fila, 0).toString());
            if(stockDAO.eliminar(id) == 1) {
                JOptionPane.showMessageDialog(stockCRUD, "Stock de producto borrado correctamente");
            } else {
                JOptionPane.showMessageDialog(stockCRUD, "Error al eliminar los datos");
            }
        }
    }
    
    public void listar(JTable tabla) {
        tablaModelo = (DefaultTableModel) tabla.getModel();
        stockCRUD.tablaStock.setModel(tablaModelo);
        List<Stock> lista = stockDAO.listar();
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getCantidadActual();
            object[2] = lista.get(i).getCantidadMinima();
            object[3] = lista.get(i).getProducto().getNombre();
            tablaModelo.addRow(object);
        }
        stockCRUD.tablaStock.setModel(tablaModelo);
    }
    
    public void borrarFilas(DefaultTableModel tabla) {
        int filas = tabla.getRowCount();
        for (int i = filas - 1; i >= 0; i--) {
            tabla.removeRow(i);
        }
    }
    
    public void limpiarCampos() {
        stockCRUD.txtID.setText("");
        stockCRUD.txtCantidadActual.setText("");
        stockCRUD.txtCantidadMinima.setText("");
        stockCRUD.cbProducto.setSelectedIndex(0);
    }
    
}
