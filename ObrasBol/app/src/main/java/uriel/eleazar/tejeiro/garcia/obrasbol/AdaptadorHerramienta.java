package uriel.eleazar.tejeiro.garcia.obrasbol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorHerramienta extends BaseAdapter {
    private Context contexto;
    private ArrayList<Herramienta> list;
    private int whichLayout;

    public AdaptadorHerramienta(Context contexto, ArrayList<Herramienta> list, int whichLayout) {
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
        HolderHerramienta elHijo;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(contexto).inflate(whichLayout, null);
            elHijo = new HolderHerramienta();
            elHijo.nombreHerramienta = convertView.findViewById(R.id.textViewAdaptadorHerramienta);
            elHijo.estadoPrestamo = convertView.findViewById(R.id.textViewAdaptadorPrestamo);
            convertView.setTag(elHijo);
        } else {
            elHijo = (HolderHerramienta) convertView.getTag();
        }
        Herramienta laHerramienta = list.get(position);
        elHijo.nombreHerramienta.setText(laHerramienta.getNombreHerramienta());
        elHijo.estadoPrestamo.setText(laHerramienta.getEstadoHerramienta());
        return convertView;
    }

    public class HolderHerramienta {
        TextView nombreHerramienta;
        TextView estadoPrestamo;
    }

    public void refreshResultList(ArrayList<Herramienta> result){
        this.list = result;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
    }
}
