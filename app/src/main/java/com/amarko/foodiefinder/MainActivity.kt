package com.amarko.foodiefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
                navController = rememberNavController()
                SetupNavGraph(navController = navController, viewModel = viewModel)
            }
        }
    }
}