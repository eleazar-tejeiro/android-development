package com.example.quizappmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup

class PantallaDos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_dos)
        logicaPregunta2()
    }

    fun logicaPregunta2() {
        val respuesta2 = findViewById<RadioGroup>(R.id.grupo2)
        siguientePantalla3(respuesta2)
    }

    fun siguientePantalla3(laRespuesta:RadioGroup) {
        val sgteFinal = findViewById<Button>(R.id.btnSiguiente2)

        sgteFinal.setOnClickListener {
            if (laRespuesta.checkedRadioButtonId == R.id.radio7)
            {
                Datos.correctas++
            } else if (laRespuesta.checkedRadioButtonId == -1)
                Datos.noContestadas++
            else
                Datos.incorrectas++

            Datos.nroPreguntas++

            val intent2 = Intent(this, PantallaTres::class.java)
            startActivity(intent2)
        }
    }
}