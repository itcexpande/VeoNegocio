package com.expandenegocio.veonegocio.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.expandenegocio.veonegocio.DAO.ProvinciaDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.Municipio;
import com.expandenegocio.veonegocio.models.Provincia;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by jesus on 30/03/2017.
 */

public class ActivityConsultas extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_consultas);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
