package com.expandenegocio.veonegocio.activities;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.expandenegocio.veonegocio.DAO.FranquiciaDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.Franquicia;
import com.expandenegocio.veonegocio.models.Lista_adaptador;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityBusquedaNegocio extends AppCompatActivity {

    private ArrayList<Franquicia> listaFranquicias = new ArrayList<>();
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_modelo_negocio);
        Franquicia franquicia = createFranquicia();
        procesarInformacion(franquicia);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda_negocio, menu);
        return true;
    }

    private Franquicia createFranquicia() {
        Franquicia franquicia = new Franquicia();
        franquicia.setId(UUID.randomUUID().toString());
        franquicia.setName("");
        return franquicia;
    }


    private void procesarInformacion(Franquicia f) {

        RequestParams params = new RequestParams();

        params.put(FranquiciaDataSource.ColumnFranquicias.ID, f.getId().toString());
        params.put(FranquiciaDataSource.ColumnFranquicias.NAME, f.getName().toString());

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
                            recogeDatos(obj);
                            procesar();
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

    private void procesar() {

        ArrayList<Franquicia> datos2 = new ArrayList<Franquicia>();
        int longitud = listaFranquicias.size();
        Franquicia franquicia = new Franquicia();
        String logo;
        String nombre;
        String descripcion;

        for (int x = 0; x < longitud; x++) {
            franquicia = new Franquicia();
            franquicia = listaFranquicias.get(x);
            logo = franquicia.getLogotipo();
            nombre = franquicia.getName();
            descripcion = franquicia.getDescription();

            Franquicia f = new Franquicia();
            f.setLogotipo(Integer.toString(R.drawable.icono));
            f.setName(nombre);
            f.setDescription(descripcion);
            datos2.add(f);
        }

        lista = (ListView) findViewById(R.id.ListView_listado);
        lista.setAdapter(new Lista_adaptador(this, R.layout.entrada_modelo_negocio, datos2) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((Franquicia) entrada).getName());

                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior);
                    if (texto_inferior_entrada != null)
                        texto_inferior_entrada.setText(((Franquicia) entrada).getDescription());

                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(Integer.parseInt(((Franquicia) entrada).getLogotipo()));
                }
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Franquicia elegido = (Franquicia) pariente.getItemAtPosition(posicion);
                Franquicia ff = (Franquicia) listaFranquicias.get(posicion);

                CharSequence texto = "Seleccionado: " + elegido.getDescription();
                String dat = ff.getId();
                Toast toast = Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG);
                toast.show();
                Bundle bundle = new Bundle();
                bundle.putString("id", ff.getId().toString());
                bundle.putString("name", ff.getName().toString());
                bundle.putString("empresa", ff.getEmpresa().toString());
                bundle.putString("tipoAc", ff.getTipo_actividad().toString());
                bundle.putString("tfno", ff.getPhone_office().toString());
                bundle.putString("web", ff.getWebsite().toString());

                FragmentManager fragmentManager = getFragmentManager();
                DialogoDetalleFranquicia dialogo = new DialogoDetalleFranquicia();
                dialogo.setArguments(bundle);
                dialogo.show(fragmentManager, "listadoDetalleFranquicia");

            }
        });
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


    public void SeleccionarOpciones(MenuItem item) {
    }
}