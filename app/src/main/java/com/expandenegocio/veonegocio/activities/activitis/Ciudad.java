package com.expandenegocio.veonegocio.activities.activitis;

import java.util.HashMap;

/**
 * Created by jesus on 30/03/2017.
 */

public class Ciudad extends HashMap<String, Object> {

    public Ciudad(String nombre, String pais,String comentario) {
        this.put(Claves.NOMBRE.toString(), nombre);
        this.put(Claves.PAIS.toString(), pais);
        this.put(Claves.COMENTARIO.toString(),comentario);
    }

    public String getPais() {

        return (String) this.get(Claves.PAIS.toString());
    }

    public String getNombre() {
        return (String) this.get(Claves.NOMBRE.toString());
    }

    public String getComentario() {

        return (String) this.get(Claves.COMENTARIO.toString());
    }

}