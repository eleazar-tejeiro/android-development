package uriel.eleazar.tejeiro.garcia.musicplayer.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

import uriel.eleazar.tejeiro.garcia.musicplayer.R;

public class MainActivity extends AppCompatActivity {
    ListView listViewSong;
    String [] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewSong = findViewById(R.id.listViewSong);
        //Ejecutar permisos y buscar canciones en el directorio
        runtimePermission();
    }

    public void runtimePermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        mostrarCanciones();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    public ArrayList<File> encuentraCancion (File archivo) {
        ArrayList <File> arrayList = new ArrayList<File>();
        File[] archivos = archivo.listFiles();
        for (File singleFile: archivos) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                arrayList.addAll(encuentraCancion(singleFile));
            } else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")) {
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }

    void mostrarCanciones() {
        final ArrayList <File> misCanciones = encuentraCancion(Environment.getExternalStorageDirectory());
        items = new String[misCanciones.size()];
        for (int i = 0; i < misCanciones.size(); i++) {
            items[i] = misCanciones.get(i).getName().toString().replace(".mp3","").replace(".wav","");
        }
        /*ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        listViewSong.setAdapter(miAdaptador);*/
        listaAdaptadorCanciones customAdaptador = new listaAdaptadorCanciones();
        listViewSong.setAdapter(customAdaptador);

        listViewSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nombreCancion = (String) listViewSong.getItemAtPosition(i);
                startActivity(new Intent (getApplicationContext(), PlayerActivity.class)
                    .putExtra("songs", misCanciones)
                    .putExtra("songname", nombreCancion)
                    .putExtra("pos", i));
            }
        });
    }

    class listaAdaptadorCanciones extends BaseAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myView = getLayoutInflater().inflate(R.layout.list_item,null);
            TextView textoCancion = myView.findViewById(R.id.txtSongName);
            textoCancion.setSelected(true);
            textoCancion.setText(items[i]);

            return myView;
        }
    }
}