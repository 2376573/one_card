package com.example.myapplication

import androidx.annotation.DrawableRes
import java.lang.IllegalArgumentException

data class Card(
    val type: CardType,
    val num: Int
){
    @DrawableRes
    fun getDrawable(): Int{
        return when (type) {
            CardType.Heart -> {
                when (num) {
                    2 -> R.drawable.hearts_2
                    3 -> R.drawable.hearts_3
                    4 -> R.drawable.hearts_4
                    5 -> R.drawable.hearts_5
                    6 -> R.drawable.hearts_6
                    7 -> R.drawable.hearts_7
                    8 -> R.drawable.hearts_8
                    9 -> R.drawable.hearts_9
                    10 -> R.drawable.hearts_10
                    11 -> R.drawable.jack_of_hearts2
                    12 -> R.drawable.queen_of_hearts2
                    13 -> R.drawable.king_of_hearts2
                    14 -> R.drawable.ace_of_hearts
                    else -> throw IllegalArgumentException()
                }
            }
            CardType.Diamond -> {
                when (num) {
                    2 -> R.drawable.diamonds_2
                    3 -> R.drawable.diamonds_3
                    4 -> R.drawable.diamonds_4
                    5 -> R.drawable.diamonds_5
                    6 -> R.drawable.diamonds_6
                    7 -> R.drawable.diamonds_7
                    8 -> R.drawable.diamonds_8
                    9 -> R.drawable.diamonds_9
                    10 -> R.drawable.diamonds_10
                    11 -> R.drawable.jack_of_diamonds2
                    12 -> R.drawable.queen_of_diamonds2
                    13 -> R.drawable.king_of_diamonds2
                    14 -> R.drawable.ace_of_diamonds
                    else -> throw IllegalArgumentException()
                }
            }
            CardType.Spade ->{
                when (num) {
                2 -> R.drawable.spades_2
                3 -> R.drawable.spades_3
                4 -> R.drawable.spades_4
                5 -> R.drawable.spades_5
                6 -> R.drawable.spades_6
                7 -> R.drawable.spades_7
                8 -> R.drawable.spades_8
                9 -> R.drawable.spades_9
                10 -> R.drawable.spades_10
                11 -> R.drawable.jack_of_spades2
                12 -> R.drawable.queen_of_spades2
                13 -> R.drawable.king_of_spades2
                14 -> R.drawable.ace_of_spades
                else -> throw IllegalArgumentException()
            }
            }
            CardType.Club ->{
                when (num) {
                    2 -> R.drawable.clubs_2
                    3 -> R.drawable.clubs_3
                    4 -> R.drawable.clubs_4
                    5 -> R.drawable.clubs_5
                    6 -> R.drawable.clubs_6
                    7 -> R.drawable.clubs_7
                    8 -> R.drawable.clubs_8
                    9 -> R.drawable.clubs_9
                    10 -> R.drawable.clubs_10
                    11 -> R.drawable.jack_of_clubs2
                    12 -> R.drawable.queen_of_clubs2
                    13 -> R.drawable.king_of_clubs2
                    14 -> R.drawable.ace_of_clubs
                    else -> throw IllegalArgumentException()
                }
            }
        }
    }
}
enum class CardType{
    Heart, Diamond, Spade, Club
}