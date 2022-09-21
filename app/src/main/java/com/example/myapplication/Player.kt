package com.example.myapplication


data class Player(
    //플레이어의 이름(유저 or 컴퓨터)
    val name: String,
    //플레이어가 유저(false)인지 컴퓨터(true)인지 판별하여 플레이어 카드 뷰를 다르게 함
    val discrimination: Boolean,
    //가지고 있는 카드
    val cards: List<Card>
)  {

}