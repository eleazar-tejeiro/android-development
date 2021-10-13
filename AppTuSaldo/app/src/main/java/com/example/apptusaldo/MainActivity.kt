package com.example.apptusaldo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var elSaldo = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var laInfo = getSharedPreferences("saldito", MODE_PRIVATE)
        leeLaInformacion(laInfo)
    }

    fun leeLaInformacion(laInfo:SharedPreferences)
    {
        val elSaldish = findViewById<TextView>(R.id.txtTenes)
        elSaldish.setText(laInfo.getInt("saldo", 0).toString())
        ponleAccion()
    }

    //Todo: Por hacer la logica al presionar el boton registrar
    //Todo: la idea es presionar el boton sumar o restar y que opere con el saldo obtenido y una vez registre recién aplique los cambios
    fun ponleAccion()
    {
        val son10 = findViewById<RadioButton>(R.id.rd10)
        val son20 = findViewById<RadioButton>(R.id.rd20)
        val son50 = findViewById<RadioButton>(R.id.rd50)
        val son100 = findViewById<RadioButton>(R.id.rd100)
        val son200 = findViewById<RadioButton>(R.id.rd200)
        val add = findViewById<Button>(R.id.btnAdiciona)
        val rem = findViewById<Button>(R.id.btnQuita)
        val guarda = findViewById<Button>(R.id.btnRegistra)
        val laInfo = getSharedPreferences("saldito", MODE_PRIVATE)
        val elEditor = laInfo.edit()
        val elSaldish = findViewById<TextView>(R.id.txtTenes)

        val elElegido = findViewById<RadioGroup>(R.id.somosBs)

        elSaldo = laInfo.getInt("saldo",0)
        add.setOnClickListener {
            when (elElegido.checkedRadioButtonId) {
                son10.id -> {
                    elSaldo += 10
                    elEditor.putInt("saldo",elSaldo)
                    elSaldish.setText(elSaldo.toString())
                }
                son20.id -> {
                    elSaldo += 20
                    elEditor.putInt("saldo",elSaldo)
                    elSaldish.setText(elSaldo.toString())
                }
                son50.id -> {
                    elSaldo += 50
                    elEditor.putInt("saldo",elSaldo)
                    elSaldish.setText(elSaldo.toString())
                }
                son100.id -> {
                    elSaldo += 100
                    elEditor.putInt("saldo",elSaldo)
                    elSaldish.setText(elSaldo.toString())
                }
                son200.id -> {
                    elSaldo += 200
                    elEditor.putInt("saldo",elSaldo)
                    elSaldish.setText(elSaldo.toString())
                }
                else -> {
                    Toast.makeText(this@MainActivity, "No ha seleccionado ningun corte", Toast.LENGTH_SHORT).show()
                }
            }
        }
        rem.setOnClickListener {
            when (elElegido.checkedRadioButtonId) {
                son10.id -> {
                    elSaldo -= 10
                    elEditor.putInt("saldo",elSaldo)
                    elSaldish.setText(elSaldo.toString())
                }
                son20.id -> {
                    elSaldo -= 20
                    elEditor.putInt("saldo",elSaldo)
                    elSaldish.setText(elSaldo.toString())
                }
                son50.id -> {
                    elSaldo -= 50
                    elEditor.putInt("saldo",elSaldo)
                    elSaldish.setText(elSaldo.toString())
                }
                son100.id -> {
                    elSaldo -= 100
                    elEditor.putInt("saldo",elSaldo)
                    elSaldish.setText(elSaldo.toString())
                }
                son200.id -> {
                    elSaldo -= 200
                    elEditor.putInt("saldo",elSaldo)
                    elSaldish.setText(elSaldo.toString())
                }
                else -> {
                    Toast.makeText(this@MainActivity, "No ha seleccionado ningun corte", Toast.LENGTH_SHORT).show()
                }
            }
        }

        guarda.setOnClickListener {
            elEditor.commit()
            finish()
        }
    }


}