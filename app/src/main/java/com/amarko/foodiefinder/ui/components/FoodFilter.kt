package com.amarko.foodiefinder.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FoodFilter(
    onApplyFilters: (String) -> Unit
) {
    val diets = listOf("gluten free", "dairy free", "ketogenic", "vegetarian", "lacto vegetarian", "ovo vegetarian", "lacto ovo vegetarian", "vegan", "pescetarian", "paleo", "primal", "whole30")
    val dishType = listOf("breakfast", "lunch", "dinner", "brunch", "beverage")

    val dietsCheckedState = remember { mutableStateListOf<Boolean>() }
    val dishTypeCheckedState = remember { mutableStateListOf<Boolean>() }

    dietsCheckedState.addAll(List(diets.size) { false })
    dishTypeCheckedState.addAll(List(dishType.size) { false })
    Box{
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 80.dp, start = 24.dp, end = 24.dp)
        ) {
            item {
                Text(
                    text = "Diets",
                    fontSize = 24.sp
                )

                diets.forEachIndexed { index, diet ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .toggleable(
                                value = dietsCheckedState.getOrNull(index) ?: false,
                                onValueChange = {
                                    dietsCheckedState[index] = it
                                }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = dietsCheckedState.getOrNull(index) ?: false,
                            onCheckedChange = {
                                dietsCheckedState[index] = it
                            }
                        )
                        Text(
                            text = diet,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                )
            }

            item {
                Text(
                    text = "Dish Types",
                    fontSize = 24.sp
                )
                dishType.forEachIndexed { index, dishType ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .toggleable(
                                value = dishTypeCheckedState.getOrNull(index) ?: false,
                                onValueChange = {
                                    dishTypeCheckedState[index] = it
                                }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = dishTypeCheckedState.getOrNull(index) ?: false,
                            onCheckedChange = {
                                dishTypeCheckedState[index] = it
                            }
                        )
                        Text(
                            text = dishType,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }


        FloatingActionButton(
            onClick = {
                val selectedDiets = diets
                    .filterIndexed { index, _ -> dietsCheckedState[index] }
                    .joinToString(", ")
                val selectedDishTypes = dishType
                    .filterIndexed { index, _ -> dishTypeCheckedState[index] }
                    .joinToString(", ")
                val selectedFilters = "$selectedDiets, $selectedDishTypes"
                onApplyFilters(selectedFilters)
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 102.dp, end = 56.dp)
                .size(58.dp)
        ) {
            Icon(
                Icons.Filled.Check,
                contentDescription = "Apply filters",
                modifier = Modifier.size(34.dp)
            )
        }
    }

}