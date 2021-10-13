package uriel.eleazar.tejeiro.garcia.aprendiendorealm.realm.utilitarios;

import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class AutoIncrementableProducto {
    public static<T extends RealmObject> AtomicInteger generarId(Realm bd, Class<T> objmodelo){
        RealmResults<T> resultado = bd.where(objmodelo).findAll();
        //Number resul = bd.where(objmodelo).max("id");
        if(resultado.size()>0) {
            //Log.d("idProdu: ","soy resultado -> "+resultado.max("id").intValue()+" --- soy el max: "+bd.where(objmodelo).max("id"));
            return new AtomicInteger(resultado.max("id").intValue()+1);
        }
        else
            return new AtomicInteger(0);
    }
}
