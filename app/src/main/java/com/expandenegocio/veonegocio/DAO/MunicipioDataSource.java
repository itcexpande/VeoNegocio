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
        public static final String CODIGO_PROVINCIA = "c_prov";
        public static final String CODIGO_MUNICIPIO = "c_mun";
        public static final String NOMBRE_MUNICIPIO = "d_mun";
        public static final String TOTAL_HABITANTES = "total_habitantes";
        public static final String TOTAL_HOMBRES = "total_hombres";
        public static final String TOTAL_MUJERES = "total_mujeres";
    }

 /*   //Campos de la tabla provincias
    public static class ColumnProvincia {
        public static final String ID = "c_prov";
        public static final String NOMBRE = "d_prov";
    }*/

    private DbHelper dbHelper;
    private SQLiteDatabase database;


    public MunicipioDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public ArrayList<Municipio> getMunicipios(int numero) {

        ArrayList<Municipio> output = new ArrayList<Municipio>();

        try {

            String query = "SELECT " +

                    ColumnMunicipio.CODIGO_PROVINCIA + "," +
                    ColumnMunicipio.CODIGO_MUNICIPIO + "," +
                    ColumnMunicipio.NOMBRE_MUNICIPIO + "," +
                    ColumnMunicipio.TOTAL_HABITANTES + "," +
                    ColumnMunicipio.TOTAL_HOMBRES + "," +
                    ColumnMunicipio.TOTAL_MUJERES +
                    " FROM " + MUNICIPIO_TABLE_NAME +
                    " WHERE " + ColumnMunicipio.CODIGO_PROVINCIA + "=" + numero +
                    " ORDER BY " + ColumnMunicipio.NOMBRE_MUNICIPIO;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Municipio municipio = new Municipio();
                    municipio.setCodigoProvincia(cursor.getInt(0));
                    municipio.setCodigoMunicipio(cursor.getInt(1));
                    municipio.setNombreMunicipio(cursor.getString(2));
                    municipio.setTotalHabitantes(cursor.getInt(3));
                    municipio.setHombres(cursor.getInt(4));
                    municipio.setMujeres(cursor.getInt(5));
                    output.add(municipio);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error getMunicipios", ex.toString());
        }

        return output;
    }
}