package com.expandenegocio.veonegocio.activities;


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
import com.expandenegocio.veonegocio.utilities.ValidatorUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityConsultaUsuario extends AppCompatActivity {
    private Provincia provincia;
    private Municipio municipio;
    private User usuario;

    private Provincia provinciaSeleccionada;
    private Municipio municipioSeleccionado;

    private String correo;
    private String password;
    private String nombre;
    private String status;
    private String apellidos;
    private String telefono;
    private Spinner spnProvincia;
    private Spinner spnMunicipio;
    private String capital;
    private String capitalObservaciones;
    private Integer cerrada;
    private String cuandoEmpezar;
    private String dateEntered;
    private String dateModified;
    private Integer deleted;
    private String disponeContacto;
    private Integer disponeLocal;
    private String empresa;
    private String firstName;
    private String id2;
    private String lastName;
    private String negocio;
    private Integer negocioAnterior;
    private String perfilFranquicia;
    private String perfilProfesional;
    private String phoneHome;
    private String phoneMobile;
    private String recursosPropios;
    private String situacionProfesional;

    private EditText txtCorreo;
    private EditText txtPassword;
    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtTelefono;
    private EditText txtCapital;
    private EditText txtCapitalObservaciones;
    private EditText txtCerrada;
    private EditText txtCuandoEmpezar;
    private EditText txtDisponeContacto;
    private EditText txtDisponeLocal;
    private EditText txtEmpresa;
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtNegocio;
    private EditText txtNegocioAnterior;
    private EditText txtPerfilFranquicia;
    private EditText txtPerfilProfesional;
    private EditText txtPhoneHome;
    private EditText txtPhoneMobile;
    private EditText txtRecursosPropios;
    private EditText txtSituacionProfesional;

    private String nCorreo;
    private String nPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_consultas_users);

        nCorreo = getIntent().getStringExtra("correo");
        nPassword = getIntent().getStringExtra("password");


        txtCorreo = (EditText) findViewById(R.id.et_consulta_correo);
        txtPassword = (EditText) findViewById(R.id.et_consulta_password);
        txtNombre = (EditText) findViewById(R.id.et_consulta_nombre);
        txtApellidos = (EditText) findViewById(R.id.et_consulta_apellidos);
        txtTelefono = (EditText) findViewById(R.id.et_consulta_telefono);
        spnProvincia = (Spinner) findViewById(R.id.spinner_provincia_consulta_user);
        spnMunicipio = (Spinner) findViewById(R.id.spinner_municipio_consulta_user);
        txtCapital = (EditText) findViewById(R.id.et_consulta_capital);
        txtCapitalObservaciones = (EditText) findViewById(R.id.et_consulta_capital_observaciones);
        txtCerrada = (EditText) findViewById(R.id.et_consulta_cerrada);
        txtCuandoEmpezar = (EditText) findViewById(R.id.et_consulta_cuando_empezar);
        txtDisponeContacto = (EditText) findViewById(R.id.et_consulta_dispone_contacto);
        txtDisponeLocal = (EditText) findViewById(R.id.et_consulta_dispone_local);
        txtEmpresa = (EditText) findViewById(R.id.et_consulta_empresa);
        txtFirstName = (EditText) findViewById(R.id.et_consulta_first_name);
        txtLastName = (EditText) findViewById(R.id.et_consulta_last_name);
        txtNegocio = (EditText) findViewById(R.id.et_consulta_negocio);
        txtNegocioAnterior = (EditText) findViewById(R.id.et_consulta_negocio_anterior);
        txtPerfilFranquicia = (EditText) findViewById(R.id.et_consulta_perfil_franquicia);
        txtPerfilProfesional = (EditText) findViewById(R.id.et_consulta_perfil_profesional);
        txtRecursosPropios = (EditText) findViewById(R.id.et_consulta_recursos_propios);
        txtSituacionProfesional = (EditText) findViewById(R.id.et_consulta_situacion_profesional);
        txtPhoneHome = (EditText) findViewById(R.id.et_consulta_phone_home);
        txtPhoneMobile = (EditText) findViewById(R.id.et_consulta_phone_mobile);

        txtCorreo.setText(nCorreo);
        txtPassword.setText(nPassword);
        provincia = loadSpinnerProvincias();

    }


    private Provincia loadSpinnerProvincias() {

        ProvinciaDataSource dataSource = new ProvinciaDataSource(this);
        final ArrayList<Provincia> listaProv = dataSource.getProvincias();

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaProv);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnProvincia = (Spinner) findViewById(R.id.spinner_provincia_consulta_user);

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

        spnMunicipio = (Spinner) findViewById(R.id.spinner_municipio_consulta_user);

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

    public void consultaUsuario2(View view) {

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

        correo = txtCorreo.getText().toString();
        password = txtPassword.getText().toString();
        nombre = txtNombre.getText().toString();
        apellidos = txtApellidos.getText().toString();
        telefono = txtTelefono.getText().toString();
        provincia = (Provincia) spnProvincia.getSelectedItem();
        municipio = (Municipio) spnMunicipio.getSelectedItem();
        capital = txtCapital.getText().toString();
        capitalObservaciones = txtCapitalObservaciones.getText().toString();
        cerrada = Integer.parseInt(txtCerrada.getText().toString());
        cuandoEmpezar = txtCuandoEmpezar.getText().toString();
        deleted = 0;
        disponeContacto = txtDisponeContacto.getText().toString();
        disponeLocal = Integer.parseInt(txtDisponeLocal.getText().toString());
        empresa = txtEmpresa.getText().toString();
        firstName = txtFirstName.getText().toString();
        lastName = txtLastName.getText().toString();
        id2 = "";
        negocio = txtNegocio.getText().toString();
        negocioAnterior = Integer.parseInt(txtNegocioAnterior.getText().toString());
        perfilFranquicia = txtPerfilFranquicia.getText().toString();
        perfilProfesional = txtPerfilProfesional.getText().toString();
        phoneHome = txtPhoneHome.getText().toString();
        phoneMobile = txtPhoneMobile.getText().toString();
        recursosPropios = txtRecursosPropios.getText().toString();
        situacionProfesional = txtSituacionProfesional.getText().toString();


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
        if (capital.trim().equals("")) {
            output = "El campo capital no puede estar vacío";
        }
        if (capitalObservaciones.trim().equals("")) {
            output = "El campo capital observaciones no puede estar vacío";
        }
        if (txtCerrada.getText().toString().trim().equals("")) {
            output = "El campo cerrada no puede estar vacío";
        }
        if (disponeContacto.trim().equals("")) {
            output = "El campo dispone contacto no puede estar vacío";
        }
        if (txtDisponeLocal.getText().toString().trim().equals("")) {
            output = "El campo dispone local no puede estar vacío";
        }
        if (empresa.trim().equals("")) {
            output = "El campo empresa no puede estar vacío";
        }
        if (firstName.trim().equals("")) {
            output = "El campo first name no puede estar vacío";
        }
        if (lastName.trim().equals("")) {
            output = "El campo last name no puede estar vacío";
        }

        if (negocio.trim().equals("")) {
            output = "El campo negocio no puede estar vacío";
        }
        if (txtNegocioAnterior.getText().toString().trim().equals("")) {
            output = "El campo negocio anterior no puede estar vacío";
        }
        if (perfilFranquicia.trim().equals("")) {
            output = "El campo perfil franquicia no puede estar vacío";
        }
        if (perfilProfesional.trim().equals("")) {
            output = "El campo perfil profesional no puede estar vacío";
        }


        return output;
    }

    private User createUsuario() {

        User usuario = new User();

        usuario.setId(UUID.randomUUID().toString());
        usuario.setEmail(correo);
        usuario.setPassword(password);
        usuario.setStatus("");
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setTelefono(telefono);
        usuario.setCodigoProv(provincia.getId());
        usuario.setCodigoMun(municipio.getCodigoMunicipio());
        usuario.setCapital(capital);
        usuario.setCapitalObservaciones(capitalObservaciones);
        usuario.setCerrada(cerrada);
        usuario.setCuandoEmpezar(cuandoEmpezar);
        usuario.setDateEntered(new Date());
        usuario.setDateModified(new Date());
        usuario.setDeleted(deleted);
        usuario.setDisponeContacto(disponeContacto);
        usuario.setDisponeLocal(disponeLocal);
        usuario.setEmpresa(empresa);
        usuario.setFirstName(firstName);
        usuario.setId2(UUID.randomUUID().toString());
        usuario.setLastName(lastName);
        usuario.setNegocio(negocio);
        usuario.setNegocioAnterior(negocioAnterior);
        usuario.setPerfilFranquicia(perfilFranquicia);
        usuario.setPerfilProfesional(perfilProfesional);
        usuario.setPhoneHome(phoneHome);
        usuario.setPhoneMobile(phoneMobile);
        usuario.setRecursosPropios(recursosPropios);
        usuario.setSituacionProfesional(situacionProfesional);

        return usuario;

    }

    private void procesarInformacion() {

        RequestParams params = new RequestParams();

        //params.put(UserDataSource.ColumnUsuarios.ID, usuario.getId().toString());
        params.put(UserDataSource.ColumnUsuarios.EMAIL, correo);
        params.put(UserDataSource.ColumnUsuarios.PASSWORD, password);
        params.put(UserDataSource.ColumnUsuarios.STATUS, status);
        params.put(UserDataSource.ColumnUsuarios.NOMBRE, nombre);
        params.put(UserDataSource.ColumnUsuarios.APELLIDOS, apellidos);
        params.put(UserDataSource.ColumnUsuarios.TELEFONO, telefono);
        params.put(UserDataSource.ColumnUsuarios.CODIGO_PROVINCIA, usuario.getCodigoProv());
        params.put(UserDataSource.ColumnUsuarios.CODIGO_MUNICIPIO, usuario.getCodigoMun());
        params.put(UserDataSource.ColumnUsuarios.CAPITAL, usuario.getCapital());
        params.put(UserDataSource.ColumnUsuarios.CAPITAL_OBSERVACIONES, usuario.getCapitalObservaciones());
        params.put(UserDataSource.ColumnUsuarios.CERRADA, usuario.getCerrada());
        params.put(UserDataSource.ColumnUsuarios.CUANDO_EMPEZAR, usuario.getCuandoEmpezar());
        params.put(UserDataSource.ColumnUsuarios.DISP_CONTACTO, usuario.getDisponeContacto());
        params.put(UserDataSource.ColumnUsuarios.DISP_LOCAL, usuario.getDisponeLocal());
        params.put(UserDataSource.ColumnUsuarios.EMPRESA, usuario.getEmpresa());
        params.put(UserDataSource.ColumnUsuarios.FIRTS_NAME, usuario.getFirstName());
        params.put(UserDataSource.ColumnUsuarios.LAST_NAME, usuario.getLastName());
        params.put(UserDataSource.ColumnUsuarios.NEGOCIO, usuario.getNegocio());
        params.put(UserDataSource.ColumnUsuarios.NEGOCIO_ANTES, usuario.getNegocioAnterior());
        params.put(UserDataSource.ColumnUsuarios.PERFIL_FRANQUICIA, usuario.getPerfilFranquicia());
        params.put(UserDataSource.ColumnUsuarios.PERFIL_PROFESIONAL, usuario.getPerfilProfesional());
        params.put(UserDataSource.ColumnUsuarios.PHONE_HOME, usuario.getPhoneHome());
        params.put(UserDataSource.ColumnUsuarios.PHONE_MOBILE, usuario.getPhoneMobile());
        params.put(UserDataSource.ColumnUsuarios.RECURSOS_PROPIOS, usuario.getRecursosPropios());
        params.put(UserDataSource.ColumnUsuarios.SITUACION_PROFESIONAL, usuario.getSituacionProfesional());


        invokeWS(params);


    }


    public void invokeWS(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/update_jesus.php", params, new AsyncHttpResponseHandler() {
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
                            Toast.makeText(getApplicationContext(), "Modificado correctamente!", Toast.LENGTH_LONG).show();
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


    public void verUsuario(View view) {

        String val = validate2();

        if (val == null) {

            usuario = createUsuario2();


            procesarInformacion2();

        } else {
            Toast.makeText(getApplicationContext(), val, Toast.LENGTH_LONG).show();
        }


    }

    private String validate2() {

        String output = null;

        correo = txtCorreo.getText().toString();
        password = txtPassword.getText().toString();


        if (correo.trim().equals("")) {
            output = "El campo correo no puede estar vacío";
        }
        if (!ValidatorUtil.validateEmail(correo)) {
            output = "El correo no es válido";
        }
        if (password.trim().equals("")) {
            output = "el campo contraseña no puede estar vacío";
        }


        return output;
    }

    private User createUsuario2() {

        User usuario = new User();

        usuario.setId(UUID.randomUUID().toString());
        usuario.setEmail(correo);
        usuario.setPassword(password);

        return usuario;

    }

    private void procesarInformacion2() {

        RequestParams params = new RequestParams();

        params.put(UserDataSource.ColumnUsuarios.EMAIL, correo);
        params.put(UserDataSource.ColumnUsuarios.PASSWORD, password);


        invokeWS2(params);


    }


    public void invokeWS2(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/retorna_usuario.php", params, new AsyncHttpResponseHandler() {
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
                            //       recogeDatos(obj.getString("info"));
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

            txtNombre.setText(var.get(UserDataSource.ColumnUsuarios.NOMBRE).toString());
            txtApellidos.setText(var.get(UserDataSource.ColumnUsuarios.APELLIDOS).toString());
            txtTelefono.setText(var.get(UserDataSource.ColumnUsuarios.TELEFONO).toString());
            spnProvincia.setSelection(Integer.parseInt(var.get(UserDataSource.ColumnUsuarios.CODIGO_PROVINCIA).toString()));
            spnMunicipio.setSelection(Integer.parseInt(var.get(UserDataSource.ColumnUsuarios.CODIGO_MUNICIPIO).toString()));
            txtCapital.setText(var.get(UserDataSource.ColumnUsuarios.CAPITAL).toString());
            txtCapitalObservaciones.setText(var.get(UserDataSource.ColumnUsuarios.CAPITAL_OBSERVACIONES).toString());
            txtCerrada.setText(var.get(UserDataSource.ColumnUsuarios.CERRADA).toString());
            txtCuandoEmpezar.setText(var.get(UserDataSource.ColumnUsuarios.CUANDO_EMPEZAR).toString());
            txtDisponeContacto.setText(var.get(UserDataSource.ColumnUsuarios.DISP_CONTACTO).toString());
            txtDisponeLocal.setText(var.get(UserDataSource.ColumnUsuarios.DISP_LOCAL).toString());
            txtEmpresa.setText(var.get(UserDataSource.ColumnUsuarios.EMPRESA).toString());
            txtFirstName.setText(var.get(UserDataSource.ColumnUsuarios.FIRTS_NAME).toString());
            txtLastName.setText(var.get(UserDataSource.ColumnUsuarios.LAST_NAME).toString());
            txtNegocio.setText(var.get(UserDataSource.ColumnUsuarios.NEGOCIO).toString());
            txtNegocioAnterior.setText(var.get(UserDataSource.ColumnUsuarios.NEGOCIO_ANTES).toString());
            txtPerfilFranquicia.setText(var.get(UserDataSource.ColumnUsuarios.PERFIL_FRANQUICIA).toString());
            txtPerfilProfesional.setText(var.get(UserDataSource.ColumnUsuarios.PERFIL_PROFESIONAL).toString());
            txtPhoneHome.setText(var.get(UserDataSource.ColumnUsuarios.PHONE_HOME).toString());
            txtPhoneMobile.setText(var.get(UserDataSource.ColumnUsuarios.PHONE_MOBILE).toString());
            txtRecursosPropios.setText(var.get(UserDataSource.ColumnUsuarios.RECURSOS_PROPIOS).toString());
            txtSituacionProfesional.setText(var.get(UserDataSource.ColumnUsuarios.SITUACION_PROFESIONAL).toString());
/*

            txtNombre.setText(var.get("nombre").toString());
            txtApellidos.setText(var.get("apellidos").toString());
            txtTelefono.setText(var.get("telefono").toString());
            spnProvincia.setSelection(Integer.parseInt(var.get("c_prov").toString()));
            spnMunicipio.setSelection(Integer.parseInt(var.get("c_mun").toString()));
            txtCapital.setText(var.get("capital").toString());
            txtCapitalObservaciones.setText(var.get("capital_observaciones").toString());
            txtCerrada.setText(var.get("cerrada").toString());
            txtCuandoEmpezar.setText(var.get("cuando_empezar").toString());
            txtDisponeContacto.setText(var.get("disp_contacto").toString());
            txtDisponeLocal.setText(var.get("dispone_local").toString());
            txtEmpresa.setText(var.get("empresa").toString());
            txtFirstName.setText(var.get("first_name").toString());
            txtLastName.setText(var.get("last_name").toString());
            txtNegocio.setText(var.get("negocio").toString());
            txtNegocioAnterior.setText(var.get("negocio_antes").toString());
            txtPerfilFranquicia.setText(var.get("perfil_franquicia").toString());
            txtPerfilProfesional.setText(var.get("perfil_profesional").toString());
            txtPhoneHome.setText(var.get("phone_home").toString());
            txtPhoneMobile.setText(var.get("phone_mobile").toString());
            txtRecursosPropios.setText(var.get("recursos_propios").toString());
            txtSituacionProfesional.setText(var.get("situacion_profesional").toString());
*/
        }
    }

    private void recogeDatos(String info) {
        String campo;

        int longitud = info.length();
        String nuevaInfo = info.substring(2, longitud - 2);
        String[] campos = nuevaInfo.split(",");
        for (int x = 0; x < campos.length; x++) {
            String[] subcampos = campos[x].split(":");
            String clave = subcampos[0].substring(1, subcampos[0].length() - 1);
            campo = subcampos[1].substring(1, subcampos[1].length() - 1);
            if (clave.equals("nombre")) {
                txtNombre.setText(campo);
            } else if (clave.equals("apellidos")) {
                txtApellidos.setText(campo);
            } else if (clave.equals("telefono")) {
                txtTelefono.setText(campo);
            } else if (clave.equals("c_prov")) {
                spnProvincia.setSelection(Integer.parseInt(campo));
            } else if (clave.equals("c_mun")) {
                spnMunicipio.setSelection(Integer.parseInt(campo));
            } else if (clave.equals("capital")) {
                txtCapital.setText(campo);
            } else if (clave.equals("capital_observaciones")) {
                txtCapitalObservaciones.setText(campo);
            } else if (clave.equals("cerrada")) {
                txtCerrada.setText(campo);
            } else if (clave.equals("cuando_empezar")) {
                txtCuandoEmpezar.setText(campo);
            } else if (clave.equals("disp_contacto")) {
                txtDisponeContacto.setText(campo);
            } else if (clave.equals("dispone_local")) {
                txtDisponeLocal.setText(campo);
            } else if (clave.equals("empresa")) {
                txtEmpresa.setText(campo);
            } else if (clave.equals("first_name")) {
                txtFirstName.setText(campo);
            } else if (clave.equals("last_name")) {
                txtLastName.setText(campo);
            } else if (clave.equals("negocio")) {
                txtNegocio.setText(campo);
            } else if (clave.equals("negocio_antes")) {
                txtNegocioAnterior.setText(campo);
            } else if (clave.equals("perfil_franquicia")) {
                txtPerfilFranquicia.setText(campo);
            } else if (clave.equals("perfil_profesional")) {
                txtPerfilProfesional.setText(campo);
            } else if (clave.equals("phone_home")) {
                txtPhoneHome.setText(campo);
            } else if (clave.equals("phone_mobile")) {
                txtPhoneMobile.setText(campo);
            } else if (clave.equals("recursos_propios")) {
                txtRecursosPropios.setText(campo);
            } else if (clave.equals("situacion_profesional")) {
                txtSituacionProfesional.setText(campo);
            }
/*
            switch (campo) {
                case "nombre":
                    txtNombre.setText(campo);
                    break;
                case "apellidos":
                    txtApellidos.setText(campo);
                    break;
                case "telefono":
                    txtTelefono.setText(campo);
                    break;
                case "c_prov":
                    spnProvincia.setSelection(Integer.parseInt(campo));
                    break;
                case "c_mun":
                    spnMunicipio.setSelection(Integer.parseInt(campo));
                    break;
                case "capital":
                    txtCapital.setText(campo);
                    break;
                case "capital_observaciones":
                    txtCapitalObservaciones.setText(campo);
                    break;
                case "cerrada":
                    txtCerrada.setText(campo);
                    break;
                case "cuando_empezar":
                    txtCuandoEmpezar.setText(campo);
                    break;
                case "disp_contacto":
                    txtDisponeContacto.setText(campo);
                    break;
                case "dispone_local":
                    txtDisponeLocal.setText(campo);
                    break;
                case "empresa":
                    txtEmpresa.setText(campo);
                    break;
                case "first_name":
                    txtFirstName.setText(campo);
                    break;
                case "last_name":
                    txtLastName.setText(campo);
                    break;
                case "negocio":
                    txtNegocio.setText(campo);
                    break;
                case "negocio_antes":
                    txtNegocioAnterior.setText(campo);
                    break;
                case "perfil_franquicia":
                    txtPerfilFranquicia.setText(campo);
                    break;
                case "perfil_profesional":
                    txtPerfilProfesional.setText(campo);
                    break;
                case "phone_home":
                    txtPhoneHome.setText(campo);
                    break;
                case "phone_mobile":
                    txtPhoneMobile.setText(campo);
                    break;
                case "recursos_propios":
                    txtRecursosPropios.setText(campo);
                    break;
                case "situacion_profesional":
                    txtSituacionProfesional.setText(campo);
                    break;
            }*/

        }
    }


}