package com.expandenegocio.veonegocio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityRegistro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registro);

    }

    public void aceptarRegistro(View view) {
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

        //    Toast.makeText(this, nombre.getText(), Toast.LENGTH_LONG).show();
     //   Toast.makeText(this, apellidos.getText(), Toast.LENGTH_LONG).show();


    }
}
