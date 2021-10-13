package com.example.centrovetpatitas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BdAppVet extends SQLiteOpenHelper {
    static final String crearTabla1 = "CREATE TABLE patitasVet (" +
            "id integer not null primary key autoincrement,"+
            "nombre text not null,"+
            "especie text not null,"+
            "genero text not null,"+
            "raza text not null,"+
            "edad text not null,"+
            "peso real not null,"+
            "esterilizacion text not null,"+
            "vacunacion text not null)";

    static final String crearTabla2 = "CREATE TABLE consultas (" +
            "id integer primary key autoincrement,"+
            "id_mascota integer not null,"+
            "veterinario text not null,"+
            "fecha_cita text not null,"+
            "hora_cita text not null,"+
            "razon text not null,"+
            "foreign key (id_mascota) references patitasVet(id))";

    public BdAppVet(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearTabla1);
        db.execSQL(crearTabla2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table patitasVet");
        db.execSQL("drop table consultas");
        db.execSQL(crearTabla1);
        db.execSQL(crearTabla2);
    }
}
