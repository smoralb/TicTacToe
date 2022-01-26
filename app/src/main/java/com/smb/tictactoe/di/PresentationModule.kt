package com.smb.tictactoe.di

import com.smb.tictactoe.presentation.TTTViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { TTTViewModel() }
}