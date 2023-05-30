package com.amarko.foodiefinder.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.amarko.foodiefinder.models.RecipeInstance

@Composable
fun RecipeCard(
    recipe: RecipeInstance
) {
    val summary = recipe.summary.replace("<.*?>".toRegex(), "")

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 10.dp,
        backgroundColor = Color.LightGray,
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            AsyncImage(
                model = recipe.image,
                contentDescription = recipe.title,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(23.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Ready in ${recipe.readyInMinutes}",
                    fontSize = 24.sp
                )
                Text(
                    text = "Serves ${recipe.servings}",
                    fontSize = 24.sp
                )
            }
            Divider(
                modifier = Modifier.padding(16.dp),
                color = Color.DarkGray
            )
            Text(
                text = summary,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}