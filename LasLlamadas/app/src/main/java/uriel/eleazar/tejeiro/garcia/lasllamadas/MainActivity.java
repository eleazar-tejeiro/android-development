package uriel.eleazar.tejeiro.garcia.lasllamadas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextNroTelefono;
    EditText editTextPaginaWeb;
    EditText editTextEnviaWpp;
    ImageButton imageButtonLlamar;
    ImageButton imageButtonNavegar;
    ImageButton imageButtonEnviaWpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarControles();
        imageButtonLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nroTelefono = editTextNroTelefono.getText().toString();
                if (!nroTelefono.isEmpty()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //VERSIONES NUEVAS
                        if (verificarPermiso(Manifest.permission.CALL_PHONE)) {
                            Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + nroTelefono));
                            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                            {
                                Log.d("miTag","Llegooo 1");
                                return;
                            }
                            startActivity(intentLlamada);

                        } else {
                            //NO TENEMOS EL PERMISO
                            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 6);
                            } else {
                                //ANTES YA SE LE PREGUNTO POR EL PERMISO Y RECHAZO
                                Toast.makeText(MainActivity.this, "Active el permiso para llamadas en el settings de la plicacion", Toast.LENGTH_SHORT).show();
                                Intent intentConfiguracion = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                                intentConfiguracion.addCategory(Intent.CATEGORY_DEFAULT);
                                intentConfiguracion.setData(Uri.parse("package" + getPackageName()));
                                intentConfiguracion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                /*intentConfiguracion.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                intentConfiguracion.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);*/
                                startActivity(intentConfiguracion);
                            }
                        }

                    } else {
                        //LLAMAR DE ACUERDO A LAS VERSIONES ANTIGUAS
                        //Toast.makeText(MainActivity.this, "Llamar version antigua de android", Toast.LENGTH_SHORT).show();
                        llamarVersionMenorAPI23(nroTelefono);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "ESCRIBE UN NUMERO", Toast.LENGTH_SHORT).show();
                }
            }

            private void llamarVersionMenorAPI23(String nroTelefono) {
                Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+nroTelefono));
                if(verificarPermiso(Manifest.permission.CALL_PHONE)) {
                    if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                        return;
                    startActivity(intentLlamada);
                }
            }
        });
        imageButtonNavegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextPaginaWeb.getText().toString();
                if(!url.isEmpty()) {
                    Intent intentWeb = new Intent();
                    intentWeb.setAction(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse("http://"+url));
                    startActivity(intentWeb);
                }
            }
        });
        imageButtonEnviaWpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensaje = editTextEnviaWpp.getText().toString();
                if(!mensaje.isEmpty()) {
                    try {
                        PackageManager pm = getPackageManager();
                        //Aqui establezco que el administrador de paquetes de android compare si existe un paquete con la firma com.whatsapp
                        //de no existir la excepcion del try catch salta
                        pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                        Intent intentEnviaWpp = new Intent();
                        //establezco la accion enviar
                        intentEnviaWpp.setAction(Intent.ACTION_SEND);
                        //establezco el mensaje como un extra del intent, de este modo cuando el intent de whatsapp abra vendra con el mensaje
                        intentEnviaWpp.putExtra(Intent.EXTRA_TEXT, mensaje);
                        intentEnviaWpp.setType("text/plain");
                        //establezco el paquete a abrir con el intent, en este caso whatsapp
                        intentEnviaWpp.setPackage("com.whatsapp");
                        //nuevamente pregunto si la actividad se resolvera, es decir si el intent tiene el paquete establecido y este existe
                        //en pocas palabras nuevamente comprueba si existe whatsapp en el dispositivo
                        //de no ser así termina con la ejecución
                        if (intentEnviaWpp.resolveActivity(getPackageManager()) == null)
                            return;
                        startActivity(intentEnviaWpp);
                    }   catch (PackageManager.NameNotFoundException e) {
                        Toast.makeText(MainActivity.this, "WhatsApp no está instalado en tu dispositivo.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private boolean verificarPermiso(String permisoARevisar) {
        if (this.checkCallingOrSelfPermission(permisoARevisar) == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }


    private void inicializarControles() {
        editTextNroTelefono = findViewById(R.id.editTextNroTelefono);
        editTextPaginaWeb = findViewById(R.id.editTextPaginaWeb);
        editTextEnviaWpp = findViewById(R.id.editTextMensajeWpp);
        imageButtonLlamar = findViewById(R.id.imageButtonLlamar);
        imageButtonNavegar = findViewById(R.id.imageButtonNavegar);
        imageButtonEnviaWpp = findViewById(R.id.imageButtonEnviaWpp);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 6:
                String permiso = permissions[0];
                int resultado = grantResults[0];
                if (resultado == PackageManager.PERMISSION_GRANTED) {
                    String nroTelefono = editTextNroTelefono.getText().toString();
                    Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + nroTelefono));
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, requestCode);
                        Log.d("miTag","Llegooo 2");
                        return;
                    }
                    startActivity(intentLlamada);
                } else {
                    Log.d("elNuncaTag","Nunca llego y requestCode es: "+requestCode);
                    Toast.makeText(MainActivity.this, "No se concedio la llamada", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(this, "Permiso no definido", Toast.LENGTH_SHORT).show();

        }
    }
}