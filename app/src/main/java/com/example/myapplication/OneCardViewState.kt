package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


data class OneCardViewState (
    val remainCards: List<Card>,
    val openCards: List<Card>,
    val player1: Player,
    val player2: Player
)
{
    //유저(플레이어2) 카드 내기
    fun throwCard(card: Card): OneCardViewState?{
        //내는 카드가 openCards와 일치 한다면 카드 내기
        return if(openCards.last().type == card.type || openCards.last().num == card.num){
            copy(
                openCards = openCards + listOf(card),
                player2 = player2.copy(cards = player2.cards - listOf(card))
            )
        }
        else {
            null
        }
    }
    //컴퓨터(플레이어1) 카드 내기
    fun throwCardByNPC(): OneCardViewState {
        //컴퓨터가 낼 카드가 있는지 확인
        val throwCandidate = player1.cards.firstOrNull {
            it.type == openCards.last().type
                    || it.num == openCards.last().num
        }
        //컴퓨터가 낼 카드가 있다면 openCards에 한 장 내려 놓기
        return if(throwCandidate != null){
            copy(
                openCards = openCards + listOf(throwCandidate),
                player1 = player1.copy(cards = player1.cards - listOf(throwCandidate))
            )
        }
        //없다면 카드 한 장 뽑기
        else{
            val c = remainCards.take(1)
            copy(
                player1 = player1.copy(cards = player1.cards + c),
                remainCards = remainCards - remainCards.take(1)
            )
        }
    }

    companion object{
        fun createGame(): OneCardViewState{
            //카드 셔플
            val shuffledDeck = cardDeck.shuffled()

            var remainCards: List<Card> = emptyList()
            var openCards: List<Card> = emptyList()
            
            //플레이어1(컴퓨터) 생성
            val player1 = Player("CPU",true,shuffledDeck.take(7))
            remainCards = shuffledDeck - player1.cards
            
            //플레이어2(유저) 생성
            val player2 = Player("user",false,remainCards.take(7))
            remainCards = remainCards - player2.cards
            
            //openCards 생성
            openCards = remainCards.take(1)
            remainCards = remainCards - openCards

            return OneCardViewState(
                remainCards, openCards, player1, player2
            )
        }
    }
}
