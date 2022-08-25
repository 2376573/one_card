package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row {
                        for (a in 1..7){
                            Card()
                        }
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(modifier = Modifier.weight(1f))
                        Card()
                        Box(modifier = Modifier.weight(1f))
                        Card()
                        Box(modifier = Modifier.weight(1f))

                    }
                    Row() {
                        for (a in 1..7) {
                            Card()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Card(){
        Image(
            painterResource(id = R.drawable.one),
            contentDescription = null,
            modifier = Modifier.size(width = 60.dp, height = 90.dp)
        )
}