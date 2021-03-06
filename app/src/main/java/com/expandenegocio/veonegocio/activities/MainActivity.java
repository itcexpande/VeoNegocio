package com.expandenegocio.veonegocio.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.expandenegocio.veonegocio.DAO.CuandoEmpezarDataSource;
import com.expandenegocio.veonegocio.DAO.DbHelper;
import com.expandenegocio.veonegocio.DAO.MunicipioDataSource;
import com.expandenegocio.veonegocio.DAO.PerfilProfesionalDataSource;
import com.expandenegocio.veonegocio.DAO.PlanInversorDataSource;
import com.expandenegocio.veonegocio.DAO.ProvinciaDataSource;
import com.expandenegocio.veonegocio.DAO.SectorActividadDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.Municipio;
import com.expandenegocio.veonegocio.models.Sector;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends ActionBarActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout mDemoSlider;
    private int codigoProvincia;
    private String denominacionProvincia;
    private int codigoMunicipio;
    private int totalHabitantes;
    private int totalHombres;
    private int totalMujeres;
    private DbHelper dbHelper;
    private DbHelper MDB;
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MDB = new DbHelper(getApplicationContext());
        // borra basedatos
          //getApplicationContext().deleteDatabase("VeoNegocio.db");
        // borra datos
        MDB.borrarProvincias();
        MDB.borrarMunicipios();

        MDB.borrarSectoresActividad();
        MDB.borrarPlanInversor();
        MDB.borrarCuandoEmpezar();
        MDB.borrarPerfilProfesional();


        pasaProvincias();


        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        TreeMap<String, Integer> fotos_presentacion = new TreeMap<>();
        fotos_presentacion.put("Presentacion1", R.drawable.presentacion1);
        fotos_presentacion.put("Presentacion2", R.drawable.presentacion2);
        fotos_presentacion.put("Presentacion3", R.drawable.presentacion3);
        fotos_presentacion.put("Presentacion4", R.drawable.presentacion4);


        for (String name : fotos_presentacion.keySet()) {
            TextSliderView textSliderView = new TextSliderView(MainActivity.this);

            textSliderView

                    .image(fotos_presentacion.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(MainActivity.this);


            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(MainActivity.this);


    }


    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        // Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * Función para comprobar si hay conexión a Internet
     *
     * @param context
     * @return boolean
     */
    public static boolean compruebaConexion(Context context) {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }

        return connected;
    }


    public void irAEnlaceTerminosDeServicio(View view) {
        Intent intent = new Intent("ActivityServicio");
        startActivity(intent);


    }

    public void InicioSesion(View view) {

        if (!compruebaConexion(this)) {
            Toast.makeText(getBaseContext(), "Necesaria conexión a internet ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent("ActivitySinConexion");
            startActivity(intent);
        } else {
            Toast.makeText(getBaseContext(), "Conectado a internet ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent("ActivityInicioSesion");
            startActivity(intent);

        }

    }


    public void irAEnlaceTerminosDePrivacidad(View view) {
        Intent intent = new Intent("ActivityPrivacidad");
        startActivity(intent);
    }


    private void pasaProvincias() {
        RequestParams params = new RequestParams();
        params.put(ProvinciaDataSource.ColumnProvincia.ID, -1);
        params.put(ProvinciaDataSource.ColumnProvincia.NOMBRE, "");
        invokeWS(params);
    }


    public void invokeWS(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/devuelve_provincias.php", params, new AsyncHttpResponseHandler() {
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
                            //pasaMunicipios();
                            pasaSectoresActividad();
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
            codigoProvincia = Integer.parseInt(var.get(ProvinciaDataSource.ColumnProvincia.ID).toString());
            denominacionProvincia = var.get(ProvinciaDataSource.ColumnProvincia.NOMBRE).toString();

            MDB.insertarProvincia(codigoProvincia, denominacionProvincia);
        }
        //  MDB.closeProvincia();
    }


    private void pasaMunicipios() {
        RequestParams params = new RequestParams();
        params.put(MunicipioDataSource.ColumnMunicipio.CODIGO_PROVINCIA, 0);
        params.put(MunicipioDataSource.ColumnMunicipio.CODIGO_MUNICIPIO, 0);
        params.put(MunicipioDataSource.ColumnMunicipio.NOMBRE_MUNICIPIO, "");
        params.put(MunicipioDataSource.ColumnMunicipio.TOTAL_HABITANTES, 0);
        params.put(MunicipioDataSource.ColumnMunicipio.TOTAL_HOMBRES, 0);
        params.put(MunicipioDataSource.ColumnMunicipio.TOTAL_MUJERES, 0);

        invokeWS2(params);
    }


    public void invokeWS2(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/devuelve_municipios.php", params, new AsyncHttpResponseHandler() {
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
                            recogeDatos3(obj);
                            pasaSectoresActividad();
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


    private void recogeDatos3(JSONObject obj) throws JSONException {
        JSONArray datos = obj.getJSONArray("info");
        int longitud = datos.length();
        for (int x = 0; x < longitud; x++) {
            JSONObject var = datos.getJSONObject(x);

            codigoProvincia = Integer.parseInt(var.get(MunicipioDataSource.ColumnMunicipio.CODIGO_PROVINCIA).toString());
            codigoMunicipio = Integer.parseInt(var.get(MunicipioDataSource.ColumnMunicipio.CODIGO_MUNICIPIO).toString());
            denominacionProvincia = var.get(MunicipioDataSource.ColumnMunicipio.NOMBRE_MUNICIPIO).toString();
            totalHabitantes = Integer.parseInt(var.get(MunicipioDataSource.ColumnMunicipio.TOTAL_HABITANTES).toString());
            totalHombres = Integer.parseInt(var.get(MunicipioDataSource.ColumnMunicipio.TOTAL_HOMBRES).toString());
            totalMujeres = Integer.parseInt(var.get(MunicipioDataSource.ColumnMunicipio.TOTAL_MUJERES).toString());

            Municipio municipio = new Municipio();
            municipio.setCodigoProvincia(codigoProvincia);
            municipio.setCodigoMunicipio(codigoMunicipio);
            municipio.setNombreMunicipio(denominacionProvincia);
            municipio.setTotalHabitantes(totalHabitantes);
            municipio.setHombres(totalHombres);
            municipio.setMujeres(totalMujeres);
            MDB.addMunicipioPoolUpdate(municipio);


        }
        MDB.commitPoolUpdate();
        //  MDB.closeMunicipo();

    }

    private void pasaSectoresActividad() {
        RequestParams params = new RequestParams();
        params.put(SectorActividadDataSource.ColumnSector.C_ID, 0);
        params.put(SectorActividadDataSource.ColumnSector.C_GRUPO_ACT, "");

        invokeWSSectoresActividad(params);
    }


    public void invokeWSSectoresActividad(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/devuelve_sectores_actividad.php", params, new AsyncHttpResponseHandler() {
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
                            recogeDatosSectoresActividad(obj);
                            pasaPlanInversor();
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

    private void recogeDatosSectoresActividad(JSONObject obj) throws JSONException {
        JSONArray datos = obj.getJSONArray("info");
        int longitud = datos.length();
        for (int x = 0; x < longitud; x++) {
            JSONObject var = datos.getJSONObject(x);
            int c_id = Integer.parseInt(var.get(SectorActividadDataSource.ColumnSector.C_ID).toString());
            String c_grupo_act = var.getString(SectorActividadDataSource.ColumnSector.C_GRUPO_ACT).toString();
            int m_orden_act = Integer.parseInt(var.get(SectorActividadDataSource.ColumnSector.M_ORDEN_ACT).toString());
            String c_sector = var.getString(SectorActividadDataSource.ColumnSector.C_SECTOR).toString();
            int m_orden_sector = Integer.parseInt(var.get(SectorActividadDataSource.ColumnSector.M_ORDEN_SECTOR).toString());
            String d_subsector = var.getString(SectorActividadDataSource.ColumnSector.D_SUBSECTOR).toString();

            Sector sector = new Sector();
            sector.setcId(c_id);
            sector.setcGrupoAct(c_grupo_act);
            sector.setmOrdenAct(m_orden_act);
            sector.setcSector(c_sector);
            sector.setmOrdenSector(m_orden_sector);
            sector.setdSubSector(d_subsector);
//            MDB.insertarSectorActividad(sector);
            MDB.addSectoresPoolUpdate(sector);

        }
        MDB.commitPoolUpdateSector();

    }

    private void pasaPlanInversor() {
        RequestParams params = new RequestParams();
        params.put(PlanInversorDataSource.ColumnPlanInversor.ID, 0);
        params.put(PlanInversorDataSource.ColumnPlanInversor.LITERAL, "");

        invokeWSPlanInversor(params);
    }


    public void invokeWSPlanInversor(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/devuelve_plan_inversor.php", params, new AsyncHttpResponseHandler() {
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
                            recogeDatosPlanInversor(obj);
                            pasaCuandoEmpezar();
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

    private void recogeDatosPlanInversor(JSONObject obj) throws JSONException {
        JSONArray datos = obj.getJSONArray("info");
        int longitud = datos.length();
        for (int x = 0; x < longitud; x++) {
            JSONObject var = datos.getJSONObject(x);

            codigoProvincia = Integer.parseInt(var.get(PlanInversorDataSource.ColumnPlanInversor.ID).toString());
            denominacionProvincia = var.get(PlanInversorDataSource.ColumnPlanInversor.LITERAL).toString();
            String resultado = denominacionProvincia.replace("\u0080", "€");

            MDB.insertarPlanInversor(codigoProvincia, resultado);
        }

    }

    private void pasaCuandoEmpezar() {
        RequestParams params = new RequestParams();
        params.put(CuandoEmpezarDataSource.ColumnCuandoEmpezar.ID, 0);
        params.put(CuandoEmpezarDataSource.ColumnCuandoEmpezar.NOMBRE, "");

        invokeWSCuandoEmpezar(params);
    }


    public void invokeWSCuandoEmpezar(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/devuelve_cuando_empezar.php", params, new AsyncHttpResponseHandler() {
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
                            recogeDatosCuandoEmpezar(obj);
                            pasaPerfilProfesional();
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

    private void recogeDatosCuandoEmpezar(JSONObject obj) throws JSONException {
        JSONArray datos = obj.getJSONArray("info");
        int longitud = datos.length();
        for (int x = 0; x < longitud; x++) {
            JSONObject var = datos.getJSONObject(x);

            codigoProvincia = Integer.parseInt(var.get(CuandoEmpezarDataSource.ColumnCuandoEmpezar.ID).toString());
            denominacionProvincia = var.get(CuandoEmpezarDataSource.ColumnCuandoEmpezar.NOMBRE).toString();

            MDB.insertarCuandoEmpezar(codigoProvincia, denominacionProvincia);
        }
        //MDB.closeMunicipo();
    }

    private void pasaPerfilProfesional() {
        RequestParams params = new RequestParams();
        params.put(PerfilProfesionalDataSource.ColumnPerfilProfesional.ID, 0);
        params.put(PerfilProfesionalDataSource.ColumnPerfilProfesional.LITERAL, "");

        invokeWSPerfilProfesional(params);
    }


    public void invokeWSPerfilProfesional(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/devuelve_perfil_profesional.php", params, new AsyncHttpResponseHandler() {
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
                            recogeDatosPerfilProfesional(obj);
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

    private void recogeDatosPerfilProfesional(JSONObject obj) throws JSONException {
        JSONArray datos = obj.getJSONArray("info");
        int longitud = datos.length();
        for (int x = 0; x < longitud; x++) {
            JSONObject var = datos.getJSONObject(x);

            codigoProvincia = Integer.parseInt(var.get(PerfilProfesionalDataSource.ColumnPerfilProfesional.ID).toString());
            denominacionProvincia = var.get(PerfilProfesionalDataSource.ColumnPerfilProfesional.LITERAL).toString();

            MDB.insertarPerfilProfesional(codigoProvincia, denominacionProvincia);
        }
        MDB.close();
    }

}
