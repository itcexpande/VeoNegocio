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


    }

    public void aceptarRegistro(View view) {
        try {
            comprobarEntrada();
            procesarInformacion();
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

    private void comprobarEntrada() throws MiExcepcion {

        String mensajeErrorSinDato=this.getString(R.string.mensajeToastFaltaDato);
        if (nombre.getText().toString().equals("")) {
            throw new MiExcepcion(nombre, mensajeErrorSinDato);
        }
        if (apellidos.getText().toString().equals("")) {
            throw new MiExcepcion(apellidos, mensajeErrorSinDato);
        }
        /*

        if (!Pattern.matches("([0-9]+)(||\\.||(\\.[0-9]{1,2}))", pvp.getText())){
            throw new MiExcepcion(pvp, mensajeErrorDecimales);
        }
        if (dto.getText().toString().equals("")) {
            throw new MiExcepcion(dto, mensajeErrorSinDato);
        }
        if (!Pattern.matches("[0-9]{1,2}", dto.getText())) {
            throw new MiExcepcion(dto, mensajeErrorPorcentaje);
        }
        if (iva.getText().toString().equals("")) {
            throw new MiExcepcion(iva, mensajeErrorSinDato);
        }
        if (!Pattern.matches("[0-9]{1,2}", iva.getText())) {
            throw new MiExcepcion(iva, mensajeErrorPorcentaje);
        }
        */
    }

    private void procesarInformacion() {
        /*
        Articulo elArticulo=new Articulo(Float.parseFloat(pvp.getText().toString()), Integer.parseInt(iva.getText().toString()), Integer.parseInt(dto.getText().toString()));
 total.setText(String.format("%.2f", elArticulo.calculaAPagar()));
 //total.setText(new BigDecimal(elArticulo.calculaAPagar().toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());

         */
    }
}
