package com.expandenegocio.veonegocio.activities;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.expandenegocio.veonegocio.R;

import java.util.ArrayList;


public class DialogoDetalleFranquicia extends DialogFragment {

    //   private GestoraDeportista gestora;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String infService = Context.LAYOUT_INFLATER_SERVICE;

        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(
                infService);
        final View layoutInflado = li.inflate(R.layout.listado_detalle_franquicia, null);
        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());
        ventana.setTitle("Detalle Franquicia");

        ventana.setView(layoutInflado);
        ArrayList<CharSequence> datos = new ArrayList<>();

        ventana.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {

                        EditText nombre = (EditText) layoutInflado.findViewById(R.id.nuevo_medallista);
                        String nuevoNombre = nombre.getText().toString();
                        EditText annoOlimpico = (EditText) layoutInflado.findViewById(R.id.nuevo_anno_olimpiadas);
                        String nuevoannoOlimpiadas = annoOlimpico.getText().toString();
                        Spinner spinner = (Spinner) layoutInflado.findViewById(R.id.nuevo_spinner_medalla);
                        String nuevoMedalla = (String) spinner.getSelectedItem();
                        Spinner spinner2 = (Spinner) layoutInflado.findViewById(R.id.nuevo_spinner_deporte);
                        String nuevoDeporte = (String) spinner2.getSelectedItem();

                        Deportista nuevo = new Deportista(
                                nuevoNombre,
                                Integer.parseInt(nuevoannoOlimpiadas),
                                Medalla.devuelveMedallaPorNombre(getActivity(), nuevoMedalla),
                                Deporte.devuelveDeportePorNombre(getActivity(), nuevoDeporte));

                        //comprobamos si es correcto
                        if (gestora.existeDeportista(nuevo)) {
                            gestora.escribeAviso(getActivity(), "Deportista ya Exsite", nuevo);
                        } else {
                            gestora.add(nuevo);
                            gestora.escribeAviso(getActivity(), "Deportista Alta", nuevo);
                        }
                        MainActivity.adaptador.notifyDataSetChanged();

                    }
                });
        return ventana.create();
    }

    /**
     * @param deportista
     * @return true si en la lista  no existe el deportista recibido como parámetro y el año de la olimpiada
     * del medallista  es correcto
     */
    private boolean entradaCorrecta(Deportista deportista) {
        //modifica el método
        return true;
    }

}
