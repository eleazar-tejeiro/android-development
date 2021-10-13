package uriel.eleazar.tejeiro.garcia.aprendiendorealm

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import io.realm.Realm
import io.realm.RealmResults
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.actividades.ActivityProducto
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.adaptadores.AdaptadorCategoria
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.Configuracion
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.cruds.Cruds
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models.Categoria
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models.Producto
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.utilitarios.AutoIncrementableProducto
import java.util.concurrent.atomic.AtomicInteger


class MainActivity : Activity() {
    lateinit var realm: Realm
    //lateinit var categorias:RealmResults<Categoria>
    lateinit var listaViewCategoria: ListView
    lateinit var adaptador: AdaptadorCategoria
    lateinit var categorias: RealmResults<Categoria>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getInstance(Configuracion().getConfiguracion())
        categorias = realm.where(Categoria::class.java).findAll()
        //Cruds().inicializarAutoincrementables()
        /* Cruds().agregaCategoria("Frutas", Date(2021,7,4))
        Cruds().agregaCategoria("Lacteos", Date(2021,7,4))
        Cruds().agregaCategoria("Pastas", Date(2021,7,4)) */
        adaptador = AdaptadorCategoria(this, categorias, R.layout.layout_adaptador_categoria)
        listaViewCategoria = findViewById(R.id.listViewCategoria)
        listaViewCategoria.adapter = adaptador
        registerForContextMenu(listaViewCategoria)
        listaViewCategoria.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                var intent = Intent(this, ActivityProducto::class.java)
                intent.putExtra("id", categorias.get(position)!!.id)
                startActivity(intent)
            }
        /*

            * */

        realm.close()
    }

    fun btn_adicionar_herramienta(view: View) {
        mostrarVentanaModalAddCategoria()
    }

    private fun mostrarVentanaModalAddCategoria() {
        var ventanita = AlertDialog.Builder(this)
        ventanita.setTitle("Adicionar Categoria")
        ventanita.setMessage("Adicionando categoria")
        var layoutInflado = LayoutInflater.from(this).inflate(
            R.layout.layout_adaptador_categoria_add,
            null
        )
        ventanita.setView(layoutInflado)
        ventanita.setPositiveButton("Adicionar", DialogInterface.OnClickListener
        { dialog, which ->
            var txtNombreCategoria = layoutInflado.findViewById<EditText>(R.id.edtNombreCategoria)
            if (!(txtNombreCategoria.text.toString().trim().isEmpty())) {
                if (!(categoriaDuplicada(categorias, txtNombreCategoria.text.toString()))) {
                    Cruds().agregaCategoria(txtNombreCategoria.text.toString())
                    actualizarListado()
                } else
                    mostrarMensaje("Categoria duplicada")
            } else {
                mostrarMensaje("Datos incorrectos")
            }
        })
        ventanita.create().show()
    }

    private fun mostrarMensaje(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun actualizarListado() {
        adaptador.refreshResultList(categorias)
        listaViewCategoria.invalidateViews()
        listaViewCategoria.adapter = adaptador
    }

    private fun categoriaDuplicada(listica: RealmResults<Categoria>, catNueva: String): Boolean {
        var bandera = false
        for (dato in listica) {
            if (catNueva == dato.nombre)
                bandera = true
            else
                bandera = false
        }
        return bandera
    }

    //---------------- MENU CONTEXTUAL ------------------------
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        //super.onCreateContextMenu(menu, v, menuInfo)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        menu!!.setHeaderTitle(categorias[info.position]!!.nombre)
        menuInflater.inflate(R.menu.menu_context_categoria, menu)
    }

    override fun onContextItemSelected(@NonNull item: MenuItem): Boolean {
        val informacionItem: AdapterView.AdapterContextMenuInfo
        informacionItem = item.menuInfo as AdapterView.AdapterContextMenuInfo

        when (item.itemId) {
            R.id.menu_editar_categoria -> {
                val posicion = informacionItem.position
                editaCategoria(categorias[posicion]!!)
                return true
            }
            R.id.menu_eliminar_categoria -> {
                val posicion = informacionItem.position
                eliminaCategoria(categorias[posicion]!!)
            }
            else -> {
                //return super.onContextItemSelected(item)
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun editaCategoria(laCategoria: Categoria) {
        var ventanita = AlertDialog.Builder(this)
        ventanita.setTitle("Categoria")
        ventanita.setMessage("Modificando")
        val layoutInflado = LayoutInflater.from(this).inflate(
            R.layout.layout_adaptador_categoria_add,
            null
        )
        ventanita.setView(layoutInflado)
        var txtNombreCategoria = layoutInflado.findViewById<EditText>(R.id.edtNombreCategoria)
        txtNombreCategoria.setText(laCategoria!!.nombre)
        ventanita.setPositiveButton("Editar", DialogInterface.OnClickListener
        { dialog, which ->
            if (!(txtNombreCategoria.text.toString().trim().isEmpty())) {
                Cruds().modificaCategoria(txtNombreCategoria.text.toString(), laCategoria)
                actualizarListado()
                mostrarMensaje("Categoria editada!!")

            } else {
                mostrarMensaje("Datos incorrectos")
            }
        })
        ventanita.create().show()
    }

    private fun eliminaCategoria(laCategoria: Categoria) {
        Cruds().eliminaCategoria(laCategoria)
        actualizarListado()
        mostrarMensaje("Categoria borrada!!")
    }
    //---------------- FIN MENU CONTEXTUAL --------------------
}