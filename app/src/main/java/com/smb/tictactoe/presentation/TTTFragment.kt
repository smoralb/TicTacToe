package com.smb.tictactoe.presentation

import com.smb.core.presentation.BaseFragment
import com.smb.tictactoe.BR
import com.smb.tictactoe.R
import com.smb.tictactoe.databinding.GameFragmentBinding

class TTTFragment : BaseFragment<TTTState, GameFragmentBinding, TTTViewModel>(
    R.layout.game_fragment, BR.viewModel
) {
    override val viewModel: TTTViewModel
        get() = TODO("Not yet implemented")
}