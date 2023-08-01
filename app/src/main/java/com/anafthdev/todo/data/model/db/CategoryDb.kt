package com.anafthdev.todo.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryDb(
    @PrimaryKey
    @ColumnInfo(name = "id_category") val id: Int,
    @ColumnInfo(name = "userId_category") val userId: Int,
    @ColumnInfo(name = "name_category") val name: String,
)
