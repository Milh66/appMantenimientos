package com.example.appmantenimientos;

public class ProformaItem {
    private String codigo;
    private String producto;
    private double precio;
    private int cantidad;

    // Constructor
    public ProformaItem(String codigo, String producto, double precio, int cantidad) {
        this.codigo = codigo;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getProducto() {
        return producto;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Setters
    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Método para calcular el total
    public double getTotal() {
        return precio * cantidad;
    }

    // Método para mostrar el formato en la lista (ListView)
    @Override
    public String toString() {
        return "DNI: " + codigo + " | " + producto + " | S/. " + precio + " | Cant: " + cantidad + " | Total: S/. " + getTotal();
    }
}