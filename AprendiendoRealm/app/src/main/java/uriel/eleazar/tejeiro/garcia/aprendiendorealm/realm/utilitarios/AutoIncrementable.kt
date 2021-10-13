package uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.utilitarios

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmResults
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.Configuracion
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.Constantes
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models.Categoria
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models.Producto
import java.util.concurrent.atomic.AtomicInteger

class AutoIncrementable {
    fun <T: RealmModel> recuperaIdMaximo(bd: Realm, objModelo:Class<T>, campoId:String): Int {
        val id: Number? = bd.where(objModelo).max(campoId)
        val idRecuperado: Int
        if (id == null) idRecuperado = 0
        else idRecuperado = id.toInt() + 1
        return idRecuperado
    }

    fun generarIdAutoIncremental(id: Int): Int {
        val realm: Realm = Realm.getInstance(Configuracion().getConfiguracion())
        val idAutoincremental: Int = when (id) {
            Constantes().ID_ESQUEMA_CATEGORIA -> recuperaIdMaximo(realm, Categoria::class.java,"id")
            Constantes().ID_ESQUEMA_PRODUCTO -> recuperaIdMaximo(realm, Producto::class.java,"id")
            else -> -1
        }
        realm.close()

        return idAutoincremental
    }

}