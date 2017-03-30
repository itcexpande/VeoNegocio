package com.expandenegocio.veonegocio.activities.activitis;

import java.util.HashMap;

/**
 * Created by Jesus on 16/11/2016.
 */
public class Pais extends HashMap<String, Object> {
    private static String[] claves = {"nombre"};

    public Pais(String nombre) {

        super();

        this.put(claves[1], nombre);

    }

    public static  String[] getClaves() {
        return claves;
    }


}
