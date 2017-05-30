package com.expandenegocio.veonegocio.models;

/**
 * Created by jesus on 30/05/2017.
 */

public class Sector {
    private Integer cId;
    private String cGrupoAct;
    private Integer mOrdenAct;
    private String cSector;
    private Integer mOrdenSector;
    private String dSubSector;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcGrupoAct() {
        return cGrupoAct;
    }

    public void setcGrupoAct(String cGrupoAct) {
        this.cGrupoAct = cGrupoAct;
    }

    public Integer getmOrdenAct() {
        return mOrdenAct;
    }

    public void setmOrdenAct(Integer mOrdenAct) {
        this.mOrdenAct = mOrdenAct;
    }

    public String getcSector() {
        return cSector;
    }

    public void setcSector(String cSector) {
        this.cSector = cSector;
    }

    public Integer getmOrdenSector() {
        return mOrdenSector;
    }

    public void setmOrdenSector(Integer mOrdenSector) {
        this.mOrdenSector = mOrdenSector;
    }

    public String getdSubSector() {
        return dSubSector;
    }

    public void setdSubSector(String dSubSector) {
        this.dSubSector = dSubSector;
    }

    @Override
    public String toString() {
        return cGrupoAct;
    }
}