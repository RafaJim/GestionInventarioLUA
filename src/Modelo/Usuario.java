package Modelo;

/**
 *
 * @author daniel
 */
public class Usuario {
    
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private Departamento departamento;
    private int privilegio;

    public Usuario() {
        
    }
    
    public Usuario(int id, String nombreUsuario, String contrasena, Departamento departamento, int privilegio) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.departamento = departamento;
        this.privilegio = privilegio;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public int getPrivilegio() {
        return privilegio;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void setPrivilegio(int privilegio) {
        this.privilegio = privilegio;
    }
        
}
