package com.expandenegocio.veonegocio.models;


/**
 * Created by Jesus on 27/03/2017.
 */
public class User {
    private String id;
    private String email;
    private String password;
    private String status;
    private String nombre;
    private String apellidos;
    private String telefono;
    private Integer codigoProv;
    private Integer codigoMun;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getCodigoProv() {
        return codigoProv;
    }

    public void setCodigoProv(Integer codigoProv) {
        this.codigoProv = codigoProv;
    }

    public Integer getCodigoMun() {
        return codigoMun;
    }

    public void setCodigoMun(Integer codigoMun) {
        this.codigoMun = codigoMun;
    }
}