package com.amarko.foodiefinder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amarko.foodiefinder.ui.HomeScreen
import com.amarko.foodiefinder.viewmodel.MainViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.Favorites.route
        ) {
            // TODO: Add favorites screen call
        }
        composable(
            route = Screen.Settings.route
        ) {
            // TODO: Add settings screen call
        }
    }
}