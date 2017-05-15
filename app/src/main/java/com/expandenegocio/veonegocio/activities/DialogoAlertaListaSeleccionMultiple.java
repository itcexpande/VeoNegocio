package com.expandenegocio.veonegocio.activities;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.expandenegocio.veonegocio.DAO.ProvinciaDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.Provincia;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by DAM on 22/11/2016.
 */
public class DialogoAlertaListaSeleccionMultiple extends DialogFragment {
    private String identificadorFranquiciaSeleccionada;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActivityFranquiciasProvincias activity = (ActivityFranquiciasProvincias) getActivity();
        //identificadorFranquiciaSeleccionada = activity.getDataFragment();
        String recibeDato = activity.getDataFragment();

//        Toast.makeText(activity, recibeDato, Toast.LENGTH_LONG).show();

        View view = inflater.inflate(R.layout.layout_franquicias_provincias, container,
                false);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());
        ventana.setIcon(android.R.drawable.ic_input_add);
        ActivityFranquiciasProvincias activity = (ActivityFranquiciasProvincias) getActivity();
        String recibeDato = activity.getDataFragment();
        String[] partes= recibeDato.split(";");
        identificadorFranquiciaSeleccionada = partes[0];
        String nombre2= partes[1];
        ventana.setTitle("Selecciona Provincias para esta Franquicia " + nombre2);


        ProvinciaDataSource dataSource = new ProvinciaDataSource(getActivity());
        ArrayList<Provincia> listaProv = dataSource.getProvincias2();
        final int longitud = listaProv.size();
        final String[] matriz = new String[longitud];
        final Integer[] c_prov = new Integer[longitud];
        final boolean[] seleccionadas = new boolean[longitud];
        for (int x = 0; x < longitud; x++) {
            matriz[x] = listaProv.get(x).getNombreProvincia();
            c_prov[x] = listaProv.get(x).getId();
            seleccionadas[x] = false;
        }

        Resources res = getResources();
        ventana.setMultiChoiceItems(matriz,
                seleccionadas,
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int opcion, boolean isChecked) {
                        // Se ejecuta cuando el usuario selecciona una opción
                        if (isChecked) {
                            Toast.makeText(getActivity(), "Has seleccionado " + matriz[opcion], Toast.LENGTH_SHORT).show();
                            seleccionadas[opcion] = true;
                        } else {
                            Toast.makeText(getActivity(), "Has deseleccionado " + matriz[opcion], Toast.LENGTH_SHORT).show();
                            seleccionadas[opcion] = false;
                        }

                    }
                });
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {

                //se ejecuta cuando el usuario hace clic sobre el botón aceptar
                Toast.makeText(getActivity(), "Has pulsado el botón 'Aceptar'", Toast.LENGTH_LONG).show();
                // alta en tabla provincia franquicias
                bajaRegistrosFranquiciasProvincias(identificadorFranquiciaSeleccionada);
                for (int x = 0; x < longitud; x++) {
                    if (seleccionadas[x] == true && c_prov != null && identificadorFranquiciaSeleccionada != null) {
                        int codigoProvincia = c_prov[x];
                        altaProvinciasFranquicia(codigoProvincia, identificadorFranquiciaSeleccionada);
                    }
                }
            }
        });
        ventana.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                Toast.makeText(getActivity(), "Has pulsado el botón 'Cancelar'", Toast.LENGTH_LONG).show();
            }
        });
        return ventana.create();
    }

    private void bajaRegistrosFranquiciasProvincias(String string) {
        RequestParams params = new RequestParams();
        params.put("id", string);
        invokeWS2(params);
    }

    public void invokeWS2(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/baja_franquicias_provincias.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);


                try {

                    JSONObject obj = new JSONObject(response);

                    switch (obj.getInt("status")) {

                        case 0:
                            Toast.makeText(getActivity(), "Borrados", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(getActivity(), "Error en baja", Toast.LENGTH_LONG).show();
                            break;
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getActivity(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                    error) {


                try {
                    if (responseBody != null) {
                        String response = new String(responseBody);
                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void altaProvinciasFranquicia(int p, String f) {
        RequestParams params = new RequestParams();

        params.put("idProvincia", p);
        params.put("id", f);

        invokeWS3(params);

    }

    public void invokeWS3(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/alta_franquicias_provincias.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);


                try {

                    JSONObject obj = new JSONObject(response);

                    switch (obj.getInt("status")) {

                        case 0:
                            Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            Toast.makeText(getActivity(), "Registrado correctamente!", Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            Toast.makeText(getActivity(), "Ya hay un registro igual", Toast.LENGTH_LONG).show();
                            break;
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getActivity(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                    error) {


                try {
                    if (responseBody != null) {
                        String response = new String(responseBody);
                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

}
