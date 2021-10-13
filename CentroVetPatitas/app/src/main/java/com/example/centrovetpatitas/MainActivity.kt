package com.example.centrovetpatitas

import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var edtNombre:EditText
    lateinit var edtEspecie:EditText
    lateinit var edtGenero:EditText
    lateinit var edtRaza:EditText
    lateinit var edtEdad:EditText
    lateinit var edtPeso:EditText
    lateinit var edtEsterilizado:ToggleButton
    lateinit var edtVacunado:ToggleButton
    lateinit var edtId:EditText
    lateinit var objLista:ArrayList<Mascota>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNombre = findViewById(R.id.cajaNombre)
        edtEspecie = findViewById(R.id.cajaEspecie)
        edtGenero = findViewById(R.id.cajaGenero)
        edtRaza = findViewById(R.id.cajaRaza)
        edtEdad = findViewById(R.id.cajaEdad)
        edtPeso = findViewById(R.id.cajaPeso)
        edtEsterilizado = findViewById(R.id.opcionEsterilizado)
        edtVacunado = findViewById(R.id.opcionVacunado)
        edtId = findViewById(R.id.cajaId)
        objLista = ArrayList<Mascota>()
    }

    fun btnGuardar_Click(view: View){
        try {
            var objBdApp = BdAppVet(this, "bdveterinaria.sqlite", null, 3)
            var db = objBdApp.getWritableDatabase()
            if (db != null) {
                var nombre = edtNombre.getText().toString()
                var especie = edtEspecie.getText().toString()
                var genero = edtGenero.getText().toString()
                var raza = edtRaza.getText().toString()
                var edad = edtEdad.getText().toString()
                var peso = edtPeso.getText().toString()
                var esterilizado = edtEsterilizado.isChecked().toString()
                var vacunado = edtVacunado.isChecked().toString()
                var sql = "INSERT INTO patitasVet (nombre, especie, genero, raza, edad, peso, esterilizacion, vacunacion) values('"+nombre+"','"+especie+"','"+genero+"','"+raza+"','"+edad+"','"+peso+"','"+esterilizado+"','"+vacunado+"')"
                db.execSQL(sql)
                mostrarMensaje("Animal guardado correctamente! ")
                limpiarEntradas()
                db.close()
            } else {
                mostrarMensaje("Error al abrir la base de datos!!")
            }
        } catch (E: Exception) {
                mostrarMensaje("ERROR " + E.message)
        }
    }

    fun btnTraerXId_Click(view: View){
        try {
            var objBdApp = BdAppVet(this, "bdveterinaria.sqlite", null, 3)
            var db = objBdApp.getReadableDatabase()
            if (db != null) {
                var id = edtId.getText().toString()
                var argumentos = arrayOf(id)
                var sql = "SELECT * FROM patitasVet where id = ?"
                var datosLeidos = db.rawQuery(sql, argumentos)
                if (datosLeidos.moveToFirst()) {
                    edtNombre.setText(datosLeidos.getString(1))
                    edtEspecie.setText(datosLeidos.getString(2))
                    edtGenero.setText(datosLeidos.getString(3))
                    edtRaza.setText(datosLeidos.getString(4))
                    edtEdad.setText(datosLeidos.getString(5))
                    edtPeso.setText(datosLeidos.getString(6))
                    edtEsterilizado.setChecked(datosLeidos.getString(7).toBoolean())
                    edtVacunado.setChecked(datosLeidos.getString(8).toBoolean())
                } else {
                    limpiarEntradas()
                    mostrarMensaje("No existe ese animal en la base de datos. ")
                }
                db.close()
            } else {
                mostrarMensaje("Error al abrir la base de datos!!")
            }
        } catch (E: Exception) {
            mostrarMensaje("ERROR " + E.message)
        }
    }

    fun btnBuscarXNombre_Click(view: View?) {
        try {
            val objBdAPP = BdAppVet(this, "bdveterinaria.sqlite", null, 3)
            val db: SQLiteDatabase = objBdAPP.getReadableDatabase()
            if (db != null) {
                val nombre = edtNombre.text.toString()
                val argumentos = arrayOfNulls<String>(1)
                argumentos[0] = "%$nombre%"
                val sql = "select * from patitasVet where nombre like ?"
                val datosLeido = db.rawQuery(sql, argumentos)
                objLista.clear()
                while (datosLeido.moveToNext()) {
                    val mascotita = Mascota(datosLeido.getInt(0), datosLeido.getString(1),
                            datosLeido.getString(2), datosLeido.getString(3), datosLeido.getString(4),
                            datosLeido.getString(5), datosLeido.getDouble(6), datosLeido.getString(7), datosLeido.getString(8))
                    objLista.add(mascotita)
                }
                db.close()
                mostrarListado()
            } else {
                mostrarMensaje("Error al abrir base de datos")
            }
        } catch (E: java.lang.Exception) {
            mostrarMensaje("Error" + E.message)
        }
    }

    private fun mostrarListado() {
        val nombrePedidos = arrayOfNulls<CharSequence>(objLista.size)
        for (i in 0 until objLista.size) {
            nombrePedidos[i] = objLista.get(i).getNombre() as CharSequence
        }
        val ventanita = AlertDialog.Builder(this)
        ventanita.setTitle("Mascotas encontradas")
        ventanita.setItems(nombrePedidos, DialogInterface.OnClickListener
        { dialog, which ->
            edtId.setText(objLista.get(which).getId().toString() + "")
            edtNombre.setText(objLista.get(which).getNombre().toString() + "")
            edtEspecie.setText(objLista.get(which).getEspecie().toString() + "")
            edtGenero.setText(objLista.get(which).getGenero().toString() + "")
            edtRaza.setText(objLista.get(which).getRaza().toString() + "")
            edtEdad.setText(objLista.get(which).getEdad().toString() + "")
            edtPeso.setText(objLista.get(which).getPeso().toString() + "")
            edtEsterilizado.setChecked(objLista.get(which).isEsterilizado().toBoolean())
            edtVacunado.setChecked(objLista.get(which).isVacunado().toBoolean())
        })
        ventanita.show()
    }

    fun btnModificar_Click(view: View?) {
        try {
            val objBdAPP = BdAppVet(this, "bdveterinaria.sqlite", null, 3)
            val db: SQLiteDatabase = objBdAPP.getWritableDatabase()
            if (db != null) {
                val id = edtId.getText().toString().toInt()
                val nombre = edtNombre.getText().toString()
                val espe = edtEspecie.getText().toString()
                val gen = edtGenero.getText().toString()
                val raz = edtRaza.getText().toString()
                val edad = edtEdad.getText().toString()
                val pes = edtPeso.getText().toString()
                val ester = edtEsterilizado.isChecked().toString()
                val vacun = edtVacunado.isChecked().toString()
                val argumentos = arrayOfNulls<String>(9)
                argumentos[0] = nombre
                argumentos[1] = espe
                argumentos[2] = gen
                argumentos[3] = raz
                argumentos[4] = edad
                argumentos[5] = pes
                argumentos[6] = ester
                argumentos[7] = vacun
                argumentos[8] = id.toString()
                val sql = "UPDATE patitasVet SET nombre = ?, especie = ?, genero = ?, raza = ?, edad = ?, peso = ?, esterilizacion = ?, vacunacion = ? where id = ?"
                db.execSQL(sql, argumentos)
                mostrarMensaje("Mascota Modificada Correctamente")
                limpiarEntradas()
                db.close()
            } else {
                mostrarMensaje("Error al abrir base de datos")
            }
        } catch (E: java.lang.Exception) {
            mostrarMensaje("Error" + E.message)
        }
    }

    fun limpiarEntradas() {
        edtNombre.setText("")
        edtEspecie.setText("")
        edtGenero.setText("")
        edtRaza.setText("")
        edtEdad.setText("")
        edtPeso.setText("")
        edtEsterilizado.setChecked(false)
        edtVacunado.setChecked(false)
    }

    fun btnEliminar_Click(view: View?) {
        try {
            val objBdAPP = BdAppVet(this, "bdveterinaria.sqlite", null, 3)
            val db: SQLiteDatabase = objBdAPP.getWritableDatabase()
            //objBdAPP.onUpgrade(db,1,3)
            if (db != null) {
                /*val id = edtId.text.toString().toInt()
                val argumentos = arrayOfNulls<String>(1)
                argumentos[0] = id.toString() + ""*/
                val id = edtId.getText().toString()
                val argumentos = arrayOf(id)
                val sql = "delete from patitasVet where id = ?"
                db.execSQL(sql,argumentos)
                mostrarMensaje("Mascota Eliminada")
                limpiarEntradas()
                db.close()
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

    fun btnMuestraMascotas_Click(view: View?) {
        var intent = Intent(this, ActivityListadoClientes::class.java)
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
    private fun deStringASi(valor: Boolean): String {
        if (valor)
            return "Si"
        else
            return "No"
    }

}