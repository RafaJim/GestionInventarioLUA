package com.company;

import java.util.List;

public class Departamento {

    private String nombre;
    private List<Producto> listaProductos;

    public Departamento(String nombre, List<Producto> listaProductos) {
        this.nombre = nombre;
        this.listaProductos = listaProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
}
