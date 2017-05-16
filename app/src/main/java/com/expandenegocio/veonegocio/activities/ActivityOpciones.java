package com.expandenegocio.veonegocio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.ClavesFranquicia;
import com.expandenegocio.veonegocio.models.GestoraFranquicia;

/**
 * Created by jesus on 05/05/2017.
 */

public class ActivityOpciones extends AppCompatActivity {
    private String nCorreo;
    private String nPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_opciones);
        nCorreo = getIntent().getStringExtra("correo");
        nPassword = getIntent().getStringExtra("password");

    }


    public void ConsultaModificacionDeUsuarios(View view) {
        Intent intent = new Intent("ActivityConsultaUsuario");
        intent.putExtra("correo", nCorreo);
        intent.putExtra("password", nPassword);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void ConsultaModificacionDeUsuarios(MenuItem item) {
    }
}
