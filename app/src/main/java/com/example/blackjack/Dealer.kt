package com.example.blackjack

import kotlin.random.Random

class Dealer: Person {
    constructor():super("The dealer")

    var CUSP: Int = 16
    fun wantToHit(): Boolean{
        if(total == CUSP){
            return Random.nextBoolean()
        }
        return total < CUSP
    }
}