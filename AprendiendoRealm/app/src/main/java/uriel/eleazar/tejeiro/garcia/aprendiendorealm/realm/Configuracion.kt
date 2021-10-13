package uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class Configuracion: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.getInstance(getConfiguracion())
        inicializarAutoIncrementables()
    }

    private fun inicializarAutoIncrementables() {
        var realm: Realm = Realm.getDefaultInstance()
    }

    fun getConfiguracion():RealmConfiguration {
        return RealmConfiguration.Builder()
            .name(Constantes().DB_TIENDA)
            .build()
    }
}