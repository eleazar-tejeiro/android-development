package uriel.eleazar.tejeiro.garcia.laboratoriofragmentos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import uriel.eleazar.tejeiro.garcia.laboratoriofragmentos.R;
import uriel.eleazar.tejeiro.garcia.laboratoriofragmentos.fragments.DetalleFragment;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        if(getIntent().getExtras() != null) {
            String nombre = getIntent().getExtras().getString("nombre");
            DetalleFragment detalleFragment = (DetalleFragment) getSupportFragmentManager().findFragmentById(R.id.detalleFragment);
            detalleFragment.mostrarDatos(nombre);
        }
    }
}