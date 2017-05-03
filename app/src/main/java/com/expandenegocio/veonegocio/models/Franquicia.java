package com.expandenegocio.veonegocio.models;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by jesus on 28/04/2017.
 */

public class Franquicia extends HashMap<String, String> implements Comparable<Franquicia> {

    private String nombre;

    public Franquicia() {
    }
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

    @Override
    public int compareTo(Franquicia f) {

        String d1 = this.get(ClavesFranquicia.NOMBRE.toString()).toString();
        String d2 = f.get(ClavesFranquicia.NOMBRE.toString()).toString();

        int[] criterio = {
                d1.compareTo(d2)
        };
        for (int uno : criterio) {
            if (uno != 0) {
                return uno;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Franquicia) {
            Franquicia unaFranquicia = (Franquicia) object;
            if (this.compareTo(unaFranquicia) == 0) {
                return true;
            } else {
                return false;
            }
        }
        return super.equals(object);
    }

}