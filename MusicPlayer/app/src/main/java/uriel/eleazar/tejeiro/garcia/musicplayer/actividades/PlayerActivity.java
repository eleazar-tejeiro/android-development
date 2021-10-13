package uriel.eleazar.tejeiro.garcia.musicplayer.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;

import soup.neumorphism.NeumorphImageView;
import soup.neumorphism.ShapeType;
import uriel.eleazar.tejeiro.garcia.musicplayer.R;

public class PlayerActivity extends AppCompatActivity {
    NeumorphImageView btnPlayPause, btnNext, btnPrevious, btnFastForward, btnFastRewind;
    TextView txtNombre, txtInicio, txtFinal;
    SeekBar seekMusic;

    String sName;
    public static final String EXTRA_NAME = "song_name";
    static MediaPlayer reproductorcito;
    int posicion;
    ArrayList<File> misCanciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        inicializarControles();

        if (reproductorcito != null) {
            reproductorcito.stop();
            reproductorcito.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        misCanciones = (ArrayList) bundle.getParcelableArrayList("songs");
        String songName = i.getStringExtra("songname");
        posicion = bundle.getInt("pos", 0);
        txtNombre.setSelected(true);
        Uri uri = Uri.parse(misCanciones.get(posicion).toString());
        sName = misCanciones.get(posicion).getName();
        txtNombre.setText(sName);

        reproductorcito = MediaPlayer.create(getApplicationContext(),uri);
        reproductorcito.start();

        btnPlayPause.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    btnPlayPause.setShapeType(ShapeType.PRESSED);
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    btnPlayPause.setShapeType(ShapeType.BASIN);
                return true;
            }
        });

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reproductorcito.isPlaying()) {
                    btnPlayPause.setImageResource(R.drawable.ic_play);
                    reproductorcito.pause();
                } else {
                    btnPlayPause.setImageResource(R.drawable.ic_pause);
                    reproductorcito.start();
                }
            }
        });
    }

    private void inicializarControles() {
        btnPlayPause = findViewById(R.id.imagePlayPause);
        btnFastRewind = findViewById(R.id.imageFastRewind);
        btnPrevious = findViewById(R.id.imagePrevious);
        btnNext = findViewById(R.id.imageNext);
        btnFastForward = findViewById(R.id.imageNext);
        txtNombre = findViewById(R.id.textCancion);
        txtInicio = findViewById(R.id.txtInicio);
        txtFinal = findViewById(R.id.txtFinal);
        seekMusic = findViewById(R.id.seekBarSong);
    }
}