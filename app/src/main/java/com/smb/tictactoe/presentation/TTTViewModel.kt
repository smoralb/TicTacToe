package com.smb.tictactoe.presentation

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.update
import com.smb.core.presentation.BaseViewModel
import com.smb.tictactoe.presentation.TTTState.Draw
import com.smb.tictactoe.presentation.TTTState.Winner
import com.smb.tictactoe.presentation.mapper.TTTMapper

class TTTViewModel(
    val mapper: TTTMapper
) : BaseViewModel<TTTState>() {

    var playerChip: MutableLiveData<Int> = MutableLiveData(1)
    var playerTurn: MutableLiveData<Int> = MutableLiveData(0)
    var clearBoard: MutableLiveData<Boolean> = MutableLiveData(false)
    var showWinnerAlert: MutableLiveData<Boolean> = MutableLiveData(false)

    fun onNewGameClick() {
        showWinnerAlert update false
        playerTurn update 1
        clearBoard update true
    }

    internal fun checkGameStatus(isWinner: Boolean, isDraw: Boolean, playerTurn: Int) {
        val gameState = mapper.getGameResult(isWinner, isDraw, playerTurn)
        if (gameState !is Winner && gameState != Draw) this.playerTurn update playerTurn
        viewState update gameState
    }

}