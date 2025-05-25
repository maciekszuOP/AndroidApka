package com.example.cocktailapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.cocktailapp.data.FakeCocktailRepository
import com.example.cocktailapp.data.Cocktail



@Composable
fun CocktailMainScreen(
    windowSizeClass: WindowWidthSizeClass,
    onToggleTheme: () -> Unit
) {
    val isTablet = windowSizeClass >= WindowWidthSizeClass.Medium
    val allCocktails = FakeCocktailRepository.cocktails

    var selectedCocktailId by remember { mutableStateOf<Int?>(null) }

    if (isTablet) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                CocktailGridScreen(
                    cocktails = allCocktails,
                    onCocktailClick = { selectedId ->
                        selectedCocktailId = selectedId
                    },
                    onToggleTheme = onToggleTheme
                )
            }
            Box(modifier = Modifier.weight(2f)) {
                val cocktail = allCocktails.find { it.id == selectedCocktailId } ?: allCocktails.first()
                CocktailDetailScreen(
                    cocktail = cocktail,
                    onToggleTheme = onToggleTheme
                )
            }
        }
    } else {
        val navController = rememberNavController()
        CocktailApp(
            navController = navController,
            onToggleTheme = onToggleTheme
        )
    }
}
