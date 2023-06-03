package com.amarko.foodiefinder.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amarko.foodiefinder.models.Equipment
import com.amarko.foodiefinder.models.RecipeInstance
import com.amarko.foodiefinder.navigation.Screen
import com.amarko.foodiefinder.navigation.SetupNavGraph
import kotlinx.coroutines.flow.StateFlow

@Composable
fun Frame(
    navController: NavHostController,
    recipeStateFlow: StateFlow<RecipeInstance?>,
    equipmentList: List<Equipment>,
    onApplyFilters: (String) -> Unit,
    onRefreshClick: () -> Unit
) {
    var selectedScreen by remember { mutableStateOf(1) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = selectedScreen == 0,
                    onClick = {
                        selectedScreen = 0
                        navController.navigate(Screen.Settings.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedScreen == 1,
                    onClick = {
                        selectedScreen = 1
                        navController.navigate(Screen.Home.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedScreen == 2,
                    onClick = {
                        selectedScreen = 2
                        navController.navigate(Screen.Favorites.route)
                    }
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 112.dp)
                .fillMaxSize()
        )
        SetupNavGraph(
            navController = navController,
            recipeStateFlow = recipeStateFlow,
            equipmentList = equipmentList,
            onApplyFilters = onApplyFilters,
            onRefreshClick = onRefreshClick
        )
    }
}
