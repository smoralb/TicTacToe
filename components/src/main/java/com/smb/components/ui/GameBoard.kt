package com.smb.components.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.smb.components.R
import com.smb.components.base.BaseCustomView
import com.smb.core.extensions.getColorByResourceId
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.math.ceil
import kotlin.math.min

private const val ZERO_F = 0F
private const val ZERO = 0
private const val MAX_CELLS = 4
private const val CELL_SEPARATOR_THICKNESS = 3F
private const val GAME_CHIPS_THICKNESS = 20F
private const val GAME_CHIPS_MARGIN = 0.2F

@KoinApiExtension
class GameBoard(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet), BaseCustomView<GameBoardViewModel>,
    KoinComponent {

    override val viewModel: GameBoardViewModel by inject()

    private var cellSize = ZERO
    private val paint: Paint = Paint()

    init {
        this.setBackgroundColor(resources.getColorByResourceId(context, R.color.white))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        /*
            With that calculation, the board will always be a square.
            For that, we will take the minimum dimension (width or height) of the device screen
            to calculate how big the board could be.
         */
        val dimension = min(measuredWidth, measuredHeight)
        /*
            The cellSize, would be adjusted depending on the screen size. So, we should divide
            our dimension by 4, the number of cells on each row and column.
         */
        cellSize = dimension / MAX_CELLS
        setMeasuredDimension(dimension, dimension)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        with(paint) {
            style = Paint.Style.STROKE
            // To smooth shape of edges
            isAntiAlias = true
        }
        drawGameBoard(canvas)
        drawMarkers(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (event.action == MotionEvent.ACTION_DOWN) {
            val row = ceil(event.y / cellSize)
            val column = ceil(event.x / cellSize)

            // To avoid user to place another chip if there is a winner
            with(viewModel) {
                if (!isWinner) {
                    if (isCellAvailable(row.toInt() - 1, column.toInt() - 1)) {
                        // To force to redraw the gameBoard, call onDraw method
                        invalidate()
                        player = updatePlayerTurn()
                        isWinner = winnerCheck(row.toInt(), column.toInt())
                    }
                }
            }
            true
        } else false
    }

    private fun drawGameBoard(canvas: Canvas) {
        with(paint) {
            color = resources.getColorByResourceId(context, R.color.cell_border)
            strokeWidth = CELL_SEPARATOR_THICKNESS
        }

        with(canvas) {
            drawColumns(this).also { drawRows(this) }
        }
    }

    private fun drawRows(canvas: Canvas) {
        for (row in ZERO..MAX_CELLS) {
            canvas.drawLine(
                ZERO_F,
                (cellSize * row).toFloat(),
                (canvas.width).toFloat(),
                (cellSize * row).toFloat(),
                paint
            )
        }
    }

    private fun drawColumns(canvas: Canvas) {
        for (col in ZERO..MAX_CELLS) {
            canvas.drawLine(
                (cellSize * col).toFloat(),
                ZERO_F,
                (cellSize * col).toFloat(),
                (canvas.width).toFloat(),
                paint
            )
        }
    }

    private fun drawMarkers(canvas: Canvas) {
        for (row in ZERO until MAX_CELLS) {
            for (column in ZERO until MAX_CELLS) {
                if (viewModel.board[row][column] != 0) {
                    if (viewModel.board[row][column] == 1) {
                        drawX(canvas, row, column)
                    } else {
                        drawO(canvas, row, column)
                    }
                }
            }
        }
    }

    // Here is defined how it is going to be shown the game chips.

    private fun drawX(canvas: Canvas, row: Int, column: Int) {
        addGameChipsStyle()

        /*
            For X, two line are going to be drawn defining the start and the end point of each
            of the edges and some margin is added to the chip to not fill the entire cell (cellSize * 0.2F)
         */

        canvas.drawLine(
            (column + 1) * cellSize - cellSize * GAME_CHIPS_MARGIN,
            row * cellSize + cellSize * GAME_CHIPS_MARGIN,
            column * cellSize + cellSize * GAME_CHIPS_MARGIN,
            (row + 1) * cellSize - cellSize * GAME_CHIPS_MARGIN,
            paint
        )

        canvas.drawLine(
            column * cellSize + cellSize * GAME_CHIPS_MARGIN,
            row * cellSize + cellSize * GAME_CHIPS_MARGIN,
            (column + 1) * cellSize - cellSize * GAME_CHIPS_MARGIN,
            (row + 1) * cellSize - cellSize * GAME_CHIPS_MARGIN,
            paint
        )
    }

    private fun drawO(canvas: Canvas, row: Int, column: Int) {
        addGameChipsStyle()
        canvas.drawOval(
            column * cellSize + cellSize * GAME_CHIPS_MARGIN,
            row * cellSize + cellSize * GAME_CHIPS_MARGIN,
            (column * cellSize + cellSize) - cellSize * GAME_CHIPS_MARGIN,
            (row * cellSize + cellSize) - cellSize * GAME_CHIPS_MARGIN,
            paint
        )
    }

    private fun addGameChipsStyle() {
        with(paint) {
            color = resources.getColorByResourceId(context, R.color.dark_gray)
            strokeWidth = GAME_CHIPS_THICKNESS
        }
    }

    private fun resetBoard() {
        viewModel.resetGameBoard()
        invalidate()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("resetGameBoard")
        fun resetGameBoard(view: GameBoard, value: Boolean) {
            if (value) {
                view.resetBoard()
            }
        }

        // Necessary for InverseDataBinding but at this moment, it is not necessary to set player from here
        @JvmStatic
        @BindingAdapter("playerTurn")
        fun setPlayerTurn(view: GameBoard, playerTurn: Int) {
        }

        @JvmStatic
        @InverseBindingAdapter(attribute = "playerTurn", event = "playerTurnAttrChanged")
        fun getPlayerTurn(view: GameBoard): Int {
            return view.viewModel.player
        }

        @SuppressLint("ClickableViewAccessibility")
        @JvmStatic
        @BindingAdapter(value = ["playerTurnAttrChanged"])
        fun setListener(view: GameBoard, listener: InverseBindingListener?) {
            listener?.let {
                view.setOnTouchListener { _, motionEvent ->
                    listener.onChange()
                    view.onTouchEvent(motionEvent)
                }
            }
        }

    }

}