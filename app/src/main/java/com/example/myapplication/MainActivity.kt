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
                //winner가 없으면 OneCardGameScreen 실행
                if (currentWinner == null) {
                    OneCardGameScreen(onGameOver = {
                        winner = it
                    })
                }
                //아니면 승리화면 실행
                else {
                    VictoryScreen(player = currentWinner)
                }
            }
        }
    }
}

    @Composable
    fun OneCardGameScreen(onGameOver: (Player) -> Unit){
        //게임 시작
        var viewState: OneCardViewState by remember {
            mutableStateOf(OneCardViewState.createGame())
        }
        Column(modifier = Modifier.fillMaxSize()) {
            //플레이어1 뷰
            playerCard(player = viewState.player1) {

            }
            Row(
                //가운데로 이동
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //빈공간
                Box(modifier = Modifier.weight(1f))
                //openCards 뷰
                CardView(modifier = Modifier.height(120.dp), viewState.openCards.last()) {
                    // Nothing to do.
                }
                Box(modifier = Modifier.weight(1f))
                //remainCards 뷰
                CardBackView(modifier = Modifier.height(120.dp)) {
                    //클릭했을 경우
                    
                    //플레이어1의 카드가 다 사라졌다면 플레이어1 우승
                    if(viewState.player1.cards.isEmpty()){
                        onGameOver(viewState.player1)
                    }
                    //remainCards가 다 없어졌다면 openCards의 카드를 섞어서 주고 openCards는 마지막 장만 가짐
                    if(viewState.remainCards.isEmpty()){
                        viewState = viewState.copy(
                            remainCards = viewState.openCards.subList(0, viewState.openCards.size - 1).shuffled(),
                            openCards = listOf(viewState.openCards.last())
                        )
                    }
                    //remainCards에서 한 장 가져옴
                    val c = viewState.remainCards.take(1)
                    viewState = viewState.copy(
                        player2 = viewState.player2.copy(cards = viewState.player2.cards + c),
                        remainCards = viewState.remainCards - c
                    )
                    //플레이어1의 차례
                    viewState = viewState.throwCardByNPC()
                }
                Box(modifier = Modifier.weight(1f))
            }
            //플레이어2(유저) 뷰
            playerCard(player = viewState.player2) {
                val state = viewState.throwCard(it)
                //카드를 냈을 경우
                if(state != null){
                    viewState = state
                    //플레이어2의 카드가 모두 사라졌을 경우 플레이어2 우승
                    if(viewState.player2.cards.isEmpty()){
                        onGameOver(viewState.player2)
                    }
                    else{
                        //플레이어1의 카드가 모두 사라졌을 경우 플레이어1 우승
                        if(viewState.player1.cards.isEmpty()){
                            onGameOver(viewState.player1)
                        }
                        
                        viewState = viewState.throwCardByNPC()
                    }
                }
            }
        }
    }



//카드의 뷰 생성
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

//카드의 뒷면만 보이게 하기
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

//플레이어 카드 생성
@Composable
fun playerCard(player: Player, onclick: (Card) -> Unit) {
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val cardWidth = maxWidth / 7
        val cards = player.cards.windowed(7,7, partialWindows = true)
        Column {
            cards.forEach{
                Row() {
                    it.forEach { card: Card ->
                        //플레이어의 discrimination이 false라면(유저 라면) CardView실행
                        if(!player.discrimination){
                            CardView(Modifier.width(cardWidth),card, onclick)
                        }
                        //아니라면 CardBackView 실행
                        else{
                            CardBackView(Modifier.width(cardWidth)) {
                                //Nothing to do
                            }
                        }

                    }
                }
            }
        }

    }
}
