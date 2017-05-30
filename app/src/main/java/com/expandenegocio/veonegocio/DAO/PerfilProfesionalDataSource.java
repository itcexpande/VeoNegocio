package com.expandenegocio.veonegocio.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.expandenegocio.veonegocio.models.CuandoEmpezar;
import com.expandenegocio.veonegocio.models.PerfilProfesional;

import java.util.ArrayList;

/**
 * Created by jesus on 05/04/2017.
 */

public class PerfilProfesionalDataSource {

    public static final String PERFIL_PROFESIONAL_TABLE_NAME = "expan_m_perfil_fran";


    //Campos de la tabla provincias
    public static class ColumnPerfilProfesional {
        public static final String ID = "id";
        public static final String LITERAL = "nombre";

    }


    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private Context contexto;

    public PerfilProfesionalDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
        // this.contexto= context;
    }

    public ArrayList<PerfilProfesional> getPerfilProfesional() {
        ArrayList<PerfilProfesional> output = new ArrayList<PerfilProfesional>();
        try {
            String query = "SELECT " + ColumnPerfilProfesional.ID + "," +
                    ColumnPerfilProfesional.LITERAL + " " +
                    " FROM " + PERFIL_PROFESIONAL_TABLE_NAME +
                    " ORDER BY " + ColumnPerfilProfesional.ID;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    PerfilProfesional perfil = new PerfilProfesional();
                    perfil.setId(Integer.parseInt(cursor.getString(0)));
                    perfil.setNombre(cursor.getString(1));
                    // Add book to books
                    output.add(perfil);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error getPerfilProfesional", ex.toString());
        }

        return output;
    }


}