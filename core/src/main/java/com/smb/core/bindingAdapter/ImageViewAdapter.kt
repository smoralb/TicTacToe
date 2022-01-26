package com.smb.core.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.smb.core.R

@BindingAdapter("drawableSrc")
fun getDrawableFromResource(imageView: ImageView, turn: Int) =
    imageView.setImageResource(
        when (turn) {
            2 -> R.drawable.ic_nought
            else -> R.drawable.ic_cross
        }
    )
