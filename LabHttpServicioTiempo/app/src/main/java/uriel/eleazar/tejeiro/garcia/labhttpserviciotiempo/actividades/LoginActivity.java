package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.R;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.globales.DatosGlobales;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Producto;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Usuario;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio.APIPRODUCTOS;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio.IServicioProducto;

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
        DatosGlobales.servicioProducto = APIPRODUCTOS.getAPI().create(IServicioProducto.class);
        DatosGlobales.usuarioRecibido = DatosGlobales.servicioProducto.getServicioLoginUsuario(login,password);
        DatosGlobales.usuarioRecibido.enqueue(new Callback<ArrayList<Usuario>>() {
            @Override
            public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                if(response.isSuccessful()){
                    DatosGlobales.cobradorLogueado = response.body().get(0);
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
            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}