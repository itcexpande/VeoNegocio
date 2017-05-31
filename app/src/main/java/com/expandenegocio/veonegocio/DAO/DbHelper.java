package com.expandenegocio.veonegocio.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.expandenegocio.veonegocio.models.CuandoEmpezar;
import com.expandenegocio.veonegocio.models.Municipio;
import com.expandenegocio.veonegocio.models.PlanInversor;
import com.expandenegocio.veonegocio.models.Provincia;
import com.expandenegocio.veonegocio.models.Sector;
import com.expandenegocio.veonegocio.models.User;

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
    private static final String TABLA_SECTOR_ACTIVIDAD = "expan_m_sectores";
    private static final String TABLA_PLAN_INVERSOR = "expan_m_capital";
    private static final String TABLA_CUANDO_EMPEZAR = "expan_m_cuando_empezar";
    private static final String TABLA_PERFIL_PROFESIONAL = "expan_m_perfil_fran";


    private static final int BD_VERSION = 1;
    private static String NOMBREBD = "VeoNegocio.db";

    private static final String BD_CREAR_PROVINCIAS = "CREATE TABLE " + TABLA_PROVINCIAS +
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


    private static final String BD_CREAR_USERS = "CREATE TABLE " + TABLA_USUARIOS + "(" +
            UserDataSource.ColumnUsuarios.ID + " text PRIMARY KEY NOT NULL," +
            UserDataSource.ColumnUsuarios.EMAIL + " text NOT NULL," +
            UserDataSource.ColumnUsuarios.PASSWORD + " text NOT NULL," +
            UserDataSource.ColumnUsuarios.STATUS + "  text NOT NULL," +
            UserDataSource.ColumnUsuarios.NOMBRE + " text NOT NULL," +
            UserDataSource.ColumnUsuarios.APELLIDOS + " text NOT NULL," +
            UserDataSource.ColumnUsuarios.TELEFONO + " text NOT NULL," +
            UserDataSource.ColumnUsuarios.CODIGO_PROVINCIA + " int(11) NOT NULL," +
            UserDataSource.ColumnUsuarios.SECTOR_ACTIVIDAD + " text not NULL," +
            UserDataSource.ColumnUsuarios.PLAN_INVERSION + " text not NULL," +
            UserDataSource.ColumnUsuarios.CUANDO_EMPEZAR + " text not NULL," +
            UserDataSource.ColumnUsuarios.PERFIL_PROFESIONAL + " text not NULL" + ")";

    private static final String BD_CREAR_SECTOR_ACTIVIDAD = "CREATE TABLE " + TABLA_SECTOR_ACTIVIDAD +
            "( c_id integer primary key not null default 0," +
            "c_grupo_act text default null," +
            "m_orden_act integer default null," +
            "c_sector text DEFAULT NULL," +
            "m_orden_sector integer DEFAULT NULL," +
            "d_subsector text DEFAULT NULL" +
            " )";


    private static final String BD_CREAR_PLAN_INVERSOR = "CREATE TABLE " + TABLA_PLAN_INVERSOR +
            "( id integer default null ," +
            " nombre text" +
            " )";

    private static final String BD_CREAR_CUANDO_EMPEZAR = "CREATE TABLE " + TABLA_CUANDO_EMPEZAR +
            "( id integer default null," +
            " nombre text" +
            " )";


    private static final String BD_CREAR_PERFIL_PROFESIONAL = "CREATE TABLE " + TABLA_PERFIL_PROFESIONAL +
            "( id integer not null primary key ," +
            " nombre text" +
            " )";

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
        db.execSQL(BD_CREAR_SECTOR_ACTIVIDAD);
        db.execSQL(BD_CREAR_PLAN_INVERSOR);
        db.execSQL(BD_CREAR_CUANDO_EMPEZAR);
        db.execSQL(BD_CREAR_PERFIL_PROFESIONAL);

    }

    // Método invocado por Android si hay un cambio de versión de la BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminamos la tabla y la volvemos a crear otra vez

        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PROVINCIAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_MUNICIPIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);

        db.execSQL("DROP TABLE IF EXISTS " + TABLA_SECTOR_ACTIVIDAD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PLAN_INVERSOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CUANDO_EMPEZAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PERFIL_PROFESIONAL);
        onCreate(db);


    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminamos la tabla y la volvemos a crear otra vez
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PROVINCIAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_MUNICIPIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);

        db.execSQL("DROP TABLE IF EXISTS " + TABLA_SECTOR_ACTIVIDAD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PLAN_INVERSOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CUANDO_EMPEZAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PERFIL_PROFESIONAL);

        onCreate(db);
    }

    public void insertarProvincia(int id, String nom) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put(ProvinciaDataSource.ColumnProvincia.ID, id);
            valores.put(ProvinciaDataSource.ColumnProvincia.NOMBRE, nom);
            db.insert(TABLA_PROVINCIAS, null, valores);
            //  db.close();
        }
    }

    public ArrayList<Provincia> getProvincias() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Provincia> output = new ArrayList<Provincia>();
        try {

            String query = "SELECT " + ProvinciaDataSource.ColumnProvincia.ID + "," +
                    ProvinciaDataSource.ColumnProvincia.NOMBRE +
                    " FROM " + TABLA_PROVINCIAS +
                    " ORDER BY " +
                    "Replace(" + ProvinciaDataSource.ColumnProvincia.NOMBRE + ",'Á','A')";

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

    public void closeDataBase() {
        SQLiteDatabase db = getWritableDatabase();
        db.close();

    }

    public void insertarMunicipo(int idp, int idm, String nom, int tha, int tho, int tmu) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put(MunicipioDataSource.ColumnMunicipio.CODIGO_PROVINCIA, idp);
            valores.put(MunicipioDataSource.ColumnMunicipio.CODIGO_MUNICIPIO, idm);
            valores.put(MunicipioDataSource.ColumnMunicipio.NOMBRE_MUNICIPIO, nom);
            valores.put(MunicipioDataSource.ColumnMunicipio.TOTAL_HABITANTES, tha);
            valores.put(MunicipioDataSource.ColumnMunicipio.TOTAL_HOMBRES, tho);
            valores.put(MunicipioDataSource.ColumnMunicipio.TOTAL_HOMBRES, tmu);
            db.insert(TABLA_MUNICIPIOS, null, valores);
            //db.close();
        }
    }


    public ArrayList<Municipio> devuelveMunicipiosPorCodigoProvincia(int codigoProvincia) {
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
                    " WHERE " + MunicipioDataSource.ColumnMunicipio.CODIGO_PROVINCIA + "=" + codigoProvincia +
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

    private String SQL = "";
    private ArrayList<String> listSQL;

    public void addMunicipioPoolUpdate(Municipio m) {
        if (listSQL == null)
            listSQL = new ArrayList<String>();
        SQL = " INSERT OR REPLACE INTO " + TABLA_MUNICIPIOS + " ( " +
                MunicipioDataSource.ColumnMunicipio.CODIGO_PROVINCIA + "," +
                MunicipioDataSource.ColumnMunicipio.CODIGO_MUNICIPIO + "," +
                MunicipioDataSource.ColumnMunicipio.NOMBRE_MUNICIPIO + "," +
                MunicipioDataSource.ColumnMunicipio.TOTAL_HABITANTES + "," +
                MunicipioDataSource.ColumnMunicipio.TOTAL_HOMBRES + "," +
                MunicipioDataSource.ColumnMunicipio.TOTAL_MUJERES +
                " )  VALUES (" + m.getCodigoProvincia() + "," +
                m.getCodigoMunicipio() + "," +
                "'" + ReplaceQuote(m.getNombreMunicipio()) + "'," +
                m.getTotalHabitantes() + "," +
                m.getHombres() + "," +
                m.getMujeres() + ");";

        listSQL.add(SQL);
    }

    public void commitPoolUpdate() {
        if (listSQL != null && listSQL.size() > 0) {
            SQLiteDatabase db = getWritableDatabase();
            if (db != null) {
                db.beginTransaction();
                for (int i = 0; i < listSQL.size(); i++) {
                    db.execSQL(listSQL.get(i));
                }
                db.setTransactionSuccessful();
                db.endTransaction();
                db.close();
            }
            listSQL.clear();
        }

    }

    private String ReplaceQuote(String t) {
        return t.replace("'", "''");
    }

    public void borrarProvincias() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "DELETE  FROM " + TABLA_PROVINCIAS + " WHERE 1=1";
        db.execSQL(query);
    }

    public void borrarMunicipios() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "DELETE  FROM " + TABLA_MUNICIPIOS + " WHERE 1=1";
        db.execSQL(query);
    }

    public void borrarSectoresActividad() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "DELETE  FROM " + TABLA_SECTOR_ACTIVIDAD + " WHERE 1=1";
        db.execSQL(query);
    }

    public void borrarPlanInversor() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "DELETE  FROM " + TABLA_PLAN_INVERSOR + " WHERE 1=1";
        db.execSQL(query);
    }

    public void borrarCuandoEmpezar() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "DELETE  FROM " + TABLA_CUANDO_EMPEZAR + " WHERE 1=1";
        db.execSQL(query);
    }

    public void borrarPerfilProfesional() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "DELETE  FROM " + TABLA_PERFIL_PROFESIONAL + " WHERE 1=1";
        db.execSQL(query);
    }

    String SQLs = "";
    private ArrayList<String> listSQLs;

    public void addSectoresPoolUpdate(Sector m) {
        if (listSQLs == null)
            listSQLs = new ArrayList<String>();
        SQLs = " INSERT OR REPLACE INTO " + TABLA_SECTOR_ACTIVIDAD + " ( " +
                SectorActividadDataSource.ColumnSector.C_ID + "," +
                SectorActividadDataSource.ColumnSector.C_GRUPO_ACT + "," +
                SectorActividadDataSource.ColumnSector.M_ORDEN_ACT + "," +
                SectorActividadDataSource.ColumnSector.C_SECTOR + "," +
                SectorActividadDataSource.ColumnSector.M_ORDEN_SECTOR + "," +
                SectorActividadDataSource.ColumnSector.D_SUBSECTOR +
                " )  VALUES (" +
                m.getcId() + "," +
                "'" + m.getcGrupoAct() + "'" + "," +
                m.getmOrdenAct() + "," +
                "'" + m.getcSector() + "'" + "," +
                m.getmOrdenSector() + "," +
                "'" + m.getdSubSector() + "'" +
                ");";

        listSQLs.add(SQLs);
    }

    public void commitPoolUpdateSector() {
        if (listSQLs != null && listSQLs.size() > 0) {
            SQLiteDatabase db = getWritableDatabase();
            if (db != null) {
                db.beginTransaction();
                for (int i = 0; i < listSQLs.size(); i++) {
                    db.execSQL(listSQLs.get(i));
                }
                db.setTransactionSuccessful();
                db.endTransaction();
                db.close();
            }
            listSQLs.clear();
        }

    }


    public void insertarSectorActividad(Sector s) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put(SectorActividadDataSource.ColumnSector.C_ID, s.getcId());
            valores.put(SectorActividadDataSource.ColumnSector.C_GRUPO_ACT, s.getcGrupoAct().toString());
            valores.put(SectorActividadDataSource.ColumnSector.M_ORDEN_ACT, s.getmOrdenAct());
            valores.put(SectorActividadDataSource.ColumnSector.C_SECTOR, s.getcSector().toString());
            valores.put(SectorActividadDataSource.ColumnSector.M_ORDEN_SECTOR, s.getmOrdenSector());
            valores.put(SectorActividadDataSource.ColumnSector.D_SUBSECTOR, s.getdSubSector().toString());

            db.insert(TABLA_SECTOR_ACTIVIDAD, null, valores);
            //  db.close();
            //  String query = "select *  FROM " + TABLA_SECTOR_ACTIVIDAD;
            //  db.execSQL(query);
        }
    }

    public ArrayList<Sector> getSectoresActividad() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Sector> output = new ArrayList<Sector>();
        try {

            String query = "SELECT " + SectorActividadDataSource.ColumnSector.C_ID + "," +
                    SectorActividadDataSource.ColumnSector.C_GRUPO_ACT + " " +
                    SectorActividadDataSource.ColumnSector.M_ORDEN_ACT + " " +
                    SectorActividadDataSource.ColumnSector.C_SECTOR + " " +
                    SectorActividadDataSource.ColumnSector.M_ORDEN_SECTOR + " " +
                    SectorActividadDataSource.ColumnSector.D_SUBSECTOR +
                    " FROM " + TABLA_SECTOR_ACTIVIDAD +
                    " ORDER BY " + SectorActividadDataSource.ColumnSector.C_ID;

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Sector sector = new Sector();
                    sector.setcId(Integer.parseInt(cursor.getString(0)));
                    sector.setcGrupoAct(cursor.getString(1));
                    sector.setmOrdenAct(Integer.parseInt(cursor.getString(2)));
                    sector.setcSector(cursor.getString(3));
                    sector.setmOrdenSector(Integer.parseInt(cursor.getString(4)));
                    sector.setdSubSector(cursor.getString(5));

                    // Add book to books
                    output.add(sector);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            Log.d("Error getSectores Actividad", ex.toString());
        }

        return output;
    }


    public void insertarPlanInversor(int id, String nom) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put(PlanInversorDataSource.ColumnPlanInversor.ID, id);
            valores.put(PlanInversorDataSource.ColumnPlanInversor.LITERAL, nom);
            db.insert(TABLA_PLAN_INVERSOR, null, valores);
            //  db.close();
        }
    }

    public ArrayList<PlanInversor> getPlanInversor() {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<PlanInversor> output = new ArrayList<PlanInversor>();

        try {

            String query = "SELECT " + PlanInversorDataSource.ColumnPlanInversor.ID + "," +
                    PlanInversorDataSource.ColumnPlanInversor.LITERAL + " " +
                    " FROM " + TABLA_PLAN_INVERSOR +
                    " ORDER BY " + PlanInversorDataSource.ColumnPlanInversor.ID;

            Cursor cursor = db.rawQuery(query, null);

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


    public void insertarPerfilProfesional(int id, String nom) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put(PerfilProfesionalDataSource.ColumnPerfilProfesional.ID, id);
            valores.put(PerfilProfesionalDataSource.ColumnPerfilProfesional.LITERAL, nom);
            db.insert(TABLA_PERFIL_PROFESIONAL, null, valores);
            //  db.close();
        }
    }


    public void insertarCuandoEmpezar(int id, String nom) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put(CuandoEmpezarDataSource.ColumnCuandoEmpezar.ID, id);
            valores.put(CuandoEmpezarDataSource.ColumnCuandoEmpezar.NOMBRE, nom);
            db.insert(TABLA_CUANDO_EMPEZAR, null, valores);
            //  db.close();
        }
    }

    public ArrayList<CuandoEmpezar> getCuandoEmpezar() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<CuandoEmpezar> output = new ArrayList<CuandoEmpezar>();
        try {
            String query = "SELECT " + CuandoEmpezarDataSource.ColumnCuandoEmpezar.ID + "," +
                    CuandoEmpezarDataSource.ColumnCuandoEmpezar.NOMBRE + " " +
                    " FROM " + TABLA_CUANDO_EMPEZAR +
                    " ORDER BY " + CuandoEmpezarDataSource.ColumnCuandoEmpezar.ID;

            Cursor cursor = db.rawQuery(query, null);

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

    public User devuelveUsuario() {
        SQLiteDatabase db = getReadableDatabase();

        User output = null;
        try {

            String query = "SELECT " + UserDataSource.ColumnUsuarios.EMAIL +
                    "," + UserDataSource.ColumnUsuarios.PASSWORD +
                    " FROM " + TABLA_USUARIOS;

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    output = new User();
                    output.setEmail(cursor.getString(0));
                    output.setPassword(cursor.getString(1));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error recoge Usuario", ex.toString());
        }
        return output;
    }


}