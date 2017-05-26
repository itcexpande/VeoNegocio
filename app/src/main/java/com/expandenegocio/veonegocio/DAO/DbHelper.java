package com.expandenegocio.veonegocio.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;


import com.expandenegocio.veonegocio.models.Municipio;
import com.expandenegocio.veonegocio.models.Provincia;

import java.util.ArrayList;

/**
 * Created by Penlopjo on 31/03/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    // public static final String NOMBREBD = Environment.getRootDirectory()+ File.separator+"Solicitudes.db";

    //private static String DB_PATH = "/data/data/es.veonegocio.veonegocio/databases/";

    //    public static final String NOMBREBD = "VeoNegocio.db";
    /*private static String NOMBREBD = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Pictures/VeoNegocio2.db";
*/

    private static final String TABLA_PROVINCIAS = "provincias";
    private static final String TABLA_MUNICIPIOS = "municipios";
    private static final String TABLA_USUARIOS = "users";


    private static final int BD_VERSION = 1;
    private static String NOMBREBD = "VeoNegocio.db";

    private static final String BD_CREAR_PROVINCIAS = "CREATE TABLE provincias " +
            "( PK_UID integer primary key autoincrement," +
            ProvinciaDataSource.ColumnProvincia.ID + " integer," +
            ProvinciaDataSource.ColumnProvincia.NOMBRE + " text)";

    private static final String BD_CREAR_MUNICIPIOS = "CREATE TABLE municipios" +
            "( PK_UID INTEGER PRIMARY KEY AUTOINCREMENT," +
            MunicipioDataSource.ColumnMunicipio.CODIGO_PROVINCIA + " INTEGER," +
            MunicipioDataSource.ColumnMunicipio.CODIGO_MUNICIPIO + " INTEGER," +
            MunicipioDataSource.ColumnMunicipio.NOMBRE_MUNICIPIO + " TEXT," +
            MunicipioDataSource.ColumnMunicipio.TOTAL_HABITANTES + " INTEGER," +
            MunicipioDataSource.ColumnMunicipio.TOTAL_HOMBRES + " INTEGER," +
            MunicipioDataSource.ColumnMunicipio.TOTAL_MUJERES + " INTEGER )";


    private static final String BD_CREAR_USERS = "CREATE TABLE users (" +
            UserDataSource.ColumnUsuarios.ID + " text PRIMARY KEY NOT NULL," +
            UserDataSource.ColumnUsuarios.EMAIL + " text NOT NULL," +
            UserDataSource.ColumnUsuarios.PASSWORD + " text NOT NULL," +
            UserDataSource.ColumnUsuarios.STATUS + "  text NOT NULL," +
            UserDataSource.ColumnUsuarios.NOMBRE + " text NOT NULL," +
            UserDataSource.ColumnUsuarios.APELLIDOS + " text NOT NULL," +
            UserDataSource.ColumnUsuarios.CODIGO_PROVINCIA + " int(11) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.CODIGO_MUNICIPIO + " int(11) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.CAPITAL + " text DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.CAPITAL_OBSERVACIONES + " text DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.CERRADA + " tinyint(4) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.CUANDO_EMPEZAR + " text DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.DATE_ENTERED + " text," +
            UserDataSource.ColumnUsuarios.DATE_MODIFIED + " text," +
            UserDataSource.ColumnUsuarios.DELETED + " tinyint(4) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.DISP_CONTACTO + " text DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.DISP_LOCAL + " tinyint(4) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.EMPRESA + " text DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.NEGOCIO + " text DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.NEGOCIO_ANTES + " tinyint(4) DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.PERFIL_FRANQUICIA + " text DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.PERFIL_PROFESIONAL + " text DEFAUKLT NULL," +
            UserDataSource.ColumnUsuarios.PHONE_HOME + " text DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.PHONE_MOBILE + " text DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.RECURSOS_PROPIOS + "  text DEFAULT NULL," +
            UserDataSource.ColumnUsuarios.SITUACION_PROFESIONAL + " text DEFAULT NULL)";


    public DbHelper(Context context) {
        super(context, NOMBREBD, null, BD_VERSION);
//        SQLiteDatabase.openOrCreateDatabase(NOMBREBD, null);

    }

    // Método invocado por Android si no existe la TABLA
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BD_CREAR_PROVINCIAS);
        db.execSQL(BD_CREAR_MUNICIPIOS);
        db.execSQL(BD_CREAR_USERS);
    }

    // Método invocado por Android si hay un cambio de versión de la BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminamos la tabla y la volvemos a crear otra vez
        /*
        db.execSQL("DROP TABLE IF EXISTS provincias");
        db.execSQL("DROP TABLE IF EXISTS municipios");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
*/

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminamos la tabla y la volvemos a crear otra vez
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PROVINCIAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_MUNICIPIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);

        onCreate(db);
    }

    public void insertarProvincia(int id, String nom) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put(ProvinciaDataSource.ColumnProvincia.ID, id);
            valores.put(ProvinciaDataSource.ColumnProvincia.NOMBRE, nom);
            db.insert(TABLA_PROVINCIAS, null, valores);
            db.close();
        }
    }

    public ArrayList<Provincia> getProvincias2() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Provincia> output = new ArrayList<Provincia>();
        try {
            String query = "SELECT " + "c_prov" + "," +
                    "d_prov" + " " +
                    " FROM " + "provincias" +
                    " WHERE " + "c_prov" + " > 0" +
                    " ORDER BY " + "d_prov";

            query = "SELECT " + ProvinciaDataSource.ColumnProvincia.ID + "," +
                    ProvinciaDataSource.ColumnProvincia.NOMBRE +
                    " FROM " + TABLA_PROVINCIAS +
                    " WHERE " + ProvinciaDataSource.ColumnProvincia.ID + " > 0" +
                    " ORDER BY " +
                    "Replace(" + ProvinciaDataSource.ColumnProvincia.NOMBRE + ",'Á','A')" +
                    " and Replace(" + ProvinciaDataSource.ColumnProvincia.NOMBRE + ",'É','E')" +
                    " and Replace(" + ProvinciaDataSource.ColumnProvincia.NOMBRE + ",'Í','I')" +
                    " and Replace(" + ProvinciaDataSource.ColumnProvincia.NOMBRE + ",'Ó','O')" +
                    " and Replace(" + ProvinciaDataSource.ColumnProvincia.NOMBRE + ",'Ú','U')" +
                    " and Replace(" + ProvinciaDataSource.ColumnProvincia.NOMBRE + ",'á','a')" +
                    " and Replace(" + ProvinciaDataSource.ColumnProvincia.NOMBRE + ",'e','e')" +
                    " and Replace(" + ProvinciaDataSource.ColumnProvincia.NOMBRE + ",'í','i')" +
                    " and Replace(" + ProvinciaDataSource.ColumnProvincia.NOMBRE + ",'ó','o')" +
                    " and Replace(" + ProvinciaDataSource.ColumnProvincia.NOMBRE + ",'ú','u')";


            Cursor cursor = db.rawQuery(query, null);

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

    /*
        public Provincia recuperarUnaProvincia(int id) {
            SQLiteDatabase db = getReadableDatabase();
            String[] valores_recuperar = {ProvinciaDataSource.ColumnProvincia.ID,
                    ProvinciaDataSource.ColumnProvincia.NOMBRE};
            Cursor c = db.query(TABLA_PROVINCIAS, valores_recuperar, "c_prov=" + id,
                    null, null, null, null, null);
            if (c != null) {
                c.moveToFirst();
            }
            Provincia provincia = new Provincia(c.getInt(0), c.getString(1));
            db.close();
            c.close();
            return provincia;
        }


        public ArrayList<Provincia> recuperarTodasLasProvincias() {
            SQLiteDatabase db = getReadableDatabase();
            ArrayList<Provincia> output = new ArrayList<Provincia>();
            try {
                String query = "SELECT " + ProvinciaDataSource.ColumnProvincia.ID + "," +
                        ProvinciaDataSource.ColumnProvincia.NOMBRE + " " +
                        " FROM " + TABLA_PROVINCIAS +
                        " WHERE " + ProvinciaDataSource.ColumnProvincia.ID + " > 0" +
                        " ORDER BY " + ProvinciaDataSource.ColumnProvincia.NOMBRE;

                Cursor cursor = db.rawQuery(query, null);

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

        public ArrayList<Provincia> recuperarTodasLasProvincias2() {
            SQLiteDatabase db = getReadableDatabase();
            ArrayList<Provincia> output = new ArrayList<Provincia>();
            String[] valores_recuperar = {"c_prov", "d_prov"};
            Cursor c = db.query("provincias", valores_recuperar,
                    null, null, null, null, null, null);
            c.moveToFirst();
            do {
                Provincia provincia = new Provincia(c.getInt(0), c.getString(1));
                output.add(provincia);
            } while (c.moveToNext());
            db.close();
            c.close();
            return output;
        }
    */

    public void insertarMunicipo(int idp, int idm, String nom, int tha, int tho, int tmu) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("c_prov", idp);
            valores.put("c_mun", idm);
            valores.put("d_mun", nom);
            valores.put("total_habitantes", tha);
            valores.put("total_hombres", tho);
            valores.put("total_mujeres", tmu);
            db.insert("municipios", null, valores);
            db.close();
        }
    }


    public ArrayList<Municipio> devuelveMunicipios(int numero) {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Municipio> output = new ArrayList<Municipio>();

        try {

            String query = "SELECT " +

                    MunicipioDataSource.ColumnMunicipio.CODIGO_PROVINCIA + "," +
                    MunicipioDataSource.ColumnMunicipio.CODIGO_MUNICIPIO + "," +
                    MunicipioDataSource.ColumnMunicipio.NOMBRE_MUNICIPIO + "," +
                    MunicipioDataSource.ColumnMunicipio.TOTAL_HABITANTES + "," +
                    MunicipioDataSource.ColumnMunicipio.TOTAL_HOMBRES + "," +
                    MunicipioDataSource.ColumnMunicipio.TOTAL_MUJERES +
                    " FROM " + TABLA_MUNICIPIOS +
                    " WHERE " + MunicipioDataSource.ColumnMunicipio.CODIGO_PROVINCIA + "=" + numero +
                    " ORDER BY " + MunicipioDataSource.ColumnMunicipio.NOMBRE_MUNICIPIO;

            Cursor cursor = db.rawQuery(query, null);

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

    public ArrayList<Municipio> devuelveMunicipiosPorCodigOProvinciaYPrimeraLetra(int id, String letraSeleccionda) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Municipio> output = new ArrayList<Municipio>();
        try {
            String query = "SELECT " +
                    "c_prov" + "," +
                    "c_mun" + "," +
                    "d_mun" + "," +
                    "total_habitantes" + "," +
                    "total_hombres" + "," +
                    "total_mujeres" +
                    " FROM  " + "municipios" +
                    " WHERE " + "c_prov" + "=" + id +
                    " ORDER BY " + "d_mun";

            query = "SELECT " +
                    "c_prov" + "," +
                    "c_mun" + "," +
                    "d_mun" + "," +
                    "total_habitantes" + "," +
                    "total_hombres" + "," +
                    "total_mujeres" +
                    " FROM  " + "municipios" +
                    " WHERE " + "c_prov" + "=" + id +
                    " AND d_mun like '" + letraSeleccionda + "%'" +
                    " ORDER BY " + "d_mun";

            Cursor cursor = db.rawQuery(query, null);

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
