package com.amarko.foodiefinder.models

data class AnalyzedInstruction (
    val name: String,
    val steps: List<Step>
)

data class Step(
    val number :Int,
    val step: String
)