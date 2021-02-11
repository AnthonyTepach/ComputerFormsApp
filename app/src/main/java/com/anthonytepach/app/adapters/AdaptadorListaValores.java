package com.anthonytepach.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.anthonytepach.app.R;
import com.anthonytepach.app.data.model.M_Valores;

import java.util.ArrayList;

public class AdaptadorListaValores extends ArrayAdapter<M_Valores> {

    public AdaptadorListaValores(Context context, ArrayList<M_Valores> mValores) {
        super(context, 0, mValores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        M_Valores m_valores = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_valores, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_valor);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.tv_desc_valor);
        // Populate the data into the template view using the data object
        tvName.setText(m_valores.getName());
        tvDesc.setText(m_valores.getDesc());
        // Return the completed view to render on screen
        return convertView;
    }

}
