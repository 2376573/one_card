package com.example.myapplication

data class Card(
    val type: CardType,
    val num: Int
){

}
enum class CardType{
    Heart, Diamond, Spade, Club
}