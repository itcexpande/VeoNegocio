package com.expandenegocio.veonegocio.utilities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.expandenegocio.veonegocio.activities.ActivityAltaUsuario;

import java.util.Calendar;

/**
 * Created by jesus on 04/05/2017.
 */

public class DialogoDate2 extends DialogFragment {

    private Calendar momento = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            momento.set(Calendar.YEAR, year);
            momento.set(Calendar.MONTH, monthOfYear);
            momento.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            ((ActivityAltaUsuario) getActivity()).actualizarLaFecha2EnTextView(momento);
        }

    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        momento = Calendar.getInstance();

        return new DatePickerDialog(getActivity(), dateSetListener, momento.get(Calendar.YEAR), momento.get(Calendar.MONTH), momento.get(Calendar.DAY_OF_MONTH));
    }
}