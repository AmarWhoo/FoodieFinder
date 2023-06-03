package com.amarko.foodiefinder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amarko.foodiefinder.models.Equipment
import com.amarko.foodiefinder.models.RecipeInstance
import com.amarko.foodiefinder.ui.FavoritesScreen
import com.amarko.foodiefinder.ui.HomeScreen
import com.amarko.foodiefinder.ui.SettingsScreen
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    recipeStateFlow: StateFlow<RecipeInstance?>,
    equipmentList: List<Equipment>,
    onRefreshClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                recipeStateFlow = recipeStateFlow,
                equipmentList = equipmentList,
                onRefreshClick = onRefreshClick
            )
        }
        composable(
            route = Screen.Favorites.route
        ) {
            FavoritesScreen()
        }
        composable(
            route = Screen.Settings.route
        ) {
            SettingsScreen()
        }
    }
}