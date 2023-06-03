package com.amarko.foodiefinder.models

data class AnalyzeInstructionsRequest(
    val instructions: List<String>
)

data class AnalyzeInstructionsResponse(
    val equipment: List<Equipment>
)

data class Equipment(
    val id: Int,
    val name: String
)