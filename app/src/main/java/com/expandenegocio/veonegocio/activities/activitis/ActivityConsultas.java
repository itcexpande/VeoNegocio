package com.expandenegocio.veonegocio.activities.activitis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.expandenegocio.veonegocio.R;

/**
 * Created by jesus on 30/03/2017.
 */

public class ActivityConsultas extends AppCompatActivity {
    private GestoraCiudades gestora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_consultas);

        // final ImageView imagen = (ImageView) this.findViewById(R.id.imagen);
        final TextView comentario = (TextView) this
                .findViewById(R.id.textView1);
        final Spinner selectorCiudad = (Spinner) this
                .findViewById(R.id.spinner_consultas);
       /* final ScrollView panelScroll = (ScrollView) this
                .findViewById(R.id.scrollView1);
*/
        final ListView lista = (ListView) this.findViewById(R.id.listView_listadoTodas);
        final RadioButton radioButton1 = (RadioButton) this.findViewById(R.id.radio_button_todas);
        final RadioButton radioButton2 = (RadioButton) this.findViewById(R.id.radio_button_ConFiltros);
        if (radioButton1.isChecked()) {

        } else {
            final GestoraCiudades gestora = new GestoraCiudades(this);
            String[] from = gestora.clavesParaSpinner();
            int[] to = {R.id.textview_nombre_ciudad, R.id.textview_pais_ciudad};
            SimpleAdapter adaptador = new SimpleAdapter(this, gestora, R.layout.layout_consultas_spinner, from, to);
            selectorCiudad.setAdapter(adaptador);
        }


    }
}
