package com.smb.core.extensions

import android.content.Context
import android.content.res.Resources
import android.os.Build

fun Resources.getColorByResourceId(context: Context, colorID: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.getColor(colorID, context.theme)
    } else {
        this.getColor(colorID)
    }