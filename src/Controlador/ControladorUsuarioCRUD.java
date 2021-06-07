package Controlador;

import Modelo.Departamento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Modelo.DepartamentoDAO;
import Vista.UsuarioCRUD;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel 
 */
public class ControladorUsuarioCRUD implements ActionListener {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    UsuarioCRUD usuarioCRUD  = new UsuarioCRUD();
    Usuario usuario = new Usuario();
    DefaultTableModel tablaModelo = new DefaultTableModel();
    DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    
    public ControladorUsuarioCRUD(UsuarioCRUD usuarioCRUD) {
        this.usuarioCRUD = usuarioCRUD;
        this.usuarioCRUD.btnListar.addActionListener(this);
        List<String> listaDepartamento = departamentoDAO.listar();
        for (int i = 0; i < listaDepartamento.size(); i++) {
            this.usuarioCRUD.cbDepartamento.addItem(listaDepartamento.get(i));
        }
        this.usuarioCRUD.btnAgregar.addActionListener(this);
        this.usuarioCRUD.btnEditar.addActionListener(this);
        this.usuarioCRUD.btnActualizar.addActionListener(this);
        this.usuarioCRUD.btnEliminar.addActionListener(this);
        listar(usuarioCRUD.tablaUsuario);
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == usuarioCRUD.btnListar) {
            borrarFilas(tablaModelo);
            listar(usuarioCRUD.tablaUsuario);
        }
        if(e.getSource() == usuarioCRUD.btnAgregar) {
            agregar();
            limpiarCampos();
            borrarFilas(tablaModelo);
            listar(usuarioCRUD.tablaUsuario);
        } 
        if(e.getSource() == usuarioCRUD.btnEditar) {
            int fila = usuarioCRUD.tablaUsuario.getSelectedRow();
            if(fila == -1) {
                JOptionPane.showMessageDialog(usuarioCRUD, "Debe seleccionar una fila");
            } else {
                String id = usuarioCRUD.tablaUsuario.getValueAt(fila, 0).toString();
                String nombreUsuario = usuarioCRUD.tablaUsuario.getValueAt(fila, 1).toString();
                String contrasena = usuarioCRUD.tablaUsuario.getValueAt(fila, 2).toString();
                String departamento = usuarioCRUD.tablaUsuario.getValueAt(fila, 3).toString();
                String privilegio = usuarioCRUD.tablaUsuario.getValueAt(fila, 4).toString();
                usuarioCRUD.txtID.setText(id);
                usuarioCRUD.txtNombre.setText(nombreUsuario);
                usuarioCRUD.txtContrasena.setText(contrasena);
                usuarioCRUD.txtPrivilegio.setText(privilegio);
                usuarioCRUD.cbDepartamento.setSelectedItem(departamento);
            }
        }
        if(e.getSource() == usuarioCRUD.btnActualizar) {
            actualizar();
            limpiarCampos();
            borrarFilas(tablaModelo);
            listar(usuarioCRUD.tablaUsuario);
        }
        if(e.getSource() == usuarioCRUD.btnEliminar) {
            eliminar();
            borrarFilas(tablaModelo);
            listar(usuarioCRUD.tablaUsuario);
        }
    }
    
    public void agregar() {
        
        try {
            String nombreUsuario = usuarioCRUD.txtNombre.getText();
            String contrasena = usuarioCRUD.txtContrasena.getText();
            String privilegio = usuarioCRUD.txtPrivilegio.getText();
            String valorSeleccionado = usuarioCRUD.cbDepartamento.getSelectedItem().toString();
            int id_departamento = departamentoDAO.buscar(valorSeleccionado);
            
            if(nombreUsuario.isEmpty() || contrasena.isEmpty() || privilegio.isEmpty() ||"SELECCIONE".equals(valorSeleccionado)) {
                JOptionPane.showMessageDialog(usuarioCRUD, "Por favor llene todos los campos");
                return;
            }
            
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasena(contrasena);
            usuario.setPrivilegio(Integer.parseInt(privilegio));
            usuario.setDepartamento(new Departamento(id_departamento));
            
            if(usuarioDAO.agregar(usuario) == 1) {
                JOptionPane.showMessageDialog(usuarioCRUD, "Usuario ingresado correctamente");
            } else {
                JOptionPane.showMessageDialog(usuarioCRUD, "Error al ingresar los datos");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(usuarioCRUD, "Por favor ingrese los datos correctamente");
        }
   
    }
    
    public void actualizar() {
        try {
            String id = usuarioCRUD.txtID.getText();
            String nombreUsuario = usuarioCRUD.txtNombre.getText();
            String contrasena = usuarioCRUD.txtContrasena.getText();
            String privilegio = usuarioCRUD.txtPrivilegio.getText();
            String valorSeleccionado = usuarioCRUD.cbDepartamento.getSelectedItem().toString();
            int id_departamento = departamentoDAO.buscar(valorSeleccionado);
        
            if(nombreUsuario.isEmpty() || contrasena.isEmpty() || privilegio.isEmpty() || "SELECCIONE".equals(valorSeleccionado)) {
                JOptionPane.showMessageDialog(usuarioCRUD, "Por favor llene todos los campos");
                return;
            }
        
            usuario.setId(Integer.parseInt(id));
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasena(contrasena);
            usuario.setPrivilegio(Integer.parseInt(privilegio));
            usuario.setDepartamento(new Departamento(id_departamento));
            
            if(usuarioDAO.actualizar(usuario) == 1) {
                JOptionPane.showMessageDialog(usuarioCRUD, "Usuario actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(usuarioCRUD, "Error al actualizar los datos");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(usuarioCRUD, "Por favor ingrese los datos correctamente");
        }
        
    }
    
    public void eliminar() {
        int fila = usuarioCRUD.tablaUsuario.getSelectedRow();
        if(fila == -1) {
            JOptionPane.showMessageDialog(usuarioCRUD, "Debe seleccionar una fila");
        } else {
            int id = Integer.parseInt(usuarioCRUD.tablaUsuario.getValueAt(fila, 0).toString());
            if(usuarioDAO.eliminar(id) == 1) {
                JOptionPane.showMessageDialog(usuarioCRUD, "Usuario borrado correctamente");
            } else {
                JOptionPane.showMessageDialog(usuarioCRUD, "Error al eliminar los datos");
            }
        }
    }
    
    public void listar(JTable tabla) {
        tablaModelo = (DefaultTableModel) tabla.getModel();
        usuarioCRUD.tablaUsuario.setModel(tablaModelo);
        List<Usuario> lista = usuarioDAO.listar();
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombreUsuario();
            object[2] = lista.get(i).getContrasena();
            object[3] = lista.get(i).getDepartamento().getNombre();
            object[4] = lista.get(i).getPrivilegio();
            tablaModelo.addRow(object);
        }
        usuarioCRUD.tablaUsuario.setModel(tablaModelo);
    }
    
    public void borrarFilas(DefaultTableModel tabla) {
        int filas = tabla.getRowCount();
        for (int i = filas - 1; i >= 0; i--) {
            tabla.removeRow(i);
        }
    }
    
    public void limpiarCampos() {
        usuarioCRUD.txtID.setText("");
        usuarioCRUD.txtNombre.setText("");
        usuarioCRUD.txtContrasena.setText("");
        usuarioCRUD.txtPrivilegio.setText("");
        usuarioCRUD.cbDepartamento.setSelectedIndex(0);
    }
    
}
