package com.company;

public class Stock {

    private Producto producto;
    private int cantidadActual;
    private int cantidadMinima;

    public Stock(Producto producto, int cantidadActual, int cantidadMinima) {
        this.producto = producto;
        this.cantidadActual = cantidadActual;
        this.cantidadMinima = cantidadMinima;
    }

    public boolean anadirStock(Stock stock){
        return false;
    }

    public boolean sacarStock(int clave, int cantidad) {
        return false;
    }

    public void activarAlerta() {

    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }
}
