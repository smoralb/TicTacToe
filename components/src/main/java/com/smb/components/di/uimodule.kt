package com.smb.components.di

import com.smb.components.ui.GameBoardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { GameBoardViewModel() }
}