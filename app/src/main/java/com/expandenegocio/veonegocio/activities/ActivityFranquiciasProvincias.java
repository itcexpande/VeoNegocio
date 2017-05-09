package com.expandenegocio.veonegocio.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.expandenegocio.veonegocio.DAO.ProvinciaDataSource;
import com.expandenegocio.veonegocio.DAO.UserDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.Franquicia;
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
import java.util.UUID;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jesus on 09/05/2017.
 */

public class ActivityFranquiciasProvincias extends AppCompatActivity {
    private String nCorreo;
    private String nPassword;
    Franquicia franquicia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_franquicias_provincias);
    //    nCorreo = getIntent().getStringExtra("correo");
    //    nPassword = getIntent().getStringExtra("password");
        Spinner spinner = (Spinner) findViewById(R.id.spinner_franquicia_provincia);
        franquicia = loadSpinnerFranquicias();


    }

    private Franquicia loadSpinnerFranquicias() {
        crearFranquicia();
        procesarInformacion();
        //   ProvinciaDataSource dataSource = new ProvinciaDataSource(this);
        final ArrayList<Franquicia> listaFran;// = dataSource.getProvincias();

      //  ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaProv);
/*
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
*/
        return franquicia;

    }


    private Franquicia crearFranquicia() {

        Franquicia franquicia = new Franquicia();

        franquicia.setId(UUID.randomUUID().toString());

        return franquicia;

    }

    private void procesarInformacion() {

        RequestParams params = new RequestParams();

        params.put("id", UUID.randomUUID().toString());

        invokeWS(params);


    }


    public void invokeWS(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/retorna_franquicias.php", params, new AsyncHttpResponseHandler() {
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

          /*  txtNombre.setText(var.get("nombre").toString());
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

}
