package com.smb.components.ui

import com.smb.components.ui.mocks.GameBoardClassMock
import com.smb.components.ui.mocks.WinnerType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import test.BaseViewModelUnitTest

class GameBoardViewModelTest : BaseViewModelUnitTest() {

    var boardMock = Array(4) { Array(4) { 0 } }
    private lateinit var viewModel: GameBoardViewModel

    @BeforeEach
    fun setUp() {
        viewModel = GameBoardViewModel()
    }

    @TestFactory
    fun `isCellAvailable should return`() = listOf(
        Triple(0, 0, true),
        Triple(0, 1, false),
    ).map { testCase ->
        DynamicTest.dynamicTest(" ${testCase.third} with values ${testCase.first} and ${testCase.second}") {
            viewModel.board[testCase.first][testCase.second] = testCase.second
            val result = viewModel.isCellAvailable(testCase.first, testCase.second)
            assertEquals(testCase.third, result)
        }
    }

    @Test
    fun `check if resetGameBoard reset board`() {
        viewModel.resetGameBoard()

        for (row in viewModel.board.indices) {
            for (col in viewModel.board.indices) {
                assertEquals(0, viewModel.board[row][col])
            }
        }
        assertEquals(1, viewModel.player)
        assertEquals(0, viewModel.diagonal)
        assertEquals(0, viewModel.reversedDiagonal)
        assertEquals(0, viewModel.verticalCount)
        assertEquals(0, viewModel.horizontalCount)
        assertFalse(viewModel.isWinner)
    }

    @TestFactory
    fun `winnerCheck should return`() = listOf(
        GameBoardClassMock(
            title = "winner when 4 in row in vertical",
            result = true,
            row = 0,
            col = 1,
            type = WinnerType.VERTICAL
        ),
        GameBoardClassMock(
            title = "winner when 4 in row in vertical",
            result = true,
            row = 0,
            col = 2,
            type = WinnerType.VERTICAL
        ),
        GameBoardClassMock(
            title = "winner when 4 in row in horizontal",
            result = true,
            row = 0,
            col = 0,
            type = WinnerType.HORIZONTAL
        ),
        GameBoardClassMock(
            title = "winner when 4 in row in horizontal",
            result = true,
            row = 0,
            col = 1,
            type = WinnerType.HORIZONTAL
        ),
        GameBoardClassMock(
            title = "winner when 4 in row in horizontal",
            result = true,
            row = 0,
            col = 3,
            type = WinnerType.HORIZONTAL
        ),
        GameBoardClassMock(
            title = "winner when 4 in row in diagonal",
            result = true,
            row = 0,
            col = 3,
            type = WinnerType.DIAGONAL
        ),
        GameBoardClassMock(
            title = "winner when 4 in row in diagonal",
            result = true,
            row = 0,
            col = 3,
            type = WinnerType.REVERSE_DIAGONAL
        )
    ).map { testCase ->
        DynamicTest.dynamicTest(" ${testCase.result} for type ${testCase.type}") {
            when (testCase.type) {
                WinnerType.VERTICAL -> populateColumn(testCase.col)
                WinnerType.HORIZONTAL -> populateRow(testCase.row)
                WinnerType.DIAGONAL -> populateDiagonal()
                WinnerType.REVERSE_DIAGONAL -> populateReverseDiagonal()
                else -> Unit
            }
            viewModel.winnerCheck(testCase.row, testCase.col + 1)
            assertTrue(viewModel.isWinner)
        }

    }

    private fun populateColumn(col: Int) {
        resetBoard()
        for (row in boardMock.indices) {
            boardMock[row][col] = 1
        }
        viewModel.board = boardMock
    }

    private fun populateRow(row: Int) {
        resetBoard()
        for (col in boardMock.indices) {
            boardMock[row][col] = 1
        }
        viewModel.board = boardMock
    }

    private fun populateDiagonal() {
        resetBoard()
        for (value in boardMock.indices) {
            boardMock
        }
        viewModel.board = boardMock
    }

    private fun populateReverseDiagonal() {
        var row = 0

        resetBoard()
        for (value in boardMock.indices.reversed()) {
            boardMock[row][value] = 1
            row += 1
        }
        viewModel.board = boardMock
    }

    private fun resetBoard() {
        for (row in boardMock.indices) {
            for (col in boardMock.indices) {
                boardMock[row][col] = 0
            }
        }
    }
}