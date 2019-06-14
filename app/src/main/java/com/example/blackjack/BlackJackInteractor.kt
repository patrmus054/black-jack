package com.example.blackjack

import android.util.Log
import kotlin.math.log

class BlackJackInteractor {
    interface OnStartClickedListner{
        fun initializGame()
        fun dealCards()
        fun onHitClicked()
        fun onStandClicked()
        fun playAgine()
    }

    private val TAG: String = "BlackJackInteractor"
    private lateinit var deck: Deck
    private var players : ArrayList<Person> = ArrayList()

    fun getPlayerCards(): ArrayList<Card>{
        return players.get(0).getCards()
    }
    fun getDealerCards(): ArrayList<Card>{
        return  players.get(1).getCards()
    }

    fun initializeGame(){
        deck = Deck()
        players.add(Player())
        players.add(Dealer())
    }
    fun dealCards(){
        for(player in players){
            deck.dealInitialCards(player)
        }
    }

    fun hit(){
        deck.dealCard(players.get(0))
    }
    fun stand(){
        DealerTurn()
    }
    fun DealerTurn(){
        while((players.get(1) as Dealer).wantToHit()){
            deck.dealCard(players.get(1))
        }
        Log.d(TAG, players.get(1).getTotal().toString())
    }
    fun declareWinner(): String{
        when {
            players.get(0).getTotal() > players.get(1).getTotal() -> return "you"
            players.get(0).getTotal() < players.get(1).getTotal() -> return "dealer"
            else -> return "pat"
        }
    }
    fun getPlayerTotalScore(): Int{
        return players.get(0).getTotal()
    }
}