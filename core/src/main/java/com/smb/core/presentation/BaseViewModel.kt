package com.smb.core.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<S : BaseState> : ViewModel() {

    val viewState: MutableLiveData<S> = MutableLiveData()

}