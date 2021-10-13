package uriel.eleazar.tejeiro.garcia.aprendiendorealm.actividades

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmList
import io.realm.RealmModel
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.MainActivity
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.R
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.adaptadores.AdaptadorProducto
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.Configuracion
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.Constantes
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.cruds.Cruds
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models.Categoria
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models.Producto
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.utilitarios.AutoIncrementable

class ActivityProducto : AppCompatActivity() {
    lateinit var listaViewProductos: ListView
    lateinit var adaptador: AdaptadorProducto
    lateinit var productos: RealmList<Producto>
    lateinit var realm: Realm
    lateinit var categoria: Categoria
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)
        realm = Realm.getInstance(Configuracion().getConfiguracion())
        var idCategoria: Int = intent.extras!!.getInt("id")
        categoria = realm.where(Categoria::class.java).equalTo("id", idCategoria).findFirst()!!
        productos = categoria.productos!!
        this.title = "Categoria: " + categoria.nombre
        listaViewProductos = findViewById(R.id.listViewProducto)
        adaptador = AdaptadorProducto(this, productos, R.layout.layout_adaptador_producto)
        listaViewProductos.adapter = adaptador

        realm.close()
    }

    fun btnAddProducto_Click(view: View) {
        mostrarPantallaAdicionar(categoria.nombre.toString())
    }

    private fun mostrarPantallaAdicionar(nombre: String) {
        var ventana: AlertDialog.Builder = AlertDialog.Builder(this)
        ventana.setTitle("Productos")
        ventana.setMessage("Adicionar productos: " + nombre)
        val ventanaInflada: View =
            LayoutInflater.from(this).inflate(R.layout.layout_add_producto, null)
        ventana.setView(ventanaInflada)
        ventana.setPositiveButton("Adicionar", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                try {
                    val edtTextNombre: EditText =
                        ventanaInflada.findViewById(R.id.editTextNombreProducto)
                    val edtTextPrecio: EditText =
                        ventanaInflada.findViewById(R.id.editTextPrecioProducto)
                    val nombre: String = edtTextNombre.text.toString()
                    val precio: Double = edtTextPrecio.text.toString().toDouble()

                    if (nombre.isEmpty() || precio <= 0)
                        throw Exception("Error el nombre no debe ser vacio y el precio debe ser mayor a cero")
                    else {
                        Cruds().agregaProducto(nombre, precio, categoria)
                        actualizarListado()
                    }
                } catch (E: Exception) {
                    mostrarMensaje("Error: " + E.message)
                }
            }

        })
        ventana.create().show()
    }

    private fun actualizarListado() {
        adaptador.refreshResultList(productos)
        listaViewProductos.invalidateViews()
        listaViewProductos.adapter = adaptador
    }

    private fun mostrarMensaje(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }


    /*private fun agregaProducto(nombre: String,
                       precio: Double) {
        //var idProducto: Int = AutoIncrementable().generarIdAutoIncremental(Constantes().ID_ESQUEMA_PRODUCTO)
        //idProducto++
        realm.beginTransaction()
        val producto: Producto = realm.createObject(Producto::class.java,MainActivity().generador_idProducto)
        producto.nombre = nombre
        producto.precio = precio
        //realm.copyToRealm(producto)
        categoria.productos!!.add(producto)
        realm.commitTransaction()
        //return idProducto
    }*/
}