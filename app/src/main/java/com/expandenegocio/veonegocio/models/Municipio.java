package com.expandenegocio.veonegocio.models;

/**
 * Created by jesus on 05/04/2017.
 */

public class Municipio {

    //   private int c_provincia;
    private int c_municipio;
    // private String nombre_provincia;
    private String nombre_municipio;
    private Provincia provincia;

    public int getC_municipio() {
        return c_municipio;
    }

    public void setC_municipio(int c_municipio) {
        this.c_municipio = c_municipio;
    }

    public String getNombre_municipio() {
        return nombre_municipio;
    }

    public void setNombre_municipio(String nombre_municipio) {
        this.nombre_municipio = nombre_municipio;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return nombre_municipio;
    }
}
