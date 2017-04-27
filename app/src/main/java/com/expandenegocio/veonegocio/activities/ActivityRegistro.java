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
import com.expandenegocio.veonegocio.DAO.UserDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.Municipio;
import com.expandenegocio.veonegocio.models.Provincia;
import com.expandenegocio.veonegocio.models.User;
import com.expandenegocio.veonegocio.utilities.MiExcepcion;
import com.expandenegocio.veonegocio.utilities.ValidatorUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

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
    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registro);

        txtCorreo = (EditText) findViewById(R.id.edit_correo_registro);
        txtPassword = (EditText) findViewById(R.id.edit_password_registro);
        txtNombre = (EditText) findViewById(R.id.edit_nombre_registro);
        txtApellidos = (EditText) findViewById(R.id.edit_apellidos_registro);
        txtTelefono = (EditText) findViewById(R.id.edit_telefono);
        spnProvincia = (Spinner) findViewById(R.id.spinner_provincia);
        spnMunicipio = (Spinner) findViewById(R.id.spinner_municipio);

        provincia = loadSpinnerProvincias();

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
                                                       if (provinciaSeleccionada != null) {
                                                           municipio = loadSpinnerMunicipios(provinciaSeleccionada.getId());
                                                       }
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

    private Municipio loadSpinnerMunicipios(Integer numeroProvincia) {
        MunicipioDataSource dataSource = new MunicipioDataSource(this);
        final ArrayList<Municipio> listaMunicipios = dataSource.getMunicipios(numeroProvincia);

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

    public void aceptarRegistro(View view) {
        try {
            String val = validate();

            if (val == null) {

                usuario = createUsuario();


                UserDataSource dataSource = new UserDataSource(this);
                dataSource.insertUsuario(usuario);

     /*
                Intent intent = new Intent(this, FranquicisasActivity.class);

                intent.putExtra("Solicitud", solicitud);
                startActivity(intent);
*/
            } else {
                Toast.makeText(getApplicationContext(), val, Toast.LENGTH_LONG).show();
            }

            // comprobarEntrada();
            // procesarInformacion();
            Intent intent = new Intent("ActivityInicioSesion");
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

    }


    private String validate() {

        String output = null;

        correo = txtCorreo.getText().toString();
        password = txtPassword.getText().toString();
        nombre = txtNombre.getText().toString();
        apellidos = txtApellidos.getText().toString();
        telefono = txtTelefono.getText().toString();
        provincia = (Provincia) spnProvincia.getSelectedItem();
        municipio = (Municipio) spnMunicipio.getSelectedItem();
/*
        if (correo.trim().equals("")) {
            output = "El campo correo no puede estar vacío";
        }
        if (!ValidatorUtil.validateEmail(correo)) {
            output = "El correo no es válido";
        }
        if (password.trim().equals("")) {
            output = "el campo contraseña no puede estar vacío";
        }
        if (nombre.trim().equals("")) {
            output = "El nombre no puede estar vacío";
        }
        if (apellidos.trim().equals("")) {
            output = "El campo Apellidos no puede estar vacío";
        }
        if (telefono.trim().equals("")) {
            output = "El campo telefono no puede estar vacío";
        }
        if (!ValidatorUtil.validateTel(telefono.trim(), "34")) {
            output = "El formato de teléfono no es válido";
        }
        if (provincia == null || provincia.getId() == -1) {
            output = "El campo provincia no puede estar vacío";
        }
        if (municipio == null || municipio.getNombreMunicipio() == null) {
            output = "El campo municipio no puede estar vacío";
        }*/

        return output;
    }

    private User createUsuario() {

        User usuario = new User();

        usuario.setId(UUID.randomUUID().toString());
        usuario.setEmail(correo);
        usuario.setPassword(password);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setTelefono(telefono);

        usuario.setCodigoProv(provincia.getId());
        usuario.setCodigoMun(municipio.getCodigoMunicipio());


/*
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        solicitud.setFecha(currentDateandTime);
*/


        return usuario;

    }

    /* private void comprobarEntrada() throws MiExcepcion {

         String mensajeErrorSinNombre = this.getString(R.string.mensajeToastFaltaDato);
         String mensajeErrorSinApellidos = getString(R.string.faltaDatoApellidos);
         String mensajeErrorMail = this.getString(R.string.mensajeToastMail);
         String mensajeErrorSinPassword = getString(R.string.faltaDatoPassword);
         String mensajeErrorSinTelefono = "Falta dato teléfono";
         String mensajeErrorSinProvincia = "Falta dato Provincia";
         String mensajeErrorSinMunicipio = "Falta dato Municipio";


         if (txtCorreo.getText().toString().equals("")) {
             throw new MiExcepcion(txtCorreo, null, mensajeErrorMail);
         }

         if (txtPassword.getText().toString().equals("")) {
             throw new MiExcepcion(txtPassword, null, mensajeErrorSinPassword);
         }

         if (txtNombre.getText().toString().equals("")) {
             throw new MiExcepcion(txtNombre, null, mensajeErrorSinNombre);
         }
         if (txtApellidos.getText().toString().equals("")) {
             throw new MiExcepcion(txtApellidos, null, mensajeErrorSinApellidos);
         }
         if (txtTelefono.getText().toString().equals("")) {
             throw new MiExcepcion(txtTelefono, null, mensajeErrorSinTelefono);
         }
         if (provincia == null) {
             throw new MiExcepcion(null, spnProvincia, mensajeErrorSinProvincia);
         }
         if (municipio == null) {
             throw new MiExcepcion(null, spnMunicipio, mensajeErrorSinMunicipio);
         }

     }
 */
    private void procesarInformacion() {
/*
        provincia = (Provincia) spnProvincia.getSelectedItem();
        municipio = (Municipio) spnMunicipio.getSelectedItem();
        String hh = "";
        User nuavoUsuario = new User();
*/

        //  RequestParams params = new RequestParams();
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
