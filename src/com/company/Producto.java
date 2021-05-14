package com.company;

public class Producto {

    private int clave;
    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(int clave, String nombre, String descripcion, double precio) {
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
}
