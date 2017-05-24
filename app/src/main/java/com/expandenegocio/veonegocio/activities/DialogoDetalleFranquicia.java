package com.expandenegocio.veonegocio.activities;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
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
        String dataId = getArguments().getString("id").toString();
        String dataName = getArguments().getString("name").toString();
        String dataEmpresa = getArguments().getString("empresa").toString();
        String dataTipoAc = getArguments().getString("tipoAc").toString();
        String dataTfno = getArguments().getString("tfno").toString();
        String dataWeb = getArguments().getString("web").toString();


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


        //    ArrayList<CharSequence> datos = new ArrayList<>();

        ventana.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                    }
                });


        return ventana.create();
    }


    private void procesarInformacion(Franquicia f) {
        RequestParams params = new RequestParams();
        params.put(FranquiciaDataSource.ColumnFranquicias.ID, f.getId().toString());
        invokeWS(params);
    }


    public void invokeWS(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/retorna_una_franquicia.php", params, new AsyncHttpResponseHandler() {
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
                            recogeDatos(obj);
                            //   procesar();
                            break;
                        case 2:
                            Toast.makeText(getActivity(), "Ya hay un usuario registrado con ese correo", Toast.LENGTH_LONG).show();
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

    private void procesar() {


        String infService = Context.LAYOUT_INFLATER_SERVICE;

        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(
                infService);
        final View layoutInflado = li.inflate(R.layout.listado_detalle_franquicia, null);

        TextView tv2 = (TextView) layoutInflado.findViewById(R.id.tv_detalle_franquicia_empresa);
        TextView tv4 = (TextView) layoutInflado.findViewById(R.id.tv_detalle_franquicia_telefonoficina);
        TextView tv3 = (TextView) layoutInflado.findViewById(R.id.tv_detalle_franquicia_tipodeactividad);
        TextView tv5 = (TextView) layoutInflado.findViewById(R.id.tv_detalle_franquicia_web);
        TextView tv1 = (TextView) layoutInflado.findViewById(R.id.tv_detalle_franquicia_nombre);
        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());
        ventana.setTitle("Detalle Franquicia");

        ventana.setView(layoutInflado);


        Franquicia franquicia = listaFranquicias.get(0);


        String nombreEmpresa = devuelveTextoSiEsNull(franquicia.getEmpresa().toString());
        String tipoActividad = devuelveTextoSiEsNull(franquicia.getTipo_actividad().toString());
        String telefono = devuelveTextoSiEsNull(franquicia.getPhone_office().toString());
        String nombre = devuelveTextoSiEsNull(franquicia.getName().toString());
        String web = devuelveTextoSiEsNull(franquicia.getWebsite().toString());

        tv1.setText("Empresa: " + nombre);
        tv2.setText("Empresa: " + nombreEmpresa);
        tv3.setText("Actividad: " + tipoActividad);
        tv4.setText("Telefono: " + telefono);
        tv5.setText("WEB: " + web);


    }

    private String devuelveTextoSiEsNull(String string) {
        if (string == null) {
            return "NULL";
        } else {
            return string;
        }
    }

    private void recogeDatos(JSONObject obj) throws JSONException {
        JSONArray datos = obj.getJSONArray("info");
        int longitud = datos.length();
        for (int x = 0; x < longitud; x++) {
            JSONObject var = datos.getJSONObject(x);
            Franquicia franquicia = new Franquicia();

            franquicia.setId(var.get(FranquiciaDataSource.ColumnFranquicias.ID).toString());
            franquicia.setName(var.get(FranquiciaDataSource.ColumnFranquicias.NAME).toString());
            franquicia.setDate_entered(DeStringADate(var.get(FranquiciaDataSource.ColumnFranquicias.DATE_ENTERED).toString()));
            franquicia.setDate_modified(DeStringADate(var.get(FranquiciaDataSource.ColumnFranquicias.DATE_MODIFIED).toString()));
            franquicia.setModified_user_id(var.get(FranquiciaDataSource.ColumnFranquicias.MODIFIED_USER_ID).toString());
            franquicia.setCreated_by(var.get(FranquiciaDataSource.ColumnFranquicias.CREATED_BY).toString());
            franquicia.setDescription(var.get(FranquiciaDataSource.ColumnFranquicias.DESCRIPTION).toString());
            franquicia.setDeleted(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.DELETED).toString()));
            franquicia.setAssigned_user_id(var.get(FranquiciaDataSource.ColumnFranquicias.ASSIGNED_USER_ID).toString());
            franquicia.setFranquicia_type(var.get(FranquiciaDataSource.ColumnFranquicias.FRANQUICIA_TYPE).toString());
            franquicia.setIndustry(var.get(FranquiciaDataSource.ColumnFranquicias.INDUSTRY).toString());
            franquicia.setAnnual_revenue(var.get(FranquiciaDataSource.ColumnFranquicias.ANNUAL_REVENUE).toString());
            franquicia.setPhone_fax(var.get(FranquiciaDataSource.ColumnFranquicias.PHONE_FAX).toString());
            franquicia.setBilling_address_street(var.get(FranquiciaDataSource.ColumnFranquicias.BILLING_ADDRESS_STREET).toString());
            franquicia.setBilling_address_city(var.get(FranquiciaDataSource.ColumnFranquicias.BILLING_ADDRESS_CITY).toString());
            franquicia.setBilling_address_state(var.get(FranquiciaDataSource.ColumnFranquicias.BILLING_ADDRESS_STATE).toString());
            franquicia.setBilling_address_postalcode(var.get(FranquiciaDataSource.ColumnFranquicias.BILLING_ADDRESS_POSTALCODE).toString());
            franquicia.setBilling_address_country(var.get(FranquiciaDataSource.ColumnFranquicias.BILLING_ADDRESS_COUNTRY).toString());
            franquicia.setRating(var.get(FranquiciaDataSource.ColumnFranquicias.RATING).toString());
            franquicia.setPhone_office(var.get(FranquiciaDataSource.ColumnFranquicias.PHONE_OFFICE).toString());
            franquicia.setPhone_alternate(var.get(FranquiciaDataSource.ColumnFranquicias.PHONE_ALTERNATE).toString());
            franquicia.setWebsite(var.get(FranquiciaDataSource.ColumnFranquicias.WEBSITE).toString());
            franquicia.setOwnership(var.get(FranquiciaDataSource.ColumnFranquicias.OWNERSHIP).toString());
            franquicia.setEmployees(var.get(FranquiciaDataSource.ColumnFranquicias.EMPLOYEES).toString());
            franquicia.setTicker_symbol(var.get(FranquiciaDataSource.ColumnFranquicias.TICKER_SYMBOL).toString());
            franquicia.setShipping_address_street(var.get(FranquiciaDataSource.ColumnFranquicias.SHIPPING_ADDRESS_STREET).toString());
            franquicia.setShipping_address_city(var.get(FranquiciaDataSource.ColumnFranquicias.SHIPPING_ADDRESS_CITY).toString());
            franquicia.setShipping_address_state(var.get(FranquiciaDataSource.ColumnFranquicias.SHIPPING_ADDRESS_STATE).toString());
            franquicia.setShipping_address_postalcode(var.get(FranquiciaDataSource.ColumnFranquicias.SHIPPING_ADDRESS_POSTALCODE).toString());
            franquicia.setShipping_address_country(var.get(FranquiciaDataSource.ColumnFranquicias.SHIPPING_ADDRESS_COUNTRY).toString());
            franquicia.setTipo_ficha(var.get(FranquiciaDataSource.ColumnFranquicias.TIPO_FICHA).toString());
            franquicia.setZona(var.get(FranquiciaDataSource.ColumnFranquicias.ZONA).toString());
            franquicia.setLogotipo(var.get(FranquiciaDataSource.ColumnFranquicias.LOGOTIPO).toString());
            franquicia.setVideo(var.get(FranquiciaDataSource.ColumnFranquicias.VIDEO).toString());
            franquicia.setExclusion_de_sector(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.EXCLUSION_DE_SECTOR).toString()));
            franquicia.setExclusion_de_subsector(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.EXCLUSION_DE_SUBSECTOR).toString()));
            franquicia.setEstado_validacion(var.get(FranquiciaDataSource.ColumnFranquicias.ESTADO_VALIDACION).toString());
            franquicia.setGestionado_por(var.get(FranquiciaDataSource.ColumnFranquicias.GESTIONADO_POR).toString());
            franquicia.setTipo_de_franquiciado(var.get(FranquiciaDataSource.ColumnFranquicias.TIPO_DE_FRANQUICIADO).toString());
            franquicia.setNecesario_titulacion(var.get(FranquiciaDataSource.ColumnFranquicias.NECESARIO_TITULACION).toString());
            franquicia.setTitulacion(var.get(FranquiciaDataSource.ColumnFranquicias.TITULACION).toString());
            franquicia.setCondiciones_especiales(var.get(FranquiciaDataSource.ColumnFranquicias.CONDICIONES_ESPECIALES).toString());
            franquicia.setFin_condiciones_especiales(DeStringADate(var.get(FranquiciaDataSource.ColumnFranquicias.FIN_CONDICIONES_ESPECIALES).toString()));
            franquicia.setObservaciones(var.get(FranquiciaDataSource.ColumnFranquicias.OBSERVACIONES).toString());
            franquicia.setInversion_minima_necesaria(DevuelveDoubleCero(var.get(FranquiciaDataSource.ColumnFranquicias.INVERSION_MINIMA_NECESARIA).toString()));
            franquicia.setCurrency_id(var.get(FranquiciaDataSource.ColumnFranquicias.CURRENCY_ID).toString());
            franquicia.setTipo_franquicia(var.get(FranquiciaDataSource.ColumnFranquicias.TIPO_FRANQUICIA).toString());
            franquicia.setNecesita_local(var.get(FranquiciaDataSource.ColumnFranquicias.NECESITA_LOCAL).toString());
            franquicia.setSuperficie_local(var.get(FranquiciaDataSource.ColumnFranquicias.SUPERFICIE_LOCAL).toString());
            franquicia.setRequisitos_local(var.get(FranquiciaDataSource.ColumnFranquicias.REQUISITOS_LOCAL).toString());
            franquicia.setEntorno_ubicacion(var.get(FranquiciaDataSource.ColumnFranquicias.ENTORNO_UBICACION).toString());
            franquicia.setObservaciones_ubicacion(var.get(FranquiciaDataSource.ColumnFranquicias.OBSERVACIONES_UBICACION).toString());
            franquicia.setProvincias_ubicar_negocio(var.get(FranquiciaDataSource.ColumnFranquicias.PROVINCIAS_UBICAR_NEGOCIO).toString());
            franquicia.setPoblacion_minima(var.get(FranquiciaDataSource.ColumnFranquicias.POBLACION_MINIMA).toString());
            franquicia.setPersonal_minimo(var.get(FranquiciaDataSource.ColumnFranquicias.PERSONAL_MINIMO).toString());
            franquicia.setZona_exclisiva(var.get(FranquiciaDataSource.ColumnFranquicias.ZONA_EXCLISIVA).toString());
            franquicia.setVigencia_contrato(var.get(FranquiciaDataSource.ColumnFranquicias.VIGENCIA_CONTRATO).toString());
            franquicia.setReconvertir_negocio(var.get(FranquiciaDataSource.ColumnFranquicias.RECONVERTIR_NEGOCIO).toString());
            franquicia.setAcuerdo_financiacion(var.get(FranquiciaDataSource.ColumnFranquicias.ACUERDO_FINANCIACION).toString());
            franquicia.setSector(var.get(FranquiciaDataSource.ColumnFranquicias.SECTOR).toString());
            franquicia.setBreve_descripcion(var.get(FranquiciaDataSource.ColumnFranquicias.BREVE_DESCRIPCION).toString());
            franquicia.setFecha_creacion(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.FECHA_CREACION).toString()));
            franquicia.setFecha_expansion(DeStringADate(var.get(FranquiciaDataSource.ColumnFranquicias.FECHA_EXPANSION).toString()));
            franquicia.setCentros_nacionales_propios(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CENTROS_NACIONALES_PROPIOS).toString()));
            franquicia.setCentros_nacionales_franquicia(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CENTROS_NACIONALES_FRANQUICIA).toString()));
            franquicia.setPresencia_internacional(var.get(FranquiciaDataSource.ColumnFranquicias.PRESENCIA_INTERNACIONAL).toString());
            franquicia.setPaises(var.get(FranquiciaDataSource.ColumnFranquicias.PAISES).toString());
            franquicia.setRed_spain(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.RED_SPAIN).toString()));
            franquicia.setCentros_extranjeros_propios(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CENTROS_EXTRANJEROS_PROPIOS).toString()));
            franquicia.setCentros_extranjeros_franqui(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CENTROS_EXTRANJEROS_FRANQUI).toString()));
            franquicia.setRed_extrangera(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.RED_EXTRANGERA).toString()));
            franquicia.setPlantilla_central(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.PLANTILLA_CENTRAL).toString()));
            franquicia.setCifra_negocio_grupo(DevuelveDoubleCero(var.get(FranquiciaDataSource.ColumnFranquicias.CIFRA_NEGOCIO_GRUPO).toString()));
            franquicia.setNifra(var.get(FranquiciaDataSource.ColumnFranquicias.NIFRA).toString());
            franquicia.setRegmarca(var.get(FranquiciaDataSource.ColumnFranquicias.REGMARCA).toString());
            franquicia.setAef(var.get(FranquiciaDataSource.ColumnFranquicias.AEF).toString());
            franquicia.setSellos_calidad(var.get(FranquiciaDataSource.ColumnFranquicias.SELLOS_CALIDAD).toString());
            franquicia.setOtro_sello_calidad(var.get(FranquiciaDataSource.ColumnFranquicias.OTRO_SELLO_CALIDAD).toString());
            franquicia.setEmpresa(var.get(FranquiciaDataSource.ColumnFranquicias.EMPRESA).toString());
            franquicia.setLocaidad(var.get(FranquiciaDataSource.ColumnFranquicias.LOCAIDAD).toString());
            franquicia.setDireccion_direccion(var.get(FranquiciaDataSource.ColumnFranquicias.DIRECCION_DIRECCION).toString());
            franquicia.setDireccion_localidad(var.get(FranquiciaDataSource.ColumnFranquicias.DIRECCION_LOCALIDAD).toString());
            franquicia.setDireccion_codigo_postal(var.get(FranquiciaDataSource.ColumnFranquicias.DIRECCION_CODIGO_POSTAL).toString());
            franquicia.setDireccion_provincia(var.get(FranquiciaDataSource.ColumnFranquicias.DIRECCION_PROVINCIA).toString());
            franquicia.setDireccion_pais(var.get(FranquiciaDataSource.ColumnFranquicias.DIRECCION_PAIS).toString());
            franquicia.setPersona_contacto(var.get(FranquiciaDataSource.ColumnFranquicias.PERSONA_CONTACTO).toString());
            franquicia.setFecha_acuerdo(DeStringADate(var.get(FranquiciaDataSource.ColumnFranquicias.FECHA_ACUERDO).toString()));
            franquicia.setFecha_activacion(DeStringADate(var.get(FranquiciaDataSource.ColumnFranquicias.FECHA_ACTIVACION).toString()));
            franquicia.setFicha_ampliada_anterior(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.FICHA_AMPLIADA_ANTERIOR).toString()));
            franquicia.setExpan_consultora_id_c(var.get(FranquiciaDataSource.ColumnFranquicias.EXPAN_CONSULTORA_ID_C).toString());
            franquicia.setEstado_fran(var.get(FranquiciaDataSource.ColumnFranquicias.ESTADO_FRAN).toString());
            franquicia.setObservaciones_inter(var.get(FranquiciaDataSource.ColumnFranquicias.OBSERVACIONES_INTER).toString());
            franquicia.setPreseleccionadas(var.get(FranquiciaDataSource.ColumnFranquicias.PRESELECCIONADAS).toString());
            franquicia.setHomologacion(var.get(FranquiciaDataSource.ColumnFranquicias.HOMOLOGACION).toString());
            franquicia.setUser_id_c(var.get(FranquiciaDataSource.ColumnFranquicias.USER_ID_C).toString());
            franquicia.setDocumentacion_pendiente(var.get(FranquiciaDataSource.ColumnFranquicias.DOCUMENTACION_PENDIENTE).toString());
            franquicia.setObjeciones_foros_bbdd(var.get(FranquiciaDataSource.ColumnFranquicias.OBJECIONES_FOROS_BBDD).toString());
            franquicia.setDerecho_entrada_min(DevuelveDoubleCero(var.get(FranquiciaDataSource.ColumnFranquicias.DERECHO_ENTRADA_MIN).toString()));
            franquicia.setDerecho_entrada_max(DevuelveDoubleCero(var.get(FranquiciaDataSource.ColumnFranquicias.DERECHO_ENTRADA_MAX).toString()));
            franquicia.setRoyalty_expltacion(var.get(FranquiciaDataSource.ColumnFranquicias.ROYALTY_EXPLTACION).toString());
            franquicia.setRoyalty_publicitario(var.get(FranquiciaDataSource.ColumnFranquicias.ROYALTY_PUBLICITARIO).toString());
            franquicia.setOtros_royalties(var.get(FranquiciaDataSource.ColumnFranquicias.OTROS_ROYALTIES).toString());
            franquicia.setFacturacion_year_unidad_fran_1(DevuelveDoubleCero(var.get(FranquiciaDataSource.ColumnFranquicias.FACTURACION_YEAR_UNIDAD_FRAN_1).toString()));
            franquicia.setFacturacion_year_unidad_fran_2(DevuelveDoubleCero(var.get(FranquiciaDataSource.ColumnFranquicias.FACTURACION_YEAR_UNIDAD_FRAN_2).toString()));
            franquicia.setFacturacion_year_unidad_fran_3(DevuelveDoubleCero(var.get(FranquiciaDataSource.ColumnFranquicias.FACTURACION_YEAR_UNIDAD_FRAN_3).toString()));
            franquicia.setAmortizacio_inversion(var.get(FranquiciaDataSource.ColumnFranquicias.AMORTIZACIO_INVERSION).toString());
            franquicia.setBeneficio_neto_unidad_fran_1(DevuelveDoubleCero(var.get(FranquiciaDataSource.ColumnFranquicias.BENEFICIO_NETO_UNIDAD_FRAN_1).toString()));
            franquicia.setBeneficio_neto_unidad_fran_2(DevuelveDoubleCero(var.get(FranquiciaDataSource.ColumnFranquicias.BENEFICIO_NETO_UNIDAD_FRAN_2).toString()));
            franquicia.setBeneficio_neto_unidad_fran_3(DevuelveDoubleCero(var.get(FranquiciaDataSource.ColumnFranquicias.BENEFICIO_NETO_UNIDAD_FRAN_3).toString()));
            franquicia.setTipo_actividad(var.get(FranquiciaDataSource.ColumnFranquicias.TIPO_ACTIVIDAD).toString());
            franquicia.setMovil_general(var.get(FranquiciaDataSource.ColumnFranquicias.MOVIL_GENERAL).toString());
            franquicia.setCargo_contacto_general(var.get(FranquiciaDataSource.ColumnFranquicias.CARGO_CONTACTO_GENERAL).toString());
            franquicia.setContacto_administracion(var.get(FranquiciaDataSource.ColumnFranquicias.CONTACTO_ADMINISTRACION).toString());
            franquicia.setTelefono_administracion(var.get(FranquiciaDataSource.ColumnFranquicias.TELEFONO_ADMINISTRACION).toString());
            franquicia.setMovil_administracion(var.get(FranquiciaDataSource.ColumnFranquicias.MOVIL_ADMINISTRACION).toString());
            franquicia.setContacto_intermediacion(var.get(FranquiciaDataSource.ColumnFranquicias.CONTACTO_INTERMEDIACION).toString());
            franquicia.setTelefono_intermediacion(var.get(FranquiciaDataSource.ColumnFranquicias.TELEFONO_INTERMEDIACION).toString());
            franquicia.setMovil_intermediacion(var.get(FranquiciaDataSource.ColumnFranquicias.MOVIL_INTERMEDIACION).toString());
            franquicia.setCorreo_administracion(var.get(FranquiciaDataSource.ColumnFranquicias.CORREO_ADMINISTRACION).toString());
            franquicia.setCorreo_intermediacion(var.get(FranquiciaDataSource.ColumnFranquicias.CORREO_INTERMEDIACION).toString());
            franquicia.setEmail1(var.get(FranquiciaDataSource.ColumnFranquicias.EMAIL1).toString());
            franquicia.setCorreo_general(var.get(FranquiciaDataSource.ColumnFranquicias.CORREO_GENERAL).toString());
            franquicia.setContacto_general_2(var.get(FranquiciaDataSource.ColumnFranquicias.CONTACTO_GENERAL_2).toString());
            franquicia.setTelefono_contacto_2(var.get(FranquiciaDataSource.ColumnFranquicias.TELEFONO_CONTACTO_2).toString());
            franquicia.setTelefono_alternativo_2(var.get(FranquiciaDataSource.ColumnFranquicias.TELEFONO_ALTERNATIVO_2).toString());
            franquicia.setMovil_general_2(var.get(FranquiciaDataSource.ColumnFranquicias.MOVIL_GENERAL_2).toString());
            franquicia.setCorreo_contacto_2(var.get(FranquiciaDataSource.ColumnFranquicias.CORREO_CONTACTO_2).toString());
            franquicia.setCargo_contacto_2(var.get(FranquiciaDataSource.ColumnFranquicias.CARGO_CONTACTO_2).toString());
            franquicia.setInicio_expansion(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.INICIO_EXPANSION).toString()));
            franquicia.setObservaciones_administracion(var.get(FranquiciaDataSource.ColumnFranquicias.OBSERVACIONES_ADMINISTRACION).toString());
            franquicia.setTipo_cuenta(var.get(FranquiciaDataSource.ColumnFranquicias.TIPO_CUENTA).toString());
            franquicia.setCorreo_envio(var.get(FranquiciaDataSource.ColumnFranquicias.CORREO_ENVIO).toString());
            franquicia.setChk_c1(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CHK_C1).toString()));
            franquicia.setChk_c2(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CHK_C2).toString()));
            franquicia.setChk_c3(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CHK_C3).toString()));
            franquicia.setChk_c4(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CHK_C4).toString()));
            franquicia.setChk_c11(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CHK_C11).toString()));
            franquicia.setChk_c12(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CHK_C12).toString()));
            franquicia.setChk_c13(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CHK_C13).toString()));
            franquicia.setChk_c14(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CHK_C14).toString()));
            franquicia.setChk_c15(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.CHK_C15).toString()));
            franquicia.setDir_cons_id_c(var.get(FranquiciaDataSource.ColumnFranquicias.DIR_CONS_ID_C).toString());
            franquicia.setLlamar_todos(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.LLAMAR_TODOS).toString()));
            franquicia.setInforme_urgente(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.INFORME_URGENTE).toString()));
            franquicia.setModNeg1(var.get(FranquiciaDataSource.ColumnFranquicias.MODNEG1).toString());
            franquicia.setModNeg2(var.get(FranquiciaDataSource.ColumnFranquicias.MODNEG2).toString());
            franquicia.setModNeg3(var.get(FranquiciaDataSource.ColumnFranquicias.MODNEG3).toString());
            franquicia.setModNeg4(var.get(FranquiciaDataSource.ColumnFranquicias.MODNEG4).toString());
            // franquicia.setPrioridad(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.PRIORIDAD).toString()));
            franquicia.setValNeg11(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG11).toString());
            franquicia.setValNeg12(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG12).toString());
            franquicia.setValNeg13(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG13).toString());
            franquicia.setValNeg14(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG14).toString());
            franquicia.setValNeg15(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG15).toString());
            franquicia.setValNeg21(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG21).toString());
            franquicia.setValNeg22(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG22).toString());
            franquicia.setValNeg23(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG23).toString());
            franquicia.setValNeg24(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG24).toString());
            franquicia.setValNeg25(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG25).toString());
            franquicia.setValNeg31(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG31).toString());
            franquicia.setValNeg32(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG32).toString());
            franquicia.setValNeg33(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG33).toString());
            franquicia.setValNeg34(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG34).toString());
            franquicia.setValNeg35(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG35).toString());
            franquicia.setValNeg41(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG41).toString());
            franquicia.setValNeg42(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG42).toString());
            franquicia.setValNeg43(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG43).toString());
            franquicia.setValNeg44(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG44).toString());
            franquicia.setValNeg45(var.get(FranquiciaDataSource.ColumnFranquicias.VALNEG45).toString());
            franquicia.setCampo_prueba(var.get(FranquiciaDataSource.ColumnFranquicias.CAMPO_PRUEBA).toString());
            franquicia.setMaster(DevuelveIntegerCero(var.get(FranquiciaDataSource.ColumnFranquicias.MASTER).toString()));

            listaFranquicias.add(franquicia);
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
