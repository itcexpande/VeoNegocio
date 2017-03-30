package com.expandenegocio.veonegocio.activities.activitis;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.expandenegocio.veonegocio.R;

import java.util.ArrayList;

/**
 * Created by Jesus on 16/11/2016.
 */
public class GestoraPais extends ArrayList<Pais> {
    private GestoraPais gestora;

    public GestoraPais(Context c) {
        Resources res = c.getResources();
        String[] nombrespaises = res.getStringArray(R.array.paises);

        int numero = nombrespaises.length;


        for (int x = 0; x < numero; x++) {
            Pais unPais = new Pais(nombrespaises[x]);
            this.add(unPais);
        }


    }
}