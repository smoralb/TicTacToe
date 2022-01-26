package com.smb.tictactoe.presentation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import test.BaseViewModelUnitTest


class TTTViewModelTest : BaseViewModelUnitTest() {

    private lateinit var viewModel: TTTViewModel

    @BeforeEach
    fun setUp() {
        viewModel = TTTViewModel()
    }

    @Test
    fun `onButtonClick should clear board game`() {
        viewModel.onButtonClick()
        assertEquals(true, viewModel.clearBoard.value)
        assertEquals(1, viewModel.playerTurn.value)
    }

    @ParameterizedTest
    @ValueSource(ints = [0,1,2])
    fun `updatePlayerTurn should update value for player turn`(turn: Int) {
        viewModel.updatePlayerTurn(turn)
        assertEquals(turn, viewModel.playerTurn.value)
    }
}