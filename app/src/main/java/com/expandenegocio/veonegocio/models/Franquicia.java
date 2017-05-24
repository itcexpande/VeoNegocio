package com.expandenegocio.veonegocio.models;

import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by jesus on 28/04/2017.
 */

public class Franquicia  {

    private String id;
    private String name;
    private Date date_entered;
    private Date date_modified;
    private String modified_user_id;
    private String created_by;
    private String description;
    private Integer deleted;
    private String assigned_user_id;
    private String franquicia_type;
    private String industry;
    private String annual_revenue;
    private String phone_fax;
    private String billing_address_street;
    private String billing_address_city;
    private String billing_address_state;
    private String billing_address_postalcode;
    private String billing_address_country;
    private String rating;
    private String phone_office;
    private String phone_alternate;
    private String website;
    private String ownership;
    private String employees;
    private String ticker_symbol;
    private String shipping_address_street;
    private String shipping_address_city;
    private String shipping_address_state;
    private String shipping_address_postalcode;
    private String shipping_address_country;
    private String tipo_ficha;
    private String zona;
    private String logotipo;
    private String video;
    private Integer exclusion_de_sector;
    private Integer exclusion_de_subsector;
    private String estado_validacion;
    private String gestionado_por;
    private String tipo_de_franquiciado;
    private String necesario_titulacion;
    private String titulacion;
    private String condiciones_especiales;
    private Date fin_condiciones_especiales;
    private String observaciones;
    private Double inversion_minima_necesaria;
    private String currency_id;
    private String tipo_franquicia;
    private String necesita_local;
    private String superficie_local;
    private String requisitos_local;
    private String entorno_ubicacion;
    private String observaciones_ubicacion;
    private String provincias_ubicar_negocio;
    private String poblacion_minima;
    private String personal_minimo;
    private String zona_exclisiva;
    private String vigencia_contrato;
    private String reconvertir_negocio;
    private String acuerdo_financiacion;
    private String sector;
    private String breve_descripcion;
    private Integer fecha_creacion;
    private Date fecha_expansion;
    private Integer centros_nacionales_propios;
    private Integer centros_nacionales_franquicia;
    private String presencia_internacional;
    private String paises;
    private Integer red_spain;
    private Integer centros_extranjeros_propios;
    private Integer centros_extranjeros_franqui;
    private Integer red_extrangera;
    private Integer plantilla_central;
    private Double cifra_negocio_grupo;
    private String nifra;
    private String regmarca;
    private String aef;
    private String sellos_calidad;
    private String otro_sello_calidad;
    private String empresa;
    private String locaidad;
    private String direccion_direccion;
    private String direccion_localidad;
    private String direccion_codigo_postal;
    private String direccion_provincia;
    private String direccion_pais;
    private String persona_contacto;
    private Date fecha_acuerdo;
    private Date fecha_activacion;
    private Integer ficha_ampliada_anterior;
    private String expan_consultora_id_c;
    private String estado_fran;
    private String observaciones_inter;
    private String preseleccionadas;
    private String homologacion;
    private String user_id_c;
    private String documentacion_pendiente;
    private String objeciones_foros_bbdd;
    private Double derecho_entrada_min;
    private Double derecho_entrada_max;
    private String royalty_expltacion;
    private String royalty_publicitario;
    private String otros_royalties;
    private Double facturacion_year_unidad_fran_1;
    private Double facturacion_year_unidad_fran_2;
    private Double facturacion_year_unidad_fran_3;
    private String amortizacio_inversion;
    private Double beneficio_neto_unidad_fran_1;
    private Double beneficio_neto_unidad_fran_2;
    private Double beneficio_neto_unidad_fran_3;
    private String tipo_actividad;
    private String movil_general;
    private String cargo_contacto_general;
    private String contacto_administracion;
    private String telefono_administracion;
    private String movil_administracion;
    private String contacto_intermediacion;
    private String telefono_intermediacion;
    private String movil_intermediacion;
    private String correo_administracion;
    private String correo_intermediacion;
    private String email1;
    private String correo_general;
    private String contacto_general_2;
    private String telefono_contacto_2;
    private String telefono_alternativo_2;
    private String movil_general_2;
    private String correo_contacto_2;
    private String cargo_contacto_2;
    private Integer inicio_expansion;
    private String observaciones_administracion;
    private String tipo_cuenta;
    private String correo_envio;
    private Integer chk_c1;
    private Integer chk_c2;
    private Integer chk_c3;
    private Integer chk_c4;
    private Integer chk_c11;
    private Integer chk_c12;
    private Integer chk_c13;
    private Integer chk_c14;
    private Integer chk_c15;
    private String dir_cons_id_c;
    private Integer llamar_todos;
    private Integer informe_urgente;
    private String modNeg1;
    private String modNeg2;
    private String modNeg3;
    private String modNeg4;
    private Integer prioridad;
    private String valNeg11;
    private String valNeg12;
    private String valNeg13;
    private String valNeg14;
    private String valNeg15;
    private String valNeg21;
    private String valNeg22;
    private String valNeg23;
    private String valNeg24;
    private String valNeg25;
    private String valNeg31;
    private String valNeg32;
    private String valNeg33;
    private String valNeg34;
    private String valNeg35;
    private String valNeg41;
    private String valNeg42;
    private String valNeg43;
    private String valNeg44;
    private String valNeg45;
    private String Campo_prueba;
    private Integer master;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_entered() {
        return date_entered;
    }

    public void setDate_entered(Date date_entered) {
        this.date_entered = date_entered;
    }

    public Date getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(Date date_modified) {
        this.date_modified = date_modified;
    }

    public String getModified_user_id() {
        return modified_user_id;
    }

    public void setModified_user_id(String modified_user_id) {
        this.modified_user_id = modified_user_id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getAssigned_user_id() {
        return assigned_user_id;
    }

    public void setAssigned_user_id(String assigned_user_id) {
        this.assigned_user_id = assigned_user_id;
    }

    public String getFranquicia_type() {
        return franquicia_type;
    }

    public void setFranquicia_type(String franquicia_type) {
        this.franquicia_type = franquicia_type;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getAnnual_revenue() {
        return annual_revenue;
    }

    public void setAnnual_revenue(String annual_revenue) {
        this.annual_revenue = annual_revenue;
    }

    public String getPhone_fax() {
        return phone_fax;
    }

    public void setPhone_fax(String phone_fax) {
        this.phone_fax = phone_fax;
    }

    public String getBilling_address_street() {
        return billing_address_street;
    }

    public void setBilling_address_street(String billing_address_street) {
        this.billing_address_street = billing_address_street;
    }

    public String getBilling_address_city() {
        return billing_address_city;
    }

    public void setBilling_address_city(String billing_address_city) {
        this.billing_address_city = billing_address_city;
    }

    public String getBilling_address_state() {
        return billing_address_state;
    }

    public void setBilling_address_state(String billing_address_state) {
        this.billing_address_state = billing_address_state;
    }

    public String getBilling_address_postalcode() {
        return billing_address_postalcode;
    }

    public void setBilling_address_postalcode(String billing_address_postalcode) {
        this.billing_address_postalcode = billing_address_postalcode;
    }

    public String getBilling_address_country() {
        return billing_address_country;
    }

    public void setBilling_address_country(String billing_address_country) {
        this.billing_address_country = billing_address_country;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPhone_office() {
        return phone_office;
    }

    public void setPhone_office(String phone_office) {
        this.phone_office = phone_office;
    }

    public String getPhone_alternate() {
        return phone_alternate;
    }

    public void setPhone_alternate(String phone_alternate) {
        this.phone_alternate = phone_alternate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public String getTicker_symbol() {
        return ticker_symbol;
    }

    public void setTicker_symbol(String ticker_symbol) {
        this.ticker_symbol = ticker_symbol;
    }

    public String getShipping_address_street() {
        return shipping_address_street;
    }

    public void setShipping_address_street(String shipping_address_street) {
        this.shipping_address_street = shipping_address_street;
    }

    public String getShipping_address_city() {
        return shipping_address_city;
    }

    public void setShipping_address_city(String shipping_address_city) {
        this.shipping_address_city = shipping_address_city;
    }

    public String getShipping_address_state() {
        return shipping_address_state;
    }

    public void setShipping_address_state(String shipping_address_state) {
        this.shipping_address_state = shipping_address_state;
    }

    public String getShipping_address_postalcode() {
        return shipping_address_postalcode;
    }

    public void setShipping_address_postalcode(String shipping_address_postalcode) {
        this.shipping_address_postalcode = shipping_address_postalcode;
    }

    public String getShipping_address_country() {
        return shipping_address_country;
    }

    public void setShipping_address_country(String shipping_address_country) {
        this.shipping_address_country = shipping_address_country;
    }

    public String getTipo_ficha() {
        return tipo_ficha;
    }

    public void setTipo_ficha(String tipo_ficha) {
        this.tipo_ficha = tipo_ficha;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getExclusion_de_sector() {
        return exclusion_de_sector;
    }

    public void setExclusion_de_sector(Integer exclusion_de_sector) {
        this.exclusion_de_sector = exclusion_de_sector;
    }

    public Integer getExclusion_de_subsector() {
        return exclusion_de_subsector;
    }

    public void setExclusion_de_subsector(Integer exclusion_de_subsector) {
        this.exclusion_de_subsector = exclusion_de_subsector;
    }

    public String getEstado_validacion() {
        return estado_validacion;
    }

    public void setEstado_validacion(String estado_validacion) {
        this.estado_validacion = estado_validacion;
    }

    public String getGestionado_por() {
        return gestionado_por;
    }

    public void setGestionado_por(String gestionado_por) {
        this.gestionado_por = gestionado_por;
    }

    public String getTipo_de_franquiciado() {
        return tipo_de_franquiciado;
    }

    public void setTipo_de_franquiciado(String tipo_de_franquiciado) {
        this.tipo_de_franquiciado = tipo_de_franquiciado;
    }

    public String getNecesario_titulacion() {
        return necesario_titulacion;
    }

    public void setNecesario_titulacion(String necesario_titulacion) {
        this.necesario_titulacion = necesario_titulacion;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public String getCondiciones_especiales() {
        return condiciones_especiales;
    }

    public void setCondiciones_especiales(String condiciones_especiales) {
        this.condiciones_especiales = condiciones_especiales;
    }

    public Date getFin_condiciones_especiales() {
        return fin_condiciones_especiales;
    }

    public void setFin_condiciones_especiales(Date fin_condiciones_especiales) {
        this.fin_condiciones_especiales = fin_condiciones_especiales;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getInversion_minima_necesaria() {
        return inversion_minima_necesaria;
    }

    public void setInversion_minima_necesaria(Double inversion_minima_necesaria) {
        this.inversion_minima_necesaria = inversion_minima_necesaria;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getTipo_franquicia() {
        return tipo_franquicia;
    }

    public void setTipo_franquicia(String tipo_franquicia) {
        this.tipo_franquicia = tipo_franquicia;
    }

    public String getNecesita_local() {
        return necesita_local;
    }

    public void setNecesita_local(String necesita_local) {
        this.necesita_local = necesita_local;
    }

    public String getSuperficie_local() {
        return superficie_local;
    }

    public void setSuperficie_local(String superficie_local) {
        this.superficie_local = superficie_local;
    }

    public String getRequisitos_local() {
        return requisitos_local;
    }

    public void setRequisitos_local(String requisitos_local) {
        this.requisitos_local = requisitos_local;
    }

    public String getEntorno_ubicacion() {
        return entorno_ubicacion;
    }

    public void setEntorno_ubicacion(String entorno_ubicacion) {
        this.entorno_ubicacion = entorno_ubicacion;
    }

    public String getObservaciones_ubicacion() {
        return observaciones_ubicacion;
    }

    public void setObservaciones_ubicacion(String observaciones_ubicacion) {
        this.observaciones_ubicacion = observaciones_ubicacion;
    }

    public String getProvincias_ubicar_negocio() {
        return provincias_ubicar_negocio;
    }

    public void setProvincias_ubicar_negocio(String provincias_ubicar_negocio) {
        this.provincias_ubicar_negocio = provincias_ubicar_negocio;
    }

    public String getPoblacion_minima() {
        return poblacion_minima;
    }

    public void setPoblacion_minima(String poblacion_minima) {
        this.poblacion_minima = poblacion_minima;
    }

    public String getPersonal_minimo() {
        return personal_minimo;
    }

    public void setPersonal_minimo(String personal_minimo) {
        this.personal_minimo = personal_minimo;
    }

    public String getZona_exclisiva() {
        return zona_exclisiva;
    }

    public void setZona_exclisiva(String zona_exclisiva) {
        this.zona_exclisiva = zona_exclisiva;
    }

    public String getVigencia_contrato() {
        return vigencia_contrato;
    }

    public void setVigencia_contrato(String vigencia_contrato) {
        this.vigencia_contrato = vigencia_contrato;
    }

    public String getReconvertir_negocio() {
        return reconvertir_negocio;
    }

    public void setReconvertir_negocio(String reconvertir_negocio) {
        this.reconvertir_negocio = reconvertir_negocio;
    }

    public String getAcuerdo_financiacion() {
        return acuerdo_financiacion;
    }

    public void setAcuerdo_financiacion(String acuerdo_financiacion) {
        this.acuerdo_financiacion = acuerdo_financiacion;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getBreve_descripcion() {
        return breve_descripcion;
    }

    public void setBreve_descripcion(String breve_descripcion) {
        this.breve_descripcion = breve_descripcion;
    }

    public Integer getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Integer fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_expansion() {
        return fecha_expansion;
    }

    public void setFecha_expansion(Date fecha_expansion) {
        this.fecha_expansion = fecha_expansion;
    }

    public Integer getCentros_nacionales_propios() {
        return centros_nacionales_propios;
    }

    public void setCentros_nacionales_propios(Integer centros_nacionales_propios) {
        this.centros_nacionales_propios = centros_nacionales_propios;
    }

    public Integer getCentros_nacionales_franquicia() {
        return centros_nacionales_franquicia;
    }

    public void setCentros_nacionales_franquicia(Integer centros_nacionales_franquicia) {
        this.centros_nacionales_franquicia = centros_nacionales_franquicia;
    }

    public String getPresencia_internacional() {
        return presencia_internacional;
    }

    public void setPresencia_internacional(String presencia_internacional) {
        this.presencia_internacional = presencia_internacional;
    }

    public String getPaises() {
        return paises;
    }

    public void setPaises(String paises) {
        this.paises = paises;
    }

    public Integer getRed_spain() {
        return red_spain;
    }

    public void setRed_spain(Integer red_spain) {
        this.red_spain = red_spain;
    }

    public Integer getCentros_extranjeros_propios() {
        return centros_extranjeros_propios;
    }

    public void setCentros_extranjeros_propios(Integer centros_extranjeros_propios) {
        this.centros_extranjeros_propios = centros_extranjeros_propios;
    }

    public Integer getCentros_extranjeros_franqui() {
        return centros_extranjeros_franqui;
    }

    public void setCentros_extranjeros_franqui(Integer centros_extranjeros_franqui) {
        this.centros_extranjeros_franqui = centros_extranjeros_franqui;
    }

    public Integer getRed_extrangera() {
        return red_extrangera;
    }

    public void setRed_extrangera(Integer red_extrangera) {
        this.red_extrangera = red_extrangera;
    }

    public Integer getPlantilla_central() {
        return plantilla_central;
    }

    public void setPlantilla_central(Integer plantilla_central) {
        this.plantilla_central = plantilla_central;
    }

    public Double getCifra_negocio_grupo() {
        return cifra_negocio_grupo;
    }

    public void setCifra_negocio_grupo(Double cifra_negocio_grupo) {
        this.cifra_negocio_grupo = cifra_negocio_grupo;
    }

    public String getNifra() {
        return nifra;
    }

    public void setNifra(String nifra) {
        this.nifra = nifra;
    }

    public String getRegmarca() {
        return regmarca;
    }

    public void setRegmarca(String regmarca) {
        this.regmarca = regmarca;
    }

    public String getAef() {
        return aef;
    }

    public void setAef(String aef) {
        this.aef = aef;
    }

    public String getSellos_calidad() {
        return sellos_calidad;
    }

    public void setSellos_calidad(String sellos_calidad) {
        this.sellos_calidad = sellos_calidad;
    }

    public String getOtro_sello_calidad() {
        return otro_sello_calidad;
    }

    public void setOtro_sello_calidad(String otro_sello_calidad) {
        this.otro_sello_calidad = otro_sello_calidad;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getLocaidad() {
        return locaidad;
    }

    public void setLocaidad(String locaidad) {
        this.locaidad = locaidad;
    }

    public String getDireccion_direccion() {
        return direccion_direccion;
    }

    public void setDireccion_direccion(String direccion_direccion) {
        this.direccion_direccion = direccion_direccion;
    }

    public String getDireccion_localidad() {
        return direccion_localidad;
    }

    public void setDireccion_localidad(String direccion_localidad) {
        this.direccion_localidad = direccion_localidad;
    }

    public String getDireccion_codigo_postal() {
        return direccion_codigo_postal;
    }

    public void setDireccion_codigo_postal(String direccion_codigo_postal) {
        this.direccion_codigo_postal = direccion_codigo_postal;
    }

    public String getDireccion_provincia() {
        return direccion_provincia;
    }

    public void setDireccion_provincia(String direccion_provincia) {
        this.direccion_provincia = direccion_provincia;
    }

    public String getDireccion_pais() {
        return direccion_pais;
    }

    public void setDireccion_pais(String direccion_pais) {
        this.direccion_pais = direccion_pais;
    }

    public String getPersona_contacto() {
        return persona_contacto;
    }

    public void setPersona_contacto(String persona_contacto) {
        this.persona_contacto = persona_contacto;
    }

    public Date getFecha_acuerdo() {
        return fecha_acuerdo;
    }

    public void setFecha_acuerdo(Date fecha_acuerdo) {
        this.fecha_acuerdo = fecha_acuerdo;
    }

    public Date getFecha_activacion() {
        return fecha_activacion;
    }

    public void setFecha_activacion(Date fecha_activacion) {
        this.fecha_activacion = fecha_activacion;
    }

    public Integer getFicha_ampliada_anterior() {
        return ficha_ampliada_anterior;
    }

    public void setFicha_ampliada_anterior(Integer ficha_ampliada_anterior) {
        this.ficha_ampliada_anterior = ficha_ampliada_anterior;
    }

    public String getExpan_consultora_id_c() {
        return expan_consultora_id_c;
    }

    public void setExpan_consultora_id_c(String expan_consultora_id_c) {
        this.expan_consultora_id_c = expan_consultora_id_c;
    }

    public String getEstado_fran() {
        return estado_fran;
    }

    public void setEstado_fran(String estado_fran) {
        this.estado_fran = estado_fran;
    }

    public String getObservaciones_inter() {
        return observaciones_inter;
    }

    public void setObservaciones_inter(String observaciones_inter) {
        this.observaciones_inter = observaciones_inter;
    }

    public String getPreseleccionadas() {
        return preseleccionadas;
    }

    public void setPreseleccionadas(String preseleccionadas) {
        this.preseleccionadas = preseleccionadas;
    }

    public String getHomologacion() {
        return homologacion;
    }

    public void setHomologacion(String homologacion) {
        this.homologacion = homologacion;
    }

    public String getUser_id_c() {
        return user_id_c;
    }

    public void setUser_id_c(String user_id_c) {
        this.user_id_c = user_id_c;
    }

    public String getDocumentacion_pendiente() {
        return documentacion_pendiente;
    }

    public void setDocumentacion_pendiente(String documentacion_pendiente) {
        this.documentacion_pendiente = documentacion_pendiente;
    }

    public String getObjeciones_foros_bbdd() {
        return objeciones_foros_bbdd;
    }

    public void setObjeciones_foros_bbdd(String objeciones_foros_bbdd) {
        this.objeciones_foros_bbdd = objeciones_foros_bbdd;
    }

    public Double getDerecho_entrada_min() {
        return derecho_entrada_min;
    }

    public void setDerecho_entrada_min(Double derecho_entrada_min) {
        this.derecho_entrada_min = derecho_entrada_min;
    }

    public Double getDerecho_entrada_max() {
        return derecho_entrada_max;
    }

    public void setDerecho_entrada_max(Double derecho_entrada_max) {
        this.derecho_entrada_max = derecho_entrada_max;
    }

    public String getRoyalty_expltacion() {
        return royalty_expltacion;
    }

    public void setRoyalty_expltacion(String royalty_expltacion) {
        this.royalty_expltacion = royalty_expltacion;
    }

    public String getRoyalty_publicitario() {
        return royalty_publicitario;
    }

    public void setRoyalty_publicitario(String royalty_publicitario) {
        this.royalty_publicitario = royalty_publicitario;
    }

    public String getOtros_royalties() {
        return otros_royalties;
    }

    public void setOtros_royalties(String otros_royalties) {
        this.otros_royalties = otros_royalties;
    }

    public Double getFacturacion_year_unidad_fran_1() {
        return facturacion_year_unidad_fran_1;
    }

    public void setFacturacion_year_unidad_fran_1(Double facturacion_year_unidad_fran_1) {
        this.facturacion_year_unidad_fran_1 = facturacion_year_unidad_fran_1;
    }

    public Double getFacturacion_year_unidad_fran_2() {
        return facturacion_year_unidad_fran_2;
    }

    public void setFacturacion_year_unidad_fran_2(Double facturacion_year_unidad_fran_2) {
        this.facturacion_year_unidad_fran_2 = facturacion_year_unidad_fran_2;
    }

    public Double getFacturacion_year_unidad_fran_3() {
        return facturacion_year_unidad_fran_3;
    }

    public void setFacturacion_year_unidad_fran_3(Double facturacion_year_unidad_fran_3) {
        this.facturacion_year_unidad_fran_3 = facturacion_year_unidad_fran_3;
    }

    public String getAmortizacio_inversion() {
        return amortizacio_inversion;
    }

    public void setAmortizacio_inversion(String amortizacio_inversion) {
        this.amortizacio_inversion = amortizacio_inversion;
    }

    public Double getBeneficio_neto_unidad_fran_1() {
        return beneficio_neto_unidad_fran_1;
    }

    public void setBeneficio_neto_unidad_fran_1(Double beneficio_neto_unidad_fran_1) {
        this.beneficio_neto_unidad_fran_1 = beneficio_neto_unidad_fran_1;
    }

    public Double getBeneficio_neto_unidad_fran_2() {
        return beneficio_neto_unidad_fran_2;
    }

    public void setBeneficio_neto_unidad_fran_2(Double beneficio_neto_unidad_fran_2) {
        this.beneficio_neto_unidad_fran_2 = beneficio_neto_unidad_fran_2;
    }

    public Double getBeneficio_neto_unidad_fran_3() {
        return beneficio_neto_unidad_fran_3;
    }

    public void setBeneficio_neto_unidad_fran_3(Double beneficio_neto_unidad_fran_3) {
        this.beneficio_neto_unidad_fran_3 = beneficio_neto_unidad_fran_3;
    }

    public String getTipo_actividad() {
        return tipo_actividad;
    }

    public void setTipo_actividad(String tipo_actividad) {
        this.tipo_actividad = tipo_actividad;
    }

    public String getMovil_general() {
        return movil_general;
    }

    public void setMovil_general(String movil_general) {
        this.movil_general = movil_general;
    }

    public String getCargo_contacto_general() {
        return cargo_contacto_general;
    }

    public void setCargo_contacto_general(String cargo_contacto_general) {
        this.cargo_contacto_general = cargo_contacto_general;
    }

    public String getContacto_administracion() {
        return contacto_administracion;
    }

    public void setContacto_administracion(String contacto_administracion) {
        this.contacto_administracion = contacto_administracion;
    }

    public String getTelefono_administracion() {
        return telefono_administracion;
    }

    public void setTelefono_administracion(String telefono_administracion) {
        this.telefono_administracion = telefono_administracion;
    }

    public String getMovil_administracion() {
        return movil_administracion;
    }

    public void setMovil_administracion(String movil_administracion) {
        this.movil_administracion = movil_administracion;
    }

    public String getContacto_intermediacion() {
        return contacto_intermediacion;
    }

    public void setContacto_intermediacion(String contacto_intermediacion) {
        this.contacto_intermediacion = contacto_intermediacion;
    }

    public String getTelefono_intermediacion() {
        return telefono_intermediacion;
    }

    public void setTelefono_intermediacion(String telefono_intermediacion) {
        this.telefono_intermediacion = telefono_intermediacion;
    }

    public String getMovil_intermediacion() {
        return movil_intermediacion;
    }

    public void setMovil_intermediacion(String movil_intermediacion) {
        this.movil_intermediacion = movil_intermediacion;
    }

    public String getCorreo_administracion() {
        return correo_administracion;
    }

    public void setCorreo_administracion(String correo_administracion) {
        this.correo_administracion = correo_administracion;
    }

    public String getCorreo_intermediacion() {
        return correo_intermediacion;
    }

    public void setCorreo_intermediacion(String correo_intermediacion) {
        this.correo_intermediacion = correo_intermediacion;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getCorreo_general() {
        return correo_general;
    }

    public void setCorreo_general(String correo_general) {
        this.correo_general = correo_general;
    }

    public String getContacto_general_2() {
        return contacto_general_2;
    }

    public void setContacto_general_2(String contacto_general_2) {
        this.contacto_general_2 = contacto_general_2;
    }

    public String getTelefono_contacto_2() {
        return telefono_contacto_2;
    }

    public void setTelefono_contacto_2(String telefono_contacto_2) {
        this.telefono_contacto_2 = telefono_contacto_2;
    }

    public String getTelefono_alternativo_2() {
        return telefono_alternativo_2;
    }

    public void setTelefono_alternativo_2(String telefono_alternativo_2) {
        this.telefono_alternativo_2 = telefono_alternativo_2;
    }

    public String getMovil_general_2() {
        return movil_general_2;
    }

    public void setMovil_general_2(String movil_general_2) {
        this.movil_general_2 = movil_general_2;
    }

    public String getCorreo_contacto_2() {
        return correo_contacto_2;
    }

    public void setCorreo_contacto_2(String correo_contacto_2) {
        this.correo_contacto_2 = correo_contacto_2;
    }

    public String getCargo_contacto_2() {
        return cargo_contacto_2;
    }

    public void setCargo_contacto_2(String cargo_contacto_2) {
        this.cargo_contacto_2 = cargo_contacto_2;
    }

    public Integer getInicio_expansion() {
        return inicio_expansion;
    }

    public void setInicio_expansion(Integer inicio_expansion) {
        this.inicio_expansion = inicio_expansion;
    }

    public String getObservaciones_administracion() {
        return observaciones_administracion;
    }

    public void setObservaciones_administracion(String observaciones_administracion) {
        this.observaciones_administracion = observaciones_administracion;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public String getCorreo_envio() {
        return correo_envio;
    }

    public void setCorreo_envio(String correo_envio) {
        this.correo_envio = correo_envio;
    }

    public Integer getChk_c1() {
        return chk_c1;
    }

    public void setChk_c1(Integer chk_c1) {
        this.chk_c1 = chk_c1;
    }

    public Integer getChk_c2() {
        return chk_c2;
    }

    public void setChk_c2(Integer chk_c2) {
        this.chk_c2 = chk_c2;
    }

    public Integer getChk_c3() {
        return chk_c3;
    }

    public void setChk_c3(Integer chk_c3) {
        this.chk_c3 = chk_c3;
    }

    public Integer getChk_c4() {
        return chk_c4;
    }

    public void setChk_c4(Integer chk_c4) {
        this.chk_c4 = chk_c4;
    }

    public Integer getChk_c11() {
        return chk_c11;
    }

    public void setChk_c11(Integer chk_c11) {
        this.chk_c11 = chk_c11;
    }

    public Integer getChk_c12() {
        return chk_c12;
    }

    public void setChk_c12(Integer chk_c12) {
        this.chk_c12 = chk_c12;
    }

    public Integer getChk_c13() {
        return chk_c13;
    }

    public void setChk_c13(Integer chk_c13) {
        this.chk_c13 = chk_c13;
    }

    public Integer getChk_c14() {
        return chk_c14;
    }

    public void setChk_c14(Integer chk_c14) {
        this.chk_c14 = chk_c14;
    }

    public Integer getChk_c15() {
        return chk_c15;
    }

    public void setChk_c15(Integer chk_c15) {
        this.chk_c15 = chk_c15;
    }

    public String getDir_cons_id_c() {
        return dir_cons_id_c;
    }

    public void setDir_cons_id_c(String dir_cons_id_c) {
        this.dir_cons_id_c = dir_cons_id_c;
    }

    public Integer getLlamar_todos() {
        return llamar_todos;
    }

    public void setLlamar_todos(Integer llamar_todos) {
        this.llamar_todos = llamar_todos;
    }

    public Integer getInforme_urgente() {
        return informe_urgente;
    }

    public void setInforme_urgente(Integer informe_urgente) {
        this.informe_urgente = informe_urgente;
    }

    public String getModNeg1() {
        return modNeg1;
    }

    public void setModNeg1(String modNeg1) {
        this.modNeg1 = modNeg1;
    }

    public String getModNeg2() {
        return modNeg2;
    }

    public void setModNeg2(String modNeg2) {
        this.modNeg2 = modNeg2;
    }

    public String getModNeg3() {
        return modNeg3;
    }

    public void setModNeg3(String modNeg3) {
        this.modNeg3 = modNeg3;
    }

    public String getModNeg4() {
        return modNeg4;
    }

    public void setModNeg4(String modNeg4) {
        this.modNeg4 = modNeg4;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public String getValNeg11() {
        return valNeg11;
    }

    public void setValNeg11(String valNeg11) {
        this.valNeg11 = valNeg11;
    }

    public String getValNeg12() {
        return valNeg12;
    }

    public void setValNeg12(String valNeg12) {
        this.valNeg12 = valNeg12;
    }

    public String getValNeg13() {
        return valNeg13;
    }

    public void setValNeg13(String valNeg13) {
        this.valNeg13 = valNeg13;
    }

    public String getValNeg14() {
        return valNeg14;
    }

    public void setValNeg14(String valNeg14) {
        this.valNeg14 = valNeg14;
    }

    public String getValNeg15() {
        return valNeg15;
    }

    public void setValNeg15(String valNeg15) {
        this.valNeg15 = valNeg15;
    }

    public String getValNeg21() {
        return valNeg21;
    }

    public void setValNeg21(String valNeg21) {
        this.valNeg21 = valNeg21;
    }

    public String getValNeg22() {
        return valNeg22;
    }

    public void setValNeg22(String valNeg22) {
        this.valNeg22 = valNeg22;
    }

    public String getValNeg23() {
        return valNeg23;
    }

    public void setValNeg23(String valNeg23) {
        this.valNeg23 = valNeg23;
    }

    public String getValNeg24() {
        return valNeg24;
    }

    public void setValNeg24(String valNeg24) {
        this.valNeg24 = valNeg24;
    }

    public String getValNeg25() {
        return valNeg25;
    }

    public void setValNeg25(String valNeg25) {
        this.valNeg25 = valNeg25;
    }

    public String getValNeg31() {
        return valNeg31;
    }

    public void setValNeg31(String valNeg31) {
        this.valNeg31 = valNeg31;
    }

    public String getValNeg32() {
        return valNeg32;
    }

    public void setValNeg32(String valNeg32) {
        this.valNeg32 = valNeg32;
    }

    public String getValNeg33() {
        return valNeg33;
    }

    public void setValNeg33(String valNeg33) {
        this.valNeg33 = valNeg33;
    }

    public String getValNeg34() {
        return valNeg34;
    }

    public void setValNeg34(String valNeg34) {
        this.valNeg34 = valNeg34;
    }

    public String getValNeg35() {
        return valNeg35;
    }

    public void setValNeg35(String valNeg35) {
        this.valNeg35 = valNeg35;
    }

    public String getValNeg41() {
        return valNeg41;
    }

    public void setValNeg41(String valNeg41) {
        this.valNeg41 = valNeg41;
    }

    public String getValNeg42() {
        return valNeg42;
    }

    public void setValNeg42(String valNeg42) {
        this.valNeg42 = valNeg42;
    }

    public String getValNeg43() {
        return valNeg43;
    }

    public void setValNeg43(String valNeg43) {
        this.valNeg43 = valNeg43;
    }

    public String getValNeg44() {
        return valNeg44;
    }

    public void setValNeg44(String valNeg44) {
        this.valNeg44 = valNeg44;
    }

    public String getValNeg45() {
        return valNeg45;
    }

    public void setValNeg45(String valNeg45) {
        this.valNeg45 = valNeg45;
    }

    public String getCampo_prueba() {
        return Campo_prueba;
    }

    public void setCampo_prueba(String campo_prueba) {
        Campo_prueba = campo_prueba;
    }

    public Integer getMaster() {
        return master;
    }

    public void setMaster(Integer master) {
        this.master = master;
    }
}