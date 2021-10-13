package com.example.centrovetpatitas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorContacto extends BaseAdapter {

    private Context contexto;
    private ArrayList<Mascota> list;
    private int whichLayout;

    public AdaptadorContacto(Context contexto, ArrayList<Mascota> list, int whichLayout) {
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
        HolderContacto elHijo;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(contexto).inflate(whichLayout, null);
            elHijo = new HolderContacto();
            elHijo.nombre = convertView.findViewById(R.id.textViewAdaptadorNombre);
            elHijo.especie = convertView.findViewById(R.id.textViewAdaptadorNumero);
            convertView.setTag(elHijo);
        } else {
            elHijo = (HolderContacto) convertView.getTag();
        }
        Mascota contacto = list.get(position);
        elHijo.nombre.setText(contacto.getNombre());
        elHijo.especie.setText(contacto.getEspecie());
        return convertView;
    }

    public class HolderContacto {
        TextView nombre;
        TextView especie;
    }

    public void refreshResultList(ArrayList<Mascota> result){
        this.list = result;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
    }
}
