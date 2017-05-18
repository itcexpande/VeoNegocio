package com.expandenegocio.veonegocio.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.expandenegocio.veonegocio.DAO.UserDataSource;
import com.expandenegocio.veonegocio.R;
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
 * Created by jesus on 20/03/2017.
 */

public class ActivityInicioSesion extends AppCompatActivity {

    private EditText emailUsuario;
    private EditText password;
    private TextView recuerdaContraseña;
    private String correo;
    private String pass;
    private User usuario;
    private String correoRemoto;
    private String passRemoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inicio_sesion);
        emailUsuario = (EditText) this.findViewById(R.id.campo_correo);
        password = (EditText) this.findViewById(R.id.campo_contrasena);
        recuerdaContraseña = (TextView) this.findViewById(R.id.recuerdoContraseña);

        usuario = new User();
        UserDataSource dataSource = new UserDataSource(this);
        usuario = dataSource.devuelveUsuario();


        if (usuario != null) {
            correo = usuario.getEmail().toString();
            pass = usuario.getPassword().toString();

            RequestParams params = new RequestParams();
            params.put("email", correo);
            params.put("password", pass);
            correoRemoto = null;
            passRemoto = null;
            invokeWS3(params);
            correoRemoto = emailUsuario.getText().toString();
            passRemoto = password.getText().toString();
            if (emailUsuario.getText().toString().equals(correoRemoto) && password.getText().toString().equals(passRemoto)) {

                Intent intent = new Intent("ActivityOpciones");
                intent.putExtra("correo", correo);
                intent.putExtra("password", pass);
                startActivity(intent);
            }

        }
    }


    public void inicioSesion(View view) {

        String val = validate();

        if (val == null) {

            usuario = createUsuario();

            procesarInformacion();

        } else {
            Toast.makeText(getApplicationContext(), val, Toast.LENGTH_LONG).show();
        }
    }

    private String validate() {

        String output = null;

        correo = emailUsuario.getText().toString();
        pass = password.getText().toString();

        if (correo.trim().equals("")) {
            output = "El campo correo no puede estar vacío";
        }
        if (!ValidatorUtil.validateEmail(correo)) {
            output = "El correo no es válido";
        }
        if (pass.trim().equals("")) {
            output = "El campo contraseña no puede estar vacío";
        }

        return output;
    }

    private User createUsuario() {

        User usuario = new User();

        usuario.setId(UUID.randomUUID().toString());
        usuario.setEmail(correo);
        usuario.setPassword(pass);

        return usuario;
    }


    private void procesarInformacion() {
        RequestParams params = new RequestParams();
        params.put("email", emailUsuario.getText().toString());
        params.put("password", password.getText().toString());


        invokeWS(params);
    }


    public void invokeWS(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        //  client.post("http://www.expandenegocio.com/app/info.php", params, new AsyncHttpResponseHandler() {
        client.post("http://www.expandenegocio.com/app/info_jesus.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);

                try {
                    // JSON Object

                    JSONObject obj = new JSONObject(response);

                    switch (obj.getInt("status")) {

                        case 0:
                            //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            Intent intent = new Intent("ActivityOpciones");
                            intent.putExtra("correo", emailUsuario.getText().toString());
                            intent.putExtra("password", password.getText().toString());
                            startActivity(intent);

                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Peticion no aceptada", Toast.LENGTH_LONG).show();
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

    public void recordarDatos(View view) {
        String val = validate2();

        if (val == null) {

            usuario = createUsuario2();

            UserDataSource dataSource = new UserDataSource(this);
            String nuevaContra = dataSource.buscaUsuarioPorEmail(usuario.getEmail().toString());
            if (nuevaContra != null) {
                // recuerdaContraseña.setText("SU CONTRASEÑA ES:===> "+nuevaContra.toString());
                procesarInformacion2();

            } else {
                Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), val, Toast.LENGTH_LONG).show();
        }

    }

    private String validate2() {

        String output = null;

        correo = emailUsuario.getText().toString();

        if (correo.trim().equals("")) {
            output = "El campo correo no puede estar vacío";
        }
        if (!ValidatorUtil.validateEmail(correo)) {
            output = "El correo no es válido";
        }

        return output;
    }

    private User createUsuario2() {

        User usuario = new User();

        usuario.setId(UUID.randomUUID().toString());
        usuario.setEmail(correo);

        return usuario;
    }

    private void procesarInformacion2() {
        RequestParams params = new RequestParams();
        params.put("email", emailUsuario.getText().toString());


        invokeWS2(params);
    }

    public void invokeWS2(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/restaura_password.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);

                try {
                    // JSON Object

                    JSONObject obj = new JSONObject(response);

                    switch (obj.getInt("status")) {

                        case 0:
                            //      Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            String nuevaContra = obj.getString("info");
                            int longitud = nuevaContra.length();
                            String nueva = nuevaContra.substring(14, longitud - 3);
                            recuerdaContraseña.setText("SU CONTRASEÑA ES:===> " + nueva.toString());


                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Peticion no aceptada", Toast.LENGTH_LONG).show();
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

    public void invokeWS3(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/retorna_usuario.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);

                try {
                    // JSON Object

                    JSONObject obj = new JSONObject(response);

                    switch (obj.getInt("status")) {

                        case 0:
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            //Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_LONG).show();
                            recogeDatos2(obj);
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Peticion no aceptada", Toast.LENGTH_LONG).show();
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
            emailUsuario.setText(var.get(UserDataSource.ColumnUsuarios.EMAIL).toString());
            password.setText(var.get(UserDataSource.ColumnUsuarios.PASSWORD).toString());


        }
    }


    public void registro(View view) {
        Intent intent = new Intent("ActivityAltaUsuario");
        intent.putExtra("correo", emailUsuario.getText().toString());
        intent.putExtra("password", password.getText().toString());
        startActivity(intent);


    }


}