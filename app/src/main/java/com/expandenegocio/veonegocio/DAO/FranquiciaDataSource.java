package com.expandenegocio.veonegocio.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.expandenegocio.veonegocio.models.User;

/**
 * Created by jesus on 05/04/2017.
 */

public class FranquiciaDataSource {

    public static final String FRANQUICIA_TABLE_NAME = "franquicias";


    //Campos de la tabla usuarios
    public static class ColumnFranquicias {

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DATE_ENTERED = "date_entered";
        public static final String DATE_MODIFIED = "date_modified";
        public static final String MODIFIED_USER_ID = "modified_user_id";
        public static final String CREATED_BY = "created_by";
        public static final String DESCRIPTION = "description";
        public static final String DELETED = "deleted";
        public static final String ASSIGNED_USER_ID = "assigned_user_id";
        public static final String FRANQUICIA_TYPE = "franquicia_type";
        public static final String INDUSTRY = "industry";
        public static final String ANNUAL_REVENUE = "annual_revenue";
        public static final String PHONE_FAX = "phone_fax";
        public static final String BILLING_ADDRESS_STREET = "billing_address_street";
        public static final String BILLING_ADDRESS_CITY = "billing_address_city";
        public static final String BILLING_ADDRESS_STATE = "billing_address_state";
        public static final String BILLING_ADDRESS_POSTALCODE = "billing_address_postalcode";
        public static final String BILLING_ADDRESS_COUNTRY = "billing_address_country";
        public static final String RATING = "rating";
        public static final String PHONE_OFFICE = "phone_office";
        public static final String PHONE_ALTERNATE = "phone_alternate";
        public static final String WEBSITE = "website";
        public static final String OWNERSHIP = "ownership";
        public static final String EMPLOYEES = "employees";
        public static final String TICKER_SYMBOL = "ticker_symbol";
        public static final String SHIPPING_ADDRESS_STREET = "shipping_address_street";
        public static final String SHIPPING_ADDRESS_CITY = "shipping_address_city";
        public static final String SHIPPING_ADDRESS_STATE = "shipping_address_state";
        public static final String SHIPPING_ADDRESS_POSTALCODE = "shipping_address_postalcode";
        public static final String SHIPPING_ADDRESS_COUNTRY = "shipping_address_country";
        public static final String TIPO_FICHA = "tipo_ficha";
        public static final String ZONA = "zona";
        public static final String LOGOTIPO = "logotipo";
        public static final String VIDEO = "video";
        public static final String EXCLUSION_DE_SECTOR = "exclusion_de_sector";
        public static final String EXCLUSION_DE_SUBSECTOR = "exclusion_de_subsector";
        public static final String ESTADO_VALIDACION = "estado_validacion";
        public static final String GESTIONADO_POR = "gestionado_por";
        public static final String TIPO_DE_FRANQUICIADO = "tipo_de_franquiciado";
        public static final String NECESARIO_TITULACION = "necesario_titulacion";
        public static final String TITULACION = "titulacion";
        public static final String CONDICIONES_ESPECIALES = "condiciones_especiales";
        public static final String FIN_CONDICIONES_ESPECIALES = "fin_condiciones_especiales";
        public static final String OBSERVACIONES = "observaciones";
        public static final String INVERSION_MINIMA_NECESARIA = "inversion_minima_necesaria";
        public static final String CURRENCY_ID = "currency_id";
        public static final String TIPO_FRANQUICIA = "tipo_franquicia";
        public static final String NECESITA_LOCAL = "necesita_local";
        public static final String SUPERFICIE_LOCAL = "superficie_local";
        public static final String REQUISITOS_LOCAL = "requisitos_local";
        public static final String ENTORNO_UBICACION = "entorno_ubicacion";
        public static final String OBSERVACIONES_UBICACION = "observaciones_ubicacion";
        public static final String PROVINCIAS_UBICAR_NEGOCIO = "provincias_ubicar_negocio";
        public static final String POBLACION_MINIMA = "poblacion_minima";
        public static final String PERSONAL_MINIMO = "personal_minimo";
        public static final String ZONA_EXCLISIVA = "zona_exclisiva";
        public static final String VIGENCIA_CONTRATO = "vigencia_contrato";
        public static final String RECONVERTIR_NEGOCIO = "reconvertir_negocio";
        public static final String ACUERDO_FINANCIACION = "acuerdo_financiacion";
        public static final String SECTOR = "sector";
        public static final String BREVE_DESCRIPCION = "breve_descripcion";
        public static final String FECHA_CREACION = "fecha_creacion";
        public static final String FECHA_EXPANSION = "fecha_expansion";
        public static final String CENTROS_NACIONALES_PROPIOS = "centros_nacionales_propios";
        public static final String CENTROS_NACIONALES_FRANQUICIA = "centros_nacionales_franquicia";
        public static final String PRESENCIA_INTERNACIONAL = "presencia_internacional";
        public static final String PAISES = "paises";
        public static final String RED_SPAIN = "red_spain";
        public static final String CENTROS_EXTRANJEROS_PROPIOS = "centros_extranjeros_propios";
        public static final String CENTROS_EXTRANJEROS_FRANQUI = "centros_extranjeros_franqui";
        public static final String RED_EXTRANGERA = "red_extrangera";
        public static final String PLANTILLA_CENTRAL = "plantilla_central";
        public static final String CIFRA_NEGOCIO_GRUPO = "cifra_negocio_grupo";
        public static final String NIFRA = "nifra";
        public static final String REGMARCA = "regmarca";
        public static final String AEF = "aef";
        public static final String SELLOS_CALIDAD = "sellos_calidad";
        public static final String OTRO_SELLO_CALIDAD = "otro_sello_calidad";
        public static final String EMPRESA = "empresa";
        public static final String LOCAIDAD = "locaidad";
        public static final String DIRECCION_DIRECCION = "direccion_direccion";
        public static final String DIRECCION_LOCALIDAD = "direccion_localidad";
        public static final String DIRECCION_CODIGO_POSTAL = "direccion_codigo_postal";
        public static final String DIRECCION_PROVINCIA = "direccion_provincia";
        public static final String DIRECCION_PAIS = "direccion_pais";
        public static final String PERSONA_CONTACTO = "persona_contacto";
        public static final String FECHA_ACUERDO = "fecha_acuerdo";
        public static final String FECHA_ACTIVACION = "fecha_activacion";
        public static final String FICHA_AMPLIADA_ANTERIOR = "ficha_ampliada_anterior";
        public static final String EXPAN_CONSULTORA_ID_C = "expan_consultora_id_c";
        public static final String ESTADO_FRAN = "estado_fran";
        public static final String OBSERVACIONES_INTER = "observaciones_inter";
        public static final String PRESELECCIONADAS = "preseleccionadas";
        public static final String HOMOLOGACION = "homologacion";
        public static final String USER_ID_C = "user_id_c";
        public static final String DOCUMENTACION_PENDIENTE = "documentacion_pendiente";
        public static final String OBJECIONES_FOROS_BBDD = "objeciones_foros_bbdd";
        public static final String DERECHO_ENTRADA_MIN = "derecho_entrada_min";
        public static final String DERECHO_ENTRADA_MAX = "derecho_entrada_max";
        public static final String ROYALTY_EXPLTACION = "royalty_expltacion";
        public static final String ROYALTY_PUBLICITARIO = "royalty_publicitario";
        public static final String OTROS_ROYALTIES = "otros_royalties";
        public static final String FACTURACION_YEAR_UNIDAD_FRAN_1 = "facturacion_year_unidad_fran_1";
        public static final String FACTURACION_YEAR_UNIDAD_FRAN_2 = "facturacion_year_unidad_fran_2";
        public static final String FACTURACION_YEAR_UNIDAD_FRAN_3 = "facturacion_year_unidad_fran_3";
        public static final String AMORTIZACIO_INVERSION = "amortizacio_inversion";
        public static final String BENEFICIO_NETO_UNIDAD_FRAN_1 = "beneficio_neto_unidad_fran_1";
        public static final String BENEFICIO_NETO_UNIDAD_FRAN_2 = "beneficio_neto_unidad_fran_2";
        public static final String BENEFICIO_NETO_UNIDAD_FRAN_3 = "beneficio_neto_unidad_fran_3";
        public static final String TIPO_ACTIVIDAD = "tipo_actividad";
        public static final String MOVIL_GENERAL = "movil_general";
        public static final String CARGO_CONTACTO_GENERAL = "cargo_contacto_general";
        public static final String CONTACTO_ADMINISTRACION = "contacto_administracion";
        public static final String TELEFONO_ADMINISTRACION = "telefono_administracion";
        public static final String MOVIL_ADMINISTRACION = "movil_administracion";
        public static final String CONTACTO_INTERMEDIACION = "contacto_intermediacion";
        public static final String TELEFONO_INTERMEDIACION = "telefono_intermediacion";
        public static final String MOVIL_INTERMEDIACION = "movil_intermediacion";
        public static final String CORREO_ADMINISTRACION = "correo_administracion";
        public static final String CORREO_INTERMEDIACION = "correo_intermediacion";
        public static final String EMAIL1 = "email1";
        public static final String CORREO_GENERAL = "correo_general";
        public static final String CONTACTO_GENERAL_2 = "contacto_general_2";
        public static final String TELEFONO_CONTACTO_2 = "telefono_contacto_2";
        public static final String TELEFONO_ALTERNATIVO_2 = "telefono_alternativo_2";
        public static final String MOVIL_GENERAL_2 = "movil_general_2";
        public static final String CORREO_CONTACTO_2 = "correo_contacto_2";
        public static final String CARGO_CONTACTO_2 = "cargo_contacto_2";
        public static final String INICIO_EXPANSION = "inicio_expansion";
        public static final String OBSERVACIONES_ADMINISTRACION = "observaciones_administracion";
        public static final String TIPO_CUENTA = "tipo_cuenta";
        public static final String CORREO_ENVIO = "correo_envio";
        public static final String CHK_C1 = "chk_c1";
        public static final String CHK_C2 = "chk_c2";
        public static final String CHK_C3 = "chk_c3";
        public static final String CHK_C4 = "chk_c4";
        public static final String CHK_C11 = "chk_c11";
        public static final String CHK_C12 = "chk_c12";
        public static final String CHK_C13 = "chk_c13";
        public static final String CHK_C14 = "chk_c14";
        public static final String CHK_C15 = "chk_c15";
        public static final String DIR_CONS_ID_C = "dir_cons_id_c";
        public static final String LLAMAR_TODOS = "llamar_todos";
        public static final String INFORME_URGENTE = "informe_urgente";
        public static final String MODNEG1 = "modNeg1";
        public static final String MODNEG2 = "modNeg2";
        public static final String MODNEG3 = "modNeg3";
        public static final String MODNEG4 = "modNeg4";
        public static final String PRIORIDAD = "prioridad";
        public static final String VALNEG11 = "valNeg11";
        public static final String VALNEG12 = "valNeg12";
        public static final String VALNEG13 = "valNeg13";
        public static final String VALNEG14 = "valNeg14";
        public static final String VALNEG15 = "valNeg15";
        public static final String VALNEG21 = "valNeg21";
        public static final String VALNEG22 = "valNeg22";
        public static final String VALNEG23 = "valNeg23";
        public static final String VALNEG24 = "valNeg24";
        public static final String VALNEG25 = "valNeg25";
        public static final String VALNEG31 = "valNeg31";
        public static final String VALNEG32 = "valNeg32";
        public static final String VALNEG33 = "valNeg33";
        public static final String VALNEG34 = "valNeg34";
        public static final String VALNEG35 = "valNeg35";
        public static final String VALNEG41 = "valNeg41";
        public static final String VALNEG42 = "valNeg42";
        public static final String VALNEG43 = "valNeg43";
        public static final String VALNEG44 = "valNeg44";
        public static final String VALNEG45 = "valNeg45";
        public static final String CAMPO_PRUEBA = "Campo_prueba";
        public static final String MASTER = "master";

    }


    private DbHelper openHelper;
    private SQLiteDatabase database;

    public FranquiciaDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        openHelper = new DbHelper(context);
        database = openHelper.getWritableDatabase();
    }


}