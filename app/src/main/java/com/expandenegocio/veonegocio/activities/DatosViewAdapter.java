package com.expandenegocio.veonegocio.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

/**
 * Created by jesus on 28/03/2017.
 */

class DatosViewAdapter implements SimpleAdapter.ViewBinder {
    @Override
    public boolean setViewValue(View view, Object o, String s) {
   /*     if (o instanceof Cliente) {
            Cliente cliente = (Cliente) o;
            ImageView view1 = (ImageView) view;
            view1.setImageResource(cliente.getImagen());
            return true;
        }
*/
        return false;
    }
}