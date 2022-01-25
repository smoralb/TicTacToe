package com.smb.core.extensions

inline fun <T> Array<Array<T>>.forEach2D(action: (T) -> Unit) {
    for (array in this) for (element in array) action(element)
}