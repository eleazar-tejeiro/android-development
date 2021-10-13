package com.uriel.tejeiro.parcial2cobrad2.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.uriel.tejeiro.parcial2cobrad2.R;
import com.uriel.tejeiro.parcial2cobrad2.globales.DatosGlobales;
import com.uriel.tejeiro.parcial2cobrad2.modelos.Deuda;
import com.uriel.tejeiro.parcial2cobrad2.modelos.Estado;
import com.uriel.tejeiro.parcial2cobrad2.modelos.Usuario;
import com.uriel.tejeiro.parcial2cobrad2.servicio.API;
import com.uriel.tejeiro.parcial2cobrad2.servicio.IServicioUsuario;
import java.sql.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListadoDeudores extends AppCompatActivity {
    ArrayAdapter<Deuda> adaptadorDeudas;
    ListView listViewDeudas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_deudores);
        listViewDeudas = findViewById(R.id.listViewDeudores);
        adaptadorDeudas = new ArrayAdapter<Deuda>(this,R.layout.support_simple_spinner_dropdown_item, DatosGlobales.deudasCliente);
        listViewDeudas.setAdapter(adaptadorDeudas);
        registerForContextMenu(listViewDeudas);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_contextual_deudas,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo infoItem;
        infoItem = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_context_pagar_cuota:
                int pos = infoItem.position;
                Toast.makeText(this, "Pago Cuota y "+pos, Toast.LENGTH_SHORT).show();
                consumir_servicio_adicionarCobranza(pos);
                adaptadorDeudas.notifyDataSetChanged();
                listViewDeudas.invalidateViews();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void consumir_servicio_adicionarCobranza(int pos) {
        Estado estado;
        int ciCobrador = Integer.parseInt(DatosGlobales.cobradorLogueado.getNro_ci());
        String nombreCobrador = DatosGlobales.cobradorLogueado.getNombre();
        int nroDeuda = DatosGlobales.deudasCliente.get(pos).getNro_deuda();
        float montoCobrado = DatosGlobales.deudasCliente.get(pos).getMonto();

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat spf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaCobranza = spf.format(dt);

        DatosGlobales.servicioUsuario = API.getAPI().create(IServicioUsuario.class);
        DatosGlobales.estadoRecibido = DatosGlobales.servicioUsuario.postServicioCobranzas(ciCobrador,nombreCobrador,nroDeuda,montoCobrado,fechaCobranza.toString());
        DatosGlobales.estadoRecibido.enqueue(new Callback<Estado>() {
            @Override
            public void onResponse(Call<Estado> call, Response<Estado> response) {
                try {
                    Toast.makeText(ListadoDeudores.this,response.message(), Toast.LENGTH_SHORT).show();
                    if(response.isSuccessful()) {
                        Estado estado  = response.body();
                        // Toast.makeText(MainActivity.this, productos.get(0).getNombre(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(ListadoDeudores.this, estado.getEstado(), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception E)
                {
                    Toast.makeText(ListadoDeudores.this, E.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Paso","Me pierdo con: "+E.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Estado> call, Throwable t) {
                Log.e("Paso","Me pierdo con: "+t.getMessage());
            }
        });


    }
}