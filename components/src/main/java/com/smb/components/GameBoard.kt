package com.smb.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.smb.core.extensions.getColorByResourceId
import kotlin.math.min

private const val ZERO_F = 0F
private const val ZERO = 0
private const val MAX_CELLS = 4
private const val CELL_SEPARATOR_THICKNESS = 3F

class GameBoard(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paint = Paint()
    private var cellSize = ZERO

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
}