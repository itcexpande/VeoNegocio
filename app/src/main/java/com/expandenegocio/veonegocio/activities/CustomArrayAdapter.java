package com.expandenegocio.veonegocio.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.expandenegocio.veonegocio.R;
import com.expandenegocio.veonegocio.models.Row;

import java.util.List;

/**
 * Created by jesus on 10/05/2017.
 */

public class CustomArrayAdapter extends ArrayAdapter<Row> {
    private LayoutInflater layoutInflater;

    public CustomArrayAdapter(Context context, List<Row> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.listview_filas_provincias, null);
            holder.setTextViewTitle((TextView) convertView.findViewById(R.id.nombreProvincia));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Row row = getItem(position);
        holder.getTextViewTitle().setText(row.getTitle());
        return convertView;
    }

    static class Holder {
        TextView textViewTitle;
        CheckBox checkBox;

        public TextView getTextViewTitle() {
            return textViewTitle;
        }

        public void setTextViewTitle(TextView textViewTitle) {
            this.textViewTitle = textViewTitle;
        }


        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

    }
}
