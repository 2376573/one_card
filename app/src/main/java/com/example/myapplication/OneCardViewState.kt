package com.example.myapplication

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


data class OneCardViewState (
    val remainCards: List<Card>,
    val openCards: List<Card>,
    val player1: Player,
    val player2: Player
)
{
    fun throwCard(card: Card): OneCardViewState?{
        return if(openCards.last().type == card.type || openCards.last().num == card.num){
            copy(
                openCards = openCards + listOf(card),
                player2 = Player("user",false,player2.cards - listOf(card))
            )
        }
        else {
            null
        }
    }

    fun throwCardByNPC(): OneCardViewState {
        val throwCandidate = player1.cards.firstOrNull {
            it.type == openCards.last().type
                    || it.num == openCards.last().num
        }
        return if(throwCandidate != null){
            copy(
                openCards = openCards + listOf(throwCandidate),
                player1 = player1.copy(cards = player1.cards - listOf(throwCandidate))
            )
        } else{
            val c = remainCards.take(1)
            copy(
                player1 = player1.copy(cards = player1.cards + c),
                remainCards = remainCards - remainCards.take(1)
            )
        }
    }

    companion object{
        fun createGame(): OneCardViewState{
            val shuffledDeck = cardDeck.shuffled()
            var remainCards: List<Card> = emptyList()
            var openCards: List<Card> = emptyList()
            val player1 = Player("user",true,shuffledDeck.take(7))
            remainCards = shuffledDeck - player1.cards
            val player2 = Player("user",false,remainCards.take(7))
            remainCards = remainCards - player2.cards
            openCards = remainCards.take(1)
            remainCards = remainCards - openCards
            return OneCardViewState(
                remainCards, openCards, player1, player2
            )
        }
    }
}
