package Modelo;

import java.util.Date;

/**
 *
 * @author daniel
 */
public class Pedido {
    
    private int id;
    private Date horaCreacion;
    private int id_usuario;

    public Pedido() {
        
    }

    public Pedido(int id, Date horaCreacion, int id_usuario) {
        this.id = id;
        this.horaCreacion = horaCreacion;
        this.id_usuario = id_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
}
