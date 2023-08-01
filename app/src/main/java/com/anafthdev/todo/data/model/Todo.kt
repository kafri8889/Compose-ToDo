package com.anafthdev.todo.data.model

import android.os.Parcelable
import com.anafthdev.todo.data.datasource.local.dao.TodoDao
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: Int,
    val categoryId: Int,
    val title: String,
    val description: String,
    val createdAt: Long,
    val finished: Boolean,

    /**
     * Check [TodoDao.getTodoByIdWithSubTodo]
     */
    val subTodo: List<SubTodo> = emptyList()
): Parcelable
