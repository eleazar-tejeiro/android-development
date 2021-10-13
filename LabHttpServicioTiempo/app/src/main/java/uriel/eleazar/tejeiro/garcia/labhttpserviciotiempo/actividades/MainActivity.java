package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.R;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.globales.DatosGlobales;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Ciudad;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Producto;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio.API;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio.APIPRODUCTOS;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio.IServicioCiudad;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio.IServicioProducto;

public class MainActivity extends AppCompatActivity {
    EditText editTextCiudaBusqueda;
    TextView textViewNombre;
    TextView textViewDescripcion;
    TextView textViewTemperatura;
    ImageView imageViewClima;
    ListView listViewProductos;
    Button btnTraerProductos;
    Button btnConsultarServicio;
    Button btnAdicionarProducto;
    IServicioCiudad servicioCiudad;
    //IServicioProducto servicioProducto;
    Call<Ciudad> ciudadRecibida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarControles();
        DatosGlobales.productos = new ArrayList<Producto>();

        if(DatosGlobales.cobradorLogueado.getEstado().equals("ACTIVO")){
            editTextCiudaBusqueda.setText(DatosGlobales.cobradorLogueado.getNombre());
        }

        //configurarndo el manejo del servicio
        servicioCiudad = API.getAPI().create(IServicioCiudad.class);
        btnConsultarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumir_servicio();
            }
        });


        btnTraerProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumir_servicio_productos();
            }
        });

        btnAdicionarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdicionarProducto.class);
                startActivity(intent);
            }
        });

    }
    private void consumir_servicio() {
        ciudadRecibida = servicioCiudad.getServicioCiudad(editTextCiudaBusqueda.getText().toString(),API.APIKEY,"metric","es");
        ciudadRecibida.enqueue(new Callback<Ciudad>() {
            @Override
            public void onResponse(Call<Ciudad> call, Response<Ciudad> response) {
                try {

                    Ciudad ciudad = response.body();//donde actual el deserializer
                    if (ciudad == null)
                        ciudad = new Ciudad(0, "no existe", "", 0, "", "04n");
                    textViewNombre.setText(ciudad.getName());
                    textViewDescripcion.setText(ciudad.getDescription());
                    textViewTemperatura.setText(ciudad.getTemperatura() + " º");
                    Picasso.with(MainActivity.this).load(API.URL_BASE_ICONOS + ciudad.getIcono() + API.FORMATO_IMAGEN).into(imageViewClima);
                }
                catch (Exception E)
                {
                    Toast.makeText(MainActivity.this, "Error:"+E.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Ciudad> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void consumir_servicio_productos() {
        DatosGlobales.servicioProducto = APIPRODUCTOS.getAPI().create(IServicioProducto.class);
        DatosGlobales.productosRecibidos = DatosGlobales.servicioProducto.getServicioProductos();
        DatosGlobales.productosRecibidos.enqueue(new Callback<ArrayList<Producto>>() {
            @Override
            public void onResponse(Call<ArrayList<Producto>> call, Response<ArrayList<Producto>> response) {
                try {
                    if(response.isSuccessful()) {

                        DatosGlobales.productos = response.body();
                        // Toast.makeText(MainActivity.this, productos.get(0).getNombre(), Toast.LENGTH_SHORT).show();
                        mostrarListadoProductos();
                    }

                }
                catch (Exception E)
                {
                    Toast.makeText(MainActivity.this, E.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Producto>> call, Throwable t) {

            }
        });

    }

    private void mostrarListadoProductos() {
        Intent intent = new Intent(this,ListadoProductos.class);
        startActivity(intent);
    }

    private void iniciarControles() {
        editTextCiudaBusqueda = findViewById(R.id.editTextCiudadBusqueda);
        textViewNombre = findViewById(R.id.textViewCiudad);
        textViewDescripcion = findViewById(R.id.textViewDescripcion);
        textViewTemperatura = findViewById(R.id.textViewTemperatura);
        imageViewClima = findViewById(R.id.imageViewCiudad);
        btnConsultarServicio = findViewById(R.id.buttonConsultar);
        btnTraerProductos = findViewById(R.id.buttonTraerProductos);
        btnAdicionarProducto = findViewById(R.id.buttonAdicionarProducto);
        //listViewProductos = findViewById(R.id.listView1);
    }
}
