package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
public class UsuarioDAO {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public Usuario login(String nombreUsuario, String contrasena) {
        Usuario usuario = null;
        String consulta = "select id, privilegio, id_departamento from usuario where nombreUsuario = ? "
                + "AND contrasena = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            while (rs.next()) {                
                usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombreUsuario(nombreUsuario);
                usuario.setContrasena(contrasena);
                usuario.setPrivilegio(rs.getInt(2));
                usuario.setDepartamento(new Departamento(rs.getInt(3)));
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error al verificar datos ingresados");
        }
        return usuario;
        
    }
    
    
    public List listar() {
        List<Usuario> datos = new ArrayList<>();
        String consulta = "select usuario.id, usuario.nombreUsuario, usuario.contrasena, "
                + "usuario.privilegio, departamento.nombre from usuario, departamento where "
                + "usuario.id_departamento = departamento.id";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setContrasena(rs.getString(3));
                usuario.setPrivilegio(rs.getInt(4));
                usuario.setDepartamento(new Departamento(rs.getString(5)));
                datos.add(usuario);
            }
            
        } catch (java.sql.SQLException e) {
            System.out.println("Error listado");
        }
        return datos;
    }
    
    public int agregar(Usuario usuario) {
        String consulta = "insert into usuario (nombreUsuario, contrasena, privilegio, "
                + "id_departamento) values (?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setInt(3, usuario.getPrivilegio());
            ps.setInt(4, usuario.getDepartamento().getId());
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public int actualizar(Usuario usuario) {
        String consulta = "update usuario set nombreUsuario=?, contrasena=?, privilegio=?, "
                + "id_departamento=? where id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setInt(3, usuario.getPrivilegio());
            ps.setInt(4, usuario.getDepartamento().getId());
            ps.setInt(5, usuario.getId());
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public int eliminar(int id) {
        String consulta = "delete from usuario where id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException e) {
            return -1;
        }
    }
    
    public Usuario buscar(int id) {
        Usuario usuario = new Usuario();
        String consulta = "select usuario.id, usuario.nombreUsuario, usuario.contrasena, "
                + "usuario.privilegio, departamento.nombre from usuario, departamento where "
                + "usuario.id_departamento = departamento.id AND usuario.id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {                
                usuario.setId(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setContrasena(rs.getString(3));
                usuario.setPrivilegio(rs.getInt(4));
                usuario.setDepartamento(new Departamento(rs.getString(5)));
            }
            
        } catch (java.sql.SQLException e) {
            System.out.println("Error busqueda");
        }
        return usuario;
    }
    
}
