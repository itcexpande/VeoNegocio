package com.expandenegocio.veonegocio.activities;


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
import com.expandenegocio.veonegocio.DAO.DbHelper;
import com.expandenegocio.veonegocio.DAO.PerfilProfesionalDataSource;
import com.expandenegocio.veonegocio.DAO.PlanInversorDataSource;
import com.expandenegocio.veonegocio.DAO.ProvinciaDataSource;
import com.expandenegocio.veonegocio.DAO.SectorActividadDataSource;
import com.expandenegocio.veonegocio.DAO.UserDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.CuandoEmpezar;
import com.expandenegocio.veonegocio.models.PerfilProfesional;
import com.expandenegocio.veonegocio.models.PlanInversor;
import com.expandenegocio.veonegocio.models.Provincia;
import com.expandenegocio.veonegocio.models.Sector;
import com.expandenegocio.veonegocio.models.User;
import com.expandenegocio.veonegocio.utilities.ValidatorUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityAltaUsuario extends AppCompatActivity {
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
    private String status;
    private String nombre;
    private String apellidos;
    private String telefono;
    // private String sector;
    // private String planInversion;
    // private String cuandoEmpezar;
    // private String perfilProfesional;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_altas_users);
        nCorreo = getIntent().getStringExtra("correo");
        nPassword = getIntent().getStringExtra("password");

        txtCorreo = (EditText) findViewById(R.id.et_alta_correo);
        txtPassword = (EditText) findViewById(R.id.et_alta_password);
        txtNombre = (EditText) findViewById(R.id.et_alta_nombre);
        txtApellidos = (EditText) findViewById(R.id.et_alta_apellidos);
        txtTelefono = (EditText) findViewById(R.id.et_alta_telefono);
        spnProvincia = (Spinner) findViewById(R.id.sp_alta_provincia);
        spnSectorActividad = (Spinner) findViewById(R.id.sp_alta_sector_actividad);
        spnPlanInversion = (Spinner) findViewById(R.id.sp_alta_plan_inversion);
        spnCuandoEmpezar = (Spinner) findViewById(R.id.sp_alta_cuando_empezar);
        spnPerfilProfesional = (Spinner) findViewById(R.id.sp_alta_perfil_profesional);
        txtCorreo.setText(nCorreo);
        txtPassword.setText(nPassword);
        provincia = loadSpinnerProvincias();

    }


    private Provincia loadSpinnerProvincias() {

        ProvinciaDataSource dataSource = new ProvinciaDataSource(this);
        final ArrayList<Provincia> listaProv = dataSource.getProvincias();

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaProv);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnProvincia = (Spinner) findViewById(R.id.sp_alta_provincia);

        spnProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                   @Override
                                                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                       Provincia provinciaSeleccionada = listaProv.get(position);
                                                       if (provinciaSeleccionada != null) {
                                                           sector = loadSpinnerSectorActividad();
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
                                                             planInversor = loadSpinnerPlanInversor();
                                                         }

                                                         @Override
                                                         public void onNothingSelected(AdapterView<?> parent) {
                                                             Sector sectorSeleccionado = null;
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
                                                           cuandoEmpezar = loadSpinnerCuandoEmpezar();
                                                       }

                                                       @Override
                                                       public void onNothingSelected(AdapterView<?> parent) {
                                                           PlanInversor planInversorSeleccionado = null;
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
                                                           perfilProfesional= loadSpinnerPerfilProfesional();
                                                       }

                                                       @Override
                                                       public void onNothingSelected(AdapterView<?> parent) {
                                                           CuandoEmpezar cuandoEmpezarSeleccionado = null;
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
                                                           perfilProfesionalSeleccionado=  listaPerfilProfesional.get(position);
                                                         //  perfilProfesional= loadSpinnerPerfilProfesional();
                                                       }

                                                       @Override
                                                       public void onNothingSelected(AdapterView<?> parent) {
                                                           PerfilProfesional perfilProfesionalSeleccionado = null;
                                                       }
                                                   }
        );

        spnPerfilProfesional.setAdapter(spinner_adapter);
        return perfilProfesionalSeleccionado;
    }

    public void altaUsuario(View view) {

        String val = validate();

        if (val == null) {

            usuario = createUsuario();
            UserDataSource dataSource = new UserDataSource(this);
            if (dataSource.buscaUsuarioPorEmailYPassword(correo, password) == null) {
                dataSource.insertUsuario(usuario);
            }
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
        //   sector = (Municipio) spnMunicipio.getSelectedItem();


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
        usuario.setCodigoProv(provincia.getId());
        // usuario.setCuandoEmpezar(cuandoEmpezar);
        return usuario;

    }


    private void procesarInformacion() {
/*
        RequestParams params = new RequestParams();

        params.put(UserDataSource.ColumnUsuarios.ID, usuario.getId().toString());
        params.put(UserDataSource.ColumnUsuarios.EMAIL, correo);
        params.put(UserDataSource.ColumnUsuarios.PASSWORD, password);
        params.put(UserDataSource.ColumnUsuarios.STATUS, status);
        params.put(UserDataSource.ColumnUsuarios.NOMBRE, nombre);
        params.put(UserDataSource.ColumnUsuarios.APELLIDOS, apellidos);
        params.put(UserDataSource.ColumnUsuarios.CODIGO_PROVINCIA, usuario.getCodigoProv());
        params.put(UserDataSource.ColumnUsuarios.CODIGO_MUNICIPIO, usuario.getCodigoMun());
        params.put(UserDataSource.ColumnUsuarios.CAPITAL, usuario.getCapital());
        params.put(UserDataSource.ColumnUsuarios.CAPITAL_OBSERVACIONES, usuario.getCapitalObservaciones());
        params.put(UserDataSource.ColumnUsuarios.CERRADA, usuario.getCerrada());
        params.put(UserDataSource.ColumnUsuarios.CUANDO_EMPEZAR, usuario.getCuandoEmpezar());
        params.put(UserDataSource.ColumnUsuarios.DATE_ENTERED, usuario.getDateEntered());
        params.put(UserDataSource.ColumnUsuarios.DATE_MODIFIED, usuario.getDateModified());
        params.put(UserDataSource.ColumnUsuarios.DELETED, usuario.getDeleted());
        params.put(UserDataSource.ColumnUsuarios.DISP_CONTACTO, usuario.getDisponeContacto());
        params.put(UserDataSource.ColumnUsuarios.DISP_LOCAL, usuario.getDisponeLocal());
        params.put(UserDataSource.ColumnUsuarios.EMPRESA, usuario.getEmpresa());
        params.put(UserDataSource.ColumnUsuarios.NEGOCIO, usuario.getNegocio());
        params.put(UserDataSource.ColumnUsuarios.NEGOCIO_ANTES, usuario.getNegocioAnterior());
        params.put(UserDataSource.ColumnUsuarios.PERFIL_FRANQUICIA, usuario.getPerfilFranquicia());
        params.put(UserDataSource.ColumnUsuarios.PERFIL_PROFESIONAL, usuario.getPerfilProfesional());
        params.put(UserDataSource.ColumnUsuarios.PHONE_HOME, usuario.getPhoneHome());
        params.put(UserDataSource.ColumnUsuarios.PHONE_MOBILE, usuario.getPhoneMobile());
        params.put(UserDataSource.ColumnUsuarios.RECURSOS_PROPIOS, usuario.getRecursosPropios());
        params.put(UserDataSource.ColumnUsuarios.SITUACION_PROFESIONAL, usuario.getSituacionProfesional());


        invokeWS(params);

*/
    }


    public void invokeWS(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/signup_jesus.php", params, new AsyncHttpResponseHandler() {
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