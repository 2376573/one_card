package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Text

@Composable
fun VictoryScreen(player: Player){
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "Winner is ${player.toString()}")
    }
}