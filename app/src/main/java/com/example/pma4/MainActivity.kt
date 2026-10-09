package com.example.pma4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pma4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rgDruhy.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbKlasik -> binding.imgMonster.setImageResource(R.drawable.mk)
                R.id.rbBily -> binding.imgMonster.setImageResource(R.drawable.mb)
                R.id.rbLando -> binding.imgMonster.setImageResource(R.drawable.mln)
            }
        }

        binding.btnObjednat.setOnClickListener {
            // Tady to taháme ze strings.xml a přidáváme odřádkování přes \n
            var souhrnText = getString(R.string.text_vybrany_monster) + "\n"

            souhrnText += when (binding.rgDruhy.checkedRadioButtonId) {
                R.id.rbKlasik -> getString(R.string.text_klasik) + "\n"
                R.id.rbBily -> getString(R.string.text_bily) + "\n"
                R.id.rbLando -> getString(R.string.text_lando) + "\n"
                else -> getString(R.string.text_nevybrano) + "\n"
            }

            souhrnText += "\n" + getString(R.string.text_dalsi_pozadavky) + "\n"

            if (binding.cbLedove.isChecked) {
                souhrnText += getString(R.string.text_ledovy) + "\n"
            }
            if (binding.cbBrcko.isChecked) {
                souhrnText += getString(R.string.text_brcko) + "\n"
            }

            if (!binding.cbLedove.isChecked && !binding.cbBrcko.isChecked) {
                souhrnText += getString(R.string.text_bez_uprav) + "\n"
            }

            binding.tvSouhrn.text = getString(R.string.text_souhrn_nadpis) + "\n" + souhrnText
        }

        binding.btnVymazat.setOnClickListener {
            binding.rgDruhy.check(R.id.rbKlasik)

            binding.cbLedove.isChecked = false
            binding.cbBrcko.isChecked = false

            binding.tvSouhrn.text = getString(R.string.tv_souhrn)
        }

    }
}