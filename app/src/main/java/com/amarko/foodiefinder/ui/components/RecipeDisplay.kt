package com.amarko.foodiefinder.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.amarko.foodiefinder.models.RecipeInstance

@Composable
fun RecipeDisplay(
    navController: NavController,
    recipe: RecipeInstance
) {
    Text(text = "Home")
}