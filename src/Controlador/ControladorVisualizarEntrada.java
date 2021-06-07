package Controlador;

import Modelo.EntradaDAO;
import Modelo.Entrada;
import Modelo.ProductoDAO;
import Vista.VisualizarEntrada;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class ControladorVisualizarEntrada {
    
    VisualizarEntrada visualizarEntrada = new VisualizarEntrada();
    EntradaDAO entradaDAO = new EntradaDAO();
    ProductoDAO productoDAO = new ProductoDAO();
    DefaultTableModel tablaModelo = new DefaultTableModel();
    
    public ControladorVisualizarEntrada(VisualizarEntrada visualizarEntrada) {
        this.visualizarEntrada = visualizarEntrada;
        listar(visualizarEntrada.tablaEntrada);
    }
    
    public void listar(JTable tabla) {
        tablaModelo = (DefaultTableModel) tabla.getModel();
        visualizarEntrada.tablaEntrada.setModel(tablaModelo);
        List<Entrada> lista = entradaDAO.listar();
        Object[] object = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = productoDAO.buscar(lista.get(i).getId_producto()).getNombre();
            object[1] = lista.get(i).getCantidad();
            object[2] = lista.get(i).getFecha().toString();
            tablaModelo.addRow(object);
        }
        visualizarEntrada.tablaEntrada.setModel(tablaModelo);
    }
  
}
