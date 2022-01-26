package com.smb.tictactoe.presentation

import android.os.Bundle
import android.view.View
import com.smb.core.presentation.BaseFragment
import com.smb.tictactoe.BR
import com.smb.tictactoe.R
import com.smb.tictactoe.databinding.GameFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TTTFragment : BaseFragment<GameFragmentBinding, TTTViewModel>(
    R.layout.game_fragment, BR.viewModel
) {
    override val viewModel by viewModel<TTTViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.playerChip.observeForever {
            viewModel.updatePlayerTurn(it)
        }
    }
}