package com.uriel.tejeiro.parcial2cobrad2.actividades;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.uriel.tejeiro.parcial2cobrad2.R;
import com.uriel.tejeiro.parcial2cobrad2.globales.DatosGlobales;
import com.uriel.tejeiro.parcial2cobrad2.modelos.Usuario;
import com.uriel.tejeiro.parcial2cobrad2.servicio.API;
import com.uriel.tejeiro.parcial2cobrad2.servicio.IServicioUsuario;

public class LoginActivity extends AppCompatActivity {
    EditText editTextLoginUsuario;
    EditText editTextPasswordUsuario;
    Button buttonLoguear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextLoginUsuario = findViewById(R.id.editTextUsuarioLogin);
        editTextPasswordUsuario = findViewById(R.id.editTextTextPassword);
        buttonLoguear = findViewById(R.id.buttonLoguear);
        buttonLoguear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarLogin();
            }
        });
    }

    private void iniciarLogin() {
        String login = editTextLoginUsuario.getText().toString();
        String password = editTextPasswordUsuario.getText().toString();
        //consumiendo el servicio
        DatosGlobales.servicioUsuario = API.getAPI().create(IServicioUsuario.class);
        DatosGlobales.usuarioRecibido = DatosGlobales.servicioUsuario.getServicioLoginUsuario(login,password);
        DatosGlobales.usuarioRecibido.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    DatosGlobales.cobradorLogueado = response.body().get(0);
                    Log.e("Paso","LLEGO PERRO con: "+DatosGlobales.cobradorLogueado);
                    if(DatosGlobales.cobradorLogueado.getEstado().equals("ACTIVO")){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Error Usuario no valido", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(LoginActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Paso","ME PERDISTE con: "+call.request().toString());
            }
        });
    }


}