package com.anafthdev.todo.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subTodo_table")
data class SubTodoDb(
    @PrimaryKey
    @ColumnInfo(name = "id_subTodo") val id: Int,
    @ColumnInfo(name = "todoId_subTodo") val todoId: Int,
    @ColumnInfo(name = "title_subTodo") val title: String,
    @ColumnInfo(name = "createdAt_subTodo") val createdAt: Long,
    @ColumnInfo(name = "finished_subTodo") val finished: Boolean,
)
