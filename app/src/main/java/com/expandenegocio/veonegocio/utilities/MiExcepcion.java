package com.expandenegocio.veonegocio.utilities;

import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by jesus on 27/03/2017.
 */

public class MiExcepcion extends Exception {


    private EditText vista;
    private Spinner vista1;

   /* public MiExcepcion(EditText v, String mensaje) {
        super(mensaje);
        this.setVista(v);

    }

    public EditText getVista() {
        return vista;
    }

    public void setVista(EditText vista) {
        this.vista = vista;
    }*/

    public MiExcepcion(EditText v, Spinner v1,String mensaje) {
        super(mensaje);
        this.setVista(v);
        this.setVista1(v1);
    }

    public MiExcepcion(EditText v,String mensaje) {
        super(mensaje);
        this.setVista(v);

    }

    public EditText getVista() {
        return vista;
    }

    public void setVista(EditText vista) {
        this.vista = vista;
    }

    public Spinner getVista1() {
        return vista1;
    }

    public void setVista1(Spinner vista1) {
        this.vista1 = vista1;
    }
}
