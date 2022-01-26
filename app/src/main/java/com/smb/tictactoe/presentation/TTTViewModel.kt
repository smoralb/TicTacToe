package com.smb.tictactoe.presentation

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.update
import com.smb.core.presentation.BaseViewModel

class TTTViewModel : BaseViewModel<TTTState>() {

    var playerChip: MutableLiveData<Int> = MutableLiveData(1)
    var playerTurn: MutableLiveData<Int> = MutableLiveData(0)
    var clearBoard: MutableLiveData<Boolean> = MutableLiveData(false)

    fun onButtonClick() {
        clearBoard update true
    }

    fun updatePlayerTurn(turn: Int) {
        playerTurn update turn
    }

}