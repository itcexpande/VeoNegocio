package com.expandenegocio.veonegocio.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.expandenegocio.veonegocio.models.CuandoEmpezar;

import java.util.ArrayList;

/**
 * Created by jesus on 05/04/2017.
 */

public class CuandoEmpezarDataSource {

    public static final String CUANDO_EMPEZAR_TABLE_NAME = "expan_m_cuando_empezar";


    //Campos de la tabla provincias
    public static class ColumnCuandoEmpezar {
        public static final String ID = "id";
        public static final String NOMBRE = "nombre";

    }


    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private Context contexto;

    public CuandoEmpezarDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
        // this.contexto= context;
    }

    public ArrayList<CuandoEmpezar> getCuandoEmpezar() {
        ArrayList<CuandoEmpezar> output = new ArrayList<CuandoEmpezar>();
        try {
            String query = "SELECT " + ColumnCuandoEmpezar.ID + "," +
                    ColumnCuandoEmpezar.NOMBRE + " " +
                    " FROM " + CUANDO_EMPEZAR_TABLE_NAME +
                    " ORDER BY " + ColumnCuandoEmpezar.ID;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    CuandoEmpezar cuandoEmpezar = new CuandoEmpezar();
                    cuandoEmpezar.setId(Integer.parseInt(cursor.getString(0)));
                    cuandoEmpezar.setNombre(cursor.getString(1));
                    // Add book to books
                    output.add(cuandoEmpezar);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error getCuandoEmpezar", ex.toString());
        }

        return output;
    }


}