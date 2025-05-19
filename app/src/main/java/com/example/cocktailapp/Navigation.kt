package com.example.cocktailapp

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.cocktailapp.data.FakeCocktailRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailApp(
    navController: NavHostController,
    onToggleTheme: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val cocktails = FakeCocktailRepository.cocktails

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Nawigacja", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))

                NavigationDrawerItem(
                    label = { Text("Menu Główne") },
                    selected = false,
                    onClick = {
                        navController.navigate("cocktailList") {
                            popUpTo("cocktailList") { inclusive = true }
                        }
                        scope.launch { drawerState.close() }
                    }
                )

                // Sekcja: Alkoholowe
                Text(
                    "Alkoholowe",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 4.dp)
                )
                cocktails.filter { it.isAlcoholic }.forEach { cocktail ->
                    NavigationDrawerItem(
                        label = { Text(cocktail.name) },
                        selected = false,
                        onClick = {
                            navController.navigate("cocktailPager/${cocktail.id}")
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

                // Sekcja: Bezalkoholowe
                Text(
                    "Bezalkoholowe",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 4.dp)
                )
                cocktails.filter { !it.isAlcoholic }.forEach { cocktail ->
                    NavigationDrawerItem(
                        label = { Text(cocktail.name) },
                        selected = false,
                        onClick = {
                            navController.navigate("cocktailPager/${cocktail.id}")
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Cocktail App") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = onToggleTheme) {
                            Icon(Icons.Filled.Brightness6, contentDescription = "Toggle Theme")
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "cocktailList",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("cocktailList") {
                    CocktailListScreen(
                        cocktails = cocktails,
                        onCocktailClick = { id ->
                            navController.navigate("cocktailPager/$id")
                        },
                        onToggleTheme = onToggleTheme
                    )
                }

                composable(
                    "cocktailPager/{cocktailId}",
                    arguments = listOf(navArgument("cocktailId") {
                        type = NavType.IntType
                    })
                ) { backStackEntry ->
                    val cocktailId = backStackEntry.arguments?.getInt("cocktailId") ?: 0
                    val initialIndex = cocktails.indexOfFirst { it.id == cocktailId }.coerceAtLeast(0)

                    val pagerState = rememberPagerState(
                        initialPage = initialIndex,
                        pageCount = { cocktails.size }
                    )

                    Scaffold { innerPadding ->
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.padding(innerPadding)
                        ) { page ->
                            CocktailDetailScreen(
                                cocktail = cocktails[page],
                                onToggleTheme = onToggleTheme
                            )
                        }
                    }
                }
            }
        }
    }
}
