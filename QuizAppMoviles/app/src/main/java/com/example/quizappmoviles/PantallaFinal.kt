package com.example.quizappmoviles

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.quizappmoviles.Datos.*
import java.text.DecimalFormat

class PantallaFinal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_final)
        logicaFinal()
    }

    fun logicaFinal() {
        val cajita = findViewById<TextView>(R.id.txtTotal)
        val cajita2 = findViewById<TextView>(R.id.txtTotal2)
        val resul = (correctas.toDouble() * 100) / nroPreguntas.toDouble()

        val formato1 = DecimalFormat("#.##")
        cajita.setText("Preguntas respondidas correctamente: "+correctas+"\nPreguntas respondidas incorrectamente: "+ incorrectas+"\nPreguntas no respondidas: "+ noContestadas+"\nTotal: "+formato1.format(resul))

        if (resul > 50) {
            cajita2.setTextColor(Color.GREEN)
            cajita2.setText("\nAPROBASTE")
        }

        else {
            cajita2.setTextColor(Color.RED)
            cajita2.setText("\nREPROBASTE")
        }

        correctas = 0
        incorrectas = 0
        noContestadas = 0
        nroPreguntas = 0
    }
}