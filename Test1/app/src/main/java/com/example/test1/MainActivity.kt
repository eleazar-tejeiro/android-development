package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Button;
import android.widget.EditText

class MainActivity : AppCompatActivity() {
   // EditText editTextValor1;
   // EditText editTextValor2;
   // EditText editTextResultado;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // almacenando la id del boton en la instancia R
        // en una variable
        val suma = findViewById<Button>(R.id.btnSumar)
        val resta = findViewById<Button>(R.id.btnMinus)
        val multiplica = findViewById<Button>(R.id.btnMultiply)
        val divide = findViewById<Button>(R.id.btnDivide)

        val caja1 = findViewById<EditText>(R.id.cajaVal1)
        val caja2 = findViewById<EditText>(R.id.cajaVal2)
        val editTextResultado = findViewById<EditText>(R.id.editTextResultado)

        // operacion a realizar cuando se presiona el boton
        suma.setOnClickListener()
        {
            // mostrando el toast
            Toast.makeText(this@MainActivity, getString(R.string.sum)+"...", Toast.LENGTH_SHORT).show()

            var a = caja1.getText().toString().toDouble()
            var b = caja2.getText().toString().toDouble()

            val c = a + b
            editTextResultado.setText(c.toString())
        }
        resta.setOnClickListener()
        {
            // mostrando el toast
            Toast.makeText(this@MainActivity, getString(R.string.rest)+"...", Toast.LENGTH_SHORT).show()

            var a = caja1.getText().toString().toDouble()
            var b = caja2.getText().toString().toDouble()

            val c = a - b
            editTextResultado.setText(c.toString())
        }
        multiplica.setOnClickListener()
        {
            // mostrando el toast
            Toast.makeText(this@MainActivity, getString(R.string.multi)+"...", Toast.LENGTH_SHORT).show()

            var a = caja1.getText().toString().toDouble()
            var b = caja2.getText().toString().toDouble()

            val c = a * b
            editTextResultado.setText(c.toString())
        }
        divide.setOnClickListener()
        {
            // mostrando el toast
            Toast.makeText(this@MainActivity, getString(R.string.divi)+"...", Toast.LENGTH_SHORT).show()

            var a = caja1.getText().toString().toDouble()
            var b = caja2.getText().toString().toDouble()

            val c = a / b
            editTextResultado.setText(c.toString())


        }

    }
}