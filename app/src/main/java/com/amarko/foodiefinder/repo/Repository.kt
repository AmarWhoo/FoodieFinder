package com.amarko.foodiefinder.repo

import com.amarko.foodiefinder.api.RetrofitInstance
import com.amarko.foodiefinder.models.RecipeInstance
import com.amarko.foodiefinder.models.Recipes
import retrofit2.Response

class Repository {
    suspend fun getRecipeInfo(apiKey: String, number: Int): Response<Recipes> {
        return RetrofitInstance.api.getRecipe(apiKey = apiKey, number = number)
    }

    suspend fun getRecipeById(apiKey: String, id: Int): Response<RecipeInstance> {
        return RetrofitInstance.api.getRecipeById(id = id, apiKey = apiKey)
    }
}