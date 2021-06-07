package Modelo;

/**
 *
 * @author daniel
 * 
 */
public class Departamento {
    
    private int id;
    private String nombre;

    public Departamento() {
        
    }
    
    public Departamento(int id) {
        this.id = id;
    }
    
    public Departamento(String nombre) {
        this.nombre = nombre;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    
}
