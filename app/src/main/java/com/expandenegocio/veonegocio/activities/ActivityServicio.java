package com.expandenegocio.veonegocio.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.expandenegocio.veonegocio.R;

/**
 * Created by jesus on 23/03/2017.
 */

public class ActivityServicio extends AppCompatActivity {
    private TextView textView;
    private CharSequence[] datos;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_terminos_de_servicio);
        ListView lista = (ListView) this.findViewById(R.id.list_view_terminos_de_servicio);

    //    String[] from={"",""};

      // int[] to = {R.id.texto_servicio_1};

       // SimpleAdapter elAdaptador = new SimpleAdapter(this, gestora, R.layout.layout_items_terminos_de_servicio, from, to);
      // lista.setAdapter(elAdaptador);
       // ListView lista;
        ArrayAdapter<String> adaptador;

       // lista = (ListView)findViewById(R.id.listView);

        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        lista.setAdapter(adaptador);

    }
}
