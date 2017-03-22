package com.expandenegocio.veonegocio.activities;

import com.expandenegocio.veonegocio.R;

/**
 * Created by jesus on 22/03/2017.
 */

public enum Imagenes {
    P1(R.string.f1_name, R.array.foto1),
    P2(R.string.f2_name, R.array.foto2),
    P3(R.string.f3_name, R.array.foto3),
    P4(R.string.f4_name, R.array.foto4),
    P5(R.string.f5_name, R.array.foto5),
    P6(R.string.f6_name, R.array.foto6),
    P7(R.string.f7_name, R.array.foto7),
    P8(R.string.f8_name, R.array.foto8),
    P9(R.string.f9_name, R.array.foto9);

    private final int nombre;
    private final int imagenes;


   private Imagenes(int nombre, int imagenes) {
        this.nombre = nombre;
        this.imagenes = imagenes;
    }
    public int getImagenes() {
        return imagenes;
    }

    public int getNombre() {
        return nombre;
    }
}
