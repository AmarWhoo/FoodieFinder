package com.amarko.foodiefinder.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amarko.foodiefinder.models.Equipment
import com.amarko.foodiefinder.models.RecipeInstance
import com.amarko.foodiefinder.repo.Repository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _individualRecipe = MutableSharedFlow<Response<RecipeInstance>?>()
    val individualRecipe: SharedFlow<Response<RecipeInstance>?> = _individualRecipe

    private val _analyzedInstructions = MutableSharedFlow<List<Equipment>>()
    val analyzedInstructions: SharedFlow<List<Equipment>> = _analyzedInstructions

    fun getRandomRecipeInstance(apiKey: String, tags: String, number: Int) {
        viewModelScope.launch {
            val response = repository.getRecipeInfo(apiKey = apiKey, tags = tags, number = number)
            if (response.isSuccessful) {
                val recipes = response.body()?.recipes
                if (recipes != null && recipes.isNotEmpty()) {
                    val individualRecipeResponse = Response.success(recipes[0])
                    _individualRecipe.emit(individualRecipeResponse)
                }
            }
        }
    }

    fun performAnalyzeInstructions(apiKey: String, instructions: List<String>) {
        viewModelScope.launch {
            try {
                val response = repository.analyzeInstructions(apiKey, instructions)
                if (response.isSuccessful) {
                    val analyzedResponse = response.body()
                    val equipmentList = analyzedResponse?.equipment ?: emptyList()
                    _analyzedInstructions.emit(equipmentList)
                }
            } catch (e: Exception) {
                Log.e(TAG, "An error occurred: ${e.message}", e)
                // Emit an error state using a separate SharedFlow or handle the error accordingly
            }
        }
    }
}