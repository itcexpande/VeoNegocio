package com.expandenegocio.veonegocio.activities;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jesus on 22/03/2017.
 */

public class GestoraImagenes extends ArrayList<Imagen> {
    private Activity activity;
    private Resources resources;


    public GestoraImagenes(Activity activity) {
        super();
        this.activity = activity;
        this.DefinirFotos();
    }

    private void DefinirFotos() {
        resources = activity.getResources();

        for (Imagenes uno : Imagenes.values()) {
            TypedArray imagenes = resources.obtainTypedArray(uno.getImagenes());
            this.agregarImagen(uno.getNombre(), imagenes);
            imagenes.recycle();
        }

    }

    private void agregarImagen(int nombre, TypedArray imagenes) {
        for (int i = 0; i < imagenes.length(); i++) {
            this.add(new Imagen(resources.getString(nombre), imagenes.getResourceId(i, 0)));
            Log.i("Imagen", activity.getResources().getString(nombre));
        }
    }
}
