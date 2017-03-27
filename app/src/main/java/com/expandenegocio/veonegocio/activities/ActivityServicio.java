package com.expandenegocio.veonegocio.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.expandenegocio.veonegocio.R;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jesus on 23/03/2017.
 */

public class ActivityServicio extends AppCompatActivity implements View.OnClickListener {

    WebView simpleWebView;
    Button loadWebPage, loadFromStaticHtml;
    String customHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_servicio_privacidad);
        loadFromStaticHtml = (Button) findViewById(R.id.privacidad);
        loadFromStaticHtml.setOnClickListener(this);
        loadWebPage = (Button) findViewById(R.id.servicios);
        loadWebPage.setOnClickListener(this);
        simpleWebView = (WebView) findViewById(R.id.simpleWebView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.servicios:

                customHtml = leerArchivo(R.raw.servicios);

                simpleWebView.loadData(customHtml, "text/html", "UTF-8");


                break;
            case R.id.privacidad:
                customHtml = leerArchivo(R.raw.privacidad);

                simpleWebView.loadData(customHtml, "text/html", "UTF-8");
                break;
        }
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