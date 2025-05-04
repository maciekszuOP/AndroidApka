package com.example.cocktailapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.* // Pamiętaj o imporcie Material 3
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cocktailapp.data.Cocktail
import com.example.cocktailapp.data.FakeCocktailRepository
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign // Import dla wyrównania tekstu

@OptIn(ExperimentalMaterial3Api::class) // Wymagana adnotacja dla TopAppBar w Material 3
@Composable
fun CocktailGridScreen(onCocktailClick: (Int) -> Unit) {
    val cocktails = FakeCocktailRepository.cocktails

    // *** Tutaj używamy Scaffold ***
    Scaffold(
        // Definicja górnego paska (TopAppBar)
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Takie tam drinki", // Tytuł paska
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
                // Możesz dodać nawigacyjne ikony lub akcje tutaj
            )
        },
        // Możesz dodać inne parametry Scaffold, np. bottomBar, floatingActionButton
    ) { paddingValues -> // Ten parametr zawiera padding wymagany przez Scaffold
        // *** Tutaj umieszczamy główną zawartość ekranu ***
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), // *** Bardzo ważne: zastosuj padding! ***
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(cocktails) { cocktail ->
                CocktailGridItem(cocktail = cocktail, onClick = { onCocktailClick(cocktail.id) })
            }
        }
    }
}

// Komponent do wyświetlania pojedynczego elementu w siatce (bez zmian w strukturze)
@Composable
fun CocktailGridItem(cocktail: Cocktail, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = cocktail.imageRes),
            contentDescription = cocktail.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(bottom = 4.dp)
                // Dodajemy modyfikatory do ulepszenia wyglądu
                .border( // Dodajemy obramówkę
                    BorderStroke(
                        1.dp, // Grubość obramówki
                        Color.Black // Kolor obramówki - czarny
                    ),
                    RoundedCornerShape(16.dp) // Obramówka ma zaokrąglone rogi o promieniu 15dp
                )
                .clip(RoundedCornerShape(15.dp)), // Zaokrąglamy rogi obrazu o promieniu 15dp
            contentScale = ContentScale.Crop // Skalowanie obrazu
        )
        Text(
            text = cocktail.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth() // Text wypełnia całą dostępną szerokość Column
                .padding(horizontal = 8.dp), // *** Padding horyzontalny 8dp ***
            textAlign = TextAlign.Center // Tekst jest wyśrodkowany poziomo
        )
    }
}