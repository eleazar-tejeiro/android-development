package com.example.quizappmoviles

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import com.example.quizappmoviles.Datos.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logicaPregunta1()
    }

    fun logicaPregunta1() {
        val respuesta1 = findViewById<RadioGroup>(R.id.grupo1)
        siguientePantalla(respuesta1)
    }
    fun siguientePantalla(laRespuesta:RadioGroup) {
        val botonSgte = findViewById<Button>(R.id.btnSiguiente)
        botonSgte.setOnClickListener {
            if (laRespuesta.checkedRadioButtonId == R.id.radio4)
            {
                correctas++
            } else if (laRespuesta.checkedRadioButtonId == -1)
                noContestadas++
            else
                incorrectas++

            nroPreguntas++
            val intent1 = Intent(this, PantallaDos::class.java)
            startActivity(intent1)
        }
    }

}