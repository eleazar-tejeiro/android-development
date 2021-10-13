package com.example.centrovetpatitas

import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ActivityListadoConsultas : AppCompatActivity() {
    lateinit var listViewConsultas: ListView
    lateinit var listaConsultas:ArrayList<Consulta>
    lateinit var adaptadorcitoConsulta:AdaptadorConsulta
    var laId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_consultas)
        //laId = intent.extras?.getInt("laIdecita")!!
        laId = getIntent().getExtras()?.getInt("laIdecita")!!
        Log.d("soyLaId", laId.toString())
        listaConsultas = ArrayList<Consulta>()
        inicializarControles()
        cargarConsultas()
        adaptadorcitoConsulta = AdaptadorConsulta(this,listaConsultas,R.layout.layout_adaptador_consulta)
        listViewConsultas.adapter = adaptadorcitoConsulta
    }

    private fun inicializarControles() {
        listViewConsultas = findViewById(R.id.listViewConsultas)
    }

    private fun cargarConsultas() {
        try {
            val objBdAPP = BdAppVet(this, "bdveterinaria.sqlite", null, 3)
            val db: SQLiteDatabase = objBdAPP.getReadableDatabase()
            if (db != null) {
                val argumentos = arrayOf(laId.toString())
                val sql = "select * from consultas where id_mascota = ? order by fecha_cita desc"
                val datosLeido = db.rawQuery(sql,argumentos)
                listaConsultas.clear()
                while (datosLeido.moveToNext()) {
                    val consultita = Consulta(datosLeido.getInt(0), datosLeido.getInt(1),
                        datosLeido.getString(2), datosLeido.getString(3), datosLeido.getString(4),
                        datosLeido.getString(5))
                    Log.d("soyLaIdd", datosLeido.getInt(1).toString())
                    listaConsultas.add(consultita)
                }
                db.close()
                //adaptadorcito = AdaptadorContacto(this,listaMascotas,R.layout.layout_adaptador_contacto)
            } else {
                mostrarMensaje("Error al abrir base de datos")
            }
        } catch (E: java.lang.Exception) {
            mostrarMensaje("Error" + E.message)
        }
    }

    fun mostrarMensaje(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    fun btn_adicionar_consulta(view: View) {
        mostrarVentanitaModalAddConsultas()
    }

    private fun mostrarVentanitaModalAddConsultas() {
        var ventanita = AlertDialog.Builder(this)
        ventanita.setTitle("Adicionar Consultar")
        ventanita.setMessage("Adicionando consulta")
        var layoutInflado = LayoutInflater.from(this).inflate(R.layout.layout_adaptador_consultas_add,null)
        ventanita.setView(layoutInflado)
        ventanita.setPositiveButton("Adicionar", DialogInterface.OnClickListener
        { dialog, which->
            var txtNombreVeterinario = layoutInflado.findViewById<EditText>(R.id.edtNombreVeterinario)
            var txtFechaCita = layoutInflado.findViewById<EditText>(R.id.edtFechaCita)
            var txtHoraCita = layoutInflado.findViewById<EditText>(R.id.edtHoraCita)
            var txtRazonCita = layoutInflado.findViewById<EditText>(R.id.edtRazonCita)
            if (!(txtNombreVeterinario.text.toString().trim().isEmpty() &&
                        txtFechaCita.text.toString().trim().isEmpty() &&
                        txtHoraCita.text.toString().trim().isEmpty() &&
                        txtRazonCita.text.toString().trim().isEmpty())) {
                guardaConsulta(txtNombreVeterinario.text.toString(),
                    txtFechaCita.text.toString(),
                    txtHoraCita.text.toString(),
                    txtRazonCita.text.toString())
            } else {
                mostrarMensaje("Datos incorrectos")
            }
        })
        ventanita.create().show()
    }

    private fun guardaConsulta(nombreVeterinario: String, fechaCita: String, horaCita: String, razonCita: String) {
        try {
            var objBdApp = BdAppVet(this, "bdveterinaria.sqlite", null, 3)
            var db = objBdApp.getWritableDatabase()

            if (db != null) {
                var sql = "INSERT INTO consultas (id_mascota, veterinario, fecha_cita, hora_cita, razon) values('"+laId+"','"+nombreVeterinario+"','"+fechaCita+"','"+horaCita+"','"+razonCita+"')"
                db.execSQL(sql)
                actualizarListado()
                mostrarMensaje("Consulta guardada correctamente! ")
                db.close()
            } else {
                mostrarMensaje("Error al abrir la base de datos!!")
            }
        } catch (E: Exception) {
            mostrarMensaje("ERROR " + E.message)
        }
    }

    private fun actualizarListado() {
        adaptadorcitoConsulta.refreshResultList(listaConsultas)
        cargarConsultas()
        //adaptadorcito = AdaptadorContacto(this, listaMascotas, R.layout.layout_adaptador_contacto)
        //TODO Creo que adaptadorcito invalidateViews es el que sirve sin embargo según había que llamar refreshResultList justo después de añadir a la BD
        adaptadorcitoConsulta.refreshResultList(listaConsultas)
        listViewConsultas.invalidateViews()
        listViewConsultas.adapter = adaptadorcitoConsulta
        //Log.d("youuu","SI llego pa")

    }
}