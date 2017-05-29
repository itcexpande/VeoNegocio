package com.expandenegocio.veonegocio.models;

import java.util.Date;

/**
 * Created by jesus on 28/04/2017.
 */

public enum ClavesFranquicia {
    ID,
    NAME,
    DATE_ENTERED,
    DATE_MODIFIED,
    MODIFIED_USER_ID,
    CREATED_BY,
    DESCRIPTION,
    DELETED,
    ASSIGNED_USER_ID,
    FRANQUICIA_TYPE,
    INDUSTRY,
    ANNUAL_REVENUE,
    PHONE_FAX,
    BILLING_ADDRESS_STREET,
    BILLING_ADDRESS_CITY,
    BILLING_ADDRESS_STATE,
    BILLING_ADDRESS_POSTALCODE,
    BILLING_ADDRESS_COUNTRY,
    RATING,
    PHONE_OFFICE,
    PHONE_ALTERNATE,
    WEBSITE,
    OWNERSHIP,
    EMPLOYEES,
    TICKER_SYMBOL,
    SHIPPING_ADDRESS_STREET,
    SHIPPING_ADDRESS_CITY,
    SHIPPING_ADDRESS_STATE,
    SHIPPING_ADDRESS_POSTALCODE,
    SHIPPING_ADDRESS_COUNTRY,
    TIPO_FICHA,
    ZONA,
    LOGOTIPO,
    VIDEO,
    EXCLUSION_DE_SECTOR,
    EXCLUSION_DE_SUBSECTOR,
    ESTADO_VALIDACION,
    GESTIONADO_POR,
    TIPO_DE_FRANQUICIADO,
    NECESARIO_TITULACION,
    TITULACION,
    CONDICIONES_ESPECIALES,
    FIN_CONDICIONES_ESPECIALES,
    OBSERVACIONES,
    INVERSION_MINIMA_NECESARIA,
    CURRENCY_ID,
    TIPO_FRANQUICIA,
    NECESITA_LOCAL,
    SUPERFICIE_LOCAL,
    REQUISITOS_LOCAL,
    ENTORNO_UBICACION,
    OBSERVACIONES_UBICACION,
    PROVINCIAS_UBICAR_NEGOCIO,
    POBLACION_MINIMA,
    PERSONAL_MINIMO,
    ZONA_EXCLISIVA,
    VIGENCIA_CONTRATO,
    RECONVERTIR_NEGOCIO,
    ACUERDO_FINANCIACION,
    SECTOR,
    BREVE_DESCRIPCION,
    FECHA_CREACION,
    FECHA_EXPANSION,
    CENTROS_NACIONALES_PROPIOS,
    CENTROS_NACIONALES_FRANQUICIA,
    PRESENCIA_INTERNACIONAL,
    PAISES,
    RED_SPAIN,
    CENTROS_EXTRANJEROS_PROPIOS,
    CENTROS_EXTRANJEROS_FRANQUI,
    RED_EXTRANGERA,
    PLANTILLA_CENTRAL,
    CIFRA_NEGOCIO_GRUPO,
    NIFRA,
    REGMARCA,
    AEF,
    SELLOS_CALIDAD,
    OTRO_SELLO_CALIDAD,
    EMPRESA,
    LOCAIDAD,
    DIRECCION_DIRECCION,
    DIRECCION_LOCALIDAD,
    DIRECCION_CODIGO_POSTAL,
    DIRECCION_PROVINCIA,
    DIRECCION_PAIS,
    PERSONA_CONTACTO,
    FECHA_ACUERDO,
    FECHA_ACTIVACION,
    FICHA_AMPLIADA_ANTERIOR,
    EXPAN_CONSULTORA_ID_C,
    ESTADO_FRAN,
    OBSERVACIONES_INTER,
    PRESELECCIONADAS,
    HOMOLOGACION,
    USER_ID_C,
    DOCUMENTACION_PENDIENTE,
    OBJECIONES_FOROS_BBDD,
    DERECHO_ENTRADA_MIN,
    DERECHO_ENTRADA_MAX,
    ROYALTY_EXPLTACION,
    ROYALTY_PUBLICITARIO,
    OTROS_ROYALTIES,
    FACTURACION_YEAR_UNIDAD_FRAN_1,
    FACTURACION_YEAR_UNIDAD_FRAN_2,
    FACTURACION_YEAR_UNIDAD_FRAN_3,
    AMORTIZACIO_INVERSION,
    BENEFICIO_NETO_UNIDAD_FRAN_1,
    BENEFICIO_NETO_UNIDAD_FRAN_2,
    BENEFICIO_NETO_UNIDAD_FRAN_3,
    TIPO_ACTIVIDAD,
    MOVIL_GENERAL,
    CARGO_CONTACTO_GENERAL,
    CONTACTO_ADMINISTRACION,
    TELEFONO_ADMINISTRACION,
    MOVIL_ADMINISTRACION,
    CONTACTO_INTERMEDIACION,
    TELEFONO_INTERMEDIACION,
    MOVIL_INTERMEDIACION,
    CORREO_ADMINISTRACION,
    CORREO_INTERMEDIACION,
    EMAIL1,
    CORREO_GENERAL,
    CONTACTO_GENERAL_2,
    TELEFONO_CONTACTO_2,
    TELEFONO_ALTERNATIVO_2,
    MOVIL_GENERAL_2,
    CORREO_CONTACTO_2,
    CARGO_CONTACTO_2,
    INICIO_EXPANSION,
    OBSERVACIONES_ADMINISTRACION,
    TIPO_CUENTA,
    CORREO_ENVIO,
    CHK_C1,
    CHK_C2,
    CHK_C3,
    CHK_C4,
    CHK_C11,
    CHK_C12,
    CHK_C13,
    CHK_C14,
    CHK_C15,
    DIR_CONS_ID_C,
    LLAMAR_TODOS,
    INFORME_URGENTE,
    MODNEG1,
    MODNEG2,
    MODNEG3,
    MODNEG4,
    PRIORIDAD,
    VALNEG11,
    VALNEG12,
    VALNEG13,
    VALNEG14,
    VALNEG15,
    VALNEG21,
    VALNEG22,
    VALNEG23,
    VALNEG24,
    VALNEG25,
    VALNEG31,
    VALNEG32,
    VALNEG33,
    VALNEG34,
    VALNEG35,
    VALNEG41,
    VALNEG42,
    VALNEG43,
    VALNEG44,
    VALNEG45,
    CAMPO_PRUEBA,
    MASTER;


    /**
     * @returnarcon los nombres de las claves de este enum
     */
    public static String[] claves() {
        //modifica este método
        ClavesFranquicia[] todas = ClavesFranquicia.values();
        int longitud = todas.length;
        String[] nombres = new String[longitud];
        for (int i = 0; i < longitud; i++) {
            nombres[i] = todas[i].toString();
        }
        return nombres;
    }


}