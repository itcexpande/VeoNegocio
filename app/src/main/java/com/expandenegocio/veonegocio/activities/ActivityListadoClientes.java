package com.expandenegocio.veonegocio.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.expandenegocio.veonegocio.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;

/**
 * Created by jesus on 28/03/2017.
 */

public class ActivityListadoClientes extends AppCompatActivity {
    //private GestoraClientes gestora = GestoraClientes.INSTANCE;
    private static String FILENAME = "clientes.dat";

    private ListView lista;
    static SimpleAdapter adaptador;
    public static GestoraClientes gestora;

    public GestoraClientes getGestora() {
        return gestora;
    }

    public static SimpleAdapter getAdaptador() {
        return adaptador;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listado_clientes);
        lista = (ListView) findViewById(R.id.listView_listado_clientes);
        this.registerForContextMenu(lista);

    }

    @Override
    protected void onStart() {
        leerFichero();

        if (gestora == null) {
            gestora = new GestoraClientes();
            grabarFichero();
        }

        super.onStart();
    }

    @Override
    protected void onStop() {
        grabarFichero();
        super.onStop();

    }

    private void leerFichero() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            gestora = (GestoraClientes) ois.readObject();
            if (gestora == null) {
                cogerDatos();
            }
            Collections.sort(gestora);
            String[] from = ClavesClientes.Claves();
            int[] to = {R.id.nombre, R.id.apellidos, R.id.telefono,R.id.mail,R.id.otros};
            adaptador = new SimpleAdapter(this, gestora, R.layout.layout_items_listado_de_clientes, from, to);
            adaptador.setViewBinder(new DatosViewAdapter());
            lista.setAdapter(adaptador);
            fis.close();
        } catch (FileNotFoundException e) {
            cogerDatos();
            e.printStackTrace();
        } catch (IOException e) {
            cogerDatos();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            cogerDatos();
            e.printStackTrace();
        }
    }

    private void cogerDatos() {
       // gestora = new GestoraClientes();
       // grabarFichero();

    }


    private void grabarFichero() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(gestora);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
