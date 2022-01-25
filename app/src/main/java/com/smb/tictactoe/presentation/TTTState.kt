package com.smb.tictactoe.presentation

import com.smb.core.presentation.BaseState

sealed class TTTState: BaseState() {
    object UpdatePlayer: TTTState()
}
