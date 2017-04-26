package com.expandenegocio.veonegocio.models;

/**
 * Created by jesus on 05/04/2017.
 */

public class Municipio {

    private int codigoProvincia;
    private int codigoMunicipio;
    private String nombreMunicipio;
    private Integer totalHabitantes;
    private Integer hombres;
    private Integer mujeres;

    public Municipio(int codigoProvincia, int codigoMunicipio, String nombreMunicipio, Integer totalHabitantes, Integer hombres, Integer mujeres) {
        this.codigoProvincia = codigoProvincia;
        this.codigoMunicipio = codigoMunicipio;
        this.nombreMunicipio = nombreMunicipio;
        this.totalHabitantes = totalHabitantes;
        this.hombres = hombres;
        this.mujeres = mujeres;
    }

    public Municipio() {
    }

    public int getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(int codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public int getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(int codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public Integer getTotalHabitantes() {
        return totalHabitantes;
    }

    public void setTotalHabitantes(Integer totalHabitantes) {
        this.totalHabitantes = totalHabitantes;
    }

    public Integer getHombres() {
        return hombres;
    }

    public void setHombres(Integer hombres) {
        this.hombres = hombres;
    }

    public Integer getMujeres() {
        return mujeres;
    }

    public void setMujeres(Integer mujeres) {
        this.mujeres = mujeres;
    }

    @Override
    public String toString() {
        return nombreMunicipio;
    }
}
