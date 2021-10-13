package com.example.labsharedpref

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    //var prefe = getSharedPreferences("preferencias", MODE_PRIVATE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var prefe = getSharedPreferences("preferencias", MODE_PRIVATE)
        leerPreferencias(prefe)
    }

    fun leerPreferencias(prefiero:SharedPreferences) {
        var editaMail = findViewById<EditText>(R.id.cajaMail)
        var editaPin = findViewById<EditText>(R.id.cajaPin)

        editaMail.setText(prefiero.getString("email",""))
        editaPin.setText(prefiero.getInt("pin",0).toString())
    }

    fun porIniciar(view:View) {
        var editaMail = findViewById<EditText>(R.id.cajaMail)
        var editaPin = findViewById<EditText>(R.id.cajaPin)
        var prefe = getSharedPreferences("preferencias", MODE_PRIVATE)
        var editor = prefe.edit()
        editor.putString("email",editaMail.getText().toString())
        editor.putInt("pin",editaPin.getText().toString().toInt())
        editor.commit()
        finish()
    }
}