package com.example.tareaconjunta;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String usuario;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
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
}
