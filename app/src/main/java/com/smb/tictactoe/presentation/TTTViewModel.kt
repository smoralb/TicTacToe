package com.smb.tictactoe.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smb.core.extensions.update

class TTTViewModel : ViewModel() {

    var playerChip: MutableLiveData<Int> = MutableLiveData(1)
    var playerTurn: MutableLiveData<Int> = MutableLiveData(0)
    var clearBoard: MutableLiveData<Boolean> = MutableLiveData(false)

    fun onButtonClick() {
        clearBoard update true
    }

    fun updatePlayerTurn(turn: Int) {
        playerTurn update 1
        playerTurn update turn
    }

}