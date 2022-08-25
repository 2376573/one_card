package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
                val player1 = Player(
                    listOf(
                        Card(CardType.Club, 5),
                        Card(CardType.Heart, 2),
                        Card(CardType.Spade, 9)
                    )
                )
                val player2 = Player(
                    listOf(
                        Card(CardType.Club, 2),
                        Card(CardType.Heart, 3),
                        Card(CardType.Spade, 4)
                    )
                )
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        for (a in 1..7) {
                            Card(R.drawable.reverse_playing_card)
                        }
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f))
                        Card(R.drawable.hearts_10)
                        Box(modifier = Modifier.weight(1f))
                        Card(R.drawable.reverse_playing_card)
                        Box(modifier = Modifier.weight(1f))

                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        player2.cards.forEach { card: Card ->
                            Card(resId = card.getDrawable())
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun RowScope.Card(@DrawableRes resId: Int) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        modifier = Modifier
            .weight(1f)
            .height(90.dp),
        contentScale = ContentScale.Fit
    )
}