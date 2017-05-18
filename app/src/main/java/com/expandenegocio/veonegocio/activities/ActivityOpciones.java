package com.expandenegocio.veonegocio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.expandenegocio.veonegocio.DAO.ProvinciaDataSource;
import com.expandenegocio.veonegocio.DAO.UserDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.Provincia;
import com.expandenegocio.veonegocio.models.User;
import com.expandenegocio.veonegocio.utilities.ValidatorUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jesus on 05/05/2017.
 */

public class ActivityOpciones extends AppCompatActivity {
    private String nCorreo;
    private String nPassword;
    private int codigoProvincia;
    private String denominacionProvincia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_opciones);
        nCorreo = getIntent().getStringExtra("correo");
        nPassword = getIntent().getStringExtra("password");

    }


    public void ConsultaModificacionDeUsuarios(View view) {
        Intent intent = new Intent("ActivityConsultaUsuario");
        intent.putExtra("correo", nCorreo);
        intent.putExtra("password", nPassword);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void consultaModificacionDeUsuarios(MenuItem item) {
        Intent intent = new Intent("ActivityConsultaUsuario");
        intent.putExtra("correo", nCorreo);
        intent.putExtra("password", nPassword);
        startActivity(intent);
    }

    public void busquedaDeNegocio(MenuItem item) {
    }

    public void pasarProvincias(MenuItem item) {

        pasaProvincias();
    }

    private void pasaProvincias() {


        Provincia provincia = new Provincia();
        RequestParams params = new RequestParams();
        invokeWS(params);
        provincia = new Provincia();
        provincia.setId(codigoProvincia);
        provincia.setNombreProvincia(denominacionProvincia);

        ProvinciaDataSource dataSource = new ProvinciaDataSource(this);
        dataSource.insertProvincia(provincia);


    }


    public void invokeWS(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/devuelve_provincias.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);


                try {

                    JSONObject obj = new JSONObject(response);

                    switch (obj.getInt("status")) {

                        case 0:
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            recogeDatos2(obj);
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Ya hay un usuario registrado con ese correo", Toast.LENGTH_LONG).show();
                            break;
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                    error) {
                try {
                    if (responseBody != null) {
                        String response = new String(responseBody);
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private void recogeDatos2(JSONObject obj) throws JSONException {
        JSONArray datos = obj.getJSONArray("info");
        int longitud = datos.length();
        for (int x = 0; x < longitud; x++) {
            JSONObject var = datos.getJSONObject(x);

            codigoProvincia = Integer.parseInt(var.get(ProvinciaDataSource.ColumnProvincia.ID).toString());
            denominacionProvincia = var.get(ProvinciaDataSource.ColumnProvincia.NOMBRE).toString();
        }
    }

}