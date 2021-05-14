package com.company;

public class Usuario {

    private String nombreUsuario;
    private String contrasena;
    private Departamento departamento;
    private int privilegio;

    public Usuario(String nombreUsuario, String contrasena, Departamento departamento, int privilegio) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.departamento = departamento;
        this.privilegio = privilegio;
    }

    public boolean solicitarProducto(int clave, int cantidad) {
        return false;
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
}
