package com.expandenegocio.veonegocio.activities.activitis;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.expandenegocio.veonegocio.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;


/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityInicioSesion extends AppCompatActivity {

    private EditText emailUsuario;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inicio_sesion);
        emailUsuario = (EditText) this.findViewById(R.id.campo_correo);
        password = (EditText) this.findViewById(R.id.campo_contrasena);
        limpiar();

    }

    public void inicioSesion(View view) {
        try {
            comprobarEntrada();
            procesarInformacion();
            limpiar();
        } catch (MiExcepcion e) {
            Toast.makeText(getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            e.getVista().selectAll();
            e.getVista().requestFocus();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

    }


    private void limpiar() {
        emailUsuario.setText("");
        password.setText("");
        emailUsuario.requestFocus();
    }

    private void comprobarEntrada() throws MiExcepcion {
        String mensajeErrorMail = this.getString(R.string.mensajeToastMail);
        String mensajeErrorSinPassword = getString(R.string.faltaDatoPassword);

        if (emailUsuario.getText().toString().equals("")) {
            throw new MiExcepcion(emailUsuario, mensajeErrorMail);
        }

        if (password.getText().toString().equals("")) {
            throw new MiExcepcion(password, mensajeErrorSinPassword);
        }

    }

    private void procesarInformacion() {
        RequestParams params = new RequestParams();
//        params.put("name", emailUsuario.getText().toString());
        // Put Http parameter username with value of Email Edit View control
        params.put("email", emailUsuario.getText().toString());
        // Put Http parameter password with value of Password Edit View control
        params.put("pwd", password.getText().toString());


        // Invoke RESTful Web Service with Http parameters
        invokeWS(params);

    }


    public void recordarDatos(View view) {
    }

    public void registro(View view) {
        Intent intent = new Intent("AcivityRegistro");
        startActivity(intent);
    }

    public void invokeWS(RequestParams params) {
        // Show Progress Dialog
        //prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/signup.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);

                //prgDialog.hide();
                try {
                    // JSON Object

                    JSONObject obj = new JSONObject(response);

                    switch (obj.getInt("status")) {

                        case 0:
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Registrado correctamente!", Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Ya hay un usuario registrado con ese correo", Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "ir a activity de la aplicacion", Toast.LENGTH_LONG).show();

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

                //prgDialog.hide();

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
}