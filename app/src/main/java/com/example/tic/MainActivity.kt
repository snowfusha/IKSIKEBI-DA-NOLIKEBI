package com.example.tic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var buttons: Array<Array<Button>>
    lateinit var textViewPlayer1: TextView
    lateinit var textViewPlayer2: TextView


    private var player1Turn: Boolean = true
    private var roundCount: Int = 0
    private var player1Points: Int = 0
    private var player2Points: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewPlayer1 = findViewById(R.id.player1TextView)
        textViewPlayer2 = findViewById(R.id.player2TextView)

        buttons = Array(3){r->

            Array(3){c->
                initButtons(r,c)
            }
        }
        val btnReset: Button = findViewById(R.id.btnReset)
        btnReset.setOnClickListener {
            player1Points = 0
            player2Points = 0
            updateScore()
            clearBoard()
        }
    }

    private fun initButtons(r: Int, c: Int): Button {
        val btn: Button =
            findViewById(resources.getIdentifier("btn$r$c", "id", packageName))
        btn.setOnClickListener {
            onBtnClick(btn)
        }
        return btn

    }

    private fun onBtnClick(btn: Button) {
        if(btn.text != "") return
        if(player1Turn){
            btn.text = "X"
        }
        else{
            btn.text = "0"
        }
        roundCount++


        if(checkForWin()){
            if(player1Turn)win(1) else win(2)
        }
        else if (roundCount == 9){
            draw()
        }else{
            player1Turn = !player1Turn

        }
        

    }

    private fun checkForWin(): Boolean {
        val fields = Array(3) { r ->
            Array(3) { c ->
                buttons[r][c].text
            }

        }
        for (i in 0..2) {
            if ((fields[i][0] == fields[i][1]) &&
                (fields[i][0] == fields[i][2]) &&
                (fields[i][0] != "")
            ) return true

        }
        for (i in 0..2) {
            if (
                (fields[0][i] == fields[1][i]) &&
                (fields[0][i] == fields[2][i]) &&
                (fields[0][i] != "")
            ) return true

        }
            if(
                (fields[0][0] == fields[1][1]) &&
                (fields[0][0] == fields[2][2]) &&
                (fields[0][0] != "")
            )return true
            if(
            (fields[0][2] == fields[1][1]) &&
            (fields[0][2] == fields[2][0]) &&
            (fields[0][2] != "")
            )return true
            return false



    }

    private fun win(player: Int){
        if (player == 1) player1Points++ else player2Points++
        Toast.makeText(applicationContext,"Player $player ???????????????!", Toast.LENGTH_SHORT).show()
        updateScore()
        clearBoard()

    }

    private fun clearBoard() {
        for(i in 0..2){
            for(j in 0..2){
                buttons[i][j].text = ""
            }
        }
        roundCount = 0
        player1Turn = true
    }

    private fun updateScore() {
        textViewPlayer1.text = "Player 1: $player1Points"
        textViewPlayer2.text = "Player 2: $player2Points"
    }

    private  fun draw(){
        Toast.makeText(applicationContext, "???????????????!", Toast.LENGTH_SHORT).show()
        clearBoard()

    }
}
