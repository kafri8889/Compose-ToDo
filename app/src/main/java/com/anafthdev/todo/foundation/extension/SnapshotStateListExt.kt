package com.anafthdev.todo.foundation.extension

import androidx.compose.runtime.snapshots.SnapshotStateList

fun <T> SnapshotStateList<T>.swap(newList: Collection<T>) {
    clear()
    addAll(newList)
}

fun <T> SnapshotStateList<T>.swap(newArray: Array<out T>) {
    clear()
    addAll(newArray)
}
