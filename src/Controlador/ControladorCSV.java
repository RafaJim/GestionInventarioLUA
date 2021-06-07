package Controlador;

import Modelo.Stock;
import Modelo.Entrada;
import Modelo.EntradaDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.StockDAO;
import Vista.ArchivoCSV;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author danie
 */
public class ControladorCSV implements ActionListener {

    ArchivoCSV archivoCSV = new ArchivoCSV();
    StockDAO stockDAO = new StockDAO();
    EntradaDAO entradaDAO = new EntradaDAO();
    Producto producto = new Producto();
    ProductoDAO productoDAO = new ProductoDAO();
    
    public ControladorCSV(ArchivoCSV archivoCSV) {
        this.archivoCSV = archivoCSV;
        this.archivoCSV.btnStock.addActionListener(this);
        this.archivoCSV.btnGasto.addActionListener(this);
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == archivoCSV.btnGasto) {
            String anoS = archivoCSV.txtAno.getText();
            String mesS = archivoCSV.txtMes.getText();
            if(anoS.isEmpty() || mesS.isEmpty()) {
                JOptionPane.showMessageDialog(archivoCSV, "Por favor llene todo los campos");
                return;
            }   
            int ano, mes;
            try {
                ano = Integer.parseInt(anoS);
                mes = Integer.parseInt(mesS);
                if(mes > 12 || mes < 1) {
                    JOptionPane.showMessageDialog(archivoCSV, "Llene los campos correctamente: mes(1-12)");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(archivoCSV, "Llene los campos correctamente");
                return;
            }
            List<Entrada> listaEntrada = entradaDAO.entradasMes(mes, ano);
            if(listaEntrada.isEmpty()) {
                JOptionPane.showMessageDialog(archivoCSV, "No se encontraron registos para exportar a .csv");
                return;
            }                  
            generarArchivoGasto(listaEntrada);
        }
        if(e.getSource() == archivoCSV.btnStock) {
            List<Stock> listaStock = stockDAO.listar();
            if(listaStock.isEmpty()) {
                JOptionPane.showMessageDialog(archivoCSV, "No se encontraron registos para exportar a .csv");
                return;
            }
            generarArchivoStock(listaStock);
        }
    }
            
    public void generarArchivoStock(List<Stock> lista) {
        JFileChooser seleccionarArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");
        seleccionarArchivo.setFileFilter(filtro);
        
        int seleccionar = seleccionarArchivo.showOpenDialog(archivoCSV);
        
        if(seleccionar == JFileChooser.APPROVE_OPTION) {
            File archivo = seleccionarArchivo.getSelectedFile();
            guardarArchivoStock(archivo, lista);
        } 
    }
    
    public void generarArchivoGasto(List<Entrada> lista) {
        JFileChooser seleccionarArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");
        seleccionarArchivo.setFileFilter(filtro);
        
        int seleccionar = seleccionarArchivo.showOpenDialog(archivoCSV);
        
        if(seleccionar == JFileChooser.APPROVE_OPTION) {
            File archivo = seleccionarArchivo.getSelectedFile();
            guardarArchivoGasto(archivo, lista);
        } 
    }
    
    public void guardarArchivoStock(File archivo, List<Stock> listaStock) {
      
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            
            String linea = "id" + "," + "CantidadActual" + "," + "CantidadMinima" + "," + "Producto";
            pw.println(linea);
            
            for (int i = 0; i < listaStock.size(); i++) {
                Stock stock = listaStock.get(i);
                linea = stock.getId() + "," + stock.getCantidadActual() + "," + stock.getCantidadMinima() + "," + stock.getProducto().getNombre();
                pw.println(linea);
            }
           
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fichero != null) {
                    fichero.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public void guardarArchivoGasto(File archivo,  List<Entrada> listaEntrada) {  
        FileWriter fichero = null;
        PrintWriter pw = null;
              
        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            
            String linea = "id" + "," + "Cantidad" + "," + "fecha" + "," + "Producto" + "," + "Precio" + "," + "Costo total de producto";
            pw.println(linea);
            
            double costo_total = 0;
            for (int i = 0; i < listaEntrada.size(); i++) {
                Entrada entrada = listaEntrada.get(i);
                Producto producto = productoDAO.buscar(listaEntrada.get(i).getId_producto());
                int cantidad = entrada.getCantidad();
                double precio = producto.getPrecio();
                double total = cantidad * precio;
                linea = entrada.getId() + "," + cantidad + "," + entrada.getFecha().toString() + "," + producto.getNombre()
                        + "," + precio + "," + (total);
                pw.println(linea);
                costo_total += total;
            }
            linea = "" + "," + "" + "," + "" + "," + "" + "," + "Costo total del mes" + "," + costo_total;
            pw.println(linea);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fichero != null) {
                    fichero.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
}
