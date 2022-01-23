package com.smb.core.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.smb.core.R

@BindingAdapter("drawableSrc")
fun getDrawableFromResource(imageView: ImageView, resource: Int) =
    imageView.setImageResource(
        when (resource) {
            0 -> R.drawable.ic_cross
            else -> resource
        }
    )
