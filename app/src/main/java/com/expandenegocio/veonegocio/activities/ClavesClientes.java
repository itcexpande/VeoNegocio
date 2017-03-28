package com.expandenegocio.veonegocio.activities;

/**
 * Created by jesus on 28/03/2017.
 */

public enum ClavesClientes {
    NOMBRE, APELLIDOS, TELEFONO, MAIL, OTROS;

    public static String[] Claves() {
        ClavesClientes[] todas = ClavesClientes.values();
        int longitud = todas.length;
        String[] nombresClaves = new String[longitud];
        for (int i = 0; i < longitud; i++) {
            nombresClaves[i] = todas[i].toString();
        }
        return nombresClaves;
    }
}