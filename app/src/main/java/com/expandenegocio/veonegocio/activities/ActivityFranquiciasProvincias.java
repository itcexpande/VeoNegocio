package com.expandenegocio.veonegocio.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.expandenegocio.veonegocio.DAO.ProvinciaDataSource;
import com.expandenegocio.veonegocio.DAO.UserDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.Franquicia;
import com.expandenegocio.veonegocio.models.Provincia;
import com.expandenegocio.veonegocio.models.Row;
import com.expandenegocio.veonegocio.models.User;
import com.expandenegocio.veonegocio.utilities.ValidatorUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jesus on 09/05/2017.
 */

public class ActivityFranquiciasProvincias extends AppCompatActivity {
    private String nCorreo;
    private String nPassword;
    Franquicia franquicia;
    private ArrayList<Franquicia> listaFran = new ArrayList<>();
    private Spinner spnFranquicia;
    private Franquicia franquiciaSeleccionada;
    private Provincia provincia;
    private Provincia provinciaSeleccionada;
    private ListView listProvincia;
    private ArrayList<Provincia> listaProv = new ArrayList<Provincia>();
    List<Row> rows;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_franquicias_provincias);
        //    nCorreo = getIntent().getStringExtra("correo");
        //    nPassword = getIntent().getStringExtra("password");
        spnFranquicia = (Spinner) findViewById(R.id.spinner_franquicia_provincia);
      //  listProvincia = (ListView) findViewById(R.id.listView_franquicias_provincias);

        ProvinciaDataSource dataSource = new ProvinciaDataSource(this);
        ArrayList<Provincia> listaProv = dataSource.getProvincias2();

        rows = new ArrayList<Row>(52);
        Row row = null;
        for (int i = 0; i < 52; i++) {
            row = new Row();
            Provincia provincia= listaProv.get(i);
            String nombreProvincia= provincia.getNombreProvincia();
            row.setTitle(nombreProvincia);
            rows.add(row);
        }
        for (int i = 0; i < 52; i++) {
            rows.get(i).setChecked(true);
        }

/*
        rows.get(3).setChecked(true);
        rows.get(6).setChecked(true);
        rows.get(9).setChecked(true);
*/

        franquicia = loadSpinnerFranquicias();
        //provincia = loadListViewProvincias();

    }

    private Provincia loadListViewProvincias() {
    //    ProvinciaDataSource dataSource = new ProvinciaDataSource(this);
     //   ArrayList<Provincia> listaProv = dataSource.getProvincias();


        listProvincia.setAdapter(new CustomArrayAdapter(this,rows) );
        listProvincia.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ActivityFranquiciasProvincias.this,
                        rows.get(position).getTitle(), Toast.LENGTH_SHORT)
                        .show();
            }
        });


 //       ArrayAdapter lArrayAdapter_adapter = new ArrayAdapter(this, android.R.layout.listView_franquicias_provincias, listaProv);

  //      lArrayAdapter_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
/*
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
*/
        return provinciaSeleccionada;


    }

    private Franquicia loadSpinnerFranquicias() {
        crearFranquicia();
        procesarInformacion();

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaFran);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnFranquicia = (Spinner) findViewById(R.id.spinner_franquicia_provincia);

        spnFranquicia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Franquicia franquiciaSeleccionada = listaFran.get(position);
                                                        if (franquiciaSeleccionada != null) {

                                                           spnFranquicia.setSelection(Integer.parseInt(franquiciaSeleccionada.getName().toString())); // municipio = loadSpinnerMunicipios(provinciaSeleccionada.getId());
                                                        }
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {
                                                        Franquicia franquiciaSeleccionada = null;
                                                    }
                                                }
        );

        spnFranquicia.setAdapter(spinner_adapter);

        return franquiciaSeleccionada;

    }


    private Franquicia crearFranquicia() {

        Franquicia franquicia = new Franquicia();

        franquicia.setId(UUID.randomUUID().toString());

        return franquicia;

    }

    private void procesarInformacion() {

        RequestParams params = new RequestParams();

        params.put("id", UUID.randomUUID().toString());
        params.put("name", UUID.randomUUID().toString());

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
            franquicia = new Franquicia();
            franquicia.setName(var.getString("name").toString());
            listaFran.add(franquicia);

        }
    }

    public void seleccionarProvincias(View view) {

    }
}
