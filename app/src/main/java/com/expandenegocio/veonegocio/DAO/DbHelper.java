package com.expandenegocio.veonegocio.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.expandenegocio.veonegocio.models.Provincia;

/**
 * Created by Penlopjo on 31/03/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    // public static final String NOMBREBD = Environment.getRootDirectory()+ File.separator+"Solicitudes.db";
    /*
    public static final String NOMBREBD = Environment.getExternalStorageDirectory()
           .getAbsolutePath() + "/Pictures/Solicitudes.db";
*/
    //private static String DB_PATH = "/data/data/es.veonegocio.veonegocio/databases/";
    /*
    private static String NOMBREBD = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Pictures/VeoNegocio.db";
            */
    //private SQLiteDatabase myDataBase;
    // private final Context myContext;

/*
    public static final String NOMBREBD = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Pictures/VeoNegocio.db";

  */

//    public static final String NOMBREBD = "VeoNegocio.db";
//    private static String NOMBREBD = Environment.getExternalStorageDirectory()
//            .getAbsolutePath() + "/Pictures/VeoNegocio.db";


    private static final String TABLA_PROVINCIAS = "provincias";
    private static final String TABLA_MUNICIPIOS = "municipios";
    private static final String TABLA_USUARIOS = "users";

    private static final int BD_VERSION = 1;
    private static String NOMBREBD = "VeoNegocio.db";


    private static final String BD_CREAR_PROVINCIAS = "CREATE TABLE provincias" +
            "( PK_UID integer primary key autoincrement," +
            ProvinciaDataSource.ColumnProvincia.ID + " integer," +
            ProvinciaDataSource.ColumnProvincia.NOMBRE + " text)";

    private static final String BD_CREAR_MUNICIPIOS = "CREATE TABLE " + TABLA_MUNICIPIOS +
            "( PK_UID INTEGER PRIMARY KEY AUTOINCREMENT," +
            MunicipioDataSource.ColumnMunicipio.CODIGO_PROVINCIA + " INTEGER," +
            MunicipioDataSource.ColumnMunicipio.CODIGO_MUNICIPIO + " INTEGER," +
            MunicipioDataSource.ColumnMunicipio.NOMBRE_MUNICIPIO + " TEXT," +
            MunicipioDataSource.ColumnMunicipio.TOTAL_HABITANTES + " INTEGER," +
            MunicipioDataSource.ColumnMunicipio.TOTAL_HOMBRES + " INTEGER," +
            MunicipioDataSource.ColumnMunicipio.TOTAL_MUJERES + " INTEGER )";


    private static final String BD_CREAR_USERS = "CREATE TABLE " + TABLA_USUARIOS +
            UserDataSource.ColumnUsuarios.ID + " varchar(36) PRIMARY KEY NOT NULL," +
            UserDataSource.ColumnUsuarios.EMAIL + " varchar(100) NOT NULL," +
            UserDataSource.ColumnUsuarios.PASSWORD + " varchar(100) NOT NULL," +
            UserDataSource.ColumnUsuarios.STATUS + "  text NOT NULL," +
            UserDataSource.ColumnUsuarios.NOMBRE + " varchar(100) NOT NULL," +
            UserDataSource.ColumnUsuarios.APELLIDOS + " varchar(100) NOT NULL," +
            UserDataSource.ColumnUsuarios.CODIGO_PROVINCIA + " int(11) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.CODIGO_MUNICIPIO + " int(11) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.CAPITAL + " varchar(100) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.CAPITAL_OBSERVACIONES + " varchar(255) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.CERRADA + " tinyint(4) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.CUANDO_EMPEZAR + "  varchar(100) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.DATE_ENTERED + "  text," +
            UserDataSource.ColumnUsuarios.DATE_MODIFIED + " text," +
            UserDataSource.ColumnUsuarios.DELETED + " tinyint(4) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.DISP_CONTACTO + "  varchar(100) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.DISP_LOCAL + " tinyint(4) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.EMPRESA + " varchar(255) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.NEGOCIO + " varchar(255) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.NEGOCIO_ANTES + " tinyint(4) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.PERFIL_FRANQUICIA + " varchar(100) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.PERFIL_PROFESIONAL + "  text CHARACTER," +
            UserDataSource.ColumnUsuarios.PHONE_HOME + " varchar(20) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.PHONE_MOBILE + " varchar(20) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.RECURSOS_PROPIOS + "  varchar(100) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.SITUACION_PROFESIONAL + "  varchar(100) DEFAULT NULL)";


    public DbHelper(Context context) {
        super(context, NOMBREBD, null, BD_VERSION);
        //   SQLiteDatabase.openOrCreateDatabase(NOMBREBD, null);
        //this.contexto = context;
    }

    // Método invocado por Android si no existe la TABLA
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BD_CREAR_PROVINCIAS);

    }

    // Método invocado por Android si hay un cambio de versión de la BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // NOTA: para simplificar este ejemplo eliminaremos directamente la tabla y la crearemos de nuevo.
        // Sin embargo, lo correcto sería migrar los datos de la tabla antigua a la nueva estructura de campos, usando por ejemplo sentencias ALTER TABLE.

        // Eliminamos la tabla y la volvemos a crear otra vez
        db.execSQL("DROP TABLE IF EXISTS provincias");
        onCreate(db);


        /*    //Actualizar la base de datos
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PROVINCIAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_MUNICIPIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);


        onCreate(db);*/
    }

    @Override
    public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // NOTA: para simplificar este ejemplo eliminaremos directamente la tabla y la crearemos de nuevo.

        // Eliminamos la tabla y la volvemos a crear otra vez
        // database.execSQL("DROP TABLE IF EXISTS " + TABLA_PROVINCIAS);
        // onCreate(database);
    }

    public void insertarProvincia(int id, String nom) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("c_prov", id);
            valores.put("d_pro", nom);
            db.insert("provincias", null, valores);
            db.close();
        }
    }

    public Provincia recuperarProvincia(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"c_prov", "d_prov"};
        Cursor c = db.query("provincias", valores_recuperar, "c_prov=" + id,
                null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        Provincia provincia = new Provincia(c.getInt(0), c.getString(1));
        db.close();
        c.close();
        return provincia;
    }

}
