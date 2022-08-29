package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val shuffledDeck = cardDeck.shuffled()
                var remainCards: List<Card> = emptyList()
                var openCards: List<Card> = emptyList()
                val player1 = Player(shuffledDeck.take(7))
                remainCards = shuffledDeck - player1.cards
                val player2 = Player(remainCards.take(7))
                remainCards = remainCards - player2.cards
                openCards = remainCards.take(1)
                remainCards = remainCards - openCards

                Column(modifier = Modifier.fillMaxSize()) {
                    playerCard(player = player1)
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f))
                        CardView(openCards.last())
                        Box(modifier = Modifier.weight(1f))
                        CardBackView()
                        Box(modifier = Modifier.weight(1f))

                    }
                    playerCard(player = player2)
                }
            }
        }
    }
}



@Composable
fun RowScope.CardView(card: Card) {
    Image(
        painter = painterResource(id = card.getDrawable()),
        contentDescription = null,
        modifier = Modifier
            .weight(1f)
            .clickable {
                Log.i("OneCard", "Clicked card type = ${card.type}, num = ${card.num}")
            },
        contentScale = ContentScale.Fit
    )
}
@Composable
fun RowScope.CardBackView(){
    Image(
        painter = painterResource(id = R.drawable.reverse_playing_card),
        contentDescription = null,
        modifier = Modifier.weight(1f),
        contentScale = ContentScale.Fit
    )
}
@Composable
fun playerCard(player: Player){
    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        player.cards.forEach { card: Card ->
            CardView(card)
        }
    }
}