package Controlador;

import Modelo.Stock;
import Modelo.StockDAO;
import Modelo.ProductoDAO;
import Modelo.Producto;
import Vista.VistaAlerta;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class ControladorAlerta {
    
    VistaAlerta alerta = new VistaAlerta();
    StockDAO stockDAO = new StockDAO();
    ProductoDAO productoDAO = new ProductoDAO();
    DefaultTableModel tablaModelo = new DefaultTableModel();
    
    public ControladorAlerta(VistaAlerta alerta) {
        this.alerta = alerta;
        listar(alerta.tablaProducto);
    }
    
    public void listar(JTable tabla) {
        tablaModelo = (DefaultTableModel) tabla.getModel();
        alerta.tablaProducto.setModel(tablaModelo);
        List<Stock> lista = stockDAO.bajoStock();
        Object[] object = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            int cantidadActual = lista.get(i).getCantidadActual();
            int cantidadMinima = lista.get(i).getCantidadMinima();
            Producto producto = lista.get(i).getProducto();
            object[0] = producto.getId();
            object[1] = producto.getNombre();
            object[2] = producto.getDescripcion();
            object[3] = cantidadActual;
            object[4] = cantidadMinima;
            object[5] = cantidadMinima - cantidadActual;
            tablaModelo.addRow(object);
        }
        alerta.tablaProducto.setModel(tablaModelo);
    }
    
}
