package com.amarko.foodiefinder.models

data class RecipeInstance(
    val id: Int,
    val title: String,
    val image: String,
    val servings: Int,
    val readyInMinutes: Int,
    val summary: String,
    val extendedIngredients: List<ExtendedIngredient>,
    val analyzedInstructions: List<AnalyzedInstruction>
)
