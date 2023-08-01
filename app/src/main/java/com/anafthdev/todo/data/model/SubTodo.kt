package com.anafthdev.todo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class SubTodo(
    val id: Int,
    val todoId: Int,
    val title: String,
    val createdAt: Long,
    val finished: Boolean,
): Parcelable, Serializable
