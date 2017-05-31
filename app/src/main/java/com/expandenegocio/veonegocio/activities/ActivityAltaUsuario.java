package com.expandenegocio.veonegocio.activities;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import com.expandenegocio.veonegocio.models.Franquicia;
import com.expandenegocio.veonegocio.models.Lista_adaptador;
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

//public class ActivityAltaUsuario extends AppCompatActivity {
public class ActivityAltaUsuario extends Activity {
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
    private ListView spnSectorActividad;
    private Spinner spnPlanInversion;
    private Spinner spnCuandoEmpezar;
    private Spinner spnPerfilProfesional;
    private String nCorreo;
    private String nPassword;

    private SparseBooleanArray seleccionados;
    private CharSequence[] datosSectores;
    private Resources res;


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
        spnSectorActividad = (ListView) findViewById(R.id.lv_alta_sector_actividad);
        spnPlanInversion = (Spinner) findViewById(R.id.sp_alta_plan_inversion);
        spnCuandoEmpezar = (Spinner) findViewById(R.id.sp_alta_cuando_empezar);
        spnPerfilProfesional = (Spinner) findViewById(R.id.sp_alta_perfil_profesional);
        txtCorreo.setText(nCorreo);
        txtPassword.setText(nPassword);
        ListView lista = (ListView) this.findViewById(R.id.lv_alta_sector_actividad);

        SectorActividadDataSource dataSource = new SectorActividadDataSource(this);
        final ArrayList<Sector> listaSector = dataSource.getSectores();
        //lista = (ListView) findViewById(R.id.ListView_listado);
        lista.setAdapter(new Lista_adaptador(this, R.layout.item_listview_alta_user, listaSector) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.tvsector_item_alta_user);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((Sector) entrada).getcGrupoAct());

                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.tvsubsector_item_alta_user);
                    if (texto_inferior_entrada != null)
                        texto_inferior_entrada.setText(((Sector) entrada).getdSubSector());

                    CheckBox ck_entrada = (CheckBox) view.findViewById(R.id.ck_item_alta_user);

                }
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Sector elegido = (Sector) pariente.getItemAtPosition(posicion);
                Sector ff = (Sector) listaSector.get(posicion);

                CharSequence texto = "Seleccionado: " + elegido.getcGrupoAct();
                String dat = ff.getdSubSector();
                Toast toast = Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG);
                toast.show();
                /*
                Bundle bundle = new Bundle();

                bundle.putSerializable("franquicia", ff);

                FragmentManager fragmentManager = getFragmentManager();
                DialogoDetalleFranquicia dialogo = new DialogoDetalleFranquicia();
                dialogo.setArguments(bundle);
                dialogo.show(fragmentManager, "listadoDetalleFranquicia");
                */

                // FALTA RELLENAR ARRAYLIST DE SECTORES Y SUBSECTORES SELECCIONADOS

            }
        });
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
                                                       provinciaSeleccionada = listaProv.get(position);
                                                       if (provinciaSeleccionada != null) {
                                                           //sector = loadSpinnerSectorActividad();
                                                           planInversor = loadSpinnerPlanInversor();

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

    /*private Sector loadSpinnerSectorActividad() {
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
    }*/

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

    public void altaUsuario(View view) {

        String val = validate();

        if (val == null) {

            usuario = createUsuario();
            UserDataSource dataSource = new UserDataSource(this);
            if (dataSource.buscaUsuarioPorEmailYPassword(correo, password) == null) {
                //dataSource.insertUsuario(usuario);
                DbHelper dbHelper = new DbHelper(getApplicationContext());
                dbHelper.insertarUsuario(usuario);
            }
            procesarInformacion();

            Intent intent = new Intent("ActivityOpciones");
            intent.putExtra("correo", correo);
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

        params.put(UserDataSource.ColumnUsuarios.ID, usuario.getId().toString());
        params.put(UserDataSource.ColumnUsuarios.EMAIL, correo);
        params.put(UserDataSource.ColumnUsuarios.PASSWORD, password);
        params.put(UserDataSource.ColumnUsuarios.STATUS, "");
        params.put(UserDataSource.ColumnUsuarios.NOMBRE, nombre);
        params.put(UserDataSource.ColumnUsuarios.APELLIDOS, apellidos);
        params.put(UserDataSource.ColumnUsuarios.TELEFONO, telefono);
        params.put(UserDataSource.ColumnUsuarios.CODIGO_PROVINCIA, usuario.getCodigoProv());
        params.put(UserDataSource.ColumnUsuarios.SECTOR_ACTIVIDAD, usuario.getSectorActividad());
        params.put(UserDataSource.ColumnUsuarios.PLAN_INVERSION, usuario.getPlanInversion());
        params.put(UserDataSource.ColumnUsuarios.CUANDO_EMPEZAR, usuario.getCuandoEmpezar());
        params.put(UserDataSource.ColumnUsuarios.PERFIL_PROFESIONAL, usuario.getPerfilProfesional());


        invokeWS(params);


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