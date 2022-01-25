package com.smb.tictactoe.di

import com.smb.tictactoe.presentation.TTTViewModel
import com.smb.tictactoe.presentation.mapper.TTTMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { TTTMapper() }
    viewModel { TTTViewModel(mapper = get()) }
}