package com.smb.tictactoe.presentation

import androidx.lifecycle.MutableLiveData
import com.smb.core.extensions.update
import com.smb.core.presentation.BaseViewModel
import com.smb.tictactoe.presentation.mapper.TTTMapper

class TTTViewModel(
    val mapper: TTTMapper
) : BaseViewModel<TTTState>() {

    var playerChip: MutableLiveData<Int> = MutableLiveData(0)
    var clearBoard: MutableLiveData<Boolean> = MutableLiveData(false)

    fun onButtonClick() {
        clearBoard update true
    }

}