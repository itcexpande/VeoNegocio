package com.expandenegocio.veonegocio.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.expandenegocio.veonegocio.models.Provincia;

import java.util.ArrayList;

/**
 * Created by jesus on 05/04/2017.
 */

public class ProvinciaDataSource {

    public static final String PROVINCIA_TABLE_NAME = "provincias";


    //Campos de la tabla provincias
    public static class ColumnProvincia {
        public static final String ID = "c_prov";
        public static final String NOMBRE = "d_prov";
    }


    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private Context contexto;

    public ProvinciaDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
        // this.contexto= context;
    }


    public void insertProvincia(Provincia provincia) {

        String insertSQL = "Insert into " + PROVINCIA_TABLE_NAME +
                "(" +
                ColumnProvincia.ID + "," +
                ColumnProvincia.NOMBRE +
                ") VALUES ( " + provincia.getId() + ",'" + provincia.getNombreProvincia() + "' )";
        try {
            database.execSQL(insertSQL);
        } catch (Exception ex) {
            Log.d("Error insertar provincia", ex.toString());
        }
    }

    public ArrayList<Provincia> getProvincias() {

        ArrayList<Provincia> output = new ArrayList<Provincia>();

        try {

            String query = "SELECT " + ColumnProvincia.ID + "," +
                    ColumnProvincia.NOMBRE +
                    " FROM " + PROVINCIA_TABLE_NAME +
                    " ORDER BY " +
                    "Replace(" + ColumnProvincia.NOMBRE + ",'Á','A')";


            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Provincia provincia = new Provincia();
                    provincia.setId(Integer.parseInt(cursor.getString(0)));
                    provincia.setNombreProvincia(cursor.getString(1));

                    // Add book to books
                    output.add(provincia);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error getProvincias", ex.toString());
        }

        return output;
    }

    public String buscarProvinciaPorId(String id) {

        String output = "";
        try {

            String query = "SELECT " + ColumnProvincia.NOMBRE +
                    " FROM " + PROVINCIA_TABLE_NAME +
                    " WHERE " + ColumnProvincia.ID + " = '" + id + "'";

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    output = cursor.getString(0);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error recoge Usuario", ex.toString());
        }
        return output;
    }

}