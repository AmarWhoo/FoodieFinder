package com.amarko.foodiefinder.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amarko.foodiefinder.models.RecipeInstance
import com.amarko.foodiefinder.ui.DetailCard

@Composable
fun RecipeDisplay(recipe: RecipeInstance) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = recipe.title,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        DetailCard(recipe = recipe)
        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            recipe.extendedIngredients.forEach{
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = it.originalName)
            }
            Spacer(modifier = Modifier.height(24.dp))
            recipe.analyzedInstructions.forEach{
                it.steps.forEach{ steps ->
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "${steps.number}. ${steps.step}")
                }
            }
        }
    }
}