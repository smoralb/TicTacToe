package com.smb.tictactoe.presentation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
    }
}