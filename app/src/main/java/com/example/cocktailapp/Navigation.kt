package com.example.cocktailapp

import com.example.cocktailapp.* // Replace with your actual package name


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cocktailapp.data.FakeCocktailRepository
import com.example.cocktailapp.data.Cocktail
@Composable
fun CocktailApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "cocktailList") {

        // Komponent listy koktajli
        composable("cocktailList") {
            CocktailGridScreen { selectedCocktailId ->
                // Nawigacja do ekranu szczegółów koktajlu
                navController.navigate("details/$selectedCocktailId")
            }
        }

        // Komponent szczegółów koktajlu
        composable("details/{cocktailId}") { backStackEntry ->
            // Pobranie ID koktajlu z argumentów
            val id = backStackEntry.arguments?.getString("cocktailId")?.toIntOrNull()
            val cocktail = FakeCocktailRepository.cocktails.find { it.id == id }

            // Jeśli koktajl został znaleziony, wyświetl jego szczegóły
            if (cocktail != null) {
                CocktailDetailScreen(cocktail)
            }
        }
    }
}
