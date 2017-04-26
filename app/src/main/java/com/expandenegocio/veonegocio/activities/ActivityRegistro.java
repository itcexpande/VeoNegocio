package com.expandenegocio.veonegocio.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.expandenegocio.veonegocio.DAO.MunicipioDataSource;
import com.expandenegocio.veonegocio.DAO.ProvinciaDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.Municipio;
import com.expandenegocio.veonegocio.models.Provincia;
import com.expandenegocio.veonegocio.utilities.MiExcepcion;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;


import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityRegistro extends AppCompatActivity {

    private String correo;
    private String password;
    private String nombre;
    private String apellidos;
    private String telefono;
    private Spinner spnProvincia;
    private Spinner spnMunicipio;
    private Provincia provincia;
    private Municipio municipio;

    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtCorreo;
    private EditText txtPassword;
    private EditText txtTelefono;
    private Provincia provinciaSeleccionada;
    private Municipio municipioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registro);

        provincia = loadSpinnerProvincias();
        loadSpinnerMunicipios(provincia.getNombreProvincia());


    }

    private Municipio loadSpinnerMunicipios(String nombreProvincia) {
        MunicipioDataSource dataSource = new MunicipioDataSource(this);
        final ArrayList<Municipio> listaMunicipios = dataSource.getMunicipios(nombreProvincia);

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaMunicipios);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnMunicipio = (Spinner) findViewById(R.id.spinner_municipio);

        spnMunicipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                   @Override
                                                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                       Municipio municipoSeleccionado = listaMunicipios.get(position);
                                                   }

                                                   @Override
                                                   public void onNothingSelected(AdapterView<?> parent) {
                                                       Municipio municipioSeleccionado = null;
                                                   }
                                               }
        );

        spnMunicipio.setAdapter(spinner_adapter);


        return municipioSeleccionado;
    }

    private Provincia loadSpinnerProvincias() {

        ProvinciaDataSource dataSource = new ProvinciaDataSource(this);
        final ArrayList<Provincia> listaProv = dataSource.getProvincias();

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaProv);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnProvincia = (Spinner) findViewById(R.id.spinner_provincia);

        spnProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                   @Override
                                                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                       Provincia provinciaSeleccionada = listaProv.get(position);
                                                   }

                                                   @Override
                                                   public void onNothingSelected(AdapterView<?> parent) {
                                                       Provincia provinciaSeleccionada = null;
                                                   }
                                               }
        );

        spnProvincia.setAdapter(spinner_adapter);

        return provinciaSeleccionada;

    }

    public void aceptarRegistro(View view) {
        try {
            comprobarEntrada();
            procesarInformacion();
            Intent intent = new Intent("ActivityInicioSesion");
            startActivity(intent);

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


    private void comprobarEntrada() throws MiExcepcion {

        String mensajeErrorSinNombre = this.getString(R.string.mensajeToastFaltaDato);
        String mensajeErrorSinApellidos = getString(R.string.faltaDatoApellidos);
        String mensajeErrorMail = this.getString(R.string.mensajeToastMail);
        String mensajeErrorSinPassword = getString(R.string.faltaDatoPassword);
        String mensajeErrorSinTelefono = "Falta dato teléfono";

        if (txtCorreo.getText().toString().equals("")) {
            throw new MiExcepcion(txtCorreo, mensajeErrorMail);
        }

        if (txtPassword.getText().toString().equals("")) {
            throw new MiExcepcion(txtPassword, mensajeErrorSinPassword);
        }

        if (txtNombre.getText().toString().equals("")) {
            throw new MiExcepcion(txtNombre, mensajeErrorSinNombre);
        }
        if (txtApellidos.getText().toString().equals("")) {
            throw new MiExcepcion(txtApellidos, mensajeErrorSinApellidos);
        }
        if (txtTelefono.getText().toString().equals("")) {
            throw new MiExcepcion(txtTelefono, mensajeErrorSinTelefono);
        }


    }

    private void procesarInformacion() {


        RequestParams params = new RequestParams();
/*
        params.put("name", "Hola");
        // Put Http parameter username with value of Email Edit View control
        params.put("email", "jgasj@gmail.com");
        // Put Http parameter password with value of Password Edit View control
        params.put("pwd", "aauaytuasa");

*/
/*
        params.put("name", nombre.getText().toString());
        // Put Http parameter username with value of Email Edit View control
        params.put("email", email.getText().toString());
        // Put Http parameter password with value of Password Edit View control
        params.put("pwd", password.getText().toString());


        // Invoke RESTful Web Service with Http parameters
        invokeWS(params);
*/
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
