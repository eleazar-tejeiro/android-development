package com.example.apptusaldo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast

class Login : AppCompatActivity() {
    var esPrimerIngreso = false
    lateinit var edtLogin:EditText
    lateinit var edtPassword1:EditText
    lateinit var edtPassword2:EditText
    lateinit var posSwitch:Switch
    lateinit var preferencias:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inicializarControles()
        leerUsuarioAlmacenado()
    }

    fun leerUsuarioAlmacenado() {
        var loginGuardado = preferencias.getString("login","")
        var passGuardado = preferencias.getString("password","")
        var rememberMe = preferencias.getBoolean("recuerdame",false)

        /*Log.d("Pendejo","Soy: "+posSwitch.isChecked)
        posSwitch.setOnCheckedChangeListener { _, isChecked ->  if (isChecked) Log.d("MyTag", "Si entro gooon")}*/

        if(!loginGuardado.isNullOrEmpty()) {
            if(rememberMe) {
                posSwitch.setChecked(true)
                edtLogin.setText(loginGuardado)
                edtPassword1.setText(passGuardado)
                edtPassword2.visibility = View.INVISIBLE
                esPrimerIngreso = false
            } else {
                posSwitch.setChecked(false)
                //edtLogin.setText(loginGuardado)
                edtPassword2.visibility = View.INVISIBLE
                esPrimerIngreso = false
            }
        } else {
            edtPassword2.visibility = View.VISIBLE
            esPrimerIngreso = true
        }
    }

    fun inicializarControles() {
        preferencias = getSharedPreferences("laInformacion", Context.MODE_PRIVATE)
        edtLogin = findViewById<EditText>(R.id.cajaUsuario)
        edtPassword1 = findViewById<EditText>(R.id.cajaPassword1)
        edtPassword2 = findViewById<EditText>(R.id.cajaPassword2)
        posSwitch = findViewById<Switch>(R.id.switchRemember)
    }


    fun btnIngresar_Click(view:View) {
        var loginGuardado = preferencias.getString("login","")
        var passGuardado = preferencias.getString("password","")
        var rememberMe = preferencias.getBoolean("recuerdame",false)

        var login = edtLogin.getText().toString().trim()
        var pass = edtPassword1.getText().toString().trim()
        var confPass = edtPassword2.getText().toString().trim()
        if(esPrimerIngreso) {
           if (login.length >= 4 && pass.length >= 4 && pass.equals(confPass)) {
                guardaPreferencias()
                llamarPantallaPrincipal()
            } else {
                Toast.makeText(this, "La contraseña debe ser mayor o igual a 4 caracteres y ambas contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
            }
        } else {
            if(login.equals(loginGuardado) && pass.equals(passGuardado))
                llamarPantallaPrincipal()
            else
                Toast.makeText(this, "Error datos incorrectos!!!", Toast.LENGTH_SHORT).show()
        }
        recordameOme()
    }

    fun llamarPantallaPrincipal() {
        var intent = Intent(this, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun guardaPreferencias() {
        var elEditor = preferencias.edit()
        elEditor.putString("login",edtLogin.getText().toString().trim())
        elEditor.putString("password",edtPassword1.getText().toString().trim())
        elEditor.putBoolean("recuerdame",posSwitch.isChecked)
        elEditor.commit()
    }

    fun recordameOme() {
        var elEditor = preferencias.edit()
        elEditor.putBoolean("recuerdame",posSwitch.isChecked)
        elEditor.commit()
    }
}