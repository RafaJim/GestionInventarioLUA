package com.company;

public class Pedido {

    private String numeroPedido;
    private String horaCreacion;
    private String status;
    private String observaciones;
    private String fechaEntrega;

    public Pedido(String numeroPedido, String horaCreacion, String status, String observaciones, String fechaEntrega) {
        this.numeroPedido = numeroPedido;
        this.horaCreacion = horaCreacion;
        this.status = status;
        this.observaciones = observaciones;
        this.fechaEntrega = fechaEntrega;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public String getHoraCreacion() {
        return horaCreacion;
    }

    public String getStatus() {
        return status;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }
}
