package com.example.blackjack

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_table.*
import android.graphics.drawable.Drawable





class BlackJackActivity : AppCompatActivity(), BlackJackView {



    private val TAG: String = "BlackJackActivity"

    private var presenter = BlackJackPresenter(this, BlackJackInteractor())

    private var total: Int = 2500
    private var bet: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)
        Log.d(TAG,"onCreate")

        button_start.setOnClickListener { startGame() }
        button_five.setOnClickListener{ onFiveClicked() }
        button_twentyfive.setOnClickListener{ onTwentyfiveClicked() }
        button_hundred.setOnClickListener{ onHundredClicked() }
        button_play.setOnClickListener{ onPlayClicked() }
        button_hit.setOnClickListener{ onHitClicked() }
        button_stand.setOnClickListener{ onStandClicked() }

    }

    private fun startGame(){
        presenter.onStartGame()
        Log.d(TAG,"startGame")
    }

    override fun onFiveClicked() {
        bet += 5
        total -= 5
        updateTextViews()
    }

    override fun onTwentyfiveClicked() {
        bet += 25
        total -= 25
        updateTextViews()
    }

    override fun onHundredClicked() {
        bet += 100
        total -= 100
        updateTextViews()
    }

    override fun onPlayClicked() {
        presenter.onPlayClicked()
    }

    override fun onHitClicked() {
        presenter.onHitClicked()
    }

    override fun onStandClicked() {
        presenter.onStandClicked()
    }

    private fun updateTextViews(){
        text_view_current_bet.setText("$bet")
        text_view_funds.setText("$total")
    }

    override fun uploadPlayerCards(cards: ArrayList<Card>) {
        var i: Int = 1

        for(card in cards) {

            when (i) {
                1 -> {
                    val context = img_card_player_1.getContext()
                    val id = context.getResources().getIdentifier("ic$card", "drawable", context.getPackageName())
                    img_card_player_1.setImageResource(id)}
                2 -> {
                    val context = img_card_player_2.getContext()
                    val id = context.getResources().getIdentifier("ic$card", "drawable", context.getPackageName())
                    img_card_player_2.setImageResource(id)}
                3 -> {
                    val context = img_card_player_3.getContext()
                    val id = context.getResources().getIdentifier("ic$card", "drawable", context.getPackageName())
                    img_card_player_3.setImageResource(id)}
                4 -> {
                    val context = img_card_player_4.getContext()
                    val id = context.getResources().getIdentifier("ic$card", "drawable", context.getPackageName())
                    img_card_player_4.setImageResource(id)}
                5 -> {
                    val context = img_card_player_5.getContext()
                    val id = context.getResources().getIdentifier("ic$card", "drawable", context.getPackageName())
                    img_card_player_5.setImageResource(id)}

            }
            i++
        }
    }

    override fun uploadDealerCards(cards: ArrayList<Card>) {
        var i: Int = 0

        for(card in cards) {

            when (i) {
                1 -> {
                    val context = img_card_computer_1.getContext()
                    val id = context.getResources().getIdentifier("ic$card", "drawable", context.getPackageName())
                    img_card_computer_1.setImageResource(id)}
                2 -> {
                    val context = img_card_computer_2.getContext()
                    val id = context.getResources().getIdentifier("ic$card", "drawable", context.getPackageName())
                    img_card_computer_2.setImageResource(id)}
                3 -> {
                    val context = img_card_computer_3.getContext()
                    val id = context.getResources().getIdentifier("ic$card", "drawable", context.getPackageName())
                    img_card_computer_3.setImageResource(id)}
                4 -> {
                    val context = img_card_computer_4.getContext()
                    val id = context.getResources().getIdentifier("ic$card", "drawable", context.getPackageName())
                    img_card_computer_4.setImageResource(id)}
                5 -> {
                    val context = img_card_computer_5.getContext()
                    val id = context.getResources().getIdentifier("ic$card", "drawable", context.getPackageName())
                    img_card_computer_5.setImageResource(id)}

            }
            i++
        }
    }

    override fun showButtonStart() {
        button_start.visibility = View.VISIBLE
    }

    override fun hideButtonStart() {
        button_start.visibility = View.GONE
    }

    override fun showButtonGame() {
        button_hit.visibility = View.VISIBLE
        button_stand.visibility = View.VISIBLE
    }

    override fun hideButtonGame() {
        button_hit.visibility = View.GONE
        button_stand.visibility = View.GONE
    }

    override fun showButtonsBet() {
        text_view_funds.visibility = View.VISIBLE
        text_view_current_bet.visibility = View.VISIBLE
        button_five.visibility = View.VISIBLE
        button_twentyfive.visibility = View.VISIBLE
        button_hundred.visibility = View.VISIBLE
        button_play.visibility = View.VISIBLE
    }

    override fun hideButtonsBet() {
        text_view_funds.visibility = View.GONE
        text_view_current_bet.visibility = View.GONE
        button_five.visibility = View.GONE
        button_twentyfive.visibility = View.GONE
        button_hundred.visibility = View.GONE
        button_play.visibility = View.GONE
    }

    override fun showPlayButton() {
        button_play.visibility = View.VISIBLE
    }

    override fun hidePlayButton() {
        button_play.visibility = View.GONE
    }

    override fun winDialog() {
        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Good job!")
            .setContentText("You WON!")
            .show()
        Thread.sleep(1_000)
        presenter = BlackJackPresenter(this, BlackJackInteractor())
        presenter.playAgine()
    }

    override fun loseDialog() {
        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("Grrrry gry gry")
            .setContentText("You LOOSED")
            .show()
        Thread.sleep(1_000)
        presenter = BlackJackPresenter(this, BlackJackInteractor())
        presenter.playAgine()
    }

    override fun gameOverDialog() {
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("UUpppppsss")
            .setContentText("GAME OVER!")
            .show()
        System.exit(0)
    }

    override fun updateTotal(amount: Int){
        total += amount*bet
        bet = 0
        updateTextViews()
    }


}
