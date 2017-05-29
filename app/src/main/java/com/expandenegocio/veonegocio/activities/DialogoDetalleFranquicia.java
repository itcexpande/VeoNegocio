package com.expandenegocio.veonegocio.activities;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.expandenegocio.veonegocio.DAO.FranquiciaDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.Franquicia;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

import static android.R.attr.fragment;


public class DialogoDetalleFranquicia extends DialogFragment {


    private ArrayList<Franquicia> listaFranquicias = new ArrayList<>();
    private TextView tv1, tv2, tv3, tv4, tv5;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        Franquicia fd = new Franquicia();
        fd = (Franquicia) getArguments().getSerializable("franquicia");


        String dataId = fd.getId().toString();
        String dataName = fd.getName().toString();
        String dataEmpresa = fd.getEmpresa().toString();
        String dataTipoAc = fd.getTipo_actividad().toString();
        String dataTfno = fd.getPhone_office().toString();
        String dataWeb = fd.getWebsite().toString();

        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(
                infService);

        final View layoutInflado = li.inflate(R.layout.listado_detalle_franquicia, null);

        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());
        ventana.setTitle("Detalle Franquicia");

        ventana.setView(layoutInflado);
        TextView tv2 = (TextView) layoutInflado.findViewById(R.id.tv_detalle_franquicia_empresa);
        TextView tv4 = (TextView) layoutInflado.findViewById(R.id.tv_detalle_franquicia_telefonoficina);
        TextView tv3 = (TextView) layoutInflado.findViewById(R.id.tv_detalle_franquicia_tipodeactividad);
        TextView tv5 = (TextView) layoutInflado.findViewById(R.id.tv_detalle_franquicia_web);
        TextView tv1 = (TextView) layoutInflado.findViewById(R.id.tv_detalle_franquicia_nombre);

        tv1.setText("Nombre: " + dataName);
        tv2.setText("Empresa: " + dataEmpresa);
        tv3.setText("Actividad: " + dataTipoAc);
        tv4.setText("Telefono: " + dataTfno);
        tv5.setText("WEB: " + dataWeb);


        ventana.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                    }
                });


        return ventana.create();
    }


    private String devuelveTextoSiEsNull(String string) {
        if (string == null) {
            return "NULL";
        } else {
            return string;
        }
    }


    public Date DeStringADate(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String strFecha = fecha;
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(strFecha);
            System.out.println(fechaDate.toString());
            return fechaDate;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return fechaDate;
        }
    }

    public Double DevuelveDoubleCero(String valor) {
        try {
            if (valor == null || valor.equals("")) {
                return 0d;
            } else {
                return Double.parseDouble(valor);
            }
        } catch (NumberFormatException e) {
            return 0d;
        }
    }

    public Integer DevuelveIntegerCero(String valor) {
        try {
            if (valor == null || valor.equals("")) {
                return 0;
            } else {
                return Integer.parseInt(valor);
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }


}