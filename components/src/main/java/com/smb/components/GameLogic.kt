package com.smb.components

import com.smb.core.extensions.forEach2D

class GameLogic {

    var board = arrayOf<Array<Int>>()
    var player = 1

    init {
        // A 4x4 array of Int, all set to 0.
        board = Array(4) { Array(4) { 0 } }
    }

    fun updateGameBoard(row: Int, column: Int): Boolean {
        return if (board[row-1][column-1] == 0) {
            board[row-1][column-1] = player
            true
        } else {
            false
        }
    }

    fun resetGameBoard() {
        board.forEach2D { board[it][it] = 0 }
    }

}