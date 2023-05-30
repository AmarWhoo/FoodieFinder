package com.amarko.foodiefinder.models

data class ExtendedIngredient (
    val id: Int,
    val originalName: String,
    val name: String,
    val amount: Double,
    val unit: String
)