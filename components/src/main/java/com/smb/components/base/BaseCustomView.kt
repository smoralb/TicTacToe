package com.smb.components.base

import com.smb.core.presentation.BaseState
import com.smb.core.presentation.BaseViewModel

interface BaseCustomView<S : BaseState, VM : BaseViewModel<S>> {

    val viewModel: VM
}