package com.example.wordle

import FourLetterWordList
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var G1: TextView
    private lateinit var G1C: TextView
    private lateinit var G2: TextView
    private lateinit var G2C: TextView
    private lateinit var G3: TextView
    private lateinit var G3C: TextView
    private lateinit var guessInputEditTexT: EditText
    private lateinit var guessButton: Button
    private lateinit var restButton:Button

    private var targetWord: String = FourLetterWordList.getRandomFourLetterWord()
    private var guessCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        G1 = findViewById(R.id.G1)
        G1C = findViewById(R.id.G1C)
        G2 = findViewById(R.id.G2)
        G2C = findViewById(R.id.G2C)
        G3 = findViewById(R.id.G3)
        G3C = findViewById(R.id.G3C)
        guessInputEditTexT = findViewById(R.id.guessEntry)

        guessButton = findViewById(R.id.button2)
        val resetButton: Button = findViewById(R.id.buttonReset)

        guessButton.setOnClickListener {
            handleGuess()
        }
        restButton.setOnClickListener() {
            resetGame()
        }
    }
    
    private fun handleGuess () {

        val guess = guessInputEditText.text.toString().uppercase()
            
        if(guess.length != 4) {
            println("Invalid guess length. Your guess is: $guess")
            return

            println("Your guess is: $guess")
        }
            
        when(guessCount) {
            0 -> {
                G1.text = guess
                G1C.text = checkGuess(guess)
            }
            1 -> {
                G2.text = guess
                G2C.text = checkGuess(guess)
            }
            2 -> {
                G3.text = guess
                G3C.text = checkGuess(guess)
                guessButton.isEnabled = false
                restButton.visibility = Button.VISIBLE
            }
        }

        guessCount++
    }

    private fun resetGame() {
        targetWord = FourLetterWordList.getRandomFourLetterWord()
        guessCount = 0

        G1.text = "Guess 1"
        G1C.text = "Guess 1 Check"
        G2.text = "Guess 2"
        G2.text = "Guess 2 Check"
        G3.text = "Guess 3"
        G3C.text = "Guess 3 Check"


        guessButton.isEnabled = true
        val resetButton = null
        resetButton.visibility  = View.GONE
    }

    private fun checkGuess(guess: String): String {
        val result = StringBuilder()

        for (i in 0 until 4) {
            if (guess [i] == targetWord[i]) {
                result.append('0')
            } else if (targetWord.contains(guess[i])) {
                result.append('+')
            } else {
                result.append('X')
            }
        }

        return result.toString()
    }
}