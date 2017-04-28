package com.expandenegocio.veonegocio.models;

/**
 * Created by jesus on 28/04/2017.
 */

public enum ClavesFranquicia {
    NOMBRE;

    /**
     * @return un array con los nombres de las claves de este enum
     */
    public static String[] claves() {
        //modifica este método
        ClavesFranquicia[] todas = ClavesFranquicia.values();
        int longitud = todas.length;
        String[] nombres = new String[longitud];
        for (int i = 0; i < longitud; i++) {
            nombres[i] = todas[i].toString();
        }
        return nombres;
    }
}

