package com.example.tareaconjunta;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String usuario;
    private String direccion;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Usuario(String usuario, String password, String direccion) {
        this.usuario = usuario;
        this.password = password;
        this.direccion = direccion;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    private String password;

    public String getDireccion() {
        return this.direccion;
    }
}
