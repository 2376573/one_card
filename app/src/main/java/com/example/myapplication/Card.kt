package com.example.myapplication

import androidx.annotation.DrawableRes
import androidx.compose.material.Card
import java.lang.IllegalArgumentException

//카드덱 생성
val cardDeck: List<Card> = CardType.values().map { cardType ->
    CardNumber.values().map { cardNumber ->
        Card(cardType, cardNumber)
    }
}.flatten()

data class Card(
    val type: CardType,
    val num: CardNumber
){
    @DrawableRes
    fun getDrawable(): Int{
        return when (type) {
            CardType.Heart -> {
                when (num) {
                    CardNumber.Num2 -> R.drawable.hearts_2
                    CardNumber.Num3 -> R.drawable.hearts_3
                    CardNumber.Num4 -> R.drawable.hearts_4
                    CardNumber.Num5 -> R.drawable.hearts_5
                    CardNumber.Num6 -> R.drawable.hearts_6
                    CardNumber.Num7 -> R.drawable.hearts_7
                    CardNumber.Num8 -> R.drawable.hearts_8
                    CardNumber.Num9 -> R.drawable.hearts_9
                    CardNumber.Num10 -> R.drawable.hearts_10
                    CardNumber.Jack -> R.drawable.jack_of_hearts2
                    CardNumber.Queen -> R.drawable.queen_of_hearts2
                    CardNumber.King -> R.drawable.king_of_hearts2
                    CardNumber.Ace -> R.drawable.ace_of_hearts
                }
            }
            CardType.Diamond -> {
                when (num) {
                    CardNumber.Num2 -> R.drawable.diamonds_2
                    CardNumber.Num3 -> R.drawable.diamonds_3
                    CardNumber.Num4 -> R.drawable.diamonds_4
                    CardNumber.Num5 -> R.drawable.diamonds_5
                    CardNumber.Num6 -> R.drawable.diamonds_6
                    CardNumber.Num7 -> R.drawable.diamonds_7
                    CardNumber.Num8 -> R.drawable.diamonds_8
                    CardNumber.Num9 -> R.drawable.diamonds_9
                    CardNumber.Num10 -> R.drawable.diamonds_10
                    CardNumber.Jack -> R.drawable.jack_of_diamonds2
                    CardNumber.Queen -> R.drawable.queen_of_diamonds2
                    CardNumber.King -> R.drawable.king_of_diamonds2
                    CardNumber.Ace -> R.drawable.ace_of_diamonds
                }
            }
            CardType.Spade ->{
                when (num) {
                CardNumber.Num2 -> R.drawable.spades_2
                CardNumber.Num3 -> R.drawable.spades_3
                CardNumber.Num4 -> R.drawable.spades_4
                CardNumber.Num5 -> R.drawable.spades_5
                CardNumber.Num6 -> R.drawable.spades_6
                CardNumber.Num7 -> R.drawable.spades_7
                CardNumber.Num8 -> R.drawable.spades_8
                CardNumber.Num9 -> R.drawable.spades_9
                CardNumber.Num10 -> R.drawable.spades_10
                CardNumber.Jack -> R.drawable.jack_of_spades2
                CardNumber.Queen -> R.drawable.queen_of_spades2
                CardNumber.King -> R.drawable.king_of_spades2
                CardNumber.Ace -> R.drawable.ace_of_spades
            }
            }
            CardType.Club ->{
                when (num) {
                    CardNumber.Num2 -> R.drawable.clubs_2
                    CardNumber.Num3 -> R.drawable.clubs_3
                    CardNumber.Num4 -> R.drawable.clubs_4
                    CardNumber.Num5 -> R.drawable.clubs_5
                    CardNumber.Num6 -> R.drawable.clubs_6
                    CardNumber.Num7 -> R.drawable.clubs_7
                    CardNumber.Num8 -> R.drawable.clubs_8
                    CardNumber.Num9 -> R.drawable.clubs_9
                    CardNumber.Num10 -> R.drawable.clubs_10
                    CardNumber.Jack -> R.drawable.jack_of_clubs2
                    CardNumber.Queen -> R.drawable.queen_of_clubs2
                    CardNumber.King -> R.drawable.king_of_clubs2
                    CardNumber.Ace -> R.drawable.ace_of_clubs
                }
            }
        }
    }
}
enum class CardType{
    Heart, Diamond, Spade, Club
}
enum class CardNumber{
    Num2,Num3,Num4,Num5,Num6,Num7,Num8,Num9,Num10,Jack,Queen,King,Ace
}