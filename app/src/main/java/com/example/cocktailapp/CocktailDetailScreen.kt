package com.example.cocktailapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cocktailapp.data.Cocktail
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CocktailDetailScreen(cocktail: Cocktail, viewModel: CocktailTimerViewModel = viewModel()) {
    val timeLeft by viewModel.timeLeft.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(text = cocktail.name, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Składniki:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        cocktail.ingredients.forEach {
            Text(text = "• $it")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Sposób przygotowania:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = cocktail.instructions)

        Spacer(modifier = Modifier.height(24.dp))
        Text("⏱️ Wstrząsaj przez: ${cocktail.shakeTimeInSeconds} sekundy")

        Spacer(modifier = Modifier.height(8.dp))
        Text("Pozostały czas: $timeLeft sek.")

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                if (timeLeft > 0 && !isRunning) {
                    viewModel.resumeTimer()
                } else {
                    viewModel.startTimer(cocktail.shakeTimeInSeconds)
                }
            }) {
                Text("Start")
            }

            Button(onClick = {
                viewModel.stopTimer()
            }) {
                Text("Stop")
            }
            Button(onClick = {
                viewModel.resetTimer(cocktail.shakeTimeInSeconds)
            }) {
                Text("Przerwij")
            }
        }
    }
}
