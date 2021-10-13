package com.uriel.tejeiro.parcial2cobrad2.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uriel.tejeiro.parcial2cobrad2.R;
import com.uriel.tejeiro.parcial2cobrad2.globales.DatosGlobales;
import com.uriel.tejeiro.parcial2cobrad2.modelos.Deuda;
import com.uriel.tejeiro.parcial2cobrad2.servicio.API;
import com.uriel.tejeiro.parcial2cobrad2.servicio.IServicioUsuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText editTextCiDeudor;
    Button btnBuscarCi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatosGlobales.deudasCliente = new ArrayList<Deuda>();
        editTextCiDeudor = findViewById(R.id.editTextCiDeudor);
        btnBuscarCi = findViewById(R.id.buttonBuscarDeudas);
        btnBuscarCi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumir_servicio_deudas();
            }
        });
    }

    private void consumir_servicio_deudas() {
        String ciDeudor = editTextCiDeudor.getText().toString();
        DatosGlobales.servicioUsuario = API.getAPI().create(IServicioUsuario.class);
        DatosGlobales.callDeudasCliente = DatosGlobales.servicioUsuario.getServicioTraerDeudas(ciDeudor);
        DatosGlobales.callDeudasCliente.enqueue(new Callback<ArrayList<Deuda>>() {
            @Override
            public void onResponse(Call<ArrayList<Deuda>> call, Response<ArrayList<Deuda>> response) {
                try {
                    if(response.isSuccessful()) {

                        DatosGlobales.deudasCliente = response.body();
                        // Toast.makeText(MainActivity.this, productos.get(0).getNombre(), Toast.LENGTH_SHORT).show();
                        mostrarListadoDeudas();
                    }

                }
                catch (Exception E)
                {
                    Toast.makeText(MainActivity.this, E.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Deuda>> call, Throwable t) {

            }
        });

    }

    private void mostrarListadoDeudas() {
        Intent intent = new Intent(this,ListadoDeudores.class);
        startActivity(intent);
    }
}