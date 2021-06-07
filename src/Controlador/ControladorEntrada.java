package Controlador;

import Modelo.Entrada;
import Modelo.EntradaDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Vista.EntradaProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class ControladorEntrada implements ActionListener {

    ProductoDAO productoDAO = new ProductoDAO();
    EntradaProducto entradaProducto = new EntradaProducto();

    public ControladorEntrada(EntradaProducto entradaProducto) {
        this.entradaProducto = entradaProducto;
        List<Producto> listaProducto = productoDAO.listar();
        for (int i = 0; i < listaProducto.size(); i++) {
            this.entradaProducto.cbProducto.addItem(listaProducto.get(i).getNombre());
        }
        this.entradaProducto.btnEntrada.addActionListener(this);
    }
      
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == entradaProducto.btnEntrada) {
            entrada();
        }
    }
    
    public void entrada() {        
        try {
            String producto = entradaProducto.cbProducto.getSelectedItem().toString();
            String cantidadS = entradaProducto.txtCantidad.getText();
        
            if("SELECCIONE".equals(producto) || cantidadS.isEmpty()) {
                JOptionPane.showMessageDialog(entradaProducto, "Por favor llene todos los campos");
                return;
            }
            
            int id_producto = productoDAO.buscarID(producto);
            int cantidad = Integer.parseInt(cantidadS);
            
            Entrada entrada = new Entrada();
            entrada.setId_producto(id_producto);
            entrada.setCantidad(cantidad);
            
            EntradaDAO entradaDAO = new EntradaDAO();
            int resultado = entradaDAO.agregar(entrada);
            switch (resultado) {
                case 1 -> JOptionPane.showMessageDialog(entradaProducto, "Entrada agregada exitosamente");
                case 2 -> JOptionPane.showMessageDialog(entradaProducto, "Es necesario ingresarlo al stock");
                default -> JOptionPane.showMessageDialog(entradaProducto, "Hubo un error");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(entradaProducto, "Por favor ingrese los datos correctamente");
        }
        
    }
    
    
}
