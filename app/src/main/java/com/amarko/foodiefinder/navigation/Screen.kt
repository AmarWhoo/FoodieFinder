package com.amarko.foodiefinder.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Favorites: Screen(route = "fav_screen")
    object Settings: Screen(route = "settings_screen")
}