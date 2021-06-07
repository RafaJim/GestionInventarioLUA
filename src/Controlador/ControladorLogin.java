package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.Login;
import Vista.Home;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel
 */
public class ControladorLogin implements ActionListener {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Login login = new Login(); 
    Usuario usuario = new Usuario();

    public ControladorLogin(Login login) {
        this.login = login;
        this.login.btnLogin.addActionListener(this);
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login.btnLogin) {
            loguear();
        }
    }
    
    public void loguear() {
        String nombreUsuario = login.txtUsuario.getText();
        String contrasena = new String(login.txtContrasena.getPassword());
        
        if(nombreUsuario.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(login, "Por favor llene todos los campos");
        } else {
            usuario = usuarioDAO.login(nombreUsuario, contrasena);
            if(usuario != null) {
                JOptionPane.showMessageDialog(login, "Datos correctos, ingresando al sistema");
                login.dispose();
                Home inicio = new Home();
                ControladorHome controlador = new ControladorHome(inicio, usuario);
                inicio.setLocationRelativeTo(null);
                inicio.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(login, "Verifique los datos ingresados");
            }
        }
    } 
    
}
