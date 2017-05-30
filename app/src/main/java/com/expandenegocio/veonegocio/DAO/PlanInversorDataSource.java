package com.expandenegocio.veonegocio.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.expandenegocio.veonegocio.models.PlanInversor;

import java.util.ArrayList;

/**
 * Created by jesus on 05/04/2017.
 */

public class PlanInversorDataSource {

    public static final String PLAN_INVERSOR_TABLE_NAME = "expan_m_perfil_fran";


    //Campos de la tabla provincias
    public static class ColumnPlanInversor {
        public static final String ID = "id";
        public static final String LITERAL = "nombre";
    }


    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private Context contexto;

    public PlanInversorDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
        // this.contexto= context;
    }


    public ArrayList<PlanInversor> getPlanInversor() {

        ArrayList<PlanInversor> output = new ArrayList<PlanInversor>();

        try {

            String query = "SELECT " + ColumnPlanInversor.ID + "," +
                    ColumnPlanInversor.LITERAL + " " +
                    " FROM " + PLAN_INVERSOR_TABLE_NAME +
                    " ORDER BY " + ColumnPlanInversor.ID;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    PlanInversor plan = new PlanInversor();
                    plan.setId(Integer.parseInt(cursor.getString(0)));
                    plan.setNombre(cursor.getString(1));

                    // Add book to books
                    output.add(plan);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error getPlanInversor", ex.toString());
        }

        return output;
    }


}