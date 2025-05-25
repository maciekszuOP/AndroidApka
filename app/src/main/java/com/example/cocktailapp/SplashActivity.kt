package com.example.cocktailapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var startAnimation by remember { mutableStateOf(false) }

            val rotation = animateFloatAsState(
                targetValue = if (startAnimation) 360f else 0f,
                animationSpec = tween(durationMillis = 1500)
            )

            LaunchedEffect(Unit) {
                startAnimation = true
                delay(1500)
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(150.dp).rotate(rotation.value)
                )
            }
        }
    }
}
