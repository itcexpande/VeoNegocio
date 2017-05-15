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

    public static final String USUARIO_TABLE_NAME = "users";


    //Campos de la tabla usuarios
    public static class ColumnUsuarios {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String STATUS = "status";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDOS = "apellidos";
        public static final String TELEFONO = "telefono";
        public static final String CODIGO_PROVINCIA = "c_prov";
        public static final String CODIGO_MUNICIPIO = "c_mun";
        public static final String CAPITAL = "capital";
        public static final String CAPITAL_OBSERVACIONES = "capital_observaciones";
        public static final String CERRADA = "cerrada";
        public static final String CUANDO_EMPEZAR = "cuando_empezar";
        public static final String DATE_ENTERED = "date_entered";
        public static final String DATE_MODIFIED = "date_modified";
        public static final String DELETED = "deleted";
        public static final String DISP_CONTACTO = "disp_contacto";
        public static final String DISP_LOCAL = "dispone_local";
        public static final String EMPRESA = "empresa";
        public static final String NEGOCIO = "negocio";
        public static final String NEGOCIO_ANTES = "negocio_antes";
        public static final String PERFIL_FRANQUICIA = "perfil_franquicia";
        public static final String PERFIL_PROFESIONAL = "perfil_profesional";
        public static final String PHONE_HOME = "phone_home";
        public static final String PHONE_MOBILE = "phone_mobile";
        public static final String RECURSOS_PROPIOS = "recursos_propios";
        public static final String SITUACION_PROFESIONAL = "situacion_profesional";


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
                ColumnUsuarios.CODIGO_PROVINCIA + ", " +
                ColumnUsuarios.CODIGO_MUNICIPIO + ", " +
                ColumnUsuarios.CAPITAL + ", " +
                ColumnUsuarios.CAPITAL_OBSERVACIONES + ", " +
                ColumnUsuarios.CERRADA + ", " +
                ColumnUsuarios.CUANDO_EMPEZAR + ", " +
                ColumnUsuarios.DATE_ENTERED + ", " +
                ColumnUsuarios.DATE_MODIFIED + ", " +
                ColumnUsuarios.DELETED + ", " +
                ColumnUsuarios.DISP_CONTACTO + ", " +
                ColumnUsuarios.DISP_LOCAL + ", " +
                ColumnUsuarios.EMPRESA + ", " +
                ColumnUsuarios.NEGOCIO + ", " +
                ColumnUsuarios.NEGOCIO_ANTES + ", " +
                ColumnUsuarios.PERFIL_FRANQUICIA + ", " +
                ColumnUsuarios.PERFIL_PROFESIONAL + ", " +
                ColumnUsuarios.PHONE_HOME + ", " +
                ColumnUsuarios.PHONE_MOBILE + ", " +
                ColumnUsuarios.RECURSOS_PROPIOS + ", " +
                ColumnUsuarios.SITUACION_PROFESIONAL +
                ") VALUES" + " ( " +
                "'" + user.getId() + "'," +
                "'" + user.getEmail() + "'," +
                "'" + user.getPassword() + "'," +
                "'" + user.getStatus() + "'," +
                "'" + user.getNombre() + "'," +
                "'" + user.getApellidos() + "'," +
                "'" + user.getTelefono() + "'," +
                "" + user.getCodigoProv() + "," +
                "" + user.getCodigoMun() + "," +
                "'" + user.getCapital() + "'," +
                "'" + user.getCapitalObservaciones() + "'," +
                "" + user.getCerrada() + "," +
                "'" + user.getCuandoEmpezar() + "'," +
                "'" + user.getDateEntered() + "'," +
                "'" + user.getDateModified() + "'," +
                "" + user.getDeleted() + "," +
                "'" + user.getDisponeContacto() + "'," +
                "'" + user.getDisponeLocal() + "'," +
                "'" + user.getEmpresa() + "'," +
                "'" + user.getNegocio() + "'," +
                "'" + user.getNegocioAnterior() + "'," +
                "'" + user.getPerfilFranquicia() + "'," +
                "'" + user.getPerfilProfesional() + "'," +
                "'" + user.getPhoneHome() + "'," +
                "'" + user.getPhoneMobile() + "'," +
                "'" + user.getRecursosPropios() + "'," +
                "'" + user.getSituacionProfesional() +"'"+
                ")";
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
                    " WHERE " + ColumnUsuarios.ID + " = '" + id + "'";

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

    public String buscaUsuarioPorEmail(String correo) {

        String output = null;

        try {

            String query = "SELECT  " +
                    ColumnUsuarios.PASSWORD +
                    " FROM " + USUARIO_TABLE_NAME +
                    " WHERE " + ColumnUsuarios.EMAIL + " = '" + correo + "'";
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


    public User buscaUsuarioPorEmailYPassword(String correo, String p) {

        User output = null;

        try {

            String query = "SELECT  " +
                    ColumnUsuarios.ID +

                    " FROM " + USUARIO_TABLE_NAME +
                    " WHERE " + ColumnUsuarios.EMAIL + " = '" + correo +
                    "' AND " + ColumnUsuarios.PASSWORD + " = '" + p + "'";
            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    output = new User();
                    output.setId(cursor.getString(0));


                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.d("Error recoge Usuario", ex.toString());
        }
        return output;
    }
}