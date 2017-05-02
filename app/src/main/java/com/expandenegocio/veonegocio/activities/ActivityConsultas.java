package com.expandenegocio.veonegocio.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.ClavesFranquicia;
import com.expandenegocio.veonegocio.models.Franquicia;
import com.expandenegocio.veonegocio.models.GestoraFranquicia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jesus on 30/03/2017.
 */

public class ActivityConsultas extends AppCompatActivity {

    private ListView lista;
    public static SimpleAdapter adaptador;
    private GestoraFranquicia gestora;

    public GestoraFranquicia getGestora() {
        return gestora;
    }

    public static SimpleAdapter getAdaptador() {
        return adaptador;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_consultas);
        if (gestora == null) {
            gestora = new GestoraFranquicia();
        }

        lista = (ListView) findViewById(R.id.listView_franquicias);
        this.registerForContextMenu(lista);

        String[] from = ClavesFranquicia.claves();
        int[] to = {R.id.text_nombre_franquicia};

        adaptador = new SimpleAdapter(this, gestora, R.layout.item_listview, from, to);
        lista.setAdapter(adaptador);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void todas(View view) {
/*
        final GestoraPais gestora = new GestoraPais(this);
        String[] from = Pais.getClaves();
        int[] to = {R.id.text_mapa};
        SimpleAdapter elAdaptador = new SimpleAdapter(this, gestora, R.layout.item_listview, from, to);
        lista.setAdapter(elAdaptador);
*/
    }

    public void filtrar(View view) {

/*
        final GestoraCiudades gestora = new GestoraCiudades(this);
        String[] from = gestora.clavesParaSpinner();
        int[] to = {R.id.textview_nombre_ciudad, R.id.textview_pais_ciudad};
        SimpleAdapter adaptador = new SimpleAdapter(this, gestora, R.layout.layout_consultas_spinner, from, to);
        selectorCiudad.setAdapter(adaptador);
*/
    }
}
