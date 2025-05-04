package com.example.cocktailapp

//import CocktailApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cocktailapp.ui.theme.CocktailAppTheme
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailAppTheme {
                val navController = rememberNavController()
                CocktailApp(navController = navController)
            }
        }
    }
}
