package com.smb.components.base

import androidx.lifecycle.ViewModel

interface BaseCustomView<VM : ViewModel> {

    val viewModel: VM
}