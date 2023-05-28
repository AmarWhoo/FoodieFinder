package com.amarko.foodiefinder.api

import com.amarko.foodiefinder.models.Recipes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonApi {
    @GET("recipes/random")
    suspend fun getRecipe(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int
    ): Response<Recipes>
}