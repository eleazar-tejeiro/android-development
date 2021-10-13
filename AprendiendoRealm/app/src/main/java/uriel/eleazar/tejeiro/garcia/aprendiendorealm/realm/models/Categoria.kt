package uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.Configuracion
import java.util.Date

open class Categoria: RealmObject() {
    @PrimaryKey
    var id: Int = 0

    var nombre: String? = null
    var createdAt: Date? = Date()
    var productos: RealmList<Producto>? = null




}