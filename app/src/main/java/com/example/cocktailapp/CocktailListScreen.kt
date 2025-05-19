package com.example.cocktailapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cocktailapp.data.Cocktail
import kotlinx.coroutines.launch

@Composable
fun CocktailListScreen(
    cocktails: List<Cocktail>,
    onCocktailClick: (Int) -> Unit,
    onToggleTheme: () -> Unit
) {
    val tabs = listOf("Główna", "Alkoholowe", "Bezalkoholowe")
    val pagerState = rememberPagerState(pageCount = { tabs.size })  // tutaj poprawka: pageCount to lambda
    val coroutineScope = rememberCoroutineScope()

    Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(title) }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> MainInfoCard()
                1 -> CocktailGridScreen(
                    cocktails = cocktails.filter { it.isAlcoholic },
                    onCocktailClick = onCocktailClick,
                    onToggleTheme = onToggleTheme
                )
                2 -> CocktailGridScreen(
                    cocktails = cocktails.filter { !it.isAlcoholic },
                    onCocktailClick = onCocktailClick,
                    onToggleTheme = onToggleTheme
                )
            }
        }
    }
}

@Composable
fun MainInfoCard() {
    Surface(
        modifier = Modifier.padding(16.dp),
        tonalElevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Witamy w aplikacji Cocktail App!", style = MaterialTheme.typography.headlineSmall)
            Text(
                "Ta aplikacja jest dla fanów koktaili alkoholowych i bezalkoholowych ale szczególnie tych alkoholowych. " +
                        "Jeśli lubisz alkohol to dobrze wybrałeś.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
