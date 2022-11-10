package com.example.assignment5.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.assignment5.ui.models.FlashCard
import com.example.assignment5.ui.screens.CardSwipeState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlashCardComponent(card: FlashCard, flipSwipeableState: SwipeableState<CardSwipeState>) {

    var textToDisplayOnCard by remember { mutableStateOf(card.front) }

    LaunchedEffect(key1 = flipSwipeableState.offset.value) {
        textToDisplayOnCard = if (flipSwipeableState.offset.value >= -900f) {
            card.front
        } else {
            card.back
        }
    }

    Surface(
        modifier = Modifier
//            .fillMaxSize()
            .graphicsLayer {
                rotationY = flipSwipeableState.offset.value / 10
            }
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp))
        ,
        color = Color.LightGray,
        shape = RoundedCornerShape(20.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    rotationY = flipSwipeableState.offset.value / 10
                }
            ,
            contentAlignment = Alignment.Center
        ) {
            Text(text = textToDisplayOnCard, textAlign = TextAlign.Center)
        }
    }
}