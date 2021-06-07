package Controlador;

import Vista.*;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author danie
 */
public class ControladorHome implements ActionListener {
    
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Home inicio = new Home();
    Usuario usuario = new Usuario();

    public ControladorHome(Home inicio, Usuario usuario) {
        this.inicio = inicio;
        this.usuario = usuario;
        //MENU GESTIONAR
        this.inicio.subUsuario.addActionListener(this);
        this.inicio.subStock.addActionListener(this);
        this.inicio.subProducto.addActionListener(this);
        //MENU GENERAR
        this.inicio.subCsv.addActionListener(this);
        //MENU ALERTA
        this.inicio.subAlerta.addActionListener(this);
        //MENU RESTRINGIR
        this.inicio.subRestringir.addActionListener(this);
        //MENU VISUALIZAR
        this.inicio.subEntradas.addActionListener(this);
        this.inicio.subSalidas.addActionListener(this); 
        //MENU SOLICITUD
        this.inicio.subSolicitar.addActionListener(this);
        //MENU ENTRADA
        this.inicio.subEntradaProducto.addActionListener(this);
        this.inicio.subPendiente.addActionListener(this);
        restriccionesUsuario();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == inicio.subUsuario) {
           UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
           ControladorUsuarioCRUD controlador = new ControladorUsuarioCRUD(usuarioCRUD);
           usuarioCRUD.setVisible(true);
           usuarioCRUD.setLocationRelativeTo(null);
      }
      if(e.getSource() == inicio.subProducto) {
           ProductoCRUD productoCRUD = new ProductoCRUD();
           ControladorProductoCRUD controlador = new ControladorProductoCRUD(productoCRUD);
           productoCRUD.setVisible(true);
           productoCRUD.setLocationRelativeTo(null);
      } 
      if(e.getSource() == inicio.subStock) {
           StockCRUD stockCRUD = new StockCRUD();
           ControladorStockCRUD controlador = new ControladorStockCRUD(stockCRUD);
           stockCRUD.setVisible(true);
           stockCRUD.setLocationRelativeTo(null);
      } 
      if(e.getSource() == inicio.subCsv) {
           ArchivoCSV archivoCSV = new ArchivoCSV();
           ControladorCSV controlador = new ControladorCSV(archivoCSV);
           archivoCSV.setVisible(true);
           archivoCSV.setLocationRelativeTo(null);
      } 
      if(e.getSource() == inicio.subAlerta) {
           VistaAlerta vistaAlerta = new VistaAlerta();
           ControladorAlerta controlador = new ControladorAlerta(vistaAlerta);
           vistaAlerta.setVisible(true);
           vistaAlerta.setLocationRelativeTo(null);
      } 
      if(e.getSource() == inicio.subRestringir) {
           RestriccionProductos restriccionProductos = new RestriccionProductos();
           ControladorRestriccionProductos controlador = new ControladorRestriccionProductos(restriccionProductos);
           restriccionProductos.setVisible(true);
           restriccionProductos.setLocationRelativeTo(null);
      } 
      if(e.getSource() == inicio.subEntradas) {
           VisualizarEntrada visualizarEntrada = new VisualizarEntrada();
           ControladorVisualizarEntrada controlador = new ControladorVisualizarEntrada(visualizarEntrada);
           visualizarEntrada.setVisible(true);
           visualizarEntrada.setLocationRelativeTo(null);
      }       
      if(e.getSource() == inicio.subSalidas) {
           VisualizarSalida visualizarPedido = new VisualizarSalida();
           ControladorVisualizarSalida controlador = new ControladorVisualizarSalida(visualizarPedido);
           visualizarPedido.setVisible(true);
           visualizarPedido.setLocationRelativeTo(null);
      }
      if(e.getSource() == inicio.subSolicitar) {
          SolicitarProducto solicitarProducto = new SolicitarProducto();
          ControladorPedido controlador = new ControladorPedido(solicitarProducto, usuario);
          solicitarProducto.setVisible(true);
          solicitarProducto.setLocationRelativeTo(null);
      }
      if(e.getSource() == inicio.subEntradaProducto) {
          EntradaProducto entradaProducto = new EntradaProducto();
          ControladorEntrada contolador = new ControladorEntrada(entradaProducto);
          entradaProducto.setVisible(true);
          entradaProducto.setLocationRelativeTo(null);
      }
      if(e.getSource() == inicio.subPendiente) {
          PedidoPendiente pedidoPendiente = new PedidoPendiente();
          ControladorPedidoPendiente contolador = new ControladorPedidoPendiente(pedidoPendiente);
          pedidoPendiente.setVisible(true);
          pedidoPendiente.setLocationRelativeTo(null);
      }
      
    }
   
   
    private void restriccionesUsuario() {
        if(usuario.getPrivilegio() == 1) {
            inicio.menuVisualizar.setVisible(false);
            inicio.menuGestionar.setVisible(false);
            inicio.menuGenerar.setVisible(false);
            inicio.munuRestringir.setVisible(false);
            inicio.menuAlertas.setVisible(false);
            inicio.menuEntrada.setVisible(false);
        } else if(usuario.getPrivilegio() == 2) {
            inicio.menuSolicitud.setVisible(false);
        }
    }
    
}
