package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.R;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.globales.DatosGlobales;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Producto;

public class ListadoProductos extends AppCompatActivity {
    ArrayAdapter<Producto> adaptadorProductos;
    ListView listViewProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_productos);
        listViewProductos = findViewById(R.id.listViewProducto);
        adaptadorProductos = new ArrayAdapter<Producto>(this,R.layout.support_simple_spinner_dropdown_item, DatosGlobales.productos);
        listViewProductos.setAdapter(adaptadorProductos);
    }
}
