package Modelo;

import java.util.Date;

/**
 *
 * @author danie
 */
public class Entrada {
    
    int id;
    int id_producto;
    Date fecha;
    int cantidad;

    public Entrada() {
        
    }
    
    public Entrada(int id, int id_producto, Date fecha, int cantidad) {
        this.id_producto = id_producto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }
   
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setId(int id) {
        this.id = id;
    }
       
}
