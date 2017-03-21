package com.expandenegocio.veonegocio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void comprobar(View view) {

        EditText usuario = (EditText) this.findViewById(R.id.edit_nombre_usuario);
        EditText contrasena = (EditText) this.findViewById(R.id.edit_contraseña);
       // Toast.makeText(this, usuario.getText(), Toast.LENGTH_LONG).show();
       // Toast.makeText(this, contrasena.getText(), Toast.LENGTH_LONG).show();
        TextView recoger1 = (TextView) this.findViewById(R.id.respuesta1);
        recoger1.setText( usuario.getText());

        TextView recoger2 = (TextView) this.findViewById(R.id.respuesta2);
        recoger1.setText( contrasena.getText());



        //  comprobar si usuario y contraseña estan en base de datos
        // si existe usuario  hacer algo ,nueva activity
        // si no exixte  lanzar activity de registro




    }

    public void lanzarPresentacion(View view) {
        Intent intent=new Intent("AcivityPresentacion");
        startActivity(intent);
    }

    public void lanzarSinConexion(View view) {
        Intent intent=new Intent("AcivitySinConexion");
        startActivity(intent);
    }

    public void lanzarRegistro(View view) {
        Intent intent=new Intent("AcivityRegistro");
        startActivity(intent);

    }
}
