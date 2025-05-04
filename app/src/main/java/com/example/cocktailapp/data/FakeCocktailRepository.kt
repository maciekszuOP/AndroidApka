package com.example.cocktailapp.data

import com.example.cocktailapp.R

object FakeCocktailRepository {
    val cocktails = listOf(
        Cocktail(
            id = 1,
            name = "Mojito",
            ingredients = listOf("Rum", "Mięta", "Cukier", "Limonka", "Woda gazowana"),
            instructions = "Zgnieć miętę z cukrem i limonką, dodaj rum i wodę gazowaną, wymieszaj.",
            shakeTimeInSeconds = 20,
            imageRes = R.drawable.mohito
        ),
        Cocktail(
            id = 2,
            name = "Bloody Mary",
            ingredients = listOf("Wódka", "Sok pomidorowy", "Sos Worcestershire", "Sól", "Pieprz"),
            instructions = "Wymieszaj wszystkie składniki i podawaj z lodem oraz selerem naciowym.",
            shakeTimeInSeconds = 30,
            imageRes = R.drawable.bloody_mary
        ),
        Cocktail(
            id = 3,
            name = "Pina Colada",
            ingredients = listOf("Rum", "Mleko kokosowe", "Sok ananasowy"),
            instructions = "Zmiksuj składniki z lodem i podawaj w wysokiej szklance.",
            shakeTimeInSeconds = 25,
            imageRes = R.drawable.pina_kolada
        ),
        Cocktail(
            id = 4,
            name = "Whiskey Sour",
            ingredients = listOf("Whiskey", "Sok z cytryny", "Syrop cukrowy"),
            instructions = "Wstrząśnij wszystkie składniki z lodem i odcedź do szklanki.",
            shakeTimeInSeconds = 69,
            imageRes = R.drawable.whiskey_sour
        ),
        Cocktail(
            id = 5,
            name = "Cosmopolitan",
            ingredients = listOf("Wódka", "Triple sec", "Sok żurawinowy", "Sok z limonki"),
            instructions = "Wstrząśnij z lodem i podawaj w kieliszku koktajlowym.",
            shakeTimeInSeconds = 30,
            imageRes = R.drawable.cosmopolitan
        ),
        Cocktail(
            id = 6,
            name = "Tequila Sunrise",
            ingredients = listOf("Tequila", "Sok pomarańczowy", "Grenadyna"),
            instructions = "Wlej składniki warstwowo, nie mieszaj, podawaj z lodem.",
            shakeTimeInSeconds = 15,
            imageRes = R.drawable.tequila_sunrise
        ),
        Cocktail(
            id = 7,
            name = "Negroni",
            ingredients = listOf("Gin", "Campari", "Słodki wermut"),
            instructions = "Wymieszaj wszystkie składniki z lodem i podawaj z plasterkiem pomarańczy.",
            shakeTimeInSeconds = 10,
            imageRes = R.drawable.negroni
        ),
        Cocktail(
            id = 8,
            name = "Caipirinha",
            ingredients = listOf("Cachaça", "Limonka", "Cukier trzcinowy"),
            instructions = "Zgnieć limonkę z cukrem, dodaj cachaçę i lód. Wymieszaj.",
            shakeTimeInSeconds = 80,
            imageRes = R.drawable.caipirinha
        ),
        Cocktail(
            id = 9,
            name = "Mai Tai",
            ingredients = listOf("Rum jasny", "Rum ciemny", "Triple sec", "Sok limonkowy", "Syrop migdałowy"),
            instructions = "Wymieszaj wszystkie składniki i podawaj z lodem.",
            shakeTimeInSeconds = 60,
            imageRes = R.drawable.mai_tai
        ),
        Cocktail(
            id = 10,
            name = "Espresso Martini",
            ingredients = listOf("Wódka", "Likier kawowy", "Świeżo zaparzone espresso"),
            instructions = "Wstrząśnij z lodem i odcedź do schłodzonego kieliszka.",
            shakeTimeInSeconds = 30,
            imageRes = R.drawable.espresso_martini
        )
    )
}
