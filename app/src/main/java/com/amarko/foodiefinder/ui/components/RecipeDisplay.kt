package com.amarko.foodiefinder.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amarko.foodiefinder.models.Equipment
import com.amarko.foodiefinder.models.RecipeInstance

@Composable
fun RecipeDisplay(
    recipe: RecipeInstance,
    equipmentList: List<Equipment>
) {
    LazyColumn{
        item {
            Text(
                text = recipe.title,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(12.dp)
            )

            DetailCard(recipe = recipe, equipmentList = equipmentList)

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Ingredients",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .align(Alignment.CenterHorizontally)
                )
                recipe.extendedIngredients.forEach{ ingredient ->
                    Text(
                        text = " - ${ingredient.originalName}",
                        modifier = Modifier.padding(6.dp)
                    )
                }

                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                )

                Text(
                    text = "Instructions",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .align(Alignment.CenterHorizontally)
                )
                recipe.analyzedInstructions.forEach{
                    it.steps.forEach{step ->
                        Text(
                            text = "Step ${step.number}",
                            fontSize = 18.sp
                        )
                        Text(text = step.step)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(168.dp))
        }
    }
}
