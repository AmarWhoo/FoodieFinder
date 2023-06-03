package com.amarko.foodiefinder.repo

import com.amarko.foodiefinder.api.RetrofitInstance
import com.amarko.foodiefinder.models.AnalyzeInstructionsRequest
import com.amarko.foodiefinder.models.AnalyzeInstructionsResponse
import com.amarko.foodiefinder.models.Recipes
import retrofit2.Response

class Repository {
    suspend fun getRecipeInfo(apiKey: String, tags: String, number: Int): Response<Recipes> {
        return RetrofitInstance.api.getRecipe(apiKey = apiKey, tags = tags, number = number)
    }

    suspend fun analyzeInstructions(apiKey: String, instructions: List<String>): Response<AnalyzeInstructionsResponse> {
        val request = AnalyzeInstructionsRequest(instructions)
        return RetrofitInstance.api.analyzeInstructions(apiKey = apiKey, request = request)
    }
}