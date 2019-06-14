package com.example.blackjack

import android.util.Log

class BlackJackPresenter(var blackJackView: BlackJackView?, val blackJackInteractor: BlackJackInteractor):
    BlackJackInteractor.OnStartClickedListner{

    private val TAG: String = "BlackJackPresenter"
    fun onStartGame() {
        blackJackView?.apply {
            showButtonsBet()
            hideButtonStart()
            Log.d(TAG,"onStartGame")
        }
    }

    fun onPlayClicked(){
        blackJackView?.apply {
            hideButtonsBet()
            initializGame()
            dealCards()
            blackJackView?.apply {
                uploadPlayerCards(blackJackInteractor.getPlayerCards())
                uploadDealerCards(blackJackInteractor.getDealerCards())
            }
            showButtonGame()
        }
    }

    override fun onHitClicked(){
        blackJackInteractor.hit()
        blackJackView?.apply {
            uploadPlayerCards(blackJackInteractor.getPlayerCards())
        }
        if(blackJackInteractor.getPlayerTotalScore() == 21){
            blackJackView?.apply {
                updateTotal(2)
                winDialog()

            }
        }
        else if(blackJackInteractor.getPlayerTotalScore() > 21){
            blackJackView?.apply {
                updateTotal(0)
                loseDialog()
            }
        }
    }

    override fun onStandClicked() {
        blackJackInteractor.stand()
        blackJackView?.apply {
            uploadDealerCards(blackJackInteractor.getDealerCards())
        }
        when{
            blackJackInteractor.getPlayerTotalScore() == 21 ->{
                blackJackView?.apply {
                    updateTotal(0)
                    loseDialog()
                }
            }
            blackJackInteractor.getPlayerTotalScore() > 21->{
                blackJackView?.apply {
                    updateTotal(2)
                    winDialog()
                }
            }
            else ->{
                when{
                    blackJackInteractor.declareWinner() == "you" -> {
                        blackJackView?.apply {
                            updateTotal(2)
                            winDialog()
                        }
                    }
                else->{
                    blackJackView?.apply {
                        updateTotal(0)
                        loseDialog()
                    }}
                }
            }
        }
    }

    override fun initializGame() {
        blackJackInteractor.initializeGame()
    }

    override fun dealCards() {
        blackJackInteractor.dealCards()
    }




    override fun playAgine() {
        blackJackView?.apply {
            var backCards: ArrayList<Card> = ArrayList()
            backCards.add(Card(13,'b'))
            backCards.add(Card(13,'b'))
            backCards.add(Card(13,'b'))
            backCards.add(Card(13,'b'))
            backCards.add(Card(13,'b'))
            hideButtonGame()
            showButtonsBet()
            uploadDealerCards(backCards)
            uploadPlayerCards(backCards)
        }
    }


}