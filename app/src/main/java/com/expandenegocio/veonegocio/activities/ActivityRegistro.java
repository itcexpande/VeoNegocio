package com.expandenegocio.veonegocio.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.expandenegocio.veonegocio.R;

import java.util.regex.Pattern;

/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityRegistro extends AppCompatActivity {
    private EditText nombre;
    private EditText apellidos;
    private EditText telefono;
    private EditText mail;
    private EditText otros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registro);
        nombre = (EditText) this.findViewById(R.id.edit_nombre_registro);
        apellidos = (EditText) this.findViewById(R.id.edit_apellidos_registro);

        telefono = (EditText) this.findViewById(R.id.edit_tfno_registro);
        mail = (EditText) this.findViewById(R.id.edit_mail_registro);
        otros = (EditText) this.findViewById(R.id.edit_otros_registro);

        limpiar();
    }

    public void aceptarRegistro(View view) {
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

        EditText nombre = (EditText) this.findViewById(R.id.edit_nombre_registro);
        EditText apellidos = (EditText) this.findViewById(R.id.edit_apellidos_registro);

        EditText telefono = (EditText) this.findViewById(R.id.edit_tfno_registro);
        EditText mail = (EditText) this.findViewById(R.id.edit_mail_registro);
        EditText otros = (EditText) this.findViewById(R.id.edit_otros_registro);

        TextView recoger1 = (TextView) this.findViewById(R.id.respuesta1);
        recoger1.setText(nombre.getText());

        TextView recoger2 = (TextView) this.findViewById(R.id.respuesta2);
        recoger2.setText(apellidos.getText());

        TextView recoger3 = (TextView) this.findViewById(R.id.respuesta3);
        recoger3.setText(telefono.getText());

        TextView recoger4 = (TextView) this.findViewById(R.id.respuesta4);
        recoger4.setText(mail.getText());

        TextView recoger5 = (TextView) this.findViewById(R.id.respuesta5);
        recoger5.setText(otros.getText());


    }

    private void limpiar() {
        nombre.setText("");
        apellidos.setText("");
        telefono.setText("");
        mail.setText("");
        otros.setText("");
        nombre.requestFocus();
    }

    private void comprobarEntrada() throws MiExcepcion {

        String mensajeErrorSinDato = this.getString(R.string.mensajeToastFaltaDato);
        String mensajeErrorTelefono = this.getString(R.string.mensajeToastTelefono);
        String mensajeErrorMail = this.getString(R.string.mensajeToastMail);


        if (nombre.getText().toString().equals("")) {
            throw new MiExcepcion(nombre, mensajeErrorSinDato);
        }
        if (apellidos.getText().toString().equals("")) {
            throw new MiExcepcion(apellidos, mensajeErrorSinDato);
        }


        if (!Pattern.matches("^(0034|\\+34)?(\\d\\d\\d)-? ?(\\d\\d)-? ?(\\d)-? ?(\\d)-? ?(\\d\\d)$", telefono.getText())) {
            throw new MiExcepcion(telefono, mensajeErrorTelefono);
        }

        if (!Pattern.matches("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$", mail.getText())) {
            throw new MiExcepcion(telefono, mensajeErrorMail);
        }

    }

    private void procesarInformacion() {
        Cliente elCliente = new Cliente(nombre.getText().toString(),
                apellidos.getText().toString(),
                telefono.getText().toString(),
                mail.getText().toString(),
                otros.getText().toString());

    }


    public void cancelarRegistro(View view) {
        limpiar();
    }
}