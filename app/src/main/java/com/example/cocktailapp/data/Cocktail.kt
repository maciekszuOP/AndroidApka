package com.example.cocktailapp.data

import androidx.annotation.DrawableRes

data class Cocktail(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: String,
    val shakeTimeInSeconds: Int,
    val isAlcoholic: Boolean, // czy koktajl jest alkoholowy
    @DrawableRes val imageRes: Int // lokalny obrazek z drawable
)