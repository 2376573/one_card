package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
                var winner: Player? by remember {
                    mutableStateOf(null)
                }
                val currentWinner = winner
                if (currentWinner == null) {
                    OneCardGameScreen(onGameOver = {
                        winner = it
                    })
                } else {
                    VictoryScreen(player = currentWinner)
                }
            }
        }
    }
}
@Composable
fun OneCardGameScreen(onGameOver: (Player) -> Unit){
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
                        CardView(modifier = Modifier.height(120.dp), viewState.openCards.last()) {
                            // Nothing to do.
                        }
                        Box(modifier = Modifier.weight(1f))
                        CardBackView(modifier = Modifier.height(120.dp)) {
                            val c = viewState.remainCards.take(1)
                            if(viewState.remainCards.isEmpty()){
                                viewState = viewState.copy(
                                    remainCards = viewState.openCards.subList(0, viewState.openCards.size - 1).shuffled(),
                                    openCards = listOf(viewState.openCards.last())
                                )
                            }
                            viewState = viewState.copy(
                                player2 = Player("user",false,viewState.player2.cards + c),
                                remainCards = viewState.remainCards - c
                            )
                            viewState = viewState.throwCardByNPC()
                        }
                        Box(modifier = Modifier.weight(1f))
                    }
                    playerCard(player = viewState.player2) {
                        val state = viewState.throwCard(it)
                        if(state != null){
                            viewState = state
                            if(viewState.player2.cards.isEmpty()){
                                onGameOver(viewState.player2)
                            }else{
                                viewState = viewState.throwCardByNPC()
                                if(viewState.player1.cards.isEmpty()){
                                    onGameOver(viewState.player1)
                                }
                            }
                        }
                    }
                }
            }



@Composable
fun CardView(modifier: Modifier = Modifier, card: Card, onclick: (Card) -> Unit) {
    Image(
        painter = painterResource(id = card.getDrawable()),
        contentDescription = null,
        modifier = modifier
            .clickable {
                Log.i("OneCard", "Clicked card type = ${card.type}, num = ${card.num}")
                onclick(card)
            },
        contentScale = ContentScale.Fit
    )
}

@Composable
fun CardBackView(modifier: Modifier = Modifier,onclick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.reverse_playing_card),
        contentDescription = null,
        modifier = modifier
            .clickable {
                Log.i("OneCard", "Clicked card type = Back, num = 0")
                onclick()
            },
        contentScale = ContentScale.Fit
    )
}

@Composable
fun playerCard(player: Player, onclick: (Card) -> Unit) {
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val cardWidth = maxWidth / 7
        val cards = player.cards.windowed(7,7, partialWindows = true)
        Column {
            cards.forEach{
                Row() {
                    it.forEach { card: Card ->
                        if(!player.discrimination) CardView(Modifier.width(cardWidth),card, onclick)
                        else CardBackView(Modifier.width(cardWidth)){

                        }
                    }
                }
            }
        }

    }
}
