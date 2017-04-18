package com.expandenegocio.veonegocio.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.expandenegocio.veonegocio.models.Municipio;
import com.expandenegocio.veonegocio.models.Provincia;

import java.util.ArrayList;

/**
 * Created by jesus on 05/04/2017.
 */

public class MunicipioDataSource {
    public static final String MUNICIPIO_TABLE_NAME = "municipios";

    //Campos de la tabla municipios
    public static class ColumnMunicipio {
        public static final String CODIGO_MUNICIPIO = "c_mun";
        public static final String NOMBRE_PROVINCIA = "d_prov";
        public static final String NOMBRE_MUNICIPIO = "d_mun";
    }

    //Campos de la tabla provincias
    public static class ColumnProvincia {
        public static final String ID = "c_prov";
        public static final String NOMBRE = "d_prov";
    }

    private DbHelper dbHelper;
    private SQLiteDatabase database;


    public MunicipioDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public ArrayList<Municipio> getMunicipios(String provincia) {

        ArrayList<Municipio> output = new ArrayList<Municipio>();

        try {

            String query = "SELECT " +
                    ColumnMunicipio.CODIGO_MUNICIPIO + "," +
                    ColumnMunicipio.NOMBRE_MUNICIPIO + "," +
                    ColumnMunicipio.NOMBRE_PROVINCIA +
                    " FROM " + MUNICIPIO_TABLE_NAME +
                    " WHERE " + ColumnMunicipio.NOMBRE_PROVINCIA + "=" + provincia +
                    " ORDER BY " + ColumnMunicipio.NOMBRE_MUNICIPIO;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Municipio municipio = new Municipio();
                    municipio.setCodigoMunicipio(cursor.getInt(0));
                    municipio.setNombreMunicipio(cursor.getString(1));
                    Provincia provincia1= new Provincia();
                    provincia1.setId(0);
                    provincia1.setNombreProvincia(cursor.getString(2));
                    municipio.setProvincia(provincia1);
                    output.add(municipio);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error getMunicipios", ex.toString());
        }

        return output;
    }
}