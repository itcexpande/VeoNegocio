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
    /*
    public static final String NOMBREBD = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "c:/Users/jesus/AndroidStudioProjects/VeoNegocioData/VeoNegocio.db";
*/
/*
   public static final String NOMBREBD = Environment.getExternalStorageDirectory()
           .getAbsolutePath() + "C:\\Users\\jesus\\AndroidStudioProjects\\VeoNegocioData/VeoNegocio.db";//"/Pictures/VeoNegocio.db";
*/
    public static final String NOMBREBD = "C:/Users/jesus/AndroidStudioProjects/VeoNegocioData/VeoNegocio.db";//"/Pictures/VeoNegocio.db";


    public DbHelper(Context context){
        super(context,NOMBREBD,null,3);
       SQLiteDatabase.openOrCreateDatabase(NOMBREBD,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear la base de datos
        try{
            //db.execSQL(DataSource.CREATE_SOLICITUDES_SCRIPT);

        }catch(Exception ex ){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Actualizar la base de datos
    }
}
