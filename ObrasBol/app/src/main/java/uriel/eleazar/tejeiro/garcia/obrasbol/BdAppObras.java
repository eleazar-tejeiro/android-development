package uriel.eleazar.tejeiro.garcia.obrasbol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BdAppObras extends SQLiteOpenHelper {

    static final String crearTabla1 = "CREATE TABLE obras (" +
            "id integer not null primary key autoincrement,"+
            "nombre_obra text not null,"+
            "descripcion_obra text not null,"+
            "fecha_inicio text not null,"+
            "estado_obra text not null,"+
            "nombre_responsable text not null)";

    static final String crearTabla2 = "CREATE TABLE herramientas (" +
            "id integer primary key autoincrement,"+
            "id_obra integer not null,"+
            "nombre_herramienta text not null,"+
            "descripcion_herramienta text not null,"+
            "fecha_prestamo text not null,"+
            "estado_herramienta text not null,"+
            "foreign key (id_obra) references obras(id))";

    public BdAppObras(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearTabla1);
        db.execSQL(crearTabla2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table obras");
        db.execSQL("drop table herramientas");
        db.execSQL(crearTabla1);
        db.execSQL(crearTabla2);
    }
}
