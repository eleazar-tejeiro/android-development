package com.example.centrovetpatitas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorConsulta extends BaseAdapter {
    private Context contexto;
    private ArrayList<Consulta> list;
    private int whichLayout;

    public AdaptadorConsulta(Context contexto, ArrayList<Consulta> list, int whichLayout) {
        this.contexto = contexto;
        this.list = list;
        this.whichLayout = whichLayout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderConsulta elHijo;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(contexto).inflate(whichLayout, null);
            elHijo = new HolderConsulta();
            elHijo.fechaCita = convertView.findViewById(R.id.textViewAdaptadorFechaCita);
            elHijo.razonCita = convertView.findViewById(R.id.textViewAdaptadorRazonCita);
            convertView.setTag(elHijo);
        } else {
            elHijo = (AdaptadorConsulta.HolderConsulta) convertView.getTag();
        }
        Consulta consulta = list.get(position);
        elHijo.fechaCita.setText(consulta.getFechaCita());
        elHijo.razonCita.setText(consulta.getRazonCita());
        return convertView;
    }

    public class HolderConsulta {
        TextView fechaCita;
        TextView razonCita;
    }

    public void refreshResultList(ArrayList<Consulta> result){
        this.list = result;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
    }
}
