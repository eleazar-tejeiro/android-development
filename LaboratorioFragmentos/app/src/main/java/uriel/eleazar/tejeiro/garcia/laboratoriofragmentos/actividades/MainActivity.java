package uriel.eleazar.tejeiro.garcia.laboratoriofragmentos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import uriel.eleazar.tejeiro.garcia.laboratoriofragmentos.R;
import uriel.eleazar.tejeiro.garcia.laboratoriofragmentos.fragments.DetalleFragment;
import uriel.eleazar.tejeiro.garcia.laboratoriofragmentos.fragments.MaestroFragment;

public class MainActivity extends FragmentActivity implements MaestroFragment.DataListener {
    private boolean esMultiPantalla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportFragmentManager().findFragmentById(R.id.detalleFragment) != null) {
            esMultiPantalla = false;
        } else {
            esMultiPantalla = true;
        }
    }

    @Override
    public void enviarDatos(String nombre) {
        if (!esMultiPantalla) {
            DetalleFragment detalleFragment = (DetalleFragment) getSupportFragmentManager().findFragmentById(R.id.detalleFragment);
            detalleFragment.mostrarDatos(nombre);
        } else {
            Intent elIntent = new Intent(this, DetalleActivity.class);
            elIntent.putExtra("nombre",nombre);
            startActivity(elIntent);
        }
    }
}