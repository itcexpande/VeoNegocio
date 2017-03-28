package com.expandenegocio.veonegocio.activities.activitis;


/**
 * Created by Jesus on 27/03/2017.
 */
public class User {
    private String Id;
    private String Nombre;
    private String Apellidos;
    private String email;

    public User(String id, String nombre, String apellidos, String email) {
        Id = id;
        Nombre = nombre;
        Apellidos = apellidos;
        this.email = email;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}