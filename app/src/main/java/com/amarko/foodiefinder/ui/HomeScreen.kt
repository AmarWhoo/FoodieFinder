package com.amarko.foodiefinder.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            // TODO: Maybe change this into an animated loading circle, but we'll see...
            CircularProgressIndicator(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center))
        }
        recipeState.value?.isSuccessful == true -> {
            val recipe: RecipeInstance? = recipeState.value?.body()
            if (recipe != null) {
                RecipeItem(recipe)
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

@Composable
fun RecipeItem(recipe: RecipeInstance) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = recipe.title,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        RecipeCard(recipe = recipe)
        Spacer(modifier = Modifier.height(24.dp))
        
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            recipe.extendedIngredients.forEach{
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = it.originalName)
            }
            Spacer(modifier = Modifier.height(24.dp))
            recipe.analyzedInstructions.forEach{
                it.steps.forEach{ steps ->
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "${steps.number}. ${steps.step}")
                }
            }
        }
    }
}