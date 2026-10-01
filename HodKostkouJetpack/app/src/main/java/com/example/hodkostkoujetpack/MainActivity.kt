package com.example.hodkostkoujetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                DiceRollerScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun DiceRollerScreen(modifier: Modifier = Modifier) {
    // Textové symboly kostky
    val diceSymbols = listOf("⚀", "⚁", "⚂", "⚃", "⚄", "⚅")

    // Stavy (State) - když se změní, Compose sám automaticky překreslí obrazovku
    var currentDiceIndex by remember { mutableIntStateOf(0) } // Index 0 až 5 pro seznam
    var isRolling by remember { mutableStateOf(false) } // Zda se zrovna hází

    // Pro asynchronní operace (potřebujeme kvůli pauze "delay")
    val coroutineScope = rememberCoroutineScope()

    // Hlavní obal, co všechno seřadí pod sebe a vycentruje doprostřed obrazovky
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1. Nadpis
        Text(
            text = "Hoď kostkou",
            fontSize = 32.sp
        )

        // Mezera 32 bodů
        Spacer(modifier = Modifier.height(32.dp))

        // 2. Velký symbol kostky
        Text(
            text = diceSymbols[currentDiceIndex],
            fontSize = 150.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 3. Tlačítko
        Button(
            onClick = {
                // Po kliknutí se spustí tahle část
                coroutineScope.launch {
                    isRolling = true // Zablokuje se tlačítko

                    // 10 náhodných změn s prodlevou 250 ms
                    for (i in 1..10) {
                        currentDiceIndex = (0..5).random() // Vybere náhodný symbol 0-5
                        delay(250) // Počká 250 milisekund
                    }

                    isRolling = false // Odblokuje se tlačítko, animace skončila
                }
            },
            // Tlačítko půjde zmáčknout, jen když se zrovna neháže (isRolling = false)
            enabled = !isRolling
        ) {
            Text(text = "Hodit", fontSize = 24.sp)
        }
    }
}