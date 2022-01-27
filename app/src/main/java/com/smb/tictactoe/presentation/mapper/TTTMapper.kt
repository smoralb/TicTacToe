package com.smb.tictactoe.presentation.mapper

import com.smb.tictactoe.R
import com.smb.tictactoe.presentation.TTTState
import com.smb.tictactoe.presentation.TTTState.Draw
import com.smb.tictactoe.presentation.TTTState.Gaming
import com.smb.tictactoe.presentation.TTTState.Winner

interface TTTMapper {

    fun getPlayerChip(player: Int): Int

    fun getGameResult(isWinner: Boolean, draw: Boolean, playerTurn: Int): TTTState

    fun getResultMessage(status: Boolean): Int
}

class TTTMapperImpl : TTTMapper {

    override fun getGameResult(isWinner: Boolean, draw: Boolean, playerTurn: Int) =
        if (isWinner && !draw) {
            Winner(getPlayerChip(playerTurn + 1))
        } else if (draw) Draw else Gaming


    override fun getResultMessage(status: Boolean) =
        when (status) {
            true -> R.string.board_winner_text
            false -> R.string.board_draw_text
        }

    override fun getPlayerChip(player: Int) = when (player) {
        2 -> R.drawable.ic_nought
        else -> R.drawable.ic_cross
    }
}