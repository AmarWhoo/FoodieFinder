package com.amarko.foodiefinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amarko.foodiefinder.models.RecipeInstance
import com.amarko.foodiefinder.repo.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    var individualRecipe: MutableLiveData<Response<RecipeInstance>> = MutableLiveData()

    fun getRecipeInstance(apiKey: String, number: Int) {
        viewModelScope.launch {
            val response = repository.getRecipeInfo(apiKey = apiKey, number = number)
            if (response.isSuccessful) {
                val recipes = response.body()?.recipes
                if (recipes != null && recipes.isNotEmpty()) {
                    val individualRecipeResponse = Response.success(recipes[0])
                    individualRecipe.value = individualRecipeResponse
                }
            }
        }
    }
}