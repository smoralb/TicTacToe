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

    fun winnerCheck(eventRow: Int, eventCol: Int): Boolean {
        diagonal = 0
        reversedDiagonal = 0
        verticalCount = 0

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

        if (!isWinner) {
            checkCorners()
        }

        if (!isWinner) {
            checkIfBox(eventRow, eventCol)
        }

        return (isWinner || checkIfBoardIsFilled())
    }

    private fun checkVertical(col: Int) {
        for (row in board.indices) {
            if (board[row][col] != 0 && board[row][col] == board[0][col]) {
                verticalCount += 1
            }
        }
        isWinner = verticalCount == board.size
    }

    private fun checkHorizontal(row: Int) {
        for (col in board.indices) {
            if (board[row][col] != 0 && board[row][col] == board[0][col]) {
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

    private fun checkCorners() {
        if ((board[0][0] + board[0][board.size - 1] + board[0][board.size - 1] + board[board.size - 1][board.size - 1]) == board.size) {
            if (board[0][0] == board[0][board.size - 1] && board[0][0] == board[board.size - 1][board.size - 1]) {
                isWinner = true
            }
        }
    }

    private fun checkIfBox(row: Int, col: Int) {
        val boardRow = row - 1
        val boardCol = col - 1

        when (row) {
            1 -> checkBottomColumn(row, col, boardRow, boardCol)
            board.size -> checkUpperColumn(col, boardRow, boardCol)
            else -> checkMiddleColumn(row, col, boardRow, boardCol)
        }
    }

    private fun checkMiddleColumn(row: Int, col: Int, boardRow: Int, boardCol: Int) {
        when (col) {
            1 -> {
                checkUpperRightPosition(col, boardRow, boardCol)
                checkBottomRightPosition(row, col, boardRow, boardCol)
            }
            board.size -> {
                checkUpperLeftPosition(boardRow, boardCol)
                checkBottomLeftPosition(row, boardRow, boardCol)
            }
            else -> {
                checkUpperRightPosition(col, boardRow, boardCol)
                checkUpperLeftPosition(boardRow, boardCol)
                checkBottomLeftPosition(row, boardRow, boardCol)
                checkBottomRightPosition(row, col, boardRow, boardCol)
            }
        }
    }

    private fun checkBottomColumn(row: Int, col: Int, boardRow: Int, boardCol: Int) {
        when (col) {
            1 -> checkBottomRightPosition(row, col, boardRow, boardCol)
            else -> {
                if (col != board.size) {
                    checkBottomLeftPosition(row, boardRow, boardCol)
                    checkBottomRightPosition(row, col, boardRow, boardCol)
                } else {
                    checkBottomLeftPosition(row, boardRow, boardCol)
                }
            }
        }
    }

    private fun checkUpperColumn(col: Int, boardRow: Int, boardCol: Int) {
        when (col) {
            1 -> checkUpperRightPosition(col, boardRow, boardCol)
            board.size -> checkUpperLeftPosition(boardRow, boardCol)
            else -> {
                checkUpperRightPosition(col, boardRow, boardCol)
                checkUpperLeftPosition(boardRow, boardCol)
            }
        }
    }

    private fun checkBottomRightPosition(row: Int, col: Int, boardRow: Int, boardCol: Int) {
        if (board[boardRow][col] != 0 && board[boardRow][col] == board[boardRow][boardCol]) {// horizontal right
            if (board[row][col] != 0 && board[row][col] == board[boardRow][boardCol]) { // diagonal bottom right
                if (board[row][boardCol] != 0 && board[row][boardCol] == board[boardRow][boardCol]) { // vertical bottom
                    isWinner = true
                }
            }
        }
    }

    private fun checkUpperRightPosition(col: Int, boardRow: Int, boardCol: Int) {
        if (board[boardRow - 1][boardCol] != 0 && board[boardRow - 1][boardCol] == board[boardRow][boardCol]) { // vertical upper
            if (board[boardRow][col] != 0 && board[boardRow][col] == board[boardRow][boardCol]) { // horizontal right
                if (board[boardRow - 1][boardCol + 1] != 0 && board[boardRow - 1][boardCol + 1] == board[boardRow][boardCol]) { // diagonal upper right
                    isWinner = true
                }
            }
        }
    }

    private fun checkUpperLeftPosition(boardRow: Int, boardCol: Int) {
        if (board[boardRow - 1][boardCol] != 0 && board[boardRow - 1][boardCol] == board[boardRow][boardCol]) { // vertical upper
            if (board[boardRow][boardCol - 1] != 0 && board[boardRow][boardCol - 1] == board[boardRow][boardCol]) { // vertical left
                if (board[boardRow - 1][boardCol - 1] != 0 && board[boardRow - 1][boardCol - 1] == board[boardRow][boardCol]) { // diagonal upper left
                    isWinner = true
                }
            }
        }
    }

    private fun checkBottomLeftPosition(row: Int, boardRow: Int, boardCol: Int) {
        if (board[boardRow][boardCol - 1] != 0 && board[boardRow][boardCol - 1] == board[boardRow][boardCol]) { // vertical left
            if (board[row][boardCol] != 0 && board[row][boardCol] == board[boardRow][boardCol]) { // vertical bottom
                if (board[row][boardCol - 1] != 0 && board[row][boardCol - 1] == board[boardRow][boardCol]) { // diagonal bottom
                    isWinner = true
                }
            }
        }
    }


    private fun checkIfBoardIsFilled() = board.all { row ->
        row.all { col ->
            col == 1
        }
    }

}