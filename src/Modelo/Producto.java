package Modelo;

/**
 *
 * @author daniel
 */
public class Producto {
    
    private int id;
    private int clave;
    private String nombre;
    private String descripcion;
    private double precio;

    public Producto() {
        
    }
    
    public Producto(int id) {
        this.id = id;
    }
    
    public Producto(String nombre) {
        this.nombre = nombre;
    }
    
    public Producto(int id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
