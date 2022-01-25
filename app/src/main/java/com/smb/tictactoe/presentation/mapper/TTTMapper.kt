package com.smb.tictactoe.presentation.mapper

import com.smb.tictactoe.R


interface TTTMapper {
    fun getCurrentPlayer(playerTurn: Int): Int
}

class TTTMapperImpl: TTTMapper {

    override fun getCurrentPlayer(playerTurn: Int) =
        when (playerTurn % 2) {
            0 -> R.drawable.ic_nought
            else -> R.drawable.ic_cross
        }
}