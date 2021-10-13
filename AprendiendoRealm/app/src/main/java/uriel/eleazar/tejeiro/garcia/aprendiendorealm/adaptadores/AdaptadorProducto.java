    package uriel.eleazar.tejeiro.garcia.aprendiendorealm.adaptadores;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.TextView;

    import java.util.List;

    import io.realm.RealmResults;
    import uriel.eleazar.tejeiro.garcia.aprendiendorealm.R;
    import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models.Producto;

    public class AdaptadorProducto extends BaseAdapter {
        private Context contexto;
        private List<Producto> list;
        private int layout;

        public AdaptadorProducto(Context contexto, List<Producto> list, int layout) {
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
            ProductoHolder ch;
            //recupero las id de mi layout
            if (convertView == null) {
                convertView = LayoutInflater.from(contexto).inflate(layout,null);
                ch = new ProductoHolder();
                ch.nombreProducto = convertView.findViewById(R.id.textViewNombreProducto);
                ch.idProducto = convertView.findViewById(R.id.textViewIdProducto);
                ch.precioProducto = convertView.findViewById(R.id.textViewPrecioProducto);
                convertView.setTag(ch);
            } else {
                ch = (ProductoHolder) convertView.getTag();
            }
            //Seteo los valores en las id de los objetos que recupere por id
            Producto producto = list.get(position);
            //Log.d("soyPosition: ", String.valueOf(position));
            //Log.d("SoyLista: ", String.valueOf(list.size()));
            ch.nombreProducto.setText(producto.getNombre());
            ch.idProducto.setText("Id: "+producto.getId());
            ch.precioProducto.setText("Bs. "+ producto.getPrecio());

            return convertView;
        }

        public void refreshResultList(List<Producto> result){
            this.list = result;
            notifyDataSetChanged();
            notifyDataSetInvalidated();
        }

        public class ProductoHolder {
            TextView nombreProducto;
            TextView idProducto;
            TextView precioProducto;
        }
    }
