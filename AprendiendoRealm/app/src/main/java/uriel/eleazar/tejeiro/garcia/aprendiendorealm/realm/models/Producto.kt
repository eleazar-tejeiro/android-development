package uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.Configuracion

open class Producto: RealmObject() {
    @PrimaryKey
    var id: Int = 0

    var nombre: String? = null
    var precio: Double? = 0.0
}