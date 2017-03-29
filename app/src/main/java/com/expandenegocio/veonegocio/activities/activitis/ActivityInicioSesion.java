package com.expandenegocio.veonegocio.activities.activitis;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.expandenegocio.veonegocio.R;

import java.util.regex.Pattern;


/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityInicioSesion extends AppCompatActivity {

    private EditText emailUsuario;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inicio_sesion);
        emailUsuario = (EditText) this.findViewById(R.id.campo_correo);
        password = (EditText) this.findViewById(R.id.campo_contrasena);
        limpiar();

    }

    public void inicioSesion(View view) {
        try {
            comprobarEntrada();
            procesarInformacion();
            limpiar();
        } catch (MiExcepcion e) {
            Toast.makeText(getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            e.getVista().selectAll();
            e.getVista().requestFocus();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

    }


    private void limpiar() {
        emailUsuario.setText("");
        password.setText("");
        emailUsuario.requestFocus();
    }

    private void comprobarEntrada() throws MiExcepcion {
        String mensajeErrorMail = this.getString(R.string.mensajeToastMail);
        String mensajeErrorSinPassword = getString(R.string.faltaDatoPassword);

        if (emailUsuario.getText().toString().equals("")) {
            throw new MiExcepcion(emailUsuario, mensajeErrorMail);
        }

        if (password.getText().toString().equals("")) {
            throw new MiExcepcion(password, mensajeErrorSinPassword);
        }

    }

    private void procesarInformacion() {
      /*  User elUsuario = new User(nombre.getText().toString(),
                apellidos.getText().toString(),
                email.getText().toString(),
                password.getText().toString());*/
    }


    public void recordarDatos(View view) {
    }

    public void registro(View view) {
        Intent intent = new Intent("AcivityRegistro");
        startActivity(intent);
    }
}