package com.smb.tictactoe.presentation

import android.os.Bundle
import android.view.View
import com.smb.core.presentation.BaseFragment
import com.smb.tictactoe.BR
import com.smb.tictactoe.R
import com.smb.tictactoe.databinding.GameFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.smb.tictactoe.presentation.TTTState.UpdatePlayer

class TTTFragment : BaseFragment<TTTState, GameFragmentBinding, TTTViewModel>(
    R.layout.game_fragment, BR.viewModel
) {
    override val viewModel by viewModel<TTTViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observeForever { checkViewState(it) }
    }

    private fun checkViewState(newState: TTTState) {
        when (newState) {
            UpdatePlayer -> Unit
        }
    }
}