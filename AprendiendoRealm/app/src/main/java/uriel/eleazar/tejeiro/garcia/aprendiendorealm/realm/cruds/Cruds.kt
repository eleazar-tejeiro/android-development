package uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.cruds

import android.util.Log
import io.realm.Realm
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.Configuracion
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.Constantes
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models.Categoria
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models.Producto
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.utilitarios.AutoIncrementable
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.utilitarios.AutoIncrementableProducto
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class Cruds {
    private val realm: Realm = Realm.getInstance(Configuracion().getConfiguracion())
    private var idProducto: AtomicInteger = AutoIncrementableProducto.generarId(realm, Producto::class.java)
    fun cerrar () = realm.close()

    fun agregaCategoria(nombre: String): Int {
        val idCategoria: Int = AutoIncrementable().generarIdAutoIncremental(Constantes().ID_ESQUEMA_CATEGORIA)
        realm.beginTransaction()
        val categoria: Categoria = realm.createObject(Categoria::class.java, idCategoria)
        categoria.nombre = nombre
        //categoria.createdAt = creado
        realm.commitTransaction()

        cerrar()
        return idCategoria
    }

    /*fun inicializarAutoincrementables() {
        generador_idProducto =
        //Log.d("elMero ", "LLego primero o no "+generador_idProducto.get())
    }*/

    fun agregaProducto(nombre: String,
                       precio: Double,
                       categoria: Categoria) {
        realm.beginTransaction()
        val producto: Producto = realm.createObject(Producto::class.java, idProducto.getAndIncrement())
        producto.nombre = nombre
        producto.precio = precio
        categoria.productos!!.add(producto)
        realm.commitTransaction()
        cerrar()
    }

    fun leeCat(pos:Int): Categoria? {
        val obj = realm.where(Categoria::class.java).findAll()
        return obj.get(pos)
    }

    fun modificaCategoria(nombre: String, categoria:Categoria) {
        realm.beginTransaction()
        categoria.nombre = nombre
        categoria.createdAt = Date()
        realm.copyToRealmOrUpdate(categoria)
        realm.commitTransaction()
    }

    fun eliminaCategoria(categoria: Categoria) {
        realm.beginTransaction()
        categoria.deleteFromRealm()
        realm.commitTransaction()
    }
}