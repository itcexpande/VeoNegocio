package com.expandenegocio.veonegocio.activities.activitis;

/**
 * Created by jesus on 30/03/2017.
 */

public enum Claves {
    NOMBRE, PAIS,COMENTARIO;

    public static String[] arrayClaves() {

        Claves[] valores = Claves.values();
        String[] arrayClaves = new String[valores.length];
        for (int i = 0; i < valores.length; i++) {

            arrayClaves[i] = valores[i].toString();

        }
        return arrayClaves;

    }
}