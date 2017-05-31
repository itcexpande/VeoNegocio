package com.expandenegocio.veonegocio.activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.expandenegocio.veonegocio.DAO.CuandoEmpezarDataSource;
import com.expandenegocio.veonegocio.DAO.MunicipioDataSource;
import com.expandenegocio.veonegocio.DAO.PerfilProfesionalDataSource;
import com.expandenegocio.veonegocio.DAO.PlanInversorDataSource;
import com.expandenegocio.veonegocio.DAO.ProvinciaDataSource;
import com.expandenegocio.veonegocio.DAO.SectorActividadDataSource;
import com.expandenegocio.veonegocio.DAO.UserDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.CuandoEmpezar;
import com.expandenegocio.veonegocio.models.Municipio;
import com.expandenegocio.veonegocio.models.PerfilProfesional;
import com.expandenegocio.veonegocio.models.PlanInversor;
import com.expandenegocio.veonegocio.models.Provincia;
import com.expandenegocio.veonegocio.models.Sector;
import com.expandenegocio.veonegocio.models.User;
import com.expandenegocio.veonegocio.utilities.ValidatorUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityConsultaUsuario extends AppCompatActivity {
    private Provincia provincia;
    private Sector sector;
    private PlanInversor planInversor;
    private CuandoEmpezar cuandoEmpezar;
    private PerfilProfesional perfilProfesional;

    private User usuario;

    private Provincia provinciaSeleccionada;
    private Sector sectorSeleccionado;
    private PlanInversor planInversorSeleccionado;
    private CuandoEmpezar cuandoEmpezarSeleccionado;
    private PerfilProfesional perfilProfesionalSeleccionado;

    private String correo;
    private String password;
    private String nombre;
    private String apellidos;
    private String telefono;


    private Spinner spnProvincia;
    private EditText txtCorreo;
    private EditText txtPassword;
    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtTelefono;
    private Spinner spnSectorActividad;
    private Spinner spnPlanInversion;
    private Spinner spnCuandoEmpezar;
    private Spinner spnPerfilProfesional;
    private String nCorreo;
    private String nPassword;
    private String status;

    /*
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
        private Spinner spnProvincia;
        private Spinner spnMunicipio;
        private String capital;
        private String capitalObservaciones;
        private Integer cerrada;
        private String cuandoEmpezar;
        private Integer deleted;
        private String disponeContacto;
        private Integer disponeLocal;
        private String empresa;
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
        private EditText txtCapital;
        private EditText txtCapitalObservaciones;
        private EditText txtCerrada;
        private EditText txtCuandoEmpezar;
        private EditText txtDisponeContacto;
        private EditText txtDisponeLocal;
        private EditText txtEmpresa;
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
    */
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
        spnProvincia = (Spinner) findViewById(R.id.sp_consulta_provincia);
        spnSectorActividad = (Spinner) findViewById(R.id.sp_consulta_sector_actividad);
        spnPlanInversion = (Spinner) findViewById(R.id.sp_consulta_plan_inversion);
        spnCuandoEmpezar = (Spinner) findViewById(R.id.sp_consulta_cuando_empezar);
        spnPerfilProfesional = (Spinner) findViewById(R.id.sp_consulta_perfil_profesional);


        txtCorreo.setText(nCorreo);
        txtPassword.setText(nPassword);
        verUsuario();
        provincia = loadSpinnerProvincias();

    }


    private Provincia loadSpinnerProvincias() {

        ProvinciaDataSource dataSource = new ProvinciaDataSource(this);
        final ArrayList<Provincia> listaProv = dataSource.getProvincias();

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaProv);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnProvincia = (Spinner) findViewById(R.id.sp_consulta_provincia);

        spnProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                   @Override
                                                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                       provinciaSeleccionada = listaProv.get(position);
                                                       if (provinciaSeleccionada != null) {
                                                           //sector = loadSpinnerSectorActividad();
                                                       }
                                                   }

                                                   @Override
                                                   public void onNothingSelected(AdapterView<?> parent) {
                                                       provinciaSeleccionada = null;
                                                   }
                                               }
        );

        spnProvincia.setAdapter(spinner_adapter);

        return provinciaSeleccionada;

    }

    private Sector loadSpinnerSectorActividad() {
        SectorActividadDataSource dataSource = new SectorActividadDataSource(this);
        final ArrayList<Sector> listaSector = dataSource.getSectores();

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaSector);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnSectorActividad = (Spinner) findViewById(R.id.sp_alta_sector_actividad);

        spnSectorActividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                         @Override
                                                         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                             ((TextView) parent.getChildAt(0)).setTextSize(12);
                                                             ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);

                                                             sectorSeleccionado = listaSector.get(position);
                                                             if (sectorSeleccionado != null) {
                                                                 planInversor = loadSpinnerPlanInversor();
                                                             }
                                                         }

                                                         @Override
                                                         public void onNothingSelected(AdapterView<?> parent) {
                                                             sectorSeleccionado = null;
                                                         }
                                                     }
        );

        spnSectorActividad.setAdapter(spinner_adapter);
        return sectorSeleccionado;
    }

    private PlanInversor loadSpinnerPlanInversor() {
        PlanInversorDataSource dataSource = new PlanInversorDataSource(this);
        final ArrayList<PlanInversor> listaPlanInversor = dataSource.getPlanInversor();

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPlanInversor);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnPlanInversion = (Spinner) findViewById(R.id.sp_alta_plan_inversion);

        spnPlanInversion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                       @Override
                                                       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                           planInversorSeleccionado = listaPlanInversor.get(position);
                                                           if (planInversorSeleccionado != null) {
                                                               cuandoEmpezar = loadSpinnerCuandoEmpezar();
                                                           }
                                                       }

                                                       @Override
                                                       public void onNothingSelected(AdapterView<?> parent) {
                                                           planInversorSeleccionado = null;
                                                       }
                                                   }
        );

        spnPlanInversion.setAdapter(spinner_adapter);
        return planInversorSeleccionado;
    }

    private CuandoEmpezar loadSpinnerCuandoEmpezar() {
        CuandoEmpezarDataSource dataSource = new CuandoEmpezarDataSource(this);
        final ArrayList<CuandoEmpezar> listaCuandoEmpezar = dataSource.getCuandoEmpezar();

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaCuandoEmpezar);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnCuandoEmpezar = (Spinner) findViewById(R.id.sp_alta_cuando_empezar);

        spnCuandoEmpezar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                       @Override
                                                       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                           cuandoEmpezarSeleccionado = listaCuandoEmpezar.get(position);
                                                           if (cuandoEmpezarSeleccionado != null) {
                                                               perfilProfesional = loadSpinnerPerfilProfesional();
                                                           }
                                                       }

                                                       @Override
                                                       public void onNothingSelected(AdapterView<?> parent) {
                                                           cuandoEmpezarSeleccionado = null;
                                                       }
                                                   }
        );

        spnCuandoEmpezar.setAdapter(spinner_adapter);
        return cuandoEmpezarSeleccionado;
    }

    private PerfilProfesional loadSpinnerPerfilProfesional() {
        PerfilProfesionalDataSource dataSource = new PerfilProfesionalDataSource(this);
        final ArrayList<PerfilProfesional> listaPerfilProfesional = dataSource.getPerfilProfesional();

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPerfilProfesional);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnPerfilProfesional = (Spinner) findViewById(R.id.sp_alta_perfil_profesional);

        spnPerfilProfesional.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                           @Override
                                                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                               perfilProfesionalSeleccionado = listaPerfilProfesional.get(position);
                                                           }

                                                           @Override
                                                           public void onNothingSelected(AdapterView<?> parent) {
                                                               perfilProfesionalSeleccionado = null;
                                                           }
                                                       }
        );

        spnPerfilProfesional.setAdapter(spinner_adapter);
        return perfilProfesionalSeleccionado;
    }

    public void consultaUsuario2(View view) {

        String val = validate();

        if (val == null) {

            usuario = createUsuario();
            UserDataSource dataSource = new UserDataSource(this);
            dataSource.updateUsuario(usuario);
            procesarInformacion();
            Intent intent = new Intent("ActivityOpciones");
            intent.putExtra("correo", nCorreo);
            intent.putExtra("password", nPassword);
            startActivity(intent);


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
        sector = (Sector) spnSectorActividad.getSelectedItem();
        planInversor = (PlanInversor) spnPlanInversion.getSelectedItem();
        cuandoEmpezar = (CuandoEmpezar) spnCuandoEmpezar.getSelectedItem();
        perfilProfesional = (PerfilProfesional) spnPerfilProfesional.getSelectedItem();

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
        if (provincia == null || provincia.getId() == -1) {
            output = "El campo provincia no puede estar vacío";
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
        usuario.setSectorActividad(sector.getcGrupoAct().toString());
        usuario.setPlanInversion(planInversor.getNombre().toString());
        usuario.setCuandoEmpezar(cuandoEmpezar.getNombre().toString());
        usuario.setPerfilProfesional(perfilProfesional.getNombre().toString());

        return usuario;

    }

    private void procesarInformacion() {

        RequestParams params = new RequestParams();

        params.put(UserDataSource.ColumnUsuarios.EMAIL, correo);
        params.put(UserDataSource.ColumnUsuarios.PASSWORD, password);
        params.put(UserDataSource.ColumnUsuarios.STATUS, status);
        params.put(UserDataSource.ColumnUsuarios.NOMBRE, nombre);
        params.put(UserDataSource.ColumnUsuarios.APELLIDOS, apellidos);
        params.put(UserDataSource.ColumnUsuarios.CODIGO_PROVINCIA, usuario.getCodigoProv());
        params.put(UserDataSource.ColumnUsuarios.TELEFONO, telefono);
        params.put(UserDataSource.ColumnUsuarios.SECTOR_ACTIVIDAD, usuario.getSectorActividad());
        params.put(UserDataSource.ColumnUsuarios.PLAN_INVERSION, usuario.getPlanInversion());
        params.put(UserDataSource.ColumnUsuarios.CUANDO_EMPEZAR, usuario.getCuandoEmpezar());
        params.put(UserDataSource.ColumnUsuarios.PERFIL_PROFESIONAL, usuario.getPerfilProfesional());


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


    // public void verUsuario(View view) {
    public void verUsuario() {

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
                            // recogeDatos(obj.getString("info"));
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
            txtTelefono.setText(var.getString(UserDataSource.ColumnUsuarios.TELEFONO).toString());
            spnProvincia.setSelection(Integer.parseInt(var.get(UserDataSource.ColumnUsuarios.CODIGO_PROVINCIA).toString()));
            spnSectorActividad.setSelection(0);
            spnPlanInversion.setSelection(0);
            spnCuandoEmpezar.setSelection(0);
            spnPerfilProfesional.setSelection(0);



        }
    }


}