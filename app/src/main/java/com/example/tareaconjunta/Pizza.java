package com.example.tareaconjunta;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Pizza implements Serializable {
    private HashMap<String, Double> ingredientes;
    private String tamano;
    private double precioTamano;
    private double precioTotal;

    public Pizza() {
        this.ingredientes = new HashMap<>();
    }

    public HashMap<String, Double> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(HashMap<String, Double> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public double getPrecioTamano() {
        return precioTamano;
    }

    public void setPrecioTamano(double precioTamano) {
        this.precioTamano = precioTamano;
    }

    public double calcularPrecio() {

        ingredientes.forEach((key, value) ->{

        });

        return 0;
    }
}
