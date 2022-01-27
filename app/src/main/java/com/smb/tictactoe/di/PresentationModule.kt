package com.smb.tictactoe.di

import com.smb.tictactoe.presentation.TTTViewModel
import com.smb.tictactoe.presentation.mapper.TTTMapper
import com.smb.tictactoe.presentation.mapper.TTTMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory<TTTMapper> { TTTMapperImpl() }

    viewModel { TTTViewModel(mapper = get()) }
}