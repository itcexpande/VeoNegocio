package com.expandenegocio.veonegocio.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.expandenegocio.veonegocio.models.Municipio;
import com.expandenegocio.veonegocio.models.Provincia;
import com.expandenegocio.veonegocio.models.User;

/**
 * Created by jesus on 05/04/2017.
 */

public class UserDataSource {
    public static final String USUARIO_TABLE_NAME = "usuarios";
    public static final String MUNICIPIO_TABLE_NAME = "municipios";
    public static final String PROVINCIA_TABLE_NAME = "provincias";

    //Campos de la tabla provincias
    public static class ColumnProvincia {
        public static final String ID = "c_prov";
        public static final String NOMBRE = "d_prov";
    }

    //Campos de la tabla usuarios
    public static class ColumnUsuarios {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String STATUS = "status";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDOS = "apellidos";
        public static final String TELEFONO = "telefono";
        public static final String MUNICIPIO = "municipio";
        public static final String PROVINCIA = "provincia";
    }

    //Campos de la tabla municipios
    public static class ColumnMunicipio {
        public static final String CODIGO_MUNICIPIO = "c_mun";
        public static final String NOMBRE_PROVINCIA = "d_prov";
        public static final String NOMBRE_MUNICIPIO = "d_mun";
    }


    private DbHelper openHelper;
    private SQLiteDatabase database;

    public UserDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        openHelper = new DbHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public void insertUsuario(User user) {

        String insertSQL = "Insert into " + USUARIO_TABLE_NAME +
                "(" +
                ColumnUsuarios.ID + "," +
                ColumnUsuarios.EMAIL + "," +
                ColumnUsuarios.PASSWORD + ", " +
                ColumnUsuarios.STATUS + ", " +
                ColumnUsuarios.NOMBRE + ", " +
                ColumnUsuarios.APELLIDOS + ", " +
                ColumnUsuarios.TELEFONO + ", " +
                ColumnUsuarios.MUNICIPIO + ", " +
                ColumnUsuarios.PROVINCIA +
                ") VALUES" + "(" +
                "'" + user.getId() + "'," +
                "'" + user.getEmail() + "'," +
                "'" + user.getPassword() + "'," +
                "'" + user.getStatus() + "'," +
                "'" + user.getNombre() + "'," +
                "'" + user.getApellidos() + "," +
                "'" + user.getTelefono() + "'," +
                "'" + user.getMunicipio() + "'," +
                "'" + user.getMunicipio().getProvincia() + "')";
        try {
            database.execSQL(insertSQL);
        } catch (Exception ex) {
            Log.d("Error insertar Usuario", ex.toString());
        }
    }

    public String buscarUsuarioPorId(String id) {

        String output = "";
        try {

            String query = "SELECT " + ColumnUsuarios.ID +
                    " FROM " + USUARIO_TABLE_NAME +
                    " WHERE " + ColumnUsuarios.ID + " = " + id;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    output = cursor.getString(0);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error recoge Usuario", ex.toString());
        }
        return output;
    }


    public User buscaUsuarioPorEmail(String correo) {

        User output = null;

        try {

            String query = "SELECT  " +
                    ColumnUsuarios.ID + "," +
                    ColumnUsuarios.EMAIL + "," +
                    ColumnUsuarios.PASSWORD + "," +
                    ColumnUsuarios.STATUS + ", " +
                    ColumnUsuarios.NOMBRE + "," +
                    ColumnUsuarios.APELLIDOS + "," +
                    ColumnUsuarios.TELEFONO + "," +
                    ColumnUsuarios.MUNICIPIO + "," +
                    ColumnUsuarios.PROVINCIA +
                    " FROM " + USUARIO_TABLE_NAME +
                    " WHERE " + ColumnUsuarios.EMAIL + " = " + correo;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    output = new User();
                    Municipio municipio = new Municipio();
                    output.setId(cursor.getString(0));
                    output.setEmail(cursor.getString(1));
                    output.setPassword(cursor.getString(2));
                    output.setStatus(cursor.getString(3));
                    output.setNombre(cursor.getString(4));
                    output.setApellidos(cursor.getString(5));
                    output.setTelefono(cursor.getString(6));
                    String nombreMunicipio = cursor.getString(7);
                    String nombreProvincia = cursor.getString(8);
                    municipio = buscaMunipio(nombreMunicipio, nombreProvincia);
                    output.setMunicipio(municipio);

                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error recoge Usuario", ex.toString());
        }

        return output;

    }

    private Municipio buscaMunipio(String nombreMunicipio, String nombreProvincia) {
        Municipio output = null;

        try {

            String query = "SELECT " +
                    MunicipioDataSource.ColumnMunicipio.CODIGO_MUNICIPIO + "," +
                    MunicipioDataSource.ColumnMunicipio.NOMBRE_MUNICIPIO + "," +
                    MunicipioDataSource.ColumnMunicipio.NOMBRE_PROVINCIA +
                    " FROM " + MUNICIPIO_TABLE_NAME +
                    " WHERE " + MunicipioDataSource.ColumnMunicipio.NOMBRE_PROVINCIA + "=" + nombreProvincia +
                    " AND " + MunicipioDataSource.ColumnMunicipio.CODIGO_MUNICIPIO + "=" + nombreMunicipio;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    output = new Municipio();
                    Provincia provincia = new Provincia();
                    output.setCodigoMunicipio(cursor.getInt(0));
                    output.setNombreMunicipio(cursor.getString(1));
                    nombreMunicipio = cursor.getString(1);
                    provincia = buscaProvincia(nombreMunicipio);
                    output.setProvincia(provincia);

                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error busca Municipio", ex.toString());
        }

        return output;
    }

    private Provincia buscaProvincia(String nombreProv) {
        Provincia output = null;
/*
        try {

            String query = "SELECT " +
                    ProvinciaDataSource.ColumnProvincia.ID + "," +
                    ProvinciaDataSource.ColumnProvincia.NOMBRE +
                    " FROM " + PROVINCIA_TABLE_NAME +
                    " WHERE " + MunicipioDataSource.ColumnMunicipio.NOMBRE_PROVINCIA + "=" + nombreProvincia +
                    " AND " + MunicipioDataSource.ColumnMunicipio.CODIGO_MUNICIPIO + "=" + nombreMunicipio;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    output = new Municipio();
                    Provincia provincia = new Provincia();
                    output.setCodigoMunicipio(cursor.getInt(0));
                    output.setNombreMunicipio(cursor.getString(1));
                    nombreMunicipio = cursor.getString(1);
                    provincia = buscaProvincia(nombreMunicipio);
                    output.setProvincia(provincia);

                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error busca Municipio", ex.toString());
        }*/

        return output;
    }

}

/*
public static final String SOLICITUD_TABLE_NAME = "Solicitudes";
public static final String PROVINCIA_TABLE_NAME = "Provincias";
public static final String FRANQUICIA_TABLE_NAME = "Franquicias";
public static final String SOLICITUD_FRANQUICIA_TABLE_NAME = "SolicitudFranquicia";
public static final String STRING_TYPE = "text";
public static final String INT_TYPE = "integer";

//Campos de la tabla Solicitudes
public static class ColumnSolicitud {
    public static final String ID = "id";
    public static final String NOMBRE = "Nombre";
    public static final String APELLIDOS = "Apellidos";
    public static final String CORREO = "Correo";
    public static final String TELEFONO = "Telefono";
    public static final String PROVINCIA = "Provincia";
    public static final String FECHA = "Fecha";
    public static final String FRANQUICIA_PRINCIPAL = "franq_principal";
}

public static class ColumnProvincia {
    public static final String ID = "c_prov";
    public static final String NOMBRE = "d_prov";
}

public static class ColumnFranquicia {
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String ACTIVO = "activo";
}

public static class ColumnSolicitudFranquicia {
    public static final String IDSol = "IDSol";
    public static final String IDFran = "IDFran";
}

    public static final String CREATE_SOLICITUDES_SCRIPT =
            "create table " + SOLICITUD_TABLE_NAME + "(" +
                    ColumnSolicitud.ID + " " + STRING_TYPE + " not null, " +
                    ColumnSolicitud.NOMBRE + " " + STRING_TYPE + " ," +
                    ColumnSolicitud.APELLIDOS + " " + STRING_TYPE + " not null, " +
                    ColumnSolicitud.CORREO + " " + STRING_TYPE + " not null, " +
                    ColumnSolicitud.TELEFONO + " " + STRING_TYPE + " not null, " +
                    ColumnSolicitud.PROVINCIA + " " + INT_TYPE + " not null, " +
                    ColumnSolicitud.FECHA + " " + STRING_TYPE + " not null, " +
                    ColumnSolicitud.FRANQUICIA_PRINCIPAL + " " + STRING_TYPE + " not null)";


    private SolicitudesDbHelper openHelper;
    private SQLiteDatabase database;

    public SolicitudesDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        openHelper = new SolicitudesDbHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public void insertSolicitud(Solicitud solicitud) {

        String insertSQL = "Insert into " + SOLICITUD_TABLE_NAME +
                "(" +
                ColumnSolicitud.ID + "," +
                ColumnSolicitud.NOMBRE + "," +
                ColumnSolicitud.APELLIDOS + ", " +
                ColumnSolicitud.CORREO + ", " +
                ColumnSolicitud.TELEFONO + ", " +
                ColumnSolicitud.PROVINCIA + ", " +
                ColumnSolicitud.FECHA + ", " +
                ColumnSolicitud.FRANQUICIA_PRINCIPAL +
                ") VALUES" + "(" +
                "'" + solicitud.getId() + "'," +
                "'" + solicitud.getNombre() + "'," +
                "'" + solicitud.getApellidos() + "'," +
                "'" + solicitud.getCorreo() + "'," +
                "'" + solicitud.getTelefono() + "'," +
                +solicitud.getProvincia() + "," +
                "'" + solicitud.getFecha() + "'," +
                "'" + solicitud.getFranq_principal() + "')";


        try {
            database.execSQL(insertSQL);
        } catch (Exception ex) {
            Log.d("Error insertar Recinto", ex.toString());
        }
    }


    public void insertSolicitudFranquicia(Solicitud solicitud, String IdFranquicia) {

        String insertSQL = "Insert into " + SOLICITUD_FRANQUICIA_TABLE_NAME +
                "(" + ColumnSolicitudFranquicia.IDSol + "," +
                ColumnSolicitudFranquicia.IDFran + ") VALUES" + "(" +
                "'" + solicitud.getId() + "'," +
                "'" + IdFranquicia + "')";
        try {
            database.execSQL(insertSQL);
        } catch (Exception ex) {
            Log.d("Error insertar Recinto", ex.toString());
        }
    }

    public String buscaFranquiciaAct() {

        String output = "";
        try {

            String query = "SELECT " + ColumnFranquicia.ID +
                    " FROM " + FRANQUICIA_TABLE_NAME +
                    " WHERE activo=1";

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    output = cursor.getString(0);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error recog FranquiAct", ex.toString());
        }

        return output;

    }

    public Franquicia buscaFranquiciaAct2() {

        Franquicia output = null;

        try {

            String query = "SELECT " + ColumnFranquicia.ID + "," + ColumnFranquicia.NOMBRE + "," + ColumnFranquicia.ACTIVO +
                    " FROM " + FRANQUICIA_TABLE_NAME +
                    " WHERE activo=1";

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {

                    output = new Franquicia();
                    output.setId(cursor.getString(0));
                    output.setNombreFranquicia(cursor.getString(1));
                    output.setActivo(cursor.getInt(2));

                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error recog FranquiAct", ex.toString());
        }

        return output;

    }

    public ArrayList<Provincia> getProvincias() {

        ArrayList<Provincia> output = new ArrayList<Provincia>();

        try {

            String query = "SELECT " + ColumnProvincia.ID + "," +
                    ColumnProvincia.NOMBRE + " " +
                    " FROM " + PROVINCIA_TABLE_NAME +
                    " ORDER BY " + ColumnProvincia.NOMBRE;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Provincia provincia = new Provincia();
                    provincia.setCodigoProv(Integer.parseInt(cursor.getString(0)));
                    provincia.setDescProv(cursor.getString(1));

                    // Add book to books
                    output.add(provincia);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error getProvincias", ex.toString());
        }

        return output;
    }


    public ArrayList<Franquicia> getFranquicia() {

        ArrayList<Franquicia> output = new ArrayList<Franquicia>();

        try {

            String query = "SELECT " + ColumnFranquicia.ID + "," + ColumnFranquicia.NOMBRE + " " +
                    " FROM " + FRANQUICIA_TABLE_NAME +
                    " ORDER BY " + ColumnFranquicia.NOMBRE;

            Cursor cursor = database.rawQuery(query, null);
            output.add(new Franquicia());

            if (cursor.moveToFirst()) {
                do {
                    Franquicia franquicia = new Franquicia();
                    franquicia.setId(cursor.getString(0));
                    franquicia.setNombreFranquicia(cursor.getString(1));

                    // Add book to books
                    output.add(franquicia);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error getFranquicias", ex.toString());
        }

        return output;
    }

    public int devuelveNumeroFranquiciasActivas() {

        int devuelve = 0;
        try {

            String query = "SELECT count(*)" +
                    " FROM " + FRANQUICIA_TABLE_NAME +
                    " where " + ColumnFranquicia.ACTIVO + "<>1";

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    devuelve++;
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error getFranquicias", ex.toString());
        }

        return devuelve;
        // return 0;
    }


     */
