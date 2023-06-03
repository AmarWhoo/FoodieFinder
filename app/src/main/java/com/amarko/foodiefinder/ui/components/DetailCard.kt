package com.amarko.foodiefinder.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.amarko.foodiefinder.models.RecipeInstance

@Composable
fun DetailCard(
    recipe: RecipeInstance,
    equipmentList: List<Equipment>
) {
    val summary = recipe.summary.replace("<.*?>".toRegex(), "")

    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = recipe.image,
            contentDescription = recipe.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Ready in ${recipe.readyInMinutes} min.", fontSize = 24.sp)
            Text(text = "Serves ${recipe.servings}", fontSize = 24.sp)
        }

        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )

        Text(
            text = "Equipment",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(vertical = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            equipmentList.forEach{
                Text(text = it.name)
            }
        }

        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )

        Text(
            text = summary,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(24.dp)
        )
    }
}
