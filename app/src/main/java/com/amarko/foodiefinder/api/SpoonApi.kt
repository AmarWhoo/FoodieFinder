package com.amarko.foodiefinder.api

import com.amarko.foodiefinder.models.RecipeInstance
import com.amarko.foodiefinder.models.Recipes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonApi {
    @GET("recipes/random")
    suspend fun getRecipe(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int
    ): Response<Recipes>

    @GET("recipes/{id}/information")
    suspend fun getRecipeById(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): Response<RecipeInstance>
}