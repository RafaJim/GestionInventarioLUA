/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author danie
 */
public class DetallePedido {
    
    int id;
    int id_producto;
    int cantidad;
    int id_pedido;
    int id_estatus;

    public DetallePedido() {
        
    }
    
    public DetallePedido(int id, int id_producto, int cantidad, int id_pedido, int id_estatus) {
        this.id = id;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.id_pedido = id_pedido;
        this.id_estatus = id_estatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_estatus() {
        return id_estatus;
    }

    public void setId_estatus(int id_estatus) {
        this.id_estatus = id_estatus;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
    
}
