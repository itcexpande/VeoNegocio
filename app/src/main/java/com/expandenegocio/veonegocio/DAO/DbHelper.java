package com.expandenegocio.veonegocio.DAO;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Penlopjo on 31/03/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    // public static final String NOMBREBD = Environment.getRootDirectory()+ File.separator+"Solicitudes.db";
    /*
    public static final String NOMBREBD = Environment.getExternalStorageDirectory()
           .getAbsolutePath() + "/Pictures/Solicitudes.db";
*/
    private static final String TABLA_PROVINCIAS = "provincias";
    private static final String TABLA_MUNICIPIOS = "municipios";
    private static final String TABLA_USUARIOS = "users";

    //private static String DB_PATH = "/data/data/es.veonegocio.veonegocio/databases/";
    private static String DB_NAME = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Pictures/VeoNegocio.db";
    //private SQLiteDatabase myDataBase;
    // private final Context myContext;

/*
    public static final String NOMBREBD = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Pictures/VeoNegocio.db";

  */

    public static final String NOMBREBD = "VeoNegocio.db";

    private static final int BD_VERSION = 3;

    private static final String BD_CREAR_MUNICIPIOS = "CREATE TABLE municipios " +
            "( PK_UID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "c_prov integer," +
            "c_mun integer ," +
            "d_mun text," +
            "total_habitantes integer ," +
            "total_hombres integer ," +
            "total_mujeres integer )";

    private static final String BD_CREAR_PROVINCIAS = "CREATE TABLE provincias " +
            "( PK_UID integer primary key autoincrement," +
            "c_prov integer," +
            "d_prov text)";


    private static final String BD_CREAR_USERS = "CREATE TABLE municipios " +
            "ID varchar(36) PRIMARY KEY NOT NULL," +
            "email varchar(100) NOT NULL," +
            "password varchar(100) NOT NULL," +
            "status  text NOT NULL," +
            "nombre varchar(100) NOT NULL," +
            "apellidos varchar(100) NOT NULL," +
            "c_prov  int(11) DEFAULT NULL," +
            "c_mun  int(11) DEFAULT NULL," +
            "capital  varchar(100) DEFAULT NULL," +
            "capital_observaciones varchar(255) DEFAULT NULL," +
            "cerrada  tinyint(4) DEFAULT NULL," +
            "cuando_empezar  varchar(100) DEFAULT NULL," +
            "date_entered  text," +
            "date_modified text," +
            "deleted  tinyint(4) DEFAULT NULL," +
            "disp_contacto  varchar(100) DEFAULT NULL," +
            "dispone_local  tinyint(4) DEFAULT NULL," +
            "empresa  varchar(255) DEFAULT NULL," +
            "negocio  varchar(255) DEFAULT NULL," +
            "negocio_antes  tinyint(4) DEFAULT NULL," +
            "perfil_franquicia  varchar(100) DEFAULT NULL," +
            "perfil_profesional  text CHARACTER," +
            "phone_home  varchar(20) DEFAULT NULL," +
            "phone_mobile varchar(20) DEFAULT NULL," +
            "recursos_propios  varchar(100) DEFAULT NULL," +
            "situacion_profesional  varchar(100) DEFAULT NULL)";


    public DbHelper(Context context) {
        super(context, NOMBREBD, null, BD_VERSION);
        SQLiteDatabase.openOrCreateDatabase(NOMBREBD, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear la base de datos
        try {
            //db.execSQL(DataSource.CREATE_SOLICITUDES_SCRIPT);
            db.execSQL(BD_CREAR_USERS);
            db.execSQL(BD_CREAR_PROVINCIAS);
            db.execSQL(BD_CREAR_MUNICIPIOS);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Actualizar la base de datos
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PROVINCIAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_MUNICIPIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);

/*

        db.execSQL(BD_CREAR_PROVINCIAS);
        db.execSQL(BD_CREAR_MUNICIPIOS);
        db.execSQL(BD_CREAR_USERS);

*/

        onCreate(db);
    }
}
