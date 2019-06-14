package com.example.blackjack

interface BlackJackView {
    fun showButtonStart()
    fun hideButtonStart()
    fun showButtonGame()
    fun hideButtonGame()
    fun showButtonsBet()
    fun hideButtonsBet()
    fun showPlayButton()
    fun hidePlayButton()
    fun uploadPlayerCards(cards: ArrayList<Card>)
    fun uploadDealerCards(cards: ArrayList<Card>)
    fun winDialog()
    fun loseDialog()
    fun gameOverDialog()
    fun onFiveClicked()
    fun onTwentyfiveClicked()
    fun onHundredClicked()
    fun onPlayClicked()
    fun onHitClicked()
    fun onStandClicked()
    fun updateTotal(amount: Int)
}