package com.expandenegocio.veonegocio.models;

import java.util.HashMap;

/**
 * Created by jesus on 28/04/2017.
 */

public class Franquicia extends HashMap<String, String> {
    private String nombre;

    public Franquicia(String nombre) {
        this.put(ClavesFranquicia.NOMBRE.toString(), nombre);
    }

    public String getNombre() {
        return get(ClavesFranquicia.NOMBRE.toString());
    }

    public void setNombre(String nombre) {
        this.put(ClavesFranquicia.NOMBRE.toString(), nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
