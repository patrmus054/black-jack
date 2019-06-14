package com.example.blackjack

open class Person(name: String) {
    private var cards : ArrayList<Card> = ArrayList()
    private var named : String = name
    internal var total : Int = 0

    fun receiveCard(card: Card): Card{
        cards.add(card)
        total += card.getCardScore()
        return card
    }

    fun getCards(): ArrayList<Card>{
        return cards
    }
    fun getTotal():Int {
        return total
    }

}