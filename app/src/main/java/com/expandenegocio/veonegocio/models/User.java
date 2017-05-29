package com.expandenegocio.veonegocio.models;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

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
    private String sectorActividad;
    private String planInversion;
    private String cuandoEmpezar;
    private String perfilProfesional;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

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

    public Integer getCodigoProv() {
        return codigoProv;
    }

    public void setCodigoProv(Integer codigoProv) {
        this.codigoProv = codigoProv;
    }

    public String getSectorActividad() {
        return sectorActividad;
    }

    public void setSectorActividad(String sectorActividad) {
        this.sectorActividad = sectorActividad;
    }

    public String getPlanInversion() {
        return planInversion;
    }

    public void setPlanInversion(String planInversion) {
        this.planInversion = planInversion;
    }

    public String getCuandoEmpezar() {
        return cuandoEmpezar;
    }

    public void setCuandoEmpezar(String cuandoEmpezar) {
        this.cuandoEmpezar = cuandoEmpezar;
    }

    public String getPerfilProfesional() {
        return perfilProfesional;
    }

    public void setPerfilProfesional(String perfilProfesional) {
        this.perfilProfesional = perfilProfesional;
    }

    @Override
    public String toString() {
        return nombre;
    }
}