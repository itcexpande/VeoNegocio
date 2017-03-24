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
    private ListView mLeadsList;
    private ArrayAdapter<String> mLeadsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_terminos_de_servicio);
        mLeadsList = (ListView) this.findViewById(R.id.list_view_terminos_de_servicio);

        String[] leadsNames = {
                "Con cien cañones por banda," +
                        "\nviento en popa a toda vela," +
                        "\nno corta el mar, sino vuela," +
                        "\nun velero bergantín:" +
                        "\n\nbajel pirata que llaman" +
                        "\npor su bravura el Temido," +
                        "\nen todo mar conocido" +
                        "\ndel uno al otro confín." +
                        "\n\nLa luna en el mar riela," +
                        "\nen la lona gime el viento," +
                        "\ny alza en blando movimiento" +
                        "\nolas de plata y azul;" +
                        "\n\ny ve el capitán pirata," +
                        "\ncantando alegre en la popa," +
                        "\nAsia a un lado, al otro Europa" +
                        "\ny allá a su frente Stambul. " +

                        "\n\nNavega, velero mío," +
                        "sin temor," +
                        "\nque ni enemigo navío," +
                        "\nni tormenta, ni bonanza," +
                        "\ntu rumbo a torcer alcanza," +
                        "\nni a sujetar tu valor. " +

                        "\n\nVeinte presas" +
                        "hemos hecho" +
                        "\na despecho" +
                        "del inglés," +
                        "\ny han rendido" +
                        "sus pendones" +
                        "\ncien naciones" +
                        "a mis pies. " +

                        "\n\n¿Qué es mi barco?Mi tesoro." +
                        "\n¿Qué es mi Dios?La libertad. " +
                        "\n¿Mi ley? ¡La fuerza y el viento !" +
                        "\n¿Mi única patria ? ¡La mar ! "


        };

        mLeadsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                leadsNames);
        mLeadsList.setAdapter(mLeadsAdapter);

    }
}
