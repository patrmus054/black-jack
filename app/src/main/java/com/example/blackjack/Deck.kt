package com.example.blackjack

import kotlin.random.Random

class Deck {
    fun nextCard(): Card{
        val  list = "cdhs".toCharArray()
        var value: Int = Random.nextInt(2,10)
        // pamiętać żeby dodoać reszte kart
        var figure: Char = list.random()
        return Card(value, figure)
    }
    fun dealCard(player: Person){
        player.receiveCard(nextCard())
    }
    fun dealInitialCards(player: Person){
        dealCard(player)
        dealCard(player)
    }
}