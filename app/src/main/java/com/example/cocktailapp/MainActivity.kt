package com.example.cocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.cocktailapp.ui.theme.CocktailAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            CocktailAppTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()

                CocktailApp(
                    navController = navController,
                    onToggleTheme = { isDarkTheme = !isDarkTheme }  // przełącz motyw globalnie
                )
            }
        }
    }
}
