package com.expandenegocio.veonegocio.activities;

import java.util.HashMap;

/**
 * Created by jesus on 22/03/2017.
 */

public class Imagen extends HashMap<String,Object> {
    private static String[] claves= {"imagen","nombre"};
    public Imagen(String p,int id_recurso_imagen){
        this.put(claves[0],id_recurso_imagen);
        this.put(claves[1],p);
    }
    public static String[] getClaves(){
        return claves;
    }
}
