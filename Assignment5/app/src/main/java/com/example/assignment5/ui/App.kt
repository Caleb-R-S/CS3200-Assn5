package com.example.assignment5.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.assignment5.Greeting
import com.example.assignment5.ui.screens.FlashCardScreen
import com.example.assignment5.ui.theme.Assignment5Theme

@Composable
fun App() {
    Assignment5Theme {
        FlashCardScreen()
    }
}