package com.expandenegocio.veonegocio.activities.activitis;

import android.app.ProgressDialog;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.expandenegocio.veonegocio.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;


/**
 * Created by jesus on 20/03/2017.
 */

public class ActivityRegistro extends AppCompatActivity {
    private EditText nombre;
    private EditText apellidos;
    private EditText email;
    private EditText password;
    ProgressDialog prgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registro);
        nombre = (EditText) this.findViewById(R.id.edit_nombre_registro);
        apellidos = (EditText) this.findViewById(R.id.edit_apellidos_registro);
        email = (EditText) this.findViewById(R.id.edit_email_registro);
        password = (EditText) this.findViewById(R.id.edit_password_registro);
        limpiar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button fab = (Button) findViewById(R.id.boton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recogemos los valores de los


            }
        });

        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Please wait...");
        prgDialog.setCancelable(false);


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

        nombre = (EditText) this.findViewById(R.id.edit_nombre_registro);
        apellidos = (EditText) this.findViewById(R.id.edit_apellidos_registro);


        email = (EditText) this.findViewById(R.id.edit_email_registro);
        password = (EditText) this.findViewById(R.id.edit_password_registro);
    }

    private void limpiar() {
        nombre.setText("");
        apellidos.setText("");
        email.setText("");
        password.setText("");
        nombre.requestFocus();
    }

    private void comprobarEntrada() throws MiExcepcion {

        String mensajeErrorSinNombre = this.getString(R.string.mensajeToastFaltaDato);
        String mensajeErrorSinApellidos = getString(R.string.faltaDatoApellidos);
        String mensajeErrorMail = this.getString(R.string.mensajeToastMail);
        String mensajeErrorSinPassword = getString(R.string.faltaDatoPassword);


        if (nombre.getText().toString().equals("")) {
            throw new MiExcepcion(nombre, mensajeErrorSinNombre);
        }
        if (apellidos.getText().toString().equals("")) {
            throw new MiExcepcion(apellidos, mensajeErrorSinApellidos);
        }
        if (!Pattern.matches(getString(R.string.patronPassword), email.getText())) {
            throw new MiExcepcion(email, mensajeErrorMail);
        }
        if (password.getText().toString().equals("")) {
            throw new MiExcepcion(password, mensajeErrorSinPassword);
        }

    }

    private void procesarInformacion() {
        User elUsuario = new User(nombre.getText().toString(),
                apellidos.getText().toString(),
                email.getText().toString(),
                password.getText().toString());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            RequestParams params = new RequestParams();

            params.put("name", "Hola");
            // Put Http parameter username with value of Email Edit View control
            params.put("email", "jgasj@gmail.com");
            // Put Http parameter password with value of Password Edit View control
            params.put("pwd", "aauaytuasa");
            // Invoke RESTful Web Service with Http parameters
            invokeWS(params);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void invokeWS(RequestParams params) {
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.expandenegocio.com/app/signup.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);

                prgDialog.hide();
                try {
                    // JSON Object

                    JSONObject obj = new JSONObject(response);

                    switch (obj.getInt("status")) {

                        case 0:
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Registrado correctamente!", Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Ya hay un usuario registrado con ese correo", Toast.LENGTH_LONG).show();
                            break;
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                    error) {

                prgDialog.hide();

                try {
                    if (responseBody != null) {
                        String response = new String(responseBody);
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}