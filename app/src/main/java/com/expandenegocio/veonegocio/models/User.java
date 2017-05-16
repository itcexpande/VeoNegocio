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
    private Integer codigoProv;
    private Integer codigoMun;
    private String capital;
    private String capitalObservaciones;
    private Integer cerrada;
    private String cuandoEmpezar;
    private String dateEntered;
    private String dateModified;
    private Integer deleted;
    private String disponeContacto;
    private Integer disponeLocal;
    private String empresa;
    private String negocio;
    private Integer negocioAnterior;
    private String perfilFranquicia;
    private String perfilProfesional;
    private String phoneHome;
    private String phoneMobile;
    private String recursosPropios;
    private String situacionProfesional;

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

    public Integer getCodigoMun() {
        return codigoMun;
    }

    public void setCodigoMun(Integer codigoMun) {
        this.codigoMun = codigoMun;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapitalObservaciones() {
        return capitalObservaciones;
    }

    public void setCapitalObservaciones(String capitalObservaciones) {
        this.capitalObservaciones = capitalObservaciones;
    }

    public Integer getCerrada() {
        return cerrada;
    }

    public void setCerrada(Integer cerrada) {
        this.cerrada = cerrada;
    }

    public String getCuandoEmpezar() {
        return cuandoEmpezar;
    }

    public void setCuandoEmpezar(String cuandoEmpezar) {
        this.cuandoEmpezar = cuandoEmpezar;
    }

    public String getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getDisponeContacto() {
        return disponeContacto;
    }

    public void setDisponeContacto(String disponeContacto) {
        this.disponeContacto = disponeContacto;
    }

    public Integer getDisponeLocal() {
        return disponeLocal;
    }

    public void setDisponeLocal(Integer disponeLocal) {
        this.disponeLocal = disponeLocal;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNegocio() {
        return negocio;
    }

    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }

    public Integer getNegocioAnterior() {
        return negocioAnterior;
    }

    public void setNegocioAnterior(Integer negocioAnterior) {
        this.negocioAnterior = negocioAnterior;
    }

    public String getPerfilFranquicia() {
        return perfilFranquicia;
    }

    public void setPerfilFranquicia(String perfilFranquicia) {
        this.perfilFranquicia = perfilFranquicia;
    }

    public String getPerfilProfesional() {
        return perfilProfesional;
    }

    public void setPerfilProfesional(String perfilProfesional) {
        this.perfilProfesional = perfilProfesional;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getRecursosPropios() {
        return recursosPropios;
    }

    public void setRecursosPropios(String recursosPropios) {
        this.recursosPropios = recursosPropios;
    }

    public String getSituacionProfesional() {
        return situacionProfesional;
    }

    public void setSituacionProfesional(String situacionProfesional) {
        this.situacionProfesional = situacionProfesional;
    }

    @Override
    public String toString() {
        return nombre;
    }
}