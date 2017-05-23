package com.expandenegocio.veonegocio.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.expandenegocio.veonegocio.DAO.FranquiciaDataSource;
import com.expandenegocio.veonegocio.DAO.MunicipioDataSource;
import com.expandenegocio.veonegocio.DAO.ProvinciaDataSource;
import com.expandenegocio.veonegocio.DAO.UserDataSource;
import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.ClavesFranquicia;
import com.expandenegocio.veonegocio.models.Franquicia;
import com.expandenegocio.veonegocio.models.Municipio;
import com.expandenegocio.veonegocio.models.Provincia;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityBusquedaNegocio extends AppCompatActivity {
    private String nCorreo;
    private String nPassword;
    private ListView lista;
    public static SimpleAdapter adaptador;
    public ArrayList<Franquicia> listaFranquicias = new ArrayList<>();

    public static SimpleAdapter getAdaptador() {
        return adaptador;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_modelo_negocio);
        nCorreo = getIntent().getStringExtra("correo");
        nPassword = getIntent().getStringExtra("password");

        lista = (ListView) findViewById(R.id.lvModeloNegocio);
        this.registerForContextMenu(lista);

        String[] from = ClavesFranquicia.claves();
        int[] to = {R.id.imageview_modelo_negocio, R.id.tv1_modelo_negocio, R.id.tv2_modelo_negocio};

        adaptador = new SimpleAdapter(this, gestora, R.layout.item_modelo_negocio, from, to);
        adaptador.setViewBinder(new DatosViewAdapter());
        lista.setAdapter(adaptador);

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

        return provinciaSeleccionada;

    }

    private Municipio loadSpinnerMunicipios(Integer numeroProvincia) {
        MunicipioDataSource dataSource = new MunicipioDataSource(this);
        final ArrayList<Municipio> listaMunicipios = dataSource.getMunicipios(numeroProvincia);

        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaMunicipios);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnMunicipio = (Spinner) findViewById(R.id.sp_alta_municipio);

        spnMunicipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                   @Override
                                                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                       Municipio municipoSeleccionado = listaMunicipios.get(position);
                                                   }

                                                   @Override
                                                   public void onNothingSelected(AdapterView<?> parent) {
                                                       Municipio municipioSeleccionado = null;
                                                   }
                                               }
        );

        spnMunicipio.setAdapter(spinner_adapter);
        return municipioSeleccionado;
    }


    private Franquicia createFranquicia() {
        Franquicia franquicia = new Franquicia();
        franquicia.setId(UUID.randomUUID().toString());
        franquicia.setName("");
        return franquicia;
    }


    private void procesarInformacion() {

        RequestParams params = new RequestParams();

        params.put(UserDataSource.ColumnUsuarios.ID, usuario.getId().toString());
        params.put(UserDataSource.ColumnUsuarios.EMAIL, correo);

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
                            //Toast.makeText(getApplicationContext(), "Registrado correctamente!", Toast.LENGTH_LONG).show();
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

    private void recogeDatos(JSONObject obj) throws JSONException {
        JSONArray datos = obj.getJSONArray("info");
        int longitud = datos.length();
        for (int x = 0; x < longitud; x++) {
            JSONObject var = datos.getJSONObject(x);
            Franquicia franquicia = new Franquicia();
            franquicia.setId(var.get(FranquiciaDataSource.ColumnFranquicias.ID).toString());


            franquicia.setName(var.get(FranquiciaDataSource.ColumnFranquicias.ID).toString());
            franquicia.setDate_entered((Date) var.get(String.valueOf(Date.parse(FranquiciaDataSource.ColumnFranquicias.DATE_ENTERED.toString()))));
            franquicia.setDate_entered((Date) var.get(String.valueOf(Date.parse(FranquiciaDataSource.ColumnFranquicias.DATE_MODIFIED.toString()))));

            franquicia.setModified_user_id(var.get(FranquiciaDataSource.ColumnFranquicias.MODIFIED_USER_ID).toString());
            franquicia.setCreated_by(var.get(FranquiciaDataSource.ColumnFranquicias.CREATED_BY).toString());
            franquicia.setDescription(var.get(FranquiciaDataSource.ColumnFranquicias.DESCRIPTION).toString());
            franquicia.setDeleted(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.DELETED).toString()));

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
            franquicia.setExclusion_de_sector(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.EXCLUSION_DE_SECTOR).toString()));
            franquicia.setExclusion_de_subsector(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.EXCLUSION_DE_SUBSECTOR).toString()));

            franquicia.setEstado_validacion(var.get(FranquiciaDataSource.ColumnFranquicias.ESTADO_VALIDACION).toString());
            franquicia.setGestionado_por(var.get(FranquiciaDataSource.ColumnFranquicias.GESTIONADO_POR).toString());
            franquicia.setTipo_de_franquiciado(var.get(FranquiciaDataSource.ColumnFranquicias.TIPO_DE_FRANQUICIADO).toString());
            franquicia.setNecesario_titulacion(var.get(FranquiciaDataSource.ColumnFranquicias.NECESARIO_TITULACION).toString());
            franquicia.setTitulacion(var.get(FranquiciaDataSource.ColumnFranquicias.TITULACION).toString());
            franquicia.setCondiciones_especiales(var.get(FranquiciaDataSource.ColumnFranquicias.CONDICIONES_ESPECIALES).toString());

            franquicia.setFin_condiciones_especiales((Date) var.get(String.valueOf(Date.parse(FranquiciaDataSource.ColumnFranquicias.FIN_CONDICIONES_ESPECIALES.toString()))));

            franquicia.setObservaciones(var.get(FranquiciaDataSource.ColumnFranquicias.OBSERVACIONES).toString());
            franquicia.setInversion_minima_necesaria(Double.parseDouble(var.get(FranquiciaDataSource.ColumnFranquicias.INVERSION_MINIMA_NECESARIA).toString()));
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

            franquicia.setFecha_creacion(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.FECHA_CREACION).toString()));
            franquicia.setFecha_expansion((Date) var.get(String.valueOf(Date.parse(FranquiciaDataSource.ColumnFranquicias.FECHA_EXPANSION.toString()))));

            franquicia.setCentros_nacionales_propios(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.CENTROS_NACIONALES_PROPIOS).toString()));
            franquicia.setCentros_nacionales_franquicia(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.CENTROS_NACIONALES_FRANQUICIA).toString()));

            franquicia.setPresencia_internacional(var.get(FranquiciaDataSource.ColumnFranquicias.PRESENCIA_INTERNACIONAL).toString());
            franquicia.setPaises(var.get(FranquiciaDataSource.ColumnFranquicias.PAISES).toString());

            franquicia.setRed_spain(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.RED_SPAIN).toString()));
            franquicia.setCentros_extranjeros_propios(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.CENTROS_EXTRANJEROS_PROPIOS).toString()));

            franquicia.setCentros_extranjeros_franqui(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.CENTROS_EXTRANJEROS_FRANQUI).toString()));
            franquicia.setRed_extrangera(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.RED_EXTRANGERA).toString()));
            franquicia.setPlantilla_central(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.PLANTILLA_CENTRAL).toString()));

            franquicia.setCifra_negocio_grupo(Double.parseDouble(var.get(FranquiciaDataSource.ColumnFranquicias.CIFRA_NEGOCIO_GRUPO).toString()));

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

            franquicia.setFecha_acuerdo((Date) var.get(String.valueOf(Date.parse(FranquiciaDataSource.ColumnFranquicias.FECHA_ACUERDO.toString()))));
            franquicia.setFecha_activacion((Date) var.get(String.valueOf(Date.parse(FranquiciaDataSource.ColumnFranquicias.FECHA_ACTIVACION.toString()))));

            franquicia.setFicha_ampliada_anterior(Integer.parseInt(var.get(FranquiciaDataSource.ColumnFranquicias.FICHA_AMPLIADA_ANTERIOR).toString()));

            franquicia.setExpan_consultora_id_c(var.get(FranquiciaDataSource.ColumnFranquicias.EXPAN_CONSULTORA_ID_C).toString());
            franquicia.setEstado_fran(var.get(FranquiciaDataSource.ColumnFranquicias.ESTADO_FRAN).toString());
            franquicia.setObservaciones_inter(var.get(FranquiciaDataSource.ColumnFranquicias.OBSERVACIONES_INTER).toString());
            franquicia.setPreseleccionadas(var.get(FranquiciaDataSource.ColumnFranquicias.PRESELECCIONADAS).toString());
            franquicia.setHomologacion(var.get(FranquiciaDataSource.ColumnFranquicias.HOMOLOGACION).toString());
            franquicia.setUser_id_c(var.get(FranquiciaDataSource.ColumnFranquicias.USER_ID_C).toString());
            franquicia.setDocumentacion_pendiente(var.get(FranquiciaDataSource.ColumnFranquicias.DOCUMENTACION_PENDIENTE).toString());
            franquicia.setObjeciones_foros_bbdd(var.get(FranquiciaDataSource.ColumnFranquicias.OBJECIONES_FOROS_BBDD).toString());

            franquicia.setDerecho_entrada_min(Double.parseDouble(var.get(FranquiciaDataSource.ColumnFranquicias.DERECHO_ENTRADA_MIN).toString()));
            franquicia.setDerecho_entrada_max(Double.parseDouble(var.get(FranquiciaDataSource.ColumnFranquicias.DERECHO_ENTRADA_MAX).toString()));

            franquicia.setRoyalty_expltacion(var.get(FranquiciaDataSource.ColumnFranquicias.ROYALTY_EXPLTACION).toString());
            franquicia.setRoyalty_publicitario(var.get(FranquiciaDataSource.ColumnFranquicias.ROYALTY_PUBLICITARIO).toString());
            franquicia.setOtros_royalties(var.get(FranquiciaDataSource.ColumnFranquicias.OTROS_ROYALTIES).toString());

            franquicia.setFacturacion_year_unidad_fran_1(Double.parseDouble(var.get(FranquiciaDataSource.ColumnFranquicias.FACTURACION_YEAR_UNIDAD_FRAN_1).toString()));
            franquicia.setFacturacion_year_unidad_fran_2(Double.parseDouble(var.get(FranquiciaDataSource.ColumnFranquicias.FACTURACION_YEAR_UNIDAD_FRAN_2).toString()));
            franquicia.setFacturacion_year_unidad_fran_3(Double.parseDouble(var.get(FranquiciaDataSource.ColumnFranquicias.FACTURACION_YEAR_UNIDAD_FRAN_3).toString()));

            franquicia.setAmortizacio_inversion(var.get(FranquiciaDataSource.ColumnFranquicias.AMORTIZACIO_INVERSION).toString());

            franquicia.setBeneficio_neto_unidad_fran_1(Double.parseDouble(var.get(FranquiciaDataSource.ColumnFranquicias.BENEFICIO_NETO_UNIDAD_FRAN_1).toString()));
            franquicia.setBeneficio_neto_unidad_fran_2(Double.parseDouble(var.get(FranquiciaDataSource.ColumnFranquicias.BENEFICIO_NETO_UNIDAD_FRAN_2).toString()));
            franquicia.setBeneficio_neto_unidad_fran_3(Double.parseDouble(var.get(FranquiciaDataSource.ColumnFranquicias.BENEFICIO_NETO_UNIDAD_FRAN_3).toString()));

            franquicia.setTipo_actividad(var.get(FranquiciaDataSource.ColumnFranquicias.TIPO_ACTIVIDAD).toString());
            franquicia.setMovil_general(var.get(FranquiciaDataSource.ColumnFranquicias.MOVIL_GENERAL).toString());
            franquicia.setCargo_contacto_general(var.get(FranquiciaDataSource.ColumnFranquicias.CARGO_CONTACTO_GENERAL).toString());
            franquicia.setContacto_administracion(var.get(FranquiciaDataSource.ColumnFranquicias.CONTACTO_ADMINISTRACION).toString());
            franquicia.setTelefono_administracion(var.get(FranquiciaDataSource.ColumnFranquicias.TELEFONO_ADMINISTRACION).toString());
            franquicia.setMovil_administracion(var.get(FranquiciaDataSource.ColumnFranquicias.MOVIL_ADMINISTRACION).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.CONTACTO_INTERMEDIACION).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.TELEFONO_INTERMEDIACION).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.MOVIL_INTERMEDIACION).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.CORREO_ADMINISTRACION).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.CORREO_INTERMEDIACION).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.EMAIL1).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.CORREO_GENERAL).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.CONTACTO_GENERAL_2).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.TELEFONO_CONTACTO_2).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.TELEFONO_ALTERNATIVO_2).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.MOVIL_GENERAL_2).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.CORREO_CONTACTO_2).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.CARGO_CONTACTO_2).toString());


            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());
            franquicia.set(var.get(FranquiciaDataSource.ColumnFranquicias.).toString());






        }
    }

}