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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
                val shuffledDeck by remember {
                    mutableStateOf(cardDeck.shuffled())
                }
                var remainCards: List<Card> by remember {
                    mutableStateOf(shuffledDeck)
                }
                var openCards: List<Card> by remember {
                    mutableStateOf(emptyList())
                }
                val player1 by remember {
                   mutableStateOf(Player(shuffledDeck.take(7)))
                }
                remainCards = remainCards - player1.cards
                var player2 by remember {
                    mutableStateOf(Player(remainCards.take(7)))
                }
                remainCards = remainCards - player2.cards
                if(openCards.isEmpty()){
                    openCards = remainCards.take(1)
                }
                remainCards = remainCards - openCards

                Column(modifier = Modifier.fillMaxSize()) {
                    playerCard(player = player1){
                        /*TODO()*/
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f))
                        CardView(openCards.last()) {
                            // Nothing to do.
                        }
                        Box(modifier = Modifier.weight(1f))
                        CardBackView{
                            player2 = Player(player2.cards + remainCards.take(1))
                        }
                        Box(modifier = Modifier.weight(1f))

                    }
                    playerCard(player = player2){
                        if(openCards.last().type == it.type || openCards.last().num == it.num){
                            openCards += listOf(it)
                            player2 = Player(player2.cards - listOf(it))
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun RowScope.CardView(card: Card, onclick: (Card) -> Unit) {
    Image(
        painter = painterResource(id = card.getDrawable()),
        contentDescription = null,
        modifier = Modifier
            .weight(1f)
            .clickable {
                Log.i("OneCard", "Clicked card type = ${card.type}, num = ${card.num}")
                onclick(card)
            },
        contentScale = ContentScale.Fit
    )
}
@Composable
fun RowScope.CardBackView(onclick: () -> Unit){
    Image(
        painter = painterResource(id = R.drawable.reverse_playing_card),
        contentDescription = null,
        modifier = Modifier
            .weight(1f)
            .clickable {
                Log.i("OneCard", "Clicked card type = Back, num = 0")
                onclick()
            },
        contentScale = ContentScale.Fit
    )
}
@Composable
fun playerCard(player: Player, onclick: (Card) -> Unit){
    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        player.cards.forEach { card: Card ->
            CardView(card, onclick)
        }
    }
}