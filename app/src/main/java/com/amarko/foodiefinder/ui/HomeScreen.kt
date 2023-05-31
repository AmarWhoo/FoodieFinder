package com.amarko.foodiefinder.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.amarko.foodiefinder.api.RetrofitInstance.API_KEY
import com.amarko.foodiefinder.models.RecipeInstance
import com.amarko.foodiefinder.ui.components.RecipeDisplay
import com.amarko.foodiefinder.viewmodel.MainViewModel
import retrofit2.Response

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val recipeState: State<Response<RecipeInstance>?> = viewModel.individualRecipe.observeAsState(initial = null)

    when {
        recipeState.value == null -> {
            // TODO: Maybe change this into an animated loading circle, but we'll see...
            CircularProgressIndicator(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center))
        }
        recipeState.value?.isSuccessful == true -> {
            val recipe: RecipeInstance? = recipeState.value?.body()
            if (recipe != null) {
                RecipeDisplay(recipe)
            } else {
                Text(text = "Recipe data is null!")
            }
        }
        else -> {
            val errorResponse: Response<RecipeInstance>? = recipeState.value
            Text(text = "Error: ${errorResponse?.message()}")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getRandomRecipeInstance(API_KEY, 1)
    }
}