package com.expandenegocio.veonegocio.activities;

/**
 * Created by Jesus on 27/03/2017.
 */
public class Cliente {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String mail;
    private String otros;

    public Cliente() {
    }

    public Cliente(String nombre, String apellidos, String telefono, String mail, String otros) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.mail = mail;
        this.otros = otros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }
}
