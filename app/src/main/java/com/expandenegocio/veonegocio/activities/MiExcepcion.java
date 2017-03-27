package com.expandenegocio.veonegocio.activities;

import android.widget.EditText;

/**
 * Created by jesus on 27/03/2017.
 */

class MiExcepcion extends Exception {


    private EditText vista;

    public MiExcepcion(EditText v, String mensaje) {
        super(mensaje);
        this.setVista(v);

    }

    public EditText getVista() {
        return vista;
    }

    public void setVista(EditText vista) {
        this.vista = vista;
    }

}
