package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {


    private fun getNextWord(): Boolean {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++currentWordCount
            wordsList.add(currentWord)
        }
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    init{
        Log.d("GameFragment","Gameviewmodel created!")
        getNextWord()
    }
    private var _score = 0
    val Score:Int get() = _score
    private var currentWordCount = 0
    private var wordsList: MutableList<String> = mutableListOf()

    private lateinit var currentWord: String
    private lateinit var _currentScrambledWord = "test"
    val currentScrambledWord: String
        get() = _currentScrambledWord
}

override fun onCleared() {
    super.onCleared()
    Log.d("GameFragment", "GameViewModel destroyed!")
}
