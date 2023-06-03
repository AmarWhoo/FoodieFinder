package com.amarko.foodiefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.amarko.foodiefinder.api.RetrofitInstance
import com.amarko.foodiefinder.models.RecipeInstance
import com.amarko.foodiefinder.repo.Repository
import com.amarko.foodiefinder.ui.components.Frame
import com.amarko.foodiefinder.ui.theme.FoodieFinderTheme
import com.amarko.foodiefinder.viewmodel.MainViewModel
import com.amarko.foodiefinder.viewmodel.MainViewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {

    private val recipeStateFlow: MutableStateFlow<RecipeInstance?> = MutableStateFlow(null)

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        super.onCreate(savedInstanceState)
        setContent {
            FoodieFinderTheme {
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    viewModel.individualRecipe.collect { response ->
                        if (response?.isSuccessful == true) {
                            val recipe = response.body()
                            recipeStateFlow.value = recipe
                        } else {
                            recipeStateFlow.value = null
                        }
                    }
                }

                LaunchedEffect(Unit) {
                    viewModel.getRandomRecipeInstance(RetrofitInstance.API_KEY, 1)
                }

                val recipeState by recipeStateFlow.collectAsState(null)
                if (recipeState != null) {
                    val instructions: List<String> = recipeState!!.analyzedInstructions.flatMap { it.steps.map { step -> step.step } }

                    viewModel.performAnalyzeInstructions(RetrofitInstance.API_KEY, instructions)
                    val equipmentList by viewModel.analyzedInstructions.collectAsState(emptyList())

//                  if (equipmentList.isNotEmpty())
                    Frame(
                        navController = navController,
                        recipeStateFlow = recipeStateFlow,
                        equipmentList = equipmentList,
                        onRefreshClick = {
                            viewModel.getRandomRecipeInstance(RetrofitInstance.API_KEY, 1)
                        }
                    )
                } else {
                    Text(
                        text = "Loading...",
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}