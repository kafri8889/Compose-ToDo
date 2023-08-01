package com.anafthdev.todo.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoDb(
    @PrimaryKey
    @ColumnInfo(name = "id_todo") val id: Int,
    @ColumnInfo(name = "categoryId_todo") val categoryId: Int,
    @ColumnInfo(name = "title_todo") val title: String,
    @ColumnInfo(name = "description_todo") val description: String,
    @ColumnInfo(name = "createdAt_todo") val createdAt: Long,
    @ColumnInfo(name = "finished_todo") val finished: Boolean,
)
