package com.anafthdev.todo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: Int,
    val categoryId: Int,
    val title: String,
    val description: String,
    val createdAt: Long,
    val finished: Boolean,
    val subTodo: List<SubTodo> = emptyList()
): Parcelable
