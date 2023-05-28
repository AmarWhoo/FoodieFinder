package com.amarko.foodiefinder.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import coil.compose.AsyncImage
import com.amarko.foodiefinder.api.RetrofitInstance.API_KEY
import com.amarko.foodiefinder.models.RecipeInstance
import com.amarko.foodiefinder.viewmodel.MainViewModel
import retrofit2.Response

@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    val recipeState: State<Response<RecipeInstance>?> = viewModel.individualRecipe.observeAsState(initial = null)

    when {
        recipeState.value == null -> {

        }
        recipeState.value?.isSuccessful == true -> {
            val recipe: RecipeInstance? = recipeState.value?.body()
            if (recipe != null) {
                RecipeItem(recipe)
            } else {
                // TODO: handle case where value is null
            }
        }
        else -> {
            val errorResponse: Response<RecipeInstance>? = recipeState.value
            println(errorResponse)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getRecipeInstance(API_KEY, 1)
    }
}

@Composable
fun RecipeItem(recipe: RecipeInstance) {
    Text(text = recipe.title)
    AsyncImage(
        model = recipe.image,
        contentDescription = null
    )
}