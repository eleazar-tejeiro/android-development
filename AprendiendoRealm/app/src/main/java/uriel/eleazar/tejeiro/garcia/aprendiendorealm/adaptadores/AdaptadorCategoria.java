package uriel.eleazar.tejeiro.garcia.aprendiendorealm.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;
import io.realm.kotlin.RealmResultsExtensionsKt;
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.R;
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models.Categoria;

public class AdaptadorCategoria extends BaseAdapter {
    private Context contexto;
    private RealmResults<Categoria> list;
    private int layout;

    public AdaptadorCategoria(Context contexto, RealmResults<Categoria> list, int layout) {
        this.contexto = contexto;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoriaHolder ch;
        //recupero las id de mi layout
        if (convertView == null) {
            convertView = LayoutInflater.from(contexto).inflate(layout,null);
            ch = new CategoriaHolder();
            ch.nombre = convertView.findViewById(R.id.textViewNombreCategoria);
            ch.fechaCreacion = convertView.findViewById(R.id.textViewFechaCreacion);
            ch.cantidadProducto = convertView.findViewById(R.id.textViewCantProductos);
            convertView.setTag(ch);
        } else {
            ch = (CategoriaHolder) convertView.getTag();
        }
        //Seteo los valores en las id de los objetos que recupere por id
        Categoria categoria = list.get(position);
        Log.d("soyPosition: ", String.valueOf(position));
        Log.d("SoyLista: ", String.valueOf(list.size()));
        ch.nombre.setText(categoria.getNombre());
        ch.fechaCreacion.setText(categoria.getCreatedAt().toString());
        ch.cantidadProducto.setText("("+categoria.getProductos().size()+")");

        return convertView;
    }

    public void refreshResultList(RealmResults<Categoria> result){
        this.list = result;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
    }

    public class CategoriaHolder {
        TextView nombre;
        TextView cantidadProducto;
        TextView fechaCreacion;
    }
}
