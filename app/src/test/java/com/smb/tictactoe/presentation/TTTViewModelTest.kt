package com.smb.tictactoe.presentation

import com.smb.tictactoe.presentation.mapper.TTTMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import test.BaseViewModelUnitTest

@ExtendWith(MockitoExtension::class)
class TTTViewModelTest : BaseViewModelUnitTest() {

    @Mock
    private lateinit var mapper: TTTMapper

    private lateinit var viewModel: TTTViewModel

    @BeforeEach
    fun setUp() {
        viewModel = TTTViewModel(mapper)
    }

    @Test
    fun `onNewGameClick should clear board game`() {
        viewModel.onNewGameClick()
        assertEquals(true, viewModel.clearBoard.value)
        assertEquals(false, viewModel.showWinnerAlert.value)
        assertEquals(1, viewModel.playerTurn.value)
    }

}