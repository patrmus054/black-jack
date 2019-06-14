package com.example.blackjack

class Card (value:Int, figure:Char){
    private val score : Int = value
    private val figure : Char = figure

    fun getCardScore(): Int {
        return score
    }

    override fun toString(): String {
        return "_" + score +"_"+ figure
    }


}