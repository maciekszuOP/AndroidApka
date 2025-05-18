package com.example.cocktailapp

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cocktailapp.data.Cocktail
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.*
//import nl.dionsegijn.konfetti.core.
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.Angle
//import nl.dionsegijn.konfetti.core.Speed
import nl.dionsegijn.konfetti.core.emitter.Emitter


@Composable
fun CocktailDetailScreen(
    cocktail: Cocktail,
    viewModel: CocktailTimerViewModel = viewModel()
) {
    val timeLeft by viewModel.timeLeft.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    val showConfetti by viewModel.showConfetti.collectAsState()

    val scale by rememberInfiniteTransition().animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        )
    )

    var parties by remember { mutableStateOf<List<Party>>(emptyList()) }

    LaunchedEffect(showConfetti) {
        println("🎉 Confetti trigger: $showConfetti")
        if (showConfetti) {
            // Zmień listę — nowa instancja za każdym razem
            val party = Party(
                emitter = Emitter(duration = 3, TimeUnit.SECONDS).perSecond(200),
                speed = 0f,
                maxSpeed = 30f,
                damping = 0.9f,
                spread = 360,
                colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                position = Position.Relative(0.5, 0.3)
            )

            parties = listOf(party) // Nowa instancja
            delay(5000)
            parties = emptyList()
            viewModel.resetConfetti()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (parties.isNotEmpty()) {
            KonfettiView(
                modifier = Modifier.fillMaxSize(),
                parties = parties
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = cocktail.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Składniki:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            cocktail.ingredients.forEach {
                Text(text = "• $it")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Sposób przygotowania:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = cocktail.instructions)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "⏱️ Wstrząsaj przez: ${cocktail.shakeTimeInSeconds} sekundy",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Pozostały czas:",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                "$timeLeft sek.",
                modifier = if(isRunning) Modifier
                    .fillMaxWidth()
                    .graphicsLayer(scaleX = scale, scaleY = scale) else Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                color = if (timeLeft == 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = {
                        if (timeLeft > 0 && !isRunning) {
                            viewModel.resumeTimer()
                        } else {
                            viewModel.startTimer(cocktail.shakeTimeInSeconds)
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "Start")
                    Spacer(Modifier.width(8.dp))
                    Text("Start")
                }

                Button(onClick = { viewModel.stopTimer() }) {
                    Icon(imageVector = Icons.Filled.Stop, contentDescription = "Stop")
                    Spacer(Modifier.width(8.dp))
                    Text("Stop")
                }

                Button(onClick = { viewModel.resetTimer(cocktail.shakeTimeInSeconds) }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Przerwij")
                    Spacer(Modifier.width(8.dp))
                    Text("Przerwij")
                }
            }
        }
    }
}
