package com.amarko.foodiefinder.api

import com.amarko.foodiefinder.models.AnalyzeInstructionsRequest
import com.amarko.foodiefinder.models.AnalyzeInstructionsResponse
import com.amarko.foodiefinder.models.Recipes
import retrofit2.Response
import retrofit2.http.*

interface SpoonApi {
    @GET("recipes/random")
    suspend fun getRecipe(
        @Query("apiKey") apiKey: String,
        @Query("tags") tags: String,
        @Query("number") number: Int
    ): Response<Recipes>

    @POST("recipes/analyzeInstructions")
    suspend fun analyzeInstructions(
        @Query("apiKey") apiKey: String,
        @Body request: AnalyzeInstructionsRequest
    ): Response<AnalyzeInstructionsResponse>
}