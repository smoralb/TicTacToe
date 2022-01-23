package com.smb.tictactoe.presentation.mapper

import com.smb.tictactoe.R

class TTTMapper {

    private var playerTurn = 1

    fun getCurrentPlayer(): Int {
        val gameChip = when (playerTurn % 2) {
            0 -> R.drawable.ic_nought
            else -> R.drawable.ic_cross
        }
        playerTurn += 1
        return gameChip
    }

    fun resetPlayerTurn() {
        playerTurn = 1
    }
}