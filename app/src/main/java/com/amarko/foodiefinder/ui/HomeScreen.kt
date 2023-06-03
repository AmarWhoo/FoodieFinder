
package com.amarko.foodiefinder.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amarko.foodiefinder.models.Equipment
import com.amarko.foodiefinder.models.RecipeInstance
import com.amarko.foodiefinder.ui.components.RecipeDisplay
import com.amarko.foodiefinder.ui.components.RecipeLoadingScreen
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    recipeStateFlow: StateFlow<RecipeInstance?>,
    equipmentList: List<Equipment>,
    onRefreshClick: () -> Unit
) {
    val recipe by recipeStateFlow.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {  },
        sheetPeekHeight = 0.dp
    ) {
        Box {
            if (recipe != null) {
                RecipeDisplay(recipe = recipe!!, equipmentList = equipmentList)
            } else {
                RecipeLoadingScreen()
            }

            FloatingActionButton(
                onClick = { coroutineScope.launch { onRefreshClick() } },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 102.dp)
                    .size(72.dp)
            ) {
                Icon(
                    Icons.Filled.Refresh,
                    contentDescription = "Refresh",
                    modifier = Modifier.size(42.dp)
                )
            }

            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 102.dp, start = 56.dp)
                    .size(58.dp)
            ) {
                Icon(
                    Icons.Filled.List,
                    contentDescription = "Filters",
                    modifier = Modifier.size(34.dp)
                )
            }

            FloatingActionButton(
                onClick = {  },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 102.dp, end = 56.dp)
                    .size(58.dp)
            ) {
                Icon(
                    Icons.Outlined.FavoriteBorder,
                    contentDescription = "Save as Favorite",
                    modifier = Modifier.size(34.dp)
                )
            }
        }
    }
}