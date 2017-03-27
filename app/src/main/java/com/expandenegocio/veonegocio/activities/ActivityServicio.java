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

import com.expandenegocio.veonegocio.R;

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

                customHtml = "<html><body><h1>SERVICIOS</h1>" +
                        "<h1>Titulo  1</h1><h2>Titulo 2</h2><h3>JESUS</h3>" +
                        "<h3>  HTML En Web view</h3>" +
                        "<p>Esto solo es una prueba linea 01</p>" +
                        "<p>Esto solo es una prueba linea 02</p>" +
                        "<p>Esto solo es una prueba linea 03</p>" +
                        "<p>Esto solo es una prueba linea 04</p>" +
                        "<p>Esto solo es una prueba linea 05</p>" +
                        "<p>Esto solo es una prueba linea 06</p>" +
                        "<p>Esto solo es una prueba linea 07</p>" +
                        "<p>Esto solo es una prueba linea 08</p>" +
                        "<p>Esto solo es una prueba linea 09</p>" +
                        "<p>Esto solo es una prueba linea 10</p>" +

                        "</body></html>";

                simpleWebView.loadData(customHtml, "text/html", "UTF-8");
                break;
            case R.id.privacidad:
                customHtml = "<html><body><h1>POLITICA PRIVACIDAD</h1>" +
                        "<h1>Titulo 1</h1><h2>Titulo 2</h2><h3>JESUS</h3>" +

                        "<p>Política de Privacidad Última modificación: 1 de marzo de"
                        + "2017 (ver versiones archivadas) (Los ejemplos con hiperenlaces están"
                        + "disponibles al final de este documento). Existen muchas formas diferentes de"
                        + "utilizar nuestros servicios para buscar y compartir información, ponerte en"
                        + "contacto con otros usuarios o crear contenido nuevo. Cuando compartes"
                        + "información con nosotros, por ejemplo, al crear una cuenta de Google, podemos"
                        + "mejorar esos servicios para mostrarte anuncios y resultados de búsqueda más"
                        + "relevantes, ayudarte a conectar con personas o compartir contenido con otros"
                        + "usuarios de forma más fácil y rápida. Ya que eres usuario de nuestros"
                        + "servicios, queremos que entiendas cómo usamos la información y lo que puedes"
                        + "hacer para proteger tu privacidad. La presente Política de privacidad describe:"
                        + "Qué datos recogemos y los fines para los que llevamos a cabo su recogida Cómo"
                        + "utilizamos esos datos Las opciones que ofrecemos, incluyendo cómo acceder a los"
                        + "datos y actualizarlos. Aunque hemos intentado proporcionar una descripción lo"
                        + "más sencilla posible, si no estás familiarizado con términos clave tales como"
                        + "«cookies», «dirección IP», «contadores de visitas» y «navegador», consulta esta"
                        + "página para informarte de su significado antes de continuar. Tu privacidad es"
                        + "importante para Google, por lo que con independencia de que seas un usuario"
                        + "nuevo o un usuario avanzado, te recomendamos que leas nuestra política y te"
                        + "pongas en contacto con nosotros si tienes cualquier duda. Datos recogidos por"
                        + "Google Recopilamos información para ofrecer mejores servicios a todos nuestros</p>" +


                        "</body></html>";
                simpleWebView.loadData(customHtml, "text/html", "UTF-8");
                break;
        }
    }

   /* private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }*/


}