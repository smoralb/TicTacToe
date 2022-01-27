package com.smb.tictactoe.presentation

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.smb.core.presentation.BaseFragment
import com.smb.tictactoe.BR
import com.smb.tictactoe.R
import com.smb.tictactoe.databinding.GameFragmentBinding
import com.smb.tictactoe.presentation.TTTState.Draw
import com.smb.tictactoe.presentation.TTTState.Gaming
import com.smb.tictactoe.presentation.TTTState.Winner
import org.koin.androidx.viewmodel.ext.android.viewModel

class TTTFragment : BaseFragment<TTTState, GameFragmentBinding, TTTViewModel>(
    R.layout.game_fragment, BR.viewModel
) {
    override val viewModel by viewModel<TTTViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observeForever { checkViewState(it) }

        viewModel.playerChip.observeForever { playerTurn ->
            viewModel.checkGameStatus(
                binding.gbGame.isWinnerMovement,
                binding.gbGame.isDraw,
                playerTurn
            )
        }
    }

    private fun checkViewState(newState: TTTState) {
        when (newState) {
            is Winner -> {
                binding.apply {
                    llResult.visibility = VISIBLE
                    ivGameResult.visibility = VISIBLE
                    lbGameResult.text = resources.getString(R.string.board_winner_text)
                    ivGameResult.setImageResource(newState.winnerChip)
                }
            }
            Draw -> {
                binding.apply {
                    llResult.visibility = VISIBLE
                    lbGameResult.text = resources.getString(R.string.board_draw_text)
                    ivGameResult.visibility = GONE
                }
            }
            Gaming -> {
                if (binding.llResult.visibility == VISIBLE)
                    binding.llResult.visibility = GONE
            }
        }
    }
}