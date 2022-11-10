@file:OptIn(ExperimentalMaterialApi::class)

package com.example.assignment5.ui.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
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
import com.example.assignment5.ui.components.FlashCardComponent
import com.example.assignment5.ui.models.FlashCard

enum class CardSwipeState {
    FRONT,
    BACK
}

enum class DisplaySwipeState {
   PREVIOUS,
   CURRENT,
   NEXT
}

@Composable
fun FlashCardScreen() {

    // CARDS
    var cards = remember {
        mutableStateListOf(
            FlashCard("Where is Utah?", "USA"),
            FlashCard("What is the best drink?", "Milk"),
            FlashCard("What is your name?", "Caleb")
        )
    }
    var currentCard by remember {
        mutableStateOf(0)
    }

    var textToDisplayOnCard by remember { mutableStateOf(cards[currentCard].front) }

    val displaySwipeableState= rememberSwipeableState(initialValue = DisplaySwipeState.CURRENT)
    val displayAnchor = mapOf(
        -400f to DisplaySwipeState.PREVIOUS,
        0f to DisplaySwipeState.CURRENT,
        400f to DisplaySwipeState.NEXT,
    )


    val flipSwipeableState = rememberSwipeableState(initialValue = CardSwipeState.FRONT)
    val filpAnchor = mapOf(
        0f to CardSwipeState.FRONT,
        -1800f to CardSwipeState.BACK
    )

    LaunchedEffect(key1 = displaySwipeableState.offset.value) {
        if (displaySwipeableState.offset.value < -100f) {
            if (currentCard < cards.size - 1) {
                currentCard ++
            }
        } else if (displaySwipeableState.offset.value > 100f) {
            if (currentCard > 0) {
                currentCard --
            }
        }
        textToDisplayOnCard = cards[currentCard].front
    }

    println("The current card is $currentCard")
    Column(
        modifier = Modifier
            .fillMaxSize(),


    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .swipeable(
                    state = displaySwipeableState,
                    anchors = displayAnchor,
                    orientation = Orientation.Horizontal
                )
            ,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
//                    .fillMaxWidth()
                    .fillMaxHeight(.4f)
                    .padding(16.dp)
                    .offset(displaySwipeableState.offset.value.toInt().dp)
//                    .swipeable(
//                        state = flipSwipeableState,
//                        anchors = filpAnchor,
//                        orientation = Orientation.Horizontal,
//                    )
                ,
            ) {
                Row() {
                    FlashCardComponent(
                        card = cards[if (currentCard - 1 > 0) currentCard - 1 else currentCard],
                        flipSwipeableState = flipSwipeableState
                    )
                    FlashCardComponent(
                        card = cards[currentCard],
                        flipSwipeableState = flipSwipeableState
                    )
                    FlashCardComponent(
                        card = cards[if (currentCard + 1 < cards.size - 1) currentCard + 1 else currentCard],
                        flipSwipeableState = flipSwipeableState
                    )
                }
            }
        }
    }
}