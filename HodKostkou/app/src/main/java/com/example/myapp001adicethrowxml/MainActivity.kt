package com.example.myapp001adicethrowxml

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Napojení na XML pomocí findViewByID (požadavek učitele)
        val tvDice1 = findViewById<TextView>(R.id.tvDice1)
        val tvDice2 = findViewById<TextView>(R.id.tvDice2)
        val btnRoll = findViewById<Button>(R.id.btnRoll)

        btnRoll.setOnClickListener {
            // Zakázání tlačítka během animace
            btnRoll.isEnabled = false

            // Spuštění asynchronní rutiny pro animaci (aby nezamrzla appka)
            lifecycleScope.launch {

                // 10 náhodných změn (animace)
                for (i in 1..10) {
                    tvDice1.text = Random.nextInt(1, 7).toString()
                    tvDice2.text = Random.nextInt(1, 7).toString()
                    delay(250) // Prodleva 250 ms podle zadání
                }

                // Finální hod po skončení animace
                tvDice1.text = Random.nextInt(1, 7).toString()
                tvDice2.text = Random.nextInt(1, 7).toString()

                // Znovu povolení tlačítka
                btnRoll.isEnabled = true
            }
        }
    }
}