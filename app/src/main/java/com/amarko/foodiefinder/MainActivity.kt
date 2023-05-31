package com.amarko.foodiefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.amarko.foodiefinder.navigation.Screen
import com.amarko.foodiefinder.navigation.SetupNavGraph
import com.amarko.foodiefinder.repo.Repository
import com.amarko.foodiefinder.ui.theme.FoodieFinderTheme
import com.amarko.foodiefinder.viewmodel.MainViewModel
import com.amarko.foodiefinder.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        super.onCreate(savedInstanceState)
        setContent {
            FoodieFinderTheme {
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
                    navController = rememberNavController()
                    SetupNavGraph(navController = navController, viewModel = viewModel)
                }

            }
        }
    }
}