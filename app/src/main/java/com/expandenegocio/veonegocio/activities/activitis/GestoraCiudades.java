package com.expandenegocio.veonegocio.activities.activitis;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.expandenegocio.veonegocio.R;

import java.util.ArrayList;

/**
 * Created by jesus on 30/03/2017.
 */

public class GestoraCiudades extends ArrayList<Ciudad> {

    public GestoraCiudades(Context c) {
        String[] array = Claves.arrayClaves();
        Resources res = c.getResources();
        String[] nombresCiudades = res.getStringArray(R.array.nombresCiudades);
        //   TypedArray fotos = res.obtainTypedArray(R.array.fotos);
        //   TypedArray fotosIcono = res.obtainTypedArray(R.array.fotos_icono);
        String[] comentariosCiudades = res
                .getStringArray(R.array.comentariosCiudad);
        String[] paises = res
                .getStringArray(R.array.paises);
        for (int x = 0; x < nombresCiudades.length; x++) {
            this.add(new Ciudad(nombresCiudades[x],  paises[x],comentariosCiudades[x]));
        }
    }

    public String[] clavesParaSpinner() {

        String[] clavesSpinner = {Claves.NOMBRE.toString(), Claves.PAIS.toString()};
        return clavesSpinner;

    }


}