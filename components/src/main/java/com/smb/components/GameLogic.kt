package com.smb.components

import com.smb.core.extensions.forEach2D

class GameLogic {

    var board = arrayOf<Array<Int>>()
    var player = 1
    var isWinner = false
    var diagonal = 0

    init {
        // A 4x4 array of Int, all set to 0.
        board = Array(4) { Array(4) { 0 } }
    }

    fun updateGameBoard(row: Int, column: Int): Boolean {
        return if (board[row - 1][column - 1] == 0) {
            board[row - 1][column - 1] = player
            true
        } else {
            false
        }
    }

    fun resetGameBoard() {
        board.forEach2D { board[it][it] = 0 }
    }

    fun winnerCheck(): Boolean {
        diagonal = 0

        for (row in board.indices) {
            if (board[row][0] == board[row][1] && board[row][0] == board[row][2] &&
                board[row][0] == board[row][3] && board[row][0] != 0
            ) {
                isWinner = true
            }
        }

        for (col in board.indices) {
            if (board[0][col] == board[1][col] && board[2][col] == board[0][col] &&
                board[3][col] == board[0][col] && board[0][col] != 0
            ) {
                isWinner = true
            }
        }

        checkMainDiagonal()


        if (board[0][3] == board[1][2] && board[2][1] == board[0][3] && board[3][0] == board[0][3]
            && board[0][3] != 0
        ) {
            isWinner = true
        }


        return (isWinner || checkIfBoardIsFilled())
    }

    private fun checkMainDiagonal(): Boolean {
        for (value in board.indices) {
            if (board[value][value] != 0 && board[value][value] == board[0][0])
                diagonal += 1
        }

        if (diagonal == board.size) {
            isWinner = true
        }
        return isWinner
    }

    private fun checkIfBoardIsFilled() = board.all { row ->
        row.all { col ->
            col == 1
        }
    }

}