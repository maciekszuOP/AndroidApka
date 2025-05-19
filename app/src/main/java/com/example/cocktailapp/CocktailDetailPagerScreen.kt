package com.example.cocktailapp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cocktailapp.data.Cocktail

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CocktailDetailPagerScreen(
    cocktails: List<Cocktail>,
    initialIndex: Int = 0,
    onToggleTheme: () -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = initialIndex,
        pageCount = { cocktails.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        CocktailDetailScreen(
            cocktail = cocktails[page],
            onToggleTheme = onToggleTheme
        )
    }
}
