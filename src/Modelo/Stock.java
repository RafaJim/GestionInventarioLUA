package Modelo;

/**
 *
 * @author daniel
 */
public class Stock {
    
    private int id;
    private int cantidadActual;
    private int cantidadMinima;
    private Producto producto;

    public Stock() {
        
    }
    
    public Stock(int id, Producto producto, int cantidadActual, int cantidadMinima) {
        this.id = id;
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

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
