package com.example.centrovetpatitas

import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog

class ActivityListadoClientes : AppCompatActivity() {
    lateinit var listViewMascotas: ListView
    lateinit var listaMascotas:ArrayList<Mascota>
    lateinit var adaptadorcito:AdaptadorContacto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_clientes)
        listaMascotas = ArrayList<Mascota>()
        inicializarControles()
        cargarMascotas()
        adaptadorcito = AdaptadorContacto(this,listaMascotas,R.layout.layout_adaptador_contacto)
        listViewMascotas.adapter = adaptadorcito
        registerForContextMenu(listViewMascotas)
    }

    private fun cargarMascotas() {
        try {
            val objBdAPP = BdAppVet(this, "bdveterinaria.sqlite", null, 3)
            val db: SQLiteDatabase = objBdAPP.getReadableDatabase()
            if (db != null) {
                val sql = "select * from patitasVet order by nombre"
                val datosLeido = db.rawQuery(sql,null)
                listaMascotas.clear()
                while (datosLeido.moveToNext()) {
                    val mascotita = Mascota(datosLeido.getInt(0), datosLeido.getString(1),
                        datosLeido.getString(2), datosLeido.getString(3), datosLeido.getString(4),
                        datosLeido.getString(5), datosLeido.getDouble(6), datosLeido.getString(7), datosLeido.getString(8))
                    listaMascotas.add(mascotita)
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

    private fun inicializarControles() {
        listViewMascotas = findViewById(R.id.listViewContactos)
    }

    fun mostrarMensaje(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    fun btn_adicionar_contacto(view: View) {
        mostrarVentanitaModalAddMascotas()
    }

    private fun mostrarVentanitaModalAddMascotas() {
        var ventanita = AlertDialog.Builder(this)
        ventanita.setTitle("Adicionar Mascota")
        ventanita.setMessage("Adicionando mascota nueva")
        var layoutInflado = LayoutInflater.from(this).inflate(R.layout.layout_adaptador_mascota_add,null)
        ventanita.setView(layoutInflado)
        ventanita.setPositiveButton("Adicionar", DialogInterface.OnClickListener
        { dialog, which->
            var txtNombre = layoutInflado.findViewById<EditText>(R.id.cajaNombre2)
            var txtEspecie = layoutInflado.findViewById<EditText>(R.id.cajaEspecie2)
            var txtGenero = layoutInflado.findViewById<EditText>(R.id.cajaGenero2)
            var txtRaza = layoutInflado.findViewById<EditText>(R.id.cajaRaza2)
            var txtEdad = layoutInflado.findViewById<EditText>(R.id.cajaEdad2)
            var txtPeso = layoutInflado.findViewById<EditText>(R.id.cajaPeso2)
            var txtEsterilizado = layoutInflado.findViewById<ToggleButton>(R.id.opcionEsterilizado2)
            var txtVacunado = layoutInflado.findViewById<ToggleButton>(R.id.opcionVacunado2)
            if (!(txtNombre.text.toString().trim().isEmpty() &&
                    txtEspecie.text.toString().trim().isEmpty() &&
                    txtGenero.text.toString().trim().isEmpty() &&
                    txtRaza.text.toString().trim().isEmpty() &&
                    txtEdad.text.toString().trim().isEmpty() &&
                    txtPeso.text.toString().trim().isEmpty())) {
                guardaContacto(txtNombre.text.toString(),
                        txtEspecie.text.toString(),
                        txtGenero.text.toString(),
                        txtRaza.text.toString(),
                        txtEdad.text.toString(),
                        txtPeso.text.toString(),
                        txtEsterilizado.isChecked.toString(),
                        txtVacunado.isChecked.toString())
            } else {
                mostrarMensaje("Datos incorrectos")
            }
        })
        ventanita.create().show()
    }

    private fun guardaContacto(nombre: String, especie: String, genero: String, raza: String, edad: String, peso: String, esterilizado: String, vacunado: String) {
        try {
            var objBdApp = BdAppVet(this, "bdveterinaria.sqlite", null, 3)
            var db = objBdApp.getWritableDatabase()
            if (db != null) {
                var sql = "INSERT INTO patitasVet (nombre, especie, genero, raza, edad, peso, esterilizacion, vacunacion) values('"+nombre+"','"+especie+"','"+genero+"','"+raza+"','"+edad+"','"+peso+"','"+esterilizado+"','"+vacunado+"')"
                db.execSQL(sql)
                actualizarListado()
                mostrarMensaje("Animal guardado correctamente! ")
                db.close()
            } else {
                mostrarMensaje("Error al abrir la base de datos!!")
            }
        } catch (E: Exception) {
            mostrarMensaje("ERROR " + E.message)
        }
    }

    private fun actualizarListado() {
        adaptadorcito.refreshResultList(listaMascotas)
        cargarMascotas()
        //adaptadorcito = AdaptadorContacto(this, listaMascotas, R.layout.layout_adaptador_contacto)
        //TODO Creo que adaptadorcito invalidateViews es el que sirve sin embargo según había que llamar refreshResultList justo después de añadir a la BD
        adaptadorcito.refreshResultList(listaMascotas)
        listViewMascotas.invalidateViews()
        listViewMascotas.adapter = adaptadorcito
        //Log.d("youuu","SI llego pa")
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        //super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_contextual_mascota,menu)
    }

    override fun onContextItemSelected(@NonNull item: MenuItem): Boolean {
        var informacionItem:AdapterView.AdapterContextMenuInfo
        informacionItem = item.menuInfo as AdapterView.AdapterContextMenuInfo

        when (item.itemId) {
            R.id.menu_context_eliminar -> {
                var posicion = informacionItem.position
                eliminaContacto(posicion)
                //mostrarMensaje("Selecciono eliminar")
                return true
            }
            R.id.menu_context_modificar -> {
                var posicion = informacionItem.position
                modificaElementoVentanaModal(posicion)
                //mostrarMensaje("Selecciono modificar")

                return true
            }
            R.id.menu_context_add_cita -> {
                var posicion = informacionItem.position
                var intent = Intent(this, ActivityListadoConsultas::class.java)
                intent.putExtra("laIdecita",listaMascotas.get(posicion).id)
                Log.d("siMando", listaMascotas.get(posicion).id.toString())
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                return true
            }
            else -> {
                return super.onContextItemSelected(item)
            }
        }

        //return super.onContextItemSelected(item)
    }

    private fun modificaElementoVentanaModal(posicion: Int) {
        var ventanita = AlertDialog.Builder(this)
        ventanita.setTitle("Modificar Mascota")
        ventanita.setMessage("Modificando mascota existente")
        var layoutInflado = LayoutInflater.from(this).inflate(R.layout.layout_adaptador_mascota_add,null)
        ventanita.setView(layoutInflado)
        //variables de los EditText
        var txtNombre = layoutInflado.findViewById<EditText>(R.id.cajaNombre2)
        var txtEspecie = layoutInflado.findViewById<EditText>(R.id.cajaEspecie2)
        var txtGenero = layoutInflado.findViewById<EditText>(R.id.cajaGenero2)
        var txtRaza = layoutInflado.findViewById<EditText>(R.id.cajaRaza2)
        var txtEdad = layoutInflado.findViewById<EditText>(R.id.cajaEdad2)
        var txtPeso = layoutInflado.findViewById<EditText>(R.id.cajaPeso2)
        var txtEsterilizado = layoutInflado.findViewById<ToggleButton>(R.id.opcionEsterilizado2)
        var txtVacunado = layoutInflado.findViewById<ToggleButton>(R.id.opcionVacunado2)
        var elId = listaMascotas.get(posicion).id
        txtNombre.setText(listaMascotas.get(posicion).nombre)
        txtEspecie.setText(listaMascotas.get(posicion).especie)
        txtGenero.setText(listaMascotas.get(posicion).genero)
        txtRaza.setText(listaMascotas.get(posicion).raza)
        txtEdad.setText(listaMascotas.get(posicion).edad)
        txtPeso.setText(listaMascotas.get(posicion).getPeso().toString())
        txtEsterilizado.setChecked(listaMascotas.get(posicion).isEsterilizado().toBoolean())
        txtVacunado.setChecked(listaMascotas.get(posicion).isVacunado().toBoolean())
        ventanita.setPositiveButton("Modificar", DialogInterface.OnClickListener
        { dialog, which->
            if (!(txtNombre.text.toString().trim().isEmpty() &&
                            txtEspecie.text.toString().trim().isEmpty() &&
                            txtGenero.text.toString().trim().isEmpty() &&
                            txtRaza.text.toString().trim().isEmpty() &&
                            txtEdad.text.toString().trim().isEmpty() &&
                            txtPeso.text.toString().trim().isEmpty())) {
                modificarContacto(elId, txtNombre.text.toString(),
                        txtEspecie.text.toString(),
                        txtGenero.text.toString(),
                        txtRaza.text.toString(),
                        txtEdad.text.toString(),
                        txtPeso.text.toString(),
                        txtEsterilizado.isChecked.toString(),
                        txtVacunado.isChecked.toString())
                actualizarListado()
            } else {
                mostrarMensaje("Datos incorrectos")
            }
        })
        ventanita.create().show()
    }

    private fun modificarContacto(id:Int, nombre: String, especie: String, genero: String, raza: String, edad: String, peso: String, esterilizado: String, vacunado: String) {
        try {
            val objBdAPP = BdAppVet(this, "bdveterinaria.sqlite", null, 3)
            val db: SQLiteDatabase = objBdAPP.getWritableDatabase()
            if (db != null) {
                val argumentos = arrayOfNulls<String>(9)
                argumentos[0] = nombre
                argumentos[1] = especie
                argumentos[2] = genero
                argumentos[3] = raza
                argumentos[4] = edad
                argumentos[5] = peso
                argumentos[6] = esterilizado
                argumentos[7] = vacunado
                argumentos[8] = id.toString()
                val sql = "UPDATE patitasVet SET nombre = ?, especie = ?, genero = ?, raza = ?, edad = ?, peso = ?, esterilizacion = ?, vacunacion = ? where id = ?"

                db.execSQL(sql, argumentos)
                mostrarMensaje("Mascota Modificada Correctamente")
                db.close()
            } else {
                mostrarMensaje("Error al abrir base de datos")
            }
        } catch (E: java.lang.Exception) {
            mostrarMensaje("Error" + E.message)
        }
    }

    private fun eliminaContacto(posicion: Int) {
         try {
            val objBdAPP = BdAppVet(this, "bdveterinaria.sqlite", null, 3)
            val db: SQLiteDatabase = objBdAPP.getWritableDatabase()
            if (db != null) {
                val id = listaMascotas.get(posicion).id.toString()
                val argumentos = arrayOf(id)
                var sql = "delete from patitasVet where id = ?"
                db.execSQL(sql,argumentos)
                actualizarListado()
                mostrarMensaje("Mascota Eliminada")
                db.close()
            } else {
                mostrarMensaje("Error al abrir base de datos")
            }
        } catch (E: java.lang.Exception) {
            mostrarMensaje("Error" + E.message)
        }
    }
}