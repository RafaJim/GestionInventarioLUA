package Modelo;

/**
 *
 * @author danie
 */
public class Alerta {

    private int id_producto;
    private int cantidadAbajo;
 
    public Alerta(int id_producto, int cantidadAbajo) {
        this.id_producto = id_producto;
        this.cantidadAbajo = cantidadAbajo;
    }

    public Alerta() {
        
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidadAbajo() {
        return cantidadAbajo;
    }

    public void setCantidadAbajo(int cantidadAbajo) {
        this.cantidadAbajo = cantidadAbajo;
    }
       
}
