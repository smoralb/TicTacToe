package com.smb.components

import com.smb.core.extensions.forEach2D

class GameLogic {

    var board = arrayOf<Array<Int>>()
    var player = 1
    var isWinner = false
    var diagonal = 0
    var reversedDiagonal = 0
    var verticalCount = 0
    var horizontalCount = 0

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
        reversedDiagonal = 0
        verticalCount = 0

        for (row in board.indices) {
            if (board[row][0] == board[row][1] && board[row][0] == board[row][2] &&
                board[row][0] == board[row][3] && board[row][0] != 0
            ) {
                isWinner = true
            }
        }

        for (col in board.indices) {
            if (!isWinner) {
                verticalCount = 0
                checkVertical(col)
            }
        }

        for (row in board.indices) {
            if (!isWinner) {
                horizontalCount = 0
                checkHorizontal(row)
            }
        }
        if (!isWinner) {
            checkMainDiagonal()
        }
        if (!isWinner) {
            checkReversedDiagonal()
        }

        return (isWinner || checkIfBoardIsFilled())
    }

    private fun checkVertical(col: Int){
        for (row in board.indices) {
            if (board[row][col] != 0 && board[row][col] == board[0][col]) {
                verticalCount += 1
            }
        }
        isWinner = verticalCount == board.size
    }

    private fun checkHorizontal(row: Int) {
        for (col in board.indices) {
            if (board[0][col] != 0 && board[row][col] == board[0][col]) {
                horizontalCount += 1
            }
        }
        isWinner = horizontalCount == board.size
    }

    private fun checkMainDiagonal() {
        for (value in board.indices) {
            if (board[value][value] != 0 && board[value][value] == board[0][0])
                diagonal += 1
        }

        isWinner = diagonal == board.size
    }

    private fun checkReversedDiagonal() {
        var row = 0

        for (value in board.indices.reversed()) {
            if (board[row][value] != 0 && board[row][value] == board[0][board.size - 1]) {
                reversedDiagonal += 1
            }
            row += 1
        }

        isWinner = reversedDiagonal == board.size
    }

    private fun checkIfBoardIsFilled() = board.all { row ->
        row.all { col ->
            col == 1
        }
    }

}