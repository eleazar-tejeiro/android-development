package uriel.eleazar.tejeiro.garcia.labhilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnProceso1;
    Button btnProceso2;
    TextView textViewAvance;
    ListView listViewDatos;
    Integer[] vDatos;
    TareaProceso2 procesito;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializando variables
        btnProceso1 = findViewById(R.id.btnTarea1);
        btnProceso2 = findViewById(R.id.btnTarea2);
        textViewAvance = findViewById(R.id.textViewAvance);
        listViewDatos = findViewById(R.id.listViewAvance);
        vDatos = new  Integer [20000];
        //---------------------------------
        //cargarDatosArray();
    }

    private void cargarDatosArray() {
        for (int i = 0; i < vDatos.length; i++) {
            Random rdn = new Random();
            vDatos[i] =  rdn.nextInt(50000)+1;

        }
    }

    public void tarea2_click(View view) {
        cargarDatosArray();
        procesito = new TareaProceso2(this.vDatos);
        procesito.execute();
    }

    public void tarea_cancelar(View view) {
        if (procesito != null) {
            procesito.cancel(true);
            Toast.makeText(this, "Se ha cancelado el proceso 2.", Toast.LENGTH_SHORT).show();
        }
    }

    public void tarea_click(View view) {
        cargarDatosArray();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        ordenarBurbuja(vDatos);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Ordenado", Toast.LENGTH_SHORT).show();
                                textViewAvance.setText("100%");
                            }
                        });
                    }
                }
        ).start();
    }

    public Integer[] ordenarBurbuja(Integer[] datos) {
        for (int i = 0; i < datos.length - 1; i++) {
            for (int j = 0; j < datos.length - 1; j++) {
                if (datos[j] > datos [j+1]) {
                    int aux = datos[j];
                    datos[j] = datos[j+1];
                    datos[j+1] = aux;
                }
            }
        }
        return datos;
    }

    private class TareaProceso2 extends AsyncTask<Void, Integer, Integer[]> {
        //Atributos
        private Integer [] datos;
        public TareaProceso2(Integer []pDatos) {
            this.datos = pDatos;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textViewAvance.setText("0 %");
            listViewDatos.setAdapter(null);
        }

        @Override
        protected Integer[] doInBackground(Void... voids) {
            for (int i = 0; i < datos.length - 1; i++) {
                for (int j = 0; j < datos.length - 1; j++) {
                    if (datos[j] > datos [j+1]) {
                        int aux = datos[j];
                        datos[j] = datos[j+1];
                        datos[j+1] = aux;
                    }
                }
                int porcentajeAvance = (i+1)*100/datos.length;
                if(!isCancelled())
                    publishProgress(porcentajeAvance);
            }

            return datos;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textViewAvance.setText(values[0].toString() + " %");
        }

        @Override
        protected void onPostExecute(Integer[] integers) {
            super.onPostExecute(integers);
            textViewAvance.setText("100 %");
            ArrayAdapter<Integer> adaptador = new ArrayAdapter<Integer>(MainActivity.this, android.R.layout.simple_list_item_1,integers);
            listViewDatos.setAdapter(adaptador);
        }
    }


}