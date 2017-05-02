package com.expandenegocio.veonegocio.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
import com.expandenegocio.veonegocio.utilities.ValidatorUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;


/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityAltaUsuario extends AppCompatActivity {

    private String correo;
    private String password;
    private String nombre;
    private String apellidos;
    private String telefono;
    private Spinner spnProvincia;
    private Spinner spnMunicipio;
    private Provincia provincia;
    private Municipio municipio;
    private User usuario;

    private String disponeContacto;
    private String disponeLocal;
    private String empresa;
    private String negocio;
    private String negocioAnterior;
    private String perfilFranquicia;
    private String profesional;
    private String recursosPropios;
    private String situacionProfesional;


    private EditText txtCorreo;
    private EditText txtPassword;
    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtTelefono;
    private Provincia provinciaSeleccionada;
    private Municipio municipioSeleccionado;

    private EditText txtCapital;
    private EditText txtCapitalObservaciones;
    private EditText txtCerrada;
    private EditText txtCuandoEmpezar;

    private DatePicker fecha1;
    private DatePicker fecha2;

    private EditText txtDisponeContacto;
    private EditText txtDisponeLocal;
    private EditText txtEmpresa;
    private EditText txtNegocio;
    private EditText txtNegocioAnterior;
    private EditText txtPerfilFranquicia;
    private EditText txtProfesional;
    private EditText txtRecursosPropios;
    private EditText txtsituacionProfesional;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_altas_users);

        txtCorreo = (EditText) findViewById(R.id.et_alta_correo);
        txtPassword = (EditText) findViewById(R.id.et_alta_password);
        txtNombre = (EditText) findViewById(R.id.et_alta_nombre);
        txtApellidos = (EditText) findViewById(R.id.et_alta_apellidos);
        txtTelefono = (EditText) findViewById(R.id.et_alta_telefono);
        spnProvincia = (Spinner) findViewById(R.id.spinner_provincia_alta_user);
        spnMunicipio = (Spinner) findViewById(R.id.spinner_municipio_alta_user);

        txtCapital = (EditText) findViewById(R.id.et_alta_capital);
        txtCapitalObservaciones = (EditText) findViewById(R.id.et_alta_capital_observaciones);
        txtCerrada = (EditText) findViewById(R.id.et_alta_cerrada);
        txtCuandoEmpezar = (EditText) findViewById(R.id.et_alta_cuando_empezar);
        fecha1 = (DatePicker) findViewById(R.id.dt_alta1);
        fecha2 = (DatePicker) findViewById(R.id.dt_alta2);

        txtDisponeContacto = (EditText) findViewById(R.id.et_alta_dispone_contacto);
        txtDisponeLocal = (EditText) findViewById(R.id.et_alta_dispone_local);
        txtEmpresa = (EditText) findViewById(R.id.et_alta_empresa);
        txtNegocio = (EditText) findViewById(R.id.et_alta_negocio);
        txtNegocioAnterior = (EditText) findViewById(R.id.et_alta_negocio_anterior);
        txtPerfilFranquicia = (EditText) findViewById(R.id.et_alta_perfil_franquicia);
        txtProfesional = (EditText) findViewById(R.id.et_alta_perfil_profesional);
        txtRecursosPropios = (EditText) findViewById(R.id.et_alta_recursos_propios);
        txtsituacionProfesional = (EditText) findViewById(R.id.et_alta_situacion_profesional);

        provincia = loadSpinnerProvincias();


    }


    private Provincia loadSpinnerProvincias() {

        ProvinciaDataSource dataSource = new ProvinciaDataSource(this);
        final ArrayList<Provincia> listaProv = dataSource.getProvincias();

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaProv);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnProvincia = (Spinner) findViewById(R.id.spinner_provincia_alta_user);

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

        spnMunicipio = (Spinner) findViewById(R.id.spinner_municipio_alta_user);

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

    public void altaUsuario(View view) {

        String val = validate();

        if (val == null) {

            usuario = createUsuario();

            UserDataSource dataSource = new UserDataSource(this);
            dataSource.insertUsuario(usuario);

            procesarInformacion();

        } else {
            Toast.makeText(getApplicationContext(), val, Toast.LENGTH_LONG).show();
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

        disponeContacto = txtDisponeContacto.getText().toString();
        disponeLocal = txtDisponeLocal.getText().toString();
        empresa = txtEmpresa.getText().toString();
        negocio = txtNegocio.getText().toString();
        negocioAnterior = txtNegocioAnterior.getText().toString();
        perfilFranquicia = txtPerfilFranquicia.getText().toString();
        profesional = txtProfesional.getText().toString();
        recursosPropios = txtRecursosPropios.getText().toString();
        situacionProfesional = txtsituacionProfesional.getText().toString();


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
        }

        if (disponeContacto.trim().equals("")) {
            output = "El campo dispone contacto no puede estar vacío";
        }
        if (disponeLocal.trim().equals("")) {
            output = "El campo dispone localno puede estar vacío";
        }
        if (empresa.trim().equals("")) {
            output = "El campo empresa no puede estar vacío";
        }
        if (negocio.trim().equals("")) {
            output = "El campo negocio no puede estar vacío";
        }
        if (negocioAnterior.trim().equals("")) {
            output = "El campo negocio anterior no puede estar vacío";
        }
        if (perfilFranquicia.trim().equals("")) {
            output = "El campo perfil franquicia no puede estar vacío";
        }
        if (profesional.trim().equals("")) {
            output = "El campo perfil profesional no puede estar vacío";
        }


        return output;
    }

    private User createUsuario() {

        User usuario = new User();

        GregorianCalendar ahora = new GregorianCalendar();

        fecha1.init(ahora.get(Calendar.YEAR), ahora.get(Calendar.MONTH),
                ahora.get(Calendar.DAY_OF_MONTH), null);

        GregorianCalendar ahora2 = new GregorianCalendar();
        fecha2.init(ahora2.get(Calendar.YEAR), ahora2.get(Calendar.MONTH),
                ahora2.get(Calendar.DAY_OF_MONTH), null);

        usuario.setId(UUID.randomUUID().toString());
        usuario.setEmail(correo);
        usuario.setPassword(password);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setTelefono(telefono);

        usuario.setCodigoProv(provincia.getId());
        usuario.setCodigoMun(municipio.getCodigoMunicipio());


        return usuario;

    }

    private void procesarInformacion() {

        RequestParams params = new RequestParams();
        params.put("ID", usuario.getId().toString());
        params.put("email", correo);
        params.put("password", password);
        params.put("status", "null");
        params.put("nombre", nombre);
        params.put("apellidos", apellidos);
        params.put("telefono", telefono);
        params.put("c_prov", usuario.getCodigoProv());
        params.put("c_mun", usuario.getCodigoMun());


        invokeWS(params);




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

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/signup.php", params, new AsyncHttpResponseHandler() {
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