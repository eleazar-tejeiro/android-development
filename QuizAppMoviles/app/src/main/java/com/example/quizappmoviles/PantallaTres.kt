package com.example.quizappmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup

class PantallaTres : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_tres)
        logicaPregunta3()
    }

    fun logicaPregunta3() {
        val respuesta3 = findViewById<RadioGroup>(R.id.grupo3)
        siguientePantallaFinal(respuesta3)
    }

    fun siguientePantallaFinal(laRespuesta: RadioGroup) {
        val sgteTres = findViewById<Button>(R.id.btnSiguiente3)
        sgteTres.setOnClickListener {
            if (laRespuesta.checkedRadioButtonId == R.id.radio9)
            {
                Datos.correctas++
            } else if (laRespuesta.checkedRadioButtonId == -1)
                Datos.noContestadas++
            else
                Datos.incorrectas++

            Datos.nroPreguntas++
            val intent3 = Intent(this, PantallaFinal::class.java)
            startActivity(intent3)
        }
    }
}