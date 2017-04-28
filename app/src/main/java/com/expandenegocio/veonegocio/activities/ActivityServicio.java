package com.expandenegocio.veonegocio.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.expandenegocio.veonegocio.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jesus on 23/03/2017.
 */

public class ActivityServicio extends AppCompatActivity  {

    WebView simpleWebViewServicio;
    String customHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_servicio);
        simpleWebViewServicio = (WebView) findViewById(R.id.simpleWebView_servicio);
        customHtml = leerArchivo(R.raw.servicios);

        simpleWebViewServicio.loadData(customHtml, "text/html", "UTF-8");

    }

    private String leerArchivo(int queArchivoLeemos) {
        try {
            InputStreamReader isr = new InputStreamReader(this.getResources().openRawResource(queArchivoLeemos));
            BufferedReader br = new BufferedReader(isr);

            String linea;

            StringBuilder texto = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                texto.append(linea);
                texto.append("\n");
            }
            br.close();
            isr.close();

            return texto.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}