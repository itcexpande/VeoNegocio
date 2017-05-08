package com.expandenegocio.veonegocio.models;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by jesus on 28/04/2017.
 */

public class Franquicia extends HashMap<String, Object> implements Comparable<Franquicia> {


    private String id;
    private String name;
    private String description;
    private String industry;
    private String phone_office;
    private String phone_alternate;
    private String website;
    private String ownership;
    private String employees;
    private String tipo_ficha;
    private String zona;
    private String logotipo;
    private String video;
    private Integer exclusion_de_sector; //tinyint(1)
    private Integer exclusion_de_subsector; //tinyint(1)
    private String estado_validacion; //varchar(100)
    private String tipo_de_franquiciado; //varchar(100)
    private String necesario_titulacion; //varchar(100)
    private String titulacion; //varchar(255)
    private String condiciones_especiales; //text
    private Date fin_condiciones_especiales; //date
    private String observaciones; //text
    private Double inversion_minima_necesaria; //decimal(26,6)
    private String currency_id; //char(36)
    private String tipo_franquicia; //text
    private String necesita_local; //varchar(100)
    private String superficie_local; //varchar(100)
    private String requisitos_local; //text
    private String entorno_ubicacion; //varchar(100)
    private String observaciones_ubicacion; //text
    private String poblacion_minima; //varchar(100)
    private String personal_minimo; //varchar(100)
    private String zona_exclisiva; //varchar(100)
    private String vigencia_contrato; //varchar(100)
    private String reconvertir_negocio; //varchar(100)
    private String acuerdo_financiacion; //varchar(100)
    private Integer sector; //tinytext
    private String breve_descripcion; //varchar(140)
    private Integer fecha_creacion; //int(255)
    private Date fecha_expansion; //date
    private Integer centros_nacionales_propios; //int(10)
    private Integer centros_nacionales_franquicia; //int(10)
    private String presencia_internacional; //varchar(100)
    private Integer red_spain; //int(10)
    private Integer centros_extranjeros_propios; //int(255)
    private Integer centros_extranjeros_franqui; //int(10)
    private Integer red_extrangera; //int(10)
    private Integer plantilla_central; //int(10)
    private Double cifra_negocio_grupo; //decimal(26,6)
    private String nifra; //varchar(255)
    private String regmarca; //varchar(255)
    private String aef; //varchar(100)
    private String sellos_calidad; //text
    private String otro_sello_calidad; //varchar(255)
    private String empresa; //varchar(255)
    private String localidad; //varchar(255)
    private String direccion_direccion; //varchar(255)
    private String direccion_localidad; //varchar(255)
    private String direccion_codigo_postal; //varchar(10)
    private String direccion_provincia; //varchar(100)
    private String direccion_pais; //varchar(100)
    private String persona_contacto; //varchar(255)
    private Date fecha_acuerdo; //date
    private Date fecha_activacion; //date
    private Integer ficha_ampliada_anterior; //tinyint(1)
    private String expan_consultora_id_c; //char(36)
    private String estado_fran; //varchar(100)
    private String observaciones_inter; //text
    private String preseleccionadas; //varchar(100)
    private String homologacion; //varchar(100)
    private String documentacion_pendiente; //text
    private Double derecho_entrada_min; //decimal(26,6)
    private Double derecho_entrada_max; //decimal(26,6)
    private String royalty_expltacion; //varchar(255)
    private String royalty_publicitario; //varchar(255)
    private String otros_royalties; //varchar(255)
    private Double facturacion_year_unidad_fran_1; //decimal(26,6)
    private Double facturacion_year_unidad_fran_2; //decimal(26,6)
    private Double facturacion_year_unidad_fran_3; //decimal(26,6)
    private String amortizacio_inversion; //varchar(100)
    private Double beneficio_neto_unidad_fran_1; //decimal(26,6)
    private Double beneficio_neto_unidad_fran_2; //decimal(26,6)
    private Double beneficio_neto_unidad_fran_3; //decimal(26,6)
    private String tipo_actividad; //varchar(255)


    public Franquicia() {
    }

   /* public Franquicia(String nombre) {
        this.put(ClavesFranquicia.NOMBRE.toString(), nombre);
    }


    public void setNombre(String nombre) {
        this.put(ClavesFranquicia.NOMBRE.toString(), nombre);
    }

    */


    public Franquicia(int initialCapacity, float loadFactor, String id, String name, String description, String industry, String phone_office, String phone_alternate, String website, String ownership, String employees, String tipo_ficha, String zona, String logotipo, String video, Integer exclusion_de_sector, Integer exclusion_de_subsector, String estado_validacion, String tipo_de_franquiciado, String necesario_titulacion, String titulacion, String condiciones_especiales, Date fin_condiciones_especiales, String observaciones, Double inversion_minima_necesaria, String currency_id, String tipo_franquicia, String necesita_local, String superficie_local, String requisitos_local, String entorno_ubicacion, String observaciones_ubicacion, String poblacion_minima, String personal_minimo, String zona_exclisiva, String vigencia_contrato, String reconvertir_negocio, String acuerdo_financiacion, Integer sector, String breve_descripcion, Integer fecha_creacion, Date fecha_expansion, Integer centros_nacionales_propios, Integer centros_nacionales_franquicia, String presencia_internacional, Integer red_spain, Integer centros_extranjeros_propios, Integer centros_extranjeros_franqui, Integer red_extrangera, Integer plantilla_central, Double cifra_negocio_grupo, String nifra, String regmarca, String aef, String sellos_calidad, String otro_sello_calidad, String empresa, String localidad, String direccion_direccion, String direccion_localidad, String direccion_codigo_postal, String direccion_provincia, String direccion_pais, String persona_contacto, Date fecha_acuerdo, Date fecha_activacion, Integer ficha_ampliada_anterior, String expan_consultora_id_c, String estado_fran, String observaciones_inter, String preseleccionadas, String homologacion, String documentacion_pendiente, Double derecho_entrada_min, Double derecho_entrada_max, String royalty_expltacion, String royalty_publicitario, String otros_royalties, Double facturacion_year_unidad_fran_1, Double facturacion_year_unidad_fran_2, Double facturacion_year_unidad_fran_3, String amortizacio_inversion, Double beneficio_neto_unidad_fran_1, Double beneficio_neto_unidad_fran_2, Double beneficio_neto_unidad_fran_3, String tipo_actividad) {

        this.put(ClavesFranquicia.ID.toString(), id);
        this.put(ClavesFranquicia.NAME.toString(), name);
        this.put(ClavesFranquicia.DESCRIPTION.toString(), description);
        this.put(ClavesFranquicia.INDUSTRY.toString(), industry);
        this.put(ClavesFranquicia.PHONE_OFFICE.toString(), phone_office);
        this.put(ClavesFranquicia.PHONE_ALTERNATE.toString(), phone_alternate);
        this.put(ClavesFranquicia.WEBSITE.toString(), website);
        this.put(ClavesFranquicia.OWNERSHIP.toString(), ownership);
        this.put(ClavesFranquicia.EMPLOYEES.toString(), employees);
        this.put(ClavesFranquicia.TIPO_FICHA.toString(), tipo_ficha);
        this.put(ClavesFranquicia.ZONA.toString(), zona);
        this.put(ClavesFranquicia.LOGOTIPO.toString(), logotipo);
        this.put(ClavesFranquicia.VIDEO.toString(), video);
        this.put(ClavesFranquicia.EXCLUSION_DE_SECTOR.toString(), exclusion_de_sector);
        this.put(ClavesFranquicia.EXCLUSION_DE_SUBSECTOR.toString(), exclusion_de_subsector);
        this.put(ClavesFranquicia.ESTADO_VALIDACION.toString(), estado_validacion);
        this.put(ClavesFranquicia.TIPO_DE_FRANQUICIADO.toString(), tipo_de_franquiciado);
        this.put(ClavesFranquicia.NECESARIO_TITULACION.toString(), necesario_titulacion);
        this.put(ClavesFranquicia.TITULACION.toString(), titulacion);
        this.put(ClavesFranquicia.CONDICIONES_ESPECIALES.toString(), condiciones_especiales);
        this.put(ClavesFranquicia.FIN_CONDICIONES_ESPECIALES.toString(), fin_condiciones_especiales);
        this.put(ClavesFranquicia.OBSERVACIONES.toString(), observaciones);
        this.put(ClavesFranquicia.INVERSION_MINIMA_NECESARIA.toString(), inversion_minima_necesaria);
        this.put(ClavesFranquicia.CURRENCY_ID.toString(), currency_id);
        this.put(ClavesFranquicia.TIPO_FRANQUICIA.toString(), tipo_franquicia);
        this.put(ClavesFranquicia.NECESITA_LOCAL.toString(), necesita_local);
        this.put(ClavesFranquicia.SUPERFICIE_LOCAL.toString(), superficie_local);
        this.put(ClavesFranquicia.REQUISITOS_LOCAL.toString(), requisitos_local);
        this.put(ClavesFranquicia.ENTORNO_UBICACION.toString(), entorno_ubicacion);
        this.put(ClavesFranquicia.OBSERVACIONES_UBICACION.toString(), observaciones_ubicacion);
        this.put(ClavesFranquicia.POBLACION_MINIMA.toString(), poblacion_minima);
        this.put(ClavesFranquicia.PERSONAL_MINIMO.toString(), personal_minimo);
        this.put(ClavesFranquicia.ZONA_EXCLISIVA.toString(), zona_exclisiva);
        this.put(ClavesFranquicia.VIGENCIA_CONTRATO.toString(), vigencia_contrato);
        this.put(ClavesFranquicia.RECONVERTIR_NEGOCIO.toString(), reconvertir_negocio);
        this.put(ClavesFranquicia.ACUERDO_FINANCIACION.toString(), acuerdo_financiacion);
        this.put(ClavesFranquicia.SECTOR.toString(), sector);
        this.put(ClavesFranquicia.BREVE_DESCRIPCION.toString(), breve_descripcion);
        this.put(ClavesFranquicia.FECHA_CREACION.toString(), fecha_creacion);
        this.put(ClavesFranquicia.FECHA_EXPANSION.toString(), fecha_expansion);
        this.put(ClavesFranquicia.CENTROS_NACIONALES_PROPIOS.toString(), centros_nacionales_propios);
        this.put(ClavesFranquicia.CENTROS_NACIONALES_FRANQUICIA.toString(), centros_nacionales_franquicia);
        this.put(ClavesFranquicia.PRESENCIA_INTERNACIONAL.toString(), presencia_internacional);
        this.put(ClavesFranquicia.RED_SPAIN.toString(), red_spain);
        this.put(ClavesFranquicia.CENTROS_EXTRANJEROS_PROPIOS.toString(), centros_extranjeros_propios);
        this.put(ClavesFranquicia.CENTROS_EXTRANJEROS_FRANQUI.toString(), centros_extranjeros_franqui);
        this.put(ClavesFranquicia.RED_EXTRANGERA.toString(), red_extrangera);
        this.put(ClavesFranquicia.PLANTILLA_CENTRAL.toString(), plantilla_central);
        this.put(ClavesFranquicia.CIFRA_NEGOCIO_GRUPO.toString(), cifra_negocio_grupo);
        this.put(ClavesFranquicia.NIFRA.toString(), nifra);
        this.put(ClavesFranquicia.REGMARCA.toString(), regmarca);
        this.put(ClavesFranquicia.AEF.toString(), aef);
        this.put(ClavesFranquicia.SELLOS_CALIDAD.toString(), sellos_calidad);
        this.put(ClavesFranquicia.OTRO_SELLO_CALIDAD.toString(), otro_sello_calidad);
        this.put(ClavesFranquicia.EMPRESA.toString(), empresa);
        this.put(ClavesFranquicia.LOCALIDAD.toString(), localidad);
        this.put(ClavesFranquicia.DIRECCION_DIRECCION.toString(), direccion_direccion);
        this.put(ClavesFranquicia.DIRECCION_LOCALIDAD.toString(), direccion_localidad);
        this.put(ClavesFranquicia.DIRECCION_CODIGO_POSTAL.toString(), direccion_codigo_postal);
        this.put(ClavesFranquicia.DIRECCION_PROVINCIA.toString(), direccion_provincia);
        this.put(ClavesFranquicia.DIRECCION_PAIS.toString(), direccion_pais);
        this.put(ClavesFranquicia.PERSONA_CONTACTO.toString(), persona_contacto);
        this.put(ClavesFranquicia.FECHA_ACUERDO.toString(), fecha_acuerdo);
        this.put(ClavesFranquicia.FECHA_ACTIVACION.toString(), fecha_activacion);
        this.put(ClavesFranquicia.FICHA_AMPLIADA_ANTERIOR.toString(), ficha_ampliada_anterior);
        this.put(ClavesFranquicia.EXPAN_CONSULTORA_ID_C.toString(), expan_consultora_id_c);
        this.put(ClavesFranquicia.ESTADO_FRAN.toString(), estado_fran);
        this.put(ClavesFranquicia.OBSERVACIONES_INTER.toString(), observaciones_inter);
        this.put(ClavesFranquicia.PRESELECCIONADAS.toString(), preseleccionadas);
        this.put(ClavesFranquicia.HOMOLOGACION.toString(), homologacion);
        this.put(ClavesFranquicia.DOCUMENTACION_PENDIENTE.toString(), documentacion_pendiente);
        this.put(ClavesFranquicia.DERECHO_ENTRADA_MIN.toString(), derecho_entrada_min);
        this.put(ClavesFranquicia.DERECHO_ENTRADA_MAX.toString(), derecho_entrada_max);
        this.put(ClavesFranquicia.ROYALTY_EXPLTACION.toString(), royalty_expltacion);
        this.put(ClavesFranquicia.ROYALTY_PUBLICITARIO.toString(), royalty_publicitario);
        this.put(ClavesFranquicia.OTROS_ROYALTIES.toString(), otros_royalties);
        this.put(ClavesFranquicia.FACTURACION_YEAR_UNIDAD_FRAN_1.toString(), facturacion_year_unidad_fran_1);
        this.put(ClavesFranquicia.FACTURACION_YEAR_UNIDAD_FRAN_2.toString(), facturacion_year_unidad_fran_2);
        this.put(ClavesFranquicia.FACTURACION_YEAR_UNIDAD_FRAN_3.toString(), facturacion_year_unidad_fran_3);
        this.put(ClavesFranquicia.AMORTIZACIO_INVERSION.toString(), amortizacio_inversion);
        this.put(ClavesFranquicia.BENEFICIO_NETO_UNIDAD_FRAN_1.toString(), beneficio_neto_unidad_fran_1);
        this.put(ClavesFranquicia.BENEFICIO_NETO_UNIDAD_FRAN_2.toString(), beneficio_neto_unidad_fran_2);
        this.put(ClavesFranquicia.BENEFICIO_NETO_UNIDAD_FRAN_3.toString(), beneficio_neto_unidad_fran_3);
        this.put(ClavesFranquicia.TIPO_ACTIVIDAD.toString(), tipo_actividad);
    }


    public String getId() {
        return (String) get(ClavesFranquicia.ID.toString());
    }


    public String getName() {
        return (String) get(ClavesFranquicia.NAME.toString());
    }

    public String getDescription() {
        return (String) get(ClavesFranquicia.DESCRIPTION.toString());
    }

    public String getIndustry() {
        return (String) get(ClavesFranquicia.INDUSTRY.toString());
    }

    public String getPhone_office() {
        return (String) get(ClavesFranquicia.PHONE_OFFICE.toString());
    }

    public String getPhone_alternate() {
        return (String) get(ClavesFranquicia.PHONE_ALTERNATE.toString());
    }

    public String getWebsite() {
        return (String) get(ClavesFranquicia.WEBSITE.toString());
    }

    public String getOwnership() {
        return (String) get(ClavesFranquicia.OWNERSHIP.toString());
    }

    public String getEmployees() {
        return (String) get(ClavesFranquicia.EMPLOYEES.toString());
    }

    public String getTipo_ficha() {
        return (String) get(ClavesFranquicia.TIPO_FICHA.toString());
    }

    public String getZona() {
        return (String) get(ClavesFranquicia.ZONA.toString());
    }

    public String getLogotipo() {
        return (String) get(ClavesFranquicia.LOGOTIPO.toString());
    }

    public String getVideo() {
        return (String) get(ClavesFranquicia.VIDEO.toString());
    }

    public Integer getExclusion_de_sector() {
        return (Integer) get(ClavesFranquicia.EXCLUSION_DE_SECTOR.toString());
    }

    public Integer getExclusion_de_subsector() {
        return (Integer) get(ClavesFranquicia.EXCLUSION_DE_SUBSECTOR.toString());
    }

    public String getEstado_validacion() {
        return (String) get(ClavesFranquicia.ESTADO_VALIDACION.toString());
    }

    public String getTipo_de_franquiciado() {
        return (String) get(ClavesFranquicia.TIPO_DE_FRANQUICIADO.toString());
    }

    public String getNecesario_titulacion() {
        return (String) get(ClavesFranquicia.NECESARIO_TITULACION.toString());
    }

    public String getTitulacion() {
        return (String) get(ClavesFranquicia.TITULACION.toString());
    }

    public String getCondiciones_especiales() {
        return (String) get(ClavesFranquicia.CONDICIONES_ESPECIALES.toString());
    }

    public Date getFin_condiciones_especiales() {
        return (Date) get(ClavesFranquicia.FIN_CONDICIONES_ESPECIALES.toString());
    }

    public String getObservaciones() {
        return (String) get(ClavesFranquicia.OBSERVACIONES.toString());
    }

    public Double getInversion_minima_necesaria() {
        return (Double) get(ClavesFranquicia.INVERSION_MINIMA_NECESARIA.toString());
    }

    public String getCurrency_id() {
        return (String) get(ClavesFranquicia.CURRENCY_ID.toString());
    }

    public String getTipo_franquicia() {
        return (String) get(ClavesFranquicia.TIPO_FRANQUICIA.toString());
    }

    public String getNecesita_local() {
        return (String) get(ClavesFranquicia.NECESITA_LOCAL.toString());
    }

    public String getSuperficie_local() {
        return (String) get(ClavesFranquicia.SUPERFICIE_LOCAL.toString());
    }

    public String getRequisitos_local() {
        return (String) get(ClavesFranquicia.REQUISITOS_LOCAL.toString());
    }

    public String getEntorno_ubicacion() {
        return (String) get(ClavesFranquicia.ENTORNO_UBICACION.toString());
    }

    public String getObservaciones_ubicacion() {
        return (String) get(ClavesFranquicia.OBSERVACIONES_UBICACION.toString());
    }

    public String getPoblacion_minima() {
        return (String) get(ClavesFranquicia.POBLACION_MINIMA.toString());
    }

    public String getPersonal_minimo() {
        return (String) get(ClavesFranquicia.PERSONAL_MINIMO.toString());
    }

    public String getZona_exclisiva() {
        return (String) get(ClavesFranquicia.ZONA_EXCLISIVA.toString());
    }

    public String getVigencia_contrato() {
        return (String) get(ClavesFranquicia.VIGENCIA_CONTRATO.toString());
    }

    public String getReconvertir_negocio() {
        return (String) get(ClavesFranquicia.RECONVERTIR_NEGOCIO.toString());
    }

    public String getAcuerdo_financiacion() {
        return (String) get(ClavesFranquicia.ACUERDO_FINANCIACION.toString());
    }

    public Integer getSector() {
        return (Integer) get(ClavesFranquicia.SECTOR.toString());
    }

    public String getBreve_descripcion() {
        return (String) get(ClavesFranquicia.BREVE_DESCRIPCION.toString());
    }

    public Integer getFecha_creacion() {
        return (Integer) get(ClavesFranquicia.FECHA_CREACION.toString());
    }

    public Date getFecha_expansion() {
        return (Date) get(ClavesFranquicia.FECHA_EXPANSION.toString());
    }

    public Integer getCentros_nacionales_propios() {
        return (Integer) get(ClavesFranquicia.CENTROS_NACIONALES_PROPIOS.toString());
    }

    public Integer getCentros_nacionales_franquicia() {
        return (Integer) get(ClavesFranquicia.CENTROS_NACIONALES_FRANQUICIA.toString());
    }

    public String getPresencia_internacional() {
        return (String) get(ClavesFranquicia.PRESENCIA_INTERNACIONAL.toString());
    }

    public Integer getRed_spain() {
        return (Integer) get(ClavesFranquicia.RED_SPAIN.toString());
    }

    public Integer getCentros_extranjeros_propios() {
        return (Integer) get(ClavesFranquicia.CENTROS_EXTRANJEROS_PROPIOS.toString());
    }

    public Integer getCentros_extranjeros_franqui() {
        return (Integer) get(ClavesFranquicia.CENTROS_EXTRANJEROS_FRANQUI.toString());
    }

    public Integer getRed_extrangera() {
        return (Integer) get(ClavesFranquicia.RED_EXTRANGERA.toString());
    }

    public Integer getPlantilla_central() {
        return (Integer) get(ClavesFranquicia.PLANTILLA_CENTRAL.toString());
    }

    public Double getCifra_negocio_grupo() {
        return (Double) get(ClavesFranquicia.CIFRA_NEGOCIO_GRUPO.toString());
    }

    public String getNifra() {
        return (String) get(ClavesFranquicia.NIFRA.toString());
    }

    public String getRegmarca() {
        return (String) get(ClavesFranquicia.REGMARCA.toString());
    }

    public String getAef() {
        return (String) get(ClavesFranquicia.AEF.toString());
    }

    public String getSellos_calidad() {
        return (String) get(ClavesFranquicia.SELLOS_CALIDAD.toString());
    }

    public String getOtro_sello_calidad() {
        return (String) get(ClavesFranquicia.OTRO_SELLO_CALIDAD.toString());
    }

    public String getEmpresa() {
        return (String) get(ClavesFranquicia.EMPRESA.toString());
    }

    public String getLocalidad() {
        return (String) get(ClavesFranquicia.LOCALIDAD.toString());
    }

    public String getDireccion_direccion() {
        return (String) get(ClavesFranquicia.DIRECCION_DIRECCION.toString());
    }

    public String getDireccion_localidad() {
        return (String) get(ClavesFranquicia.DIRECCION_LOCALIDAD.toString());
    }

    public String getDireccion_codigo_postal() {
        return (String) get(ClavesFranquicia.DIRECCION_CODIGO_POSTAL.toString());
    }

    public String getDireccion_provincia() {
        return (String) get(ClavesFranquicia.DIRECCION_PROVINCIA.toString());
    }

    public String getDireccion_pais() {
        return (String) get(ClavesFranquicia.DIRECCION_PAIS.toString());
    }

    public String getPersona_contacto() {
        return (String) get(ClavesFranquicia.PERSONA_CONTACTO.toString());
    }

    public Date getFecha_acuerdo() {
        return (Date) get(ClavesFranquicia.FECHA_ACUERDO.toString());
    }

    public Date getFecha_activacion() {
        return (Date) get(ClavesFranquicia.FECHA_ACTIVACION.toString());
    }

    public Integer getFicha_ampliada_anterior() {
        return (Integer) get(ClavesFranquicia.FICHA_AMPLIADA_ANTERIOR.toString());
    }

    public String getExpan_consultora_id_c() {
        return (String) get(ClavesFranquicia.EXPAN_CONSULTORA_ID_C.toString());
    }

    public String getEstado_fran() {
        return (String) get(ClavesFranquicia.ESTADO_FRAN.toString());
    }

    public String getObservaciones_inter() {
        return (String) get(ClavesFranquicia.OBSERVACIONES_INTER.toString());
    }

    public String getPreseleccionadas() {
        return (String) get(ClavesFranquicia.PRESELECCIONADAS.toString());
    }

    public String getHomologacion() {
        return (String) get(ClavesFranquicia.HOMOLOGACION.toString());
    }

    public String getDocumentacion_pendiente() {
        return (String) get(ClavesFranquicia.DOCUMENTACION_PENDIENTE.toString());
    }

    public Double getDerecho_entrada_min() {
        return (Double) get(ClavesFranquicia.DERECHO_ENTRADA_MIN.toString());
    }

    public Double getDerecho_entrada_max() {
        return (Double) get(ClavesFranquicia.DERECHO_ENTRADA_MAX.toString());
    }

    public String getRoyalty_expltacion() {
        return (String) get(ClavesFranquicia.ROYALTY_EXPLTACION.toString());
    }

    public String getRoyalty_publicitario() {
        return (String) get(ClavesFranquicia.ROYALTY_PUBLICITARIO.toString());
    }

    public String getOtros_royalties() {
        return (String) get(ClavesFranquicia.OTROS_ROYALTIES.toString());
    }

    public Double getFacturacion_year_unidad_fran_1() {
        return (Double) get(ClavesFranquicia.FACTURACION_YEAR_UNIDAD_FRAN_1.toString());
    }

    public Double getFacturacion_year_unidad_fran_2() {
        return (Double) get(ClavesFranquicia.FACTURACION_YEAR_UNIDAD_FRAN_2.toString());
    }

    public Double getFacturacion_year_unidad_fran_3() {
        return (Double) get(ClavesFranquicia.FACTURACION_YEAR_UNIDAD_FRAN_3.toString());
    }

    public String getAmortizacio_inversion() {
        return (String) get(ClavesFranquicia.AMORTIZACIO_INVERSION.toString());
    }

    public Double getBeneficio_neto_unidad_fran_1() {
        return (Double) get(ClavesFranquicia.BENEFICIO_NETO_UNIDAD_FRAN_1.toString());
    }

    public Double getBeneficio_neto_unidad_fran_2() {
        return (Double) get(ClavesFranquicia.BENEFICIO_NETO_UNIDAD_FRAN_2.toString());
    }

    public Double getBeneficio_neto_unidad_fran_3() {
        return (Double) get(ClavesFranquicia.BENEFICIO_NETO_UNIDAD_FRAN_3.toString());
    }

    public String getTipo_actividad() {
        return (String) get(ClavesFranquicia.TIPO_ACTIVIDAD.toString());
    }

    public void setId(String id) {
        this.put(ClavesFranquicia.ID.toString(), id);
    }

    public void setName(String name) {
        this.put(ClavesFranquicia.NAME.toString(), name);
    }

    public void setDescription(String description) {
        this.put(ClavesFranquicia.DESCRIPTION.toString(), description);
    }

    public void setIndustry(String industry) {
        this.put(ClavesFranquicia.INDUSTRY.toString(), industry);
    }

    public void setPhone_office(String phone_office) {
        this.put(ClavesFranquicia.PHONE_OFFICE.toString(), phone_office);
    }

    public void setPhone_alternate(String phone_alternate) {
        this.put(ClavesFranquicia.PHONE_ALTERNATE.toString(), phone_alternate);
    }

    public void setWebsite(String website) {
        this.put(ClavesFranquicia.WEBSITE.toString(), website);
    }

    public void setOwnership(String ownership) {
        this.put(ClavesFranquicia.OWNERSHIP.toString(), ownership);
    }

    public void setEmployees(String employees) {
        this.put(ClavesFranquicia.EMPLOYEES.toString(), employees);
    }

    public void setTipo_ficha(String tipo_ficha) {
        this.put(ClavesFranquicia.TIPO_FICHA.toString(), tipo_ficha);
    }

    public void setZona(String zona) {
        this.put(ClavesFranquicia.ZONA.toString(), zona);
    }

    public void setLogotipo(String logotipo) {
        this.put(ClavesFranquicia.LOGOTIPO.toString(), logotipo);
    }

    public void setVideo(String video) {
        this.put(ClavesFranquicia.VIDEO.toString(), video);
    }

    public void setExclusion_de_sector(Integer exclusion_de_sector) {
        this.put(ClavesFranquicia.EXCLUSION_DE_SECTOR.toString(), exclusion_de_sector);
    }

    public void setExclusion_de_subsector(Integer exclusion_de_subsector) {
        this.put(ClavesFranquicia.EXCLUSION_DE_SUBSECTOR.toString(), exclusion_de_subsector);
    }

    public void setEstado_validacion(String estado_validacion) {
        this.put(ClavesFranquicia.ESTADO_VALIDACION.toString(), estado_validacion);
    }

    public void setTipo_de_franquiciado(String tipo_de_franquiciado) {
        this.put(ClavesFranquicia.TIPO_DE_FRANQUICIADO.toString(), tipo_de_franquiciado);
    }

    public void setNecesario_titulacion(String necesario_titulacion) {
        this.put(ClavesFranquicia.NECESARIO_TITULACION.toString(), necesario_titulacion);
    }

    public void setTitulacion(String titulacion) {
        this.put(ClavesFranquicia.TITULACION.toString(), titulacion);
    }

    public void setCondiciones_especiales(String condiciones_especiales) {
        this.put(ClavesFranquicia.CONDICIONES_ESPECIALES.toString(), condiciones_especiales);
    }

    public void setFin_condiciones_especiales(Date fin_condiciones_especiales) {
        this.put(ClavesFranquicia.FIN_CONDICIONES_ESPECIALES.toString(), fin_condiciones_especiales);
    }

    public void setObservaciones(String observaciones) {
        this.put(ClavesFranquicia.OBSERVACIONES.toString(), observaciones);
    }

    public void setInversion_minima_necesaria(Double inversion_minima_necesaria) {
        this.put(ClavesFranquicia.INVERSION_MINIMA_NECESARIA.toString(), inversion_minima_necesaria);
    }

    public void setCurrency_id(String currency_id) {
        this.put(ClavesFranquicia.CURRENCY_ID.toString(), currency_id);
    }

    public void setTipo_franquicia(String tipo_franquicia) {
        this.put(ClavesFranquicia.TIPO_FRANQUICIA.toString(), tipo_franquicia);
    }

    public void setNecesita_local(String necesita_local) {
        this.put(ClavesFranquicia.NECESITA_LOCAL.toString(), necesita_local);
    }

    public void setSuperficie_local(String superficie_local) {
        this.put(ClavesFranquicia.SUPERFICIE_LOCAL.toString(), superficie_local);
    }

    public void setRequisitos_local(String requisitos_local) {
        this.put(ClavesFranquicia.REQUISITOS_LOCAL.toString(), requisitos_local);
    }

    public void setEntorno_ubicacion(String entorno_ubicacion) {
        this.put(ClavesFranquicia.ENTORNO_UBICACION.toString(), entorno_ubicacion);
    }

    public void setObservaciones_ubicacion(String observaciones_ubicacion) {
        this.put(ClavesFranquicia.OBSERVACIONES_UBICACION.toString(), observaciones_ubicacion);
    }

    public void setPoblacion_minima(String poblacion_minima) {
        this.put(ClavesFranquicia.POBLACION_MINIMA.toString(), poblacion_minima);
    }

    public void setPersonal_minimo(String personal_minimo) {
        this.put(ClavesFranquicia.PERSONAL_MINIMO.toString(), personal_minimo);
    }

    public void setZona_exclisiva(String zona_exclisiva) {
        this.put(ClavesFranquicia.ZONA_EXCLISIVA.toString(), zona_exclisiva);
    }

    public void setVigencia_contrato(String vigencia_contrato) {
        this.put(ClavesFranquicia.VIGENCIA_CONTRATO.toString(), vigencia_contrato);
    }

    public void setReconvertir_negocio(String reconvertir_negocio) {
        this.put(ClavesFranquicia.RECONVERTIR_NEGOCIO.toString(), reconvertir_negocio);
    }

    public void setAcuerdo_financiacion(String acuerdo_financiacion) {
        this.put(ClavesFranquicia.ACUERDO_FINANCIACION.toString(), acuerdo_financiacion);
    }

    public void setSector(Integer sector) {
        this.put(ClavesFranquicia.SECTOR.toString(), sector);
    }

    public void setBreve_descripcion(String breve_descripcion) {
        this.put(ClavesFranquicia.BREVE_DESCRIPCION.toString(), breve_descripcion);
    }

    public void setFecha_creacion(Integer fecha_creacion) {
        this.put(ClavesFranquicia.FECHA_CREACION.toString(), fecha_creacion);
    }


    public void setFecha_expansion(Date fecha_expansion) {
        this.put(ClavesFranquicia.FECHA_EXPANSION.toString(), fecha_expansion);
    }

    public void setCentros_nacionales_propios(Integer centros_nacionales_propios) {
        this.put(ClavesFranquicia.CENTROS_NACIONALES_PROPIOS.toString(), centros_nacionales_propios);
    }

    public void setCentros_nacionales_franquicia(Integer centros_nacionales_franquicia) {
        this.put(ClavesFranquicia.CENTROS_NACIONALES_FRANQUICIA.toString(), centros_nacionales_franquicia);
    }

    public void setPresencia_internacional(String presencia_internacional) {
        this.put(ClavesFranquicia.PRESENCIA_INTERNACIONAL.toString(), presencia_internacional);
    }

    public void setRed_spain(Integer red_spain) {
        this.put(ClavesFranquicia.RED_SPAIN.toString(), red_spain);
    }

    public void setCentros_extranjeros_propios(Integer centros_extranjeros_propios) {
        this.put(ClavesFranquicia.CENTROS_EXTRANJEROS_PROPIOS.toString(), centros_extranjeros_propios);
    }

    public void setCentros_extranjeros_franqui(Integer centros_extranjeros_franqui) {
        this.put(ClavesFranquicia.CENTROS_EXTRANJEROS_FRANQUI.toString(), centros_extranjeros_franqui);
    }

    public void setRed_extrangera(Integer red_extrangera) {
        this.put(ClavesFranquicia.RED_EXTRANGERA.toString(), red_extrangera);
    }

    public void setPlantilla_central(Integer plantilla_central) {
        this.put(ClavesFranquicia.PLANTILLA_CENTRAL.toString(), plantilla_central);
    }

    public void setCifra_negocio_grupo(Double cifra_negocio_grupo) {
        this.put(ClavesFranquicia.CIFRA_NEGOCIO_GRUPO.toString(), cifra_negocio_grupo);
    }

    public void setNifra(String nifra) {
        this.put(ClavesFranquicia.NIFRA.toString(), nifra);
    }

    public void setRegmarca(String regmarca) {
        this.put(ClavesFranquicia.REGMARCA.toString(), regmarca);
    }

    public void setAef(String aef) {
        this.put(ClavesFranquicia.AEF.toString(), aef);
    }

    public void setSellos_calidad(String sellos_calidad) {
        this.put(ClavesFranquicia.SELLOS_CALIDAD.toString(), sellos_calidad);
    }

    public void setOtro_sello_calidad(String otro_sello_calidad) {
        this.put(ClavesFranquicia.OTRO_SELLO_CALIDAD.toString(), otro_sello_calidad);
    }

    public void setEmpresa(String empresa) {
        this.put(ClavesFranquicia.EMPRESA.toString(), empresa);
    }

    public void setLocalidad(String localidad) {
        this.put(ClavesFranquicia.LOCALIDAD.toString(), localidad);
    }

    public void setDireccion_direccion(String direccion_direccion) {
        this.put(ClavesFranquicia.DIRECCION_DIRECCION.toString(), direccion_direccion);
    }

    public void setDireccion_localidad(String direccion_localidad) {
        this.put(ClavesFranquicia.DIRECCION_LOCALIDAD.toString(), direccion_localidad);
    }

    public void setDireccion_codigo_postal(String direccion_codigo_postal) {
        this.put(ClavesFranquicia.DIRECCION_CODIGO_POSTAL.toString(), direccion_codigo_postal);
    }

    public void setDireccion_provincia(String direccion_provincia) {
        this.put(ClavesFranquicia.DIRECCION_PROVINCIA.toString(), direccion_provincia);
    }

    public void setDireccion_pais(String direccion_pais) {
        this.put(ClavesFranquicia.DIRECCION_PAIS.toString(), direccion_pais);
    }

    public void setPersona_contacto(String persona_contacto) {
        this.put(ClavesFranquicia.PERSONA_CONTACTO.toString(), persona_contacto);
    }

    public void setFecha_acuerdo(Date fecha_acuerdo) {
        this.put(ClavesFranquicia.FECHA_ACUERDO.toString(), fecha_acuerdo);
    }

    public void setFecha_activacion(Date fecha_activacion) {
        this.put(ClavesFranquicia.FECHA_ACTIVACION.toString(), fecha_activacion);
    }

    public void setFicha_ampliada_anterior(Integer ficha_ampliada_anterior) {
        this.put(ClavesFranquicia.FICHA_AMPLIADA_ANTERIOR.toString(), ficha_ampliada_anterior);
    }

    public void setExpan_consultora_id_c(String expan_consultora_id_c) {
        this.put(ClavesFranquicia.EXPAN_CONSULTORA_ID_C.toString(), expan_consultora_id_c);
    }

    public void setEstado_fran(String estado_fran) {
        this.put(ClavesFranquicia.ESTADO_FRAN.toString(), estado_fran);
    }

    public void setObservaciones_inter(String observaciones_inter) {
        this.put(ClavesFranquicia.OBSERVACIONES_INTER.toString(), observaciones_inter);
    }

    public void setPreseleccionadas(String preseleccionadas) {
        this.put(ClavesFranquicia.PRESELECCIONADAS.toString(), preseleccionadas);
    }

    public void setHomologacion(String homologacion) {
        this.put(ClavesFranquicia.HOMOLOGACION.toString(), homologacion);
    }

    public void setDocumentacion_pendiente(String documentacion_pendiente) {
        this.put(ClavesFranquicia.DOCUMENTACION_PENDIENTE.toString(), documentacion_pendiente);
    }

    public void setDerecho_entrada_min(Double derecho_entrada_min) {
        this.put(ClavesFranquicia.DERECHO_ENTRADA_MIN.toString(), derecho_entrada_min);
    }

    public void setDerecho_entrada_max(Double derecho_entrada_max) {
        this.put(ClavesFranquicia.DERECHO_ENTRADA_MAX.toString(), derecho_entrada_max);
    }

    public void setRoyalty_expltacion(String royalty_expltacion) {
        this.put(ClavesFranquicia.ROYALTY_EXPLTACION.toString(), royalty_expltacion);
    }

    public void setRoyalty_publicitario(String royalty_publicitario) {
        this.put(ClavesFranquicia.ROYALTY_PUBLICITARIO.toString(), royalty_publicitario);
    }

    public void setOtros_royalties(String otros_royalties) {
        this.put(ClavesFranquicia.OTROS_ROYALTIES.toString(), otros_royalties);
    }

    public void setFacturacion_year_unidad_fran_1(Double facturacion_year_unidad_fran_1) {
        this.put(ClavesFranquicia.FACTURACION_YEAR_UNIDAD_FRAN_1.toString(), facturacion_year_unidad_fran_1);
    }

    public void setFacturacion_year_unidad_fran_2(Double facturacion_year_unidad_fran_2) {
        this.put(ClavesFranquicia.FACTURACION_YEAR_UNIDAD_FRAN_2.toString(), facturacion_year_unidad_fran_2);
    }

    public void setFacturacion_year_unidad_fran_3(Double facturacion_year_unidad_fran_3) {
        this.put(ClavesFranquicia.FACTURACION_YEAR_UNIDAD_FRAN_3.toString(), facturacion_year_unidad_fran_3);
    }

    public void setAmortizacio_inversion(String amortizacio_inversion) {
        this.put(ClavesFranquicia.AMORTIZACIO_INVERSION.toString(), amortizacio_inversion);
    }

    public void setBeneficio_neto_unidad_fran_1(Double beneficio_neto_unidad_fran_1) {
        this.put(ClavesFranquicia.BENEFICIO_NETO_UNIDAD_FRAN_1.toString(), beneficio_neto_unidad_fran_1);
    }

    public void setBeneficio_neto_unidad_fran_2(Double beneficio_neto_unidad_fran_2) {
        this.put(ClavesFranquicia.BENEFICIO_NETO_UNIDAD_FRAN_2.toString(), beneficio_neto_unidad_fran_2);
    }

    public void setBeneficio_neto_unidad_fran_3(Double beneficio_neto_unidad_fran_3) {
        this.put(ClavesFranquicia.BENEFICIO_NETO_UNIDAD_FRAN_3.toString(), beneficio_neto_unidad_fran_3);
    }

    public void setTipo_actividad(String tipo_actividad) {
        this.put(ClavesFranquicia.TIPO_ACTIVIDAD.toString(), tipo_actividad);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Franquicia f) {

        String d1 = this.get(ClavesFranquicia.NAME.toString()).toString();
        String d2 = f.get(ClavesFranquicia.NAME.toString()).toString();

        int[] criterio = {
                d1.compareTo(d2)
        };
        for (int uno : criterio) {
            if (uno != 0) {
                return uno;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Franquicia) {
            Franquicia unaFranquicia = (Franquicia) object;
            if (this.compareTo(unaFranquicia) == 0) {
                return true;
            } else {
                return false;
            }
        }
        return super.equals(object);
    }

}