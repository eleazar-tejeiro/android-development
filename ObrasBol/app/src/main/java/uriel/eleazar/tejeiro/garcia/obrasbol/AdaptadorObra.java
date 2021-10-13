package uriel.eleazar.tejeiro.garcia.obrasbol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorObra extends BaseAdapter {
    private Context contexto;
    private ArrayList<Obra> list;
    private int whichLayout;

    public AdaptadorObra(Context contexto, ArrayList<Obra> list, int whichLayout) {
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
        HolderObra elHijo;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(contexto).inflate(whichLayout, null);
            elHijo = new HolderObra();
            elHijo.nombreObra = convertView.findViewById(R.id.textViewAdaptadorObra);
            elHijo.estadoObra = convertView.findViewById(R.id.textViewAdaptadorEstado);
            convertView.setTag(elHijo);
        } else {
            elHijo = (HolderObra) convertView.getTag();
        }
        Obra laObra = list.get(position);
        elHijo.nombreObra.setText(laObra.getNombreObra());
        elHijo.estadoObra.setText(laObra.getEstadoObra());
        return convertView;
    }

    public class HolderObra {
        TextView nombreObra;
        TextView estadoObra;
    }

    public void refreshResultList(ArrayList<Obra> result){
        this.list = result;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
    }
}
