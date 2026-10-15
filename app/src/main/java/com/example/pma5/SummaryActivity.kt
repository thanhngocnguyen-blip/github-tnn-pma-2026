package com.example.pma5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pma5.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val druh = intent.getStringExtra("DRUH") ?: "Nevybráno"
        val cena = intent.getIntExtra("CENA", 0)
        val maLed = intent.getBooleanExtra("LED", false)
        val maBrcko = intent.getBooleanExtra("BRCKO", false)

        binding.tvSelectedDruh.text = "Vybraný Monster: $druh"
        binding.tvFinalPrice.text = "Celková cena: $cena Kč"

        var textVybavy = "Něco navíc:\n"
        if (maLed) textVybavy += "- Extra led (+5 Kč)\n"
        if (maBrcko) textVybavy += "- Papírové brčko (+2 Kč)\n"
        if (!maLed && !maBrcko) textVybavy += "- nic"

        binding.tvExtras.text = textVybavy

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}