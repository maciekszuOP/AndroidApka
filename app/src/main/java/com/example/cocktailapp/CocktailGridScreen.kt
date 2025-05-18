package com.example.cocktailapp

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cocktailapp.data.Cocktail
import com.example.cocktailapp.data.FakeCocktailRepository
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign // Import dla wyrównania tekstu
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue


@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class) // Wymagana adnotacja dla TopAppBar w Material 3

@Composable
fun CocktailGridScreen(onCocktailClick: (Int) -> Unit) {
    val cocktails = FakeCocktailRepository.cocktails

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Takie tam drinki",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val columns = when {
                maxWidth < 400.dp -> 1
                maxWidth < 600.dp -> 2
                maxWidth < 840.dp -> 3
                else -> 4
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cocktails) { cocktail ->
                    CocktailGridItem(
                        cocktail = cocktail,
                        onClick = { onCocktailClick(cocktail.id) }
                    )
                }
            }
        }
    }
}



@Composable
fun CocktailGridItem(cocktail: Cocktail, onClick: () -> Unit) {
    var imageWidthPx by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val imageWidthDp = with(density) { imageWidthPx.toDp() }

    // Współczynnik skalowania
    val scale = imageWidthDp.value / 100f

    // Dynamiczne style
    val fontSize = (scale * 10f).sp
    val horizontalPadding = (scale * 8f).dp
    val borderThickness = (scale * 1f).dp
    val cornerRadius = (scale * 12f).dp
    val spacing = (scale * 4f).dp

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
                .padding(bottom = spacing)
                .border(
                    BorderStroke(borderThickness, Color.Black),
                    RoundedCornerShape(cornerRadius)
                )
                .clip(RoundedCornerShape(cornerRadius))
                .onSizeChanged { size ->
                    imageWidthPx = size.width
                },
            contentScale = ContentScale.Crop
        )
        Text(
            text = cocktail.name,
            fontSize = fontSize,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding),
            textAlign = TextAlign.Center
        )
    }
}

