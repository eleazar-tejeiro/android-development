package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.R;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.globales.DatosGlobales;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Estado;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Producto;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio.APIPRODUCTOS;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio.IServicioProducto;

public class AdicionarProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_producto);
        Button btnGuardarProducto = findViewById(R.id.buttonGuardarProducto);
        btnGuardarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumir_servicio_adicionarProducto();
            }
        });
    }

    private void consumir_servicio_adicionarProducto() {
        Estado estado;
        EditText editTextNombre = findViewById(R.id.editTextNombre);
        EditText editTextPrecio = findViewById(R.id.editTextPrecio);
        String nombre = editTextNombre.getText().toString();
        float precio = Float.parseFloat(editTextPrecio.getText().toString());
        DatosGlobales.servicioProducto = APIPRODUCTOS.getAPI().create(IServicioProducto.class);
        DatosGlobales.estadoRecibido = DatosGlobales.servicioProducto.postServicioProductos(nombre,precio);
        DatosGlobales.estadoRecibido.enqueue(new Callback<Estado>() {
            @Override
            public void onResponse(Call<Estado> call, Response<Estado> response) {
                try {
                    Toast.makeText(AdicionarProducto.this,response.message(), Toast.LENGTH_SHORT).show();
                    if(response.isSuccessful()) {
                         Estado estado  = response.body();
                        // Toast.makeText(MainActivity.this, productos.get(0).getNombre(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(AdicionarProducto.this, estado.getEstado(), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception E)
                {
                    Toast.makeText(AdicionarProducto.this, E.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Estado> call, Throwable t) {

            }
        });


    }
}
