package com.example.pma5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pma5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fun prepocitejCenu() {
            var cena = 0

            if (binding.rgDruhy.checkedRadioButtonId == R.id.rbKlasik) {
                cena += 40
                binding.imgMonster.setImageResource(R.drawable.mk)
            }
            if (binding.rgDruhy.checkedRadioButtonId == R.id.rbBily) {
                cena += 45
                binding.imgMonster.setImageResource(R.drawable.mb)
            }
            if (binding.rgDruhy.checkedRadioButtonId == R.id.rbLando) {
                cena += 50
                binding.imgMonster.setImageResource(R.drawable.mln)
            }

            if (binding.cbLed.isChecked) cena += 5
            if (binding.cbBrcko.isChecked) cena += 2

            binding.tvCena.text = "Celková cena: $cena Kč"
        }

        binding.rgDruhy.setOnCheckedChangeListener { _, _ -> prepocitejCenu() }
        binding.cbLed.setOnCheckedChangeListener { _, _ -> prepocitejCenu() }
        binding.cbBrcko.setOnCheckedChangeListener { _, _ -> prepocitejCenu() }

        binding.btnObjednat.setOnClickListener {
            var finalniCena = 0
            var nazevDruh = ""

            if (binding.rgDruhy.checkedRadioButtonId == R.id.rbKlasik) {
                finalniCena += 40
                nazevDruh = "Klasik"
            } else if (binding.rgDruhy.checkedRadioButtonId == R.id.rbBily) {
                finalniCena += 45
                nazevDruh = "Bílý Ultra"
            } else {
                finalniCena += 50
                nazevDruh = "Lando"
            }

            if (binding.cbLed.isChecked) finalniCena += 5
            if (binding.cbBrcko.isChecked) finalniCena += 2

            val intent = Intent(this, SummaryActivity::class.java)
            intent.putExtra("DRUH", nazevDruh)
            intent.putExtra("CENA", finalniCena)
            intent.putExtra("LED", binding.cbLed.isChecked)
            intent.putExtra("BRCKO", binding.cbBrcko.isChecked)
            startActivity(intent)
        }
    }
}