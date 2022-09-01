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
                var viewState: OneCardViewState by remember {
                    mutableStateOf(OneCardViewState.createGame())
                }

                Column(modifier = Modifier.fillMaxSize()) {
                    playerCard(player = viewState.player1) {

                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f))
                        CardView(viewState.openCards.last()) {
                            // Nothing to do.
                        }
                        Box(modifier = Modifier.weight(1f))
                        CardBackView {
                            viewState = viewState.copy(
                                player2 = Player(viewState.player2.cards + viewState.remainCards.take(1)),
                                remainCards = viewState.remainCards - viewState.remainCards.take(1)
                            )
                            viewState = viewState.throwCardByNPC()
                        }
                        Box(modifier = Modifier.weight(1f))
                    }
                    playerCard(player = viewState.player2) {
                        val state = viewState.throwCard(it)
                        if(state != null){
                            viewState = state
                            viewState = viewState.throwCardByNPC()
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
            .height(120.dp)
            .clickable {
                Log.i("OneCard", "Clicked card type = ${card.type}, num = ${card.num}")
                onclick(card)
            },
        contentScale = ContentScale.Fit
    )
}

@Composable
fun RowScope.CardBackView(onclick: () -> Unit) {
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
fun playerCard(player: Player, onclick: (Card) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        player.cards.forEach { card: Card ->
            CardView(card, onclick)
        }
    }
}