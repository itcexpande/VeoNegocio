package com.expandenegocio.veonegocio.models;

/**
 * Created by jesus on 05/04/2017.
 */

public class Provincia {

    private int id;
    private String nombreProvincia;

    public Provincia(int id, String nombreProvincia) {
        this.id = id;
        this.nombreProvincia = nombreProvincia;
    }

    public Provincia() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    @Override
    public String toString() {
        return nombreProvincia;
    }
}
