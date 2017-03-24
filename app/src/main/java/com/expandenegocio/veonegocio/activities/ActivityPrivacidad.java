package com.expandenegocio.veonegocio.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.expandenegocio.veonegocio.R;

/**
 * Created by jesus on 23/03/2017.
 */

public class ActivityPrivacidad extends Activity {
    private ListView mLeadsList;
    private ArrayAdapter<String> mLeadsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_politica_privacidad);
        mLeadsList = (ListView) this.findViewById(R.id.list_view_politica_deprivacidad);

        String[] leadsNames = {
                "Política de Privacidad" +
                        "Última modificación: 1 de marzo de 2017 (ver versiones archivadas) (Los ejemplos con hiperenlaces están disponibles" +
                        "al final de este documento)." +
                        "Existen muchas formas diferentes de utilizar nuestros servicios para buscar y compartir información, ponerte en" +
                        "contacto con otros usuarios o crear contenido nuevo. Cuando compartes información con nosotros, por ejemplo, al" +
                        "crear una cuenta de Google, podemos mejorar esos servicios para mostrarte anuncios y resultados de búsqueda" +
                        "más relevantes, ayudarte a conectar con personas o compartir contenido con otros usuarios de forma más fácil" +
                        "y rápida. Ya que eres usuario de nuestros servicios, queremos que entiendas cómo usamos la información y lo que" +
                        "puedes hacer para proteger tu privacidad." +
                        "La presente Política de privacidad describe:" +
                        "Qué datos recogemos y los fines para los que llevamos a cabo su recogida" +
                        "Cómo utilizamos esos datos"


        };

        mLeadsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                leadsNames);
        mLeadsList.setAdapter(mLeadsAdapter);


    }
}

