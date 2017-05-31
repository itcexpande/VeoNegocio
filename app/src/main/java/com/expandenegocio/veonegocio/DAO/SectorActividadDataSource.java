package com.expandenegocio.veonegocio.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.expandenegocio.veonegocio.models.Sector;

import java.util.ArrayList;

/**
 * Created by jesus on 05/04/2017.
 */

public class SectorActividadDataSource {

    public static final String SECTOR_ACTIVIDAD_TABLE_NAME = "expan_m_sectores";


    //Campos de la tabla provincias
    public static class ColumnSector {
        public static final String C_ID = "c_id";
        public static final String C_GRUPO_ACT = "c_grupo_act";
        public static final String M_ORDEN_ACT = "m_orden_act";
        public static final String C_SECTOR = "c_sector";
        public static final String M_ORDEN_SECTOR = "m_orden_sector";
        public static final String D_SUBSECTOR = "d_subsector";

    }


    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private Context contexto;

    public SectorActividadDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
        // this.contexto= context;
    }


    public ArrayList<Sector> getSectores() {

        ArrayList<Sector> output = new ArrayList<Sector>();

        try {

            String query = "SELECT " + ColumnSector.C_ID + "," +
                    ColumnSector.C_GRUPO_ACT + "," +
                    ColumnSector.M_ORDEN_ACT + "," +
                    ColumnSector.C_SECTOR + "," +
                    ColumnSector.M_ORDEN_SECTOR + "," +
                    ColumnSector.D_SUBSECTOR +
                    " FROM " + SECTOR_ACTIVIDAD_TABLE_NAME +
                    " ORDER BY " + ColumnSector.C_ID;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Sector sector = new Sector();
                    sector.setcId(Integer.parseInt(cursor.getString(0)));
                    sector.setcGrupoAct(cursor.getString(1));
                    sector.setmOrdenAct(Integer.parseInt(cursor.getString(2)));
                    sector.setcSector(cursor.getString(3));
                    sector.setmOrdenSector(Integer.parseInt(cursor.getString(4)));
                    sector.setdSubSector(cursor.getString(5));

                    // Add book to books
                    output.add(sector);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error getSectores Actividad", ex.toString());
        }

        return output;
    }


}