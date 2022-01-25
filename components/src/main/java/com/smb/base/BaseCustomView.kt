package com.smb.base

import androidx.lifecycle.LifecycleOwner
import com.smb.core.presentation.BaseState
import com.smb.core.presentation.BaseViewModel

interface BaseCustomView<S : BaseState, VM : BaseViewModel<S>> {

    val viewModel: VM

    /*
    To attach LifeCycleOwner in order to use Livedata
     */
    fun onLifecycleOwnerAttached(lifeCycleOwner: LifecycleOwner)
}