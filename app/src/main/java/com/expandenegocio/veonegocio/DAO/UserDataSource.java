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
                ColumnUsuarios.CODIGO_MUNICIPIO +
                ") VALUES" + " ( " +
                "'" + user.getId() + "'," +
                "'" + user.getEmail() + "'," +
                "'" + user.getPassword() + "'," +
                "'" + user.getStatus() + "'," +
                "'" + user.getNombre() + "'," +
                "'" + user.getApellidos() + "'," +
                "'" + user.getTelefono() + "'," +
                "" + user.getCodigoProv() + "," +
                user.getCodigoMun() + " " +
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