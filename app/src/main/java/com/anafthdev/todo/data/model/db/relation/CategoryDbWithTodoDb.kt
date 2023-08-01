package com.anafthdev.todo.data.model.db.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.anafthdev.todo.data.model.db.CategoryDb
import com.anafthdev.todo.data.model.db.TodoDb

data class CategoryDbWithTodoDb(
    @Embedded
    val categoryDb: CategoryDb,

    @Relation(
        entity = TodoDb::class,
        entityColumn = "categoryId_todo",
        parentColumn = "id_category"
    )
    val todoList: List<TodoDb>
)
