package com.smb.tictactoe.presentation

import com.smb.tictactoe.presentation.mapper.TTTMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import test.BaseViewModelUnitTest


class TTTViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var mapper: TTTMapper

    private lateinit var viewModel: TTTViewModel

    @BeforeEach
    fun setUp() {
        viewModel = TTTViewModel(mapper)
    }

    @Test
    fun `onButtonClick should clear board game`() {
        viewModel.onButtonClick()
        assertEquals(true, viewModel.clearBoard.value)
    }
}