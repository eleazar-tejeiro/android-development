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

class ActivityListadoHerramientas : AppCompatActivity() {
    lateinit var listViewHerramientas: ListView
    lateinit var listaHerramientas:ArrayList<Herramienta>
    lateinit var adaptaHerramienta:AdaptadorHerramienta
    var laId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_herramientas)
        laId = getIntent().getExtras()?.getInt("laIdecita")!!
        Log.d("soyLaId", laId.toString())
        listaHerramientas = ArrayList<Herramienta>()
        inicializarControles()
        cargarHerramientas()
        adaptaHerramienta = AdaptadorHerramienta(this,listaHerramientas,R.layout.layout_adaptador_herramienta)
        listViewHerramientas.adapter = adaptaHerramienta
        registerForContextMenu(listViewHerramientas)
    }

    private fun inicializarControles() {
        listViewHerramientas = findViewById(R.id.listViewHerramientas)
    }

    private fun cargarHerramientas() {
        try {
            val objBdAPP = BdAppObras(this, "bdobrasbol.sqlite", null, 1)
            val db: SQLiteDatabase = objBdAPP.getReadableDatabase()
            if (db != null) {
                val argumentos = arrayOf(laId.toString())
                val sql = "select * from herramientas where id_obra = ?  order by estado_herramienta desc"
                val datosLeido = db.rawQuery(sql,argumentos)
                listaHerramientas.clear()
                while (datosLeido.moveToNext()) {
                    val herramientita = Herramienta(datosLeido.getInt(0), datosLeido.getInt(1),
                        datosLeido.getString(2), datosLeido.getString(3), datosLeido.getString(4),
                        datosLeido.getString(5))
                    Log.d("soyLaIdd", datosLeido.getInt(1).toString())
                    listaHerramientas.add(herramientita)
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

    private fun mostrarMensaje(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    fun btn_adicionar_herramienta(view: View) {
        mostrarVentanitaModalAddHerramientas()
    }

    private fun mostrarVentanitaModalAddHerramientas() {
        var ventanita = AlertDialog.Builder(this)
        ventanita.setTitle("Adicionar Consultar")
        ventanita.setMessage("Adicionando consulta")
        var layoutInflado = LayoutInflater.from(this).inflate(R.layout.layout_adaptador_herramientas_add,null)
        ventanita.setView(layoutInflado)
        ventanita.setPositiveButton("Adicionar", DialogInterface.OnClickListener
        { dialog, which->
            var txtNombreHerramienta = layoutInflado.findViewById<EditText>(R.id.edtNombreHerramienta)
            var txtDescripcionHerramienta = layoutInflado.findViewById<EditText>(R.id.edtDescripcionHerramienta)
            var txtFechaPrestamoHerramienta = layoutInflado.findViewById<EditText>(R.id.edtFechaPrestamoHerramienta)
            var txtEstadoPrestamoHerramienta = layoutInflado.findViewById<TextView>(R.id.edtEstadoPrestamoHerramienta)
            if (!(txtNombreHerramienta.text.toString().trim().isEmpty() &&
                        txtDescripcionHerramienta.text.toString().trim().isEmpty() &&
                        txtFechaPrestamoHerramienta.text.toString().trim().isEmpty() &&
                        txtEstadoPrestamoHerramienta.text.toString().trim().isEmpty())) {
                guardaHerramienta(txtNombreHerramienta.text.toString(),
                    txtDescripcionHerramienta.text.toString(),
                    txtFechaPrestamoHerramienta.text.toString(),
                    txtEstadoPrestamoHerramienta.text.toString())
            } else {
                mostrarMensaje("Datos incorrectos")
            }
        })
        ventanita.create().show()
    }

    private fun guardaHerramienta(nombreHerramienta: String, descripcionHerramienta: String, fechaPrestamoHerramienta: String, estadoPrestamoHerramienta: String) {
        try {
            var objBdApp = BdAppObras(this, "bdobrasbol.sqlite", null, 1)
            var db = objBdApp.getWritableDatabase()

            if (db != null) {
                var sql = "INSERT INTO herramientas (id_obra, nombre_herramienta, descripcion_herramienta, fecha_prestamo, estado_herramienta) values('"+laId+"','"+nombreHerramienta+"','"+descripcionHerramienta+"','"+fechaPrestamoHerramienta+"','"+estadoPrestamoHerramienta+"')"
                db.execSQL(sql)
                actualizarListado()
                mostrarMensaje("Herramienta guardada correctamente! ")
                db.close()
            } else {
                mostrarMensaje("Error al abrir la base de datos!!")
            }
        } catch (E: Exception) {
            mostrarMensaje("ERROR " + E.message)
        }
    }

    private fun actualizarListado() {
        adaptaHerramienta.refreshResultList(listaHerramientas)
        cargarHerramientas()
        //TODO Creo que adaptadorcito invalidateViews es el que sirve sin embargo según había que llamar refreshResultList justo después de añadir a la BD
        adaptaHerramienta.refreshResultList(listaHerramientas)
        listViewHerramientas.invalidateViews()
        listViewHerramientas.adapter = adaptaHerramienta
        //Log.d("youuu","SI llego pa")

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        //super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_contextual_herramientas,menu)
    }

    override fun onContextItemSelected(@NonNull item: MenuItem): Boolean {
        var informacionItem: AdapterView.AdapterContextMenuInfo
        informacionItem = item.menuInfo as AdapterView.AdapterContextMenuInfo

        when (item.itemId) {
            R.id.menu_context_devolver -> {
                var posicion = informacionItem.position
                //eliminaContacto(posicion)
                devuelvemeLaHerramienta(posicion)
                mostrarMensaje("Selecciono devolver herramienta")
                return true
            }
            else -> {
                return super.onContextItemSelected(item)
            }
        }
    }

    private fun devuelvemeLaHerramienta(pos:Int) {
        try {
            val objBdAPP = BdAppObras(this, "bdobrasbol.sqlite", null, 1)
            val db: SQLiteDatabase = objBdAPP.getWritableDatabase()
            if (db != null) {
                val id = listaHerramientas.get(pos).id.toString()
                val argumentos = arrayOf(id)
                val sql = "UPDATE herramientas SET estado_herramienta = 'DEVUELTO' where id = ?"
                db.execSQL(sql, argumentos)
                actualizarListado()
                mostrarMensaje("Herramienta Devuelta Correctamente")
                db.close()
            } else {
                mostrarMensaje("Error al abrir base de datos")
            }
        } catch (E: java.lang.Exception) {
            mostrarMensaje("Error" + E.message)
        }
    }
}