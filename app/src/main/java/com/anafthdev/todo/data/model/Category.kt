package com.anafthdev.todo.data.model

import android.os.Parcelable
import com.anafthdev.todo.data.datasource.local.dao.CategoryDao
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val name: String,

    /**
     * Check [CategoryDao.getCategoryByIdWithTodo]
     */
    val todo: List<Todo> = emptyList()
): Parcelable
