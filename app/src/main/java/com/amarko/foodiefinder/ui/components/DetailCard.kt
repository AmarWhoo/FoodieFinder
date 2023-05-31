package com.amarko.foodiefinder.ui.components

import androidx.compose.runtime.Composable
import com.amarko.foodiefinder.models.RecipeInstance

@Composable
fun DetailCard(
    recipe: RecipeInstance
) {
    val summary = recipe.summary.replace("<.*?>".toRegex(), "")

//    Card(
//        shape = RoundedCornerShape(12.dp),
//        elevation = 10.dp,
//        backgroundColor = Color.LightGray,
//        modifier = Modifier.height(IntrinsicSize.Min)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//        ){
//            AsyncImage(
//                model = recipe.image,
//                contentDescription = recipe.title,
//                modifier = Modifier
//                    .fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(23.dp))
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Text(
//                    text = "Ready in ${recipe.readyInMinutes}",
//                    fontSize = 24.sp
//                )
//                Text(
//                    text = "Serves ${recipe.servings}",
//                    fontSize = 24.sp
//                )
//            }
//            Divider(
//                modifier = Modifier.padding(16.dp),
//                color = Color.DarkGray
//            )
//            Text(
//                text = summary,
//                overflow = TextOverflow.Ellipsis,
//                modifier = Modifier.padding(horizontal = 24.dp)
//            )
//        }
//    }
}