package com.smb.tictactoe.presentation

import com.smb.core.presentation.BaseState

sealed class TTTState: BaseState() {
    object Gaming: TTTState()
    data class Winner(val winnerChip: Int): TTTState()
    object Draw: TTTState()
}