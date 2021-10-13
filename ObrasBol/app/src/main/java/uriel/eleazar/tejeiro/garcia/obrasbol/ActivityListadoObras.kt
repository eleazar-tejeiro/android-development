package uriel.eleazar.tejeiro.garcia.obrasbol

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

class ActivityListadoObras : AppCompatActivity() {
    lateinit var listViewObras: ListView
    lateinit var listaObras:ArrayList<Obra>
    lateinit var adaptaObra:AdaptadorObra

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_obras)
        listaObras = ArrayList<Obra>()
        inicializarControles()
        cargarObras()
        adaptaObra = AdaptadorObra(this,listaObras,R.layout.layout_adaptador_obra)
        listViewObras.adapter = adaptaObra
        registerForContextMenu(listViewObras)
    }

    private fun cargarObras() {
        //Aqui cargo las obras que hay en la BD con estado Vigente
        try {
            val objBdAPP = BdAppObras(this, "bdobrasbol.sqlite", null, 1)
            val db: SQLiteDatabase = objBdAPP.getReadableDatabase()
            if (db != null) {
                val sql = "select * from obras where estado_obra = 'vigente' order by nombre_obra"
                val datosLeido = db.rawQuery(sql,null)
                listaObras.clear()
                while (datosLeido.moveToNext()) {
                    val obrita = Obra(datosLeido.getInt(0), datosLeido.getString(1),
                        datosLeido.getString(2), datosLeido.getString(3), datosLeido.getString(4),
                        datosLeido.getString(5))
                    listaObras.add(obrita)
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

    fun btn_adicionar_obra(view: View) {
        mostrarVentanitaModalAddObras()
    }

    private fun inicializarControles() {
        listViewObras = findViewById(R.id.listViewObras)
    }

    private fun mostrarMensaje(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun mostrarVentanitaModalAddObras() {
        var ventanita = AlertDialog.Builder(this)
        ventanita.setTitle("Adicionar Obra")
        ventanita.setMessage("Adicionando obra nueva")
        var layoutInflado = LayoutInflater.from(this).inflate(R.layout.layout_adaptador_obras_add,null)
        ventanita.setView(layoutInflado)
        ventanita.setPositiveButton("Adicionar", DialogInterface.OnClickListener
        { dialog, which ->
            var txtNombreObra = layoutInflado.findViewById<EditText>(R.id.edtNombreObra)
            var txtDescripcionObra = layoutInflado.findViewById<EditText>(R.id.edtDescripcionObra)
            var txtFechaInicioObra = layoutInflado.findViewById<EditText>(R.id.edtFechaInicioObra)
            var txtEstadoObra = layoutInflado.findViewById<TextView>(R.id.edtEstadoVigenteObra)
            var txtNombreResponsableObra = layoutInflado.findViewById<EditText>(R.id.edtNombreResponsableObra)
            if (!(txtNombreObra.text.toString().trim().isEmpty() &&
                        txtDescripcionObra.text.toString().trim().isEmpty() &&
                        txtFechaInicioObra.text.toString().trim().isEmpty() &&
                        txtEstadoObra.text.toString().trim().isEmpty() &&
                        txtNombreResponsableObra.text.toString().trim().isEmpty())) {
                guardaObra(txtNombreObra.text.toString(),
                    txtDescripcionObra.text.toString(),
                    txtFechaInicioObra.text.toString(),
                    txtEstadoObra.text.toString(),
                    txtNombreResponsableObra.text.toString())
            } else {
                mostrarMensaje("Datos incorrectos")
            }
        })
        ventanita.create().show()
    }

    private fun guardaObra(nombreObra: String, descripcionObra: String, fechaInicioObra: String, estadoObra: String, nombreResponsableObra: String) {
        try {
            var objBdApp = BdAppObras(this, "bdobrasbol.sqlite", null, 1)
            var db = objBdApp.getWritableDatabase()
            if (db != null) {
                var sql = "INSERT INTO obras (nombre_obra, descripcion_obra, fecha_inicio, estado_obra, nombre_responsable) values('"+nombreObra+"','"+descripcionObra+"','"+fechaInicioObra+"','"+estadoObra+"','"+nombreResponsableObra+"')"
                db.execSQL(sql)
                actualizarListado()
                mostrarMensaje("Obra guardada correctamente! ")
                db.close()
            } else {
                mostrarMensaje("Error al abrir la base de datos!!")
            }
        } catch (E: Exception) {
            mostrarMensaje("ERROR " + E.message)
        }
    }

    private fun actualizarListado() {
        adaptaObra.refreshResultList(listaObras)
        cargarObras()
        //adaptadorcito = AdaptadorContacto(this, listaMascotas, R.layout.layout_adaptador_contacto)
        //TODO Creo que adaptaObras invalidateViews es el que sirve sin embargo según había que llamar refreshResultList justo después de añadir a la BD
        adaptaObra.refreshResultList(listaObras)
        listViewObras.invalidateViews()
        listViewObras.adapter = adaptaObra
        //Log.d("youuu","SI llego pa")
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        //super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_contextual_obras,menu)
    }

    override fun onContextItemSelected(@NonNull item: MenuItem): Boolean {
        var informacionItem:AdapterView.AdapterContextMenuInfo
        informacionItem = item.menuInfo as AdapterView.AdapterContextMenuInfo

        when (item.itemId) {
            R.id.menu_context_cerrar_obra -> {
                var posicion = informacionItem.position
                if(verificoMisHerramientas(posicion))
                    cierrameLaObra(posicion)
                else
                    mostrarMensaje("Aun faltan herramientas por devolver")
                //mostrarMensaje("Selecciono eliminar")
                return true
            }
            R.id.menu_context_herramientas -> {
                var posicion = informacionItem.position
                var intent = Intent(this, ActivityListadoHerramientas::class.java)
                intent.putExtra("laIdecita",listaObras.get(posicion).id)
                Log.d("siMando", listaObras.get(posicion).id.toString())
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                return true
            }
            else -> {
                return super.onContextItemSelected(item)
            }
        }
    }

    private fun cierrameLaObra(pos:Int) {
        try {
            val objBdAPP = BdAppObras(this, "bdobrasbol.sqlite", null, 1)
            val db: SQLiteDatabase = objBdAPP.getWritableDatabase()
            if (db != null) {
                val id = listaObras.get(pos).id.toString()
                val argumentos = arrayOf(id)
                val sql = "UPDATE obras SET estado_obra = 'cerrado' where id = ?"
                db.execSQL(sql, argumentos)
                actualizarListado()
                mostrarMensaje("Obra Cerrada Correctamente")
                db.close()
            } else {
                mostrarMensaje("Error al abrir base de datos")
            }
        } catch (E: java.lang.Exception) {
            mostrarMensaje("Error" + E.message)
        }
    }

    private fun verificoMisHerramientas(posicion:Int): Boolean {
        var aux = false
        try {
            val objBdAPP = BdAppObras(this, "bdobrasbol.sqlite", null, 1)
            val db: SQLiteDatabase = objBdAPP.getReadableDatabase()
            if (db != null) {
                val id = listaObras.get(posicion).id.toString()
                val argumentos = arrayOf(id)
                val sql = "select * from herramientas where estado_herramienta = 'PRESTADO' and id_obra = ?"
                val datosLeido = db.rawQuery(sql,argumentos)
                aux = !datosLeido.moveToFirst()
                db.close()
            } else {
                mostrarMensaje("Error al abrir base de datos")
            }
        } catch (E: java.lang.Exception) {
            mostrarMensaje("Error" + E.message)
        }
        return aux
    }
}