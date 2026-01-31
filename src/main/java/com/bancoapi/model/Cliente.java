package com.bancoapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona{

    private String contrasena;
    private Boolean estado;

    // getters y setters

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}
