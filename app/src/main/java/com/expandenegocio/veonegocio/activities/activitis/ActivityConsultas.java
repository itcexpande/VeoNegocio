package com.expandenegocio.veonegocio.activities.activitis;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.expandenegocio.veonegocio.R;

/**
 * Created by jesus on 30/03/2017.
 */

public class ActivityConsultas extends AppCompatActivity {
    private GestoraCiudades gestora;
    //    private CharSequence[] datos;
    private Resources res;
    private ListView lista;
    private Spinner selectorCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_consultas);

        // final ImageView imagen = (ImageView) this.findViewById(R.id.imagen);
        //   final TextView comentario = (TextView) this.findViewById(R.id.textView1);
        final Spinner selectorCiudad = (Spinner) this.findViewById(R.id.spinner_consultas);
       /* final ScrollView panelScroll = (ScrollView) this
                .findViewById(R.id.scrollView1);
*/
        final ListView lista = (ListView) this.findViewById(R.id.listView_listadoTodas);
        final RadioButton radioButtonTodos = (RadioButton) this.findViewById(R.id.radio_button_todas);
        final RadioButton radioButtonFiltros = (RadioButton) this.findViewById(R.id.radio_button_ConFiltros);
        /*
        if (radioButtonTodos.isChecked()==true) {


            final GestoraPais gestora = new GestoraPais(this);
            String[] from = Pais.getClaves();
            int[] to = {R.id.text_mapa};
            SimpleAdapter elAdaptador = new SimpleAdapter(this, gestora, R.layout.item_listview, from, to);
            lista.setAdapter(elAdaptador);


        } else {


            final GestoraCiudades gestora = new GestoraCiudades(this);
            String[] from = gestora.clavesParaSpinner();
            int[] to = {R.id.textview_nombre_ciudad, R.id.textview_pais_ciudad};
            SimpleAdapter adaptador = new SimpleAdapter(this, gestora, R.layout.layout_consultas_spinner, from, to);
            selectorCiudad.setAdapter(adaptador);
        }
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void todas(View view) {
        Toast.makeText(this, "listview", Toast.LENGTH_LONG).show();
        final GestoraPais gestora = new GestoraPais(this);
        String[] from = Pais.getClaves();
        int[] to = {R.id.text_mapa};
        SimpleAdapter elAdaptador = new SimpleAdapter(this, gestora, R.layout.item_listview, from, to);
        lista.setAdapter(elAdaptador);

    }

    public void filtrar(View view) {

        Toast.makeText(this, "spinner", Toast.LENGTH_LONG).show();

        final GestoraCiudades gestora = new GestoraCiudades(this);
        String[] from = gestora.clavesParaSpinner();
        int[] to = {R.id.textview_nombre_ciudad, R.id.textview_pais_ciudad};
        SimpleAdapter adaptador = new SimpleAdapter(this, gestora, R.layout.layout_consultas_spinner, from, to);
        selectorCiudad.setAdapter(adaptador);

    }
}
