package com.expandenegocio.veonegocio.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

/**
 * Created by Penlopjo on 31/03/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    // public static final String NOMBREBD = Environment.getRootDirectory()+ File.separator+"Solicitudes.db";
    /*
    public static final String NOMBREBD = Environment.getExternalStorageDirectory()
           .getAbsolutePath() + "/Pictures/Solicitudes.db";
*/


    public static final String NOMBREBD = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Pictures/VeoNegocio.db";

    private static final String BD_CREAR_PROVINCIAS = "CREATE TABLE provincias " +
            "(PK_UID INTEGER PRIMARY KEY AUTOINCREMENT , " +
            "c_prov INTEGER," +
            " d_prov TEXT) ";

    private static final String BD_CREAR_MUNICIPIOS = "CREATE TABLE municipios " +
            "(PK_UID INTEGER PRIMARY KEY AUTOINCREMENT , " +
            "c_prov INTEGER," +
            "c_mun INTEGER," +
            "d_mun TEXT," +
            "total_habitantes INTEGER," +
            "total_hombres INTEGER," +
            "total_mujeres) ";

    public static final String BD_INSERTAR_PROVINCIAS=
            "INSERT " +
                    "" +
                    "" +
                    "";


    public DbHelper(Context context) {
        super(context, NOMBREBD, null, 3);
        SQLiteDatabase.openOrCreateDatabase(NOMBREBD, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear la base de datos
        try {
            //db.execSQL(DataSource.CREATE_SOLICITUDES_SCRIPT);
            db.execSQL(BD_CREAR_PROVINCIAS);
            db.execSQL(BD_CREAR_MUNICIPIOS);
        } catch (Exception ex) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Actualizar la base de datos
    }
}
