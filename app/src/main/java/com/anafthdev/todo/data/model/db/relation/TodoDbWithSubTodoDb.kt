package com.anafthdev.todo.data.model.db.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.anafthdev.todo.data.model.db.SubTodoDb
import com.anafthdev.todo.data.model.db.TodoDb

data class TodoDbWithSubTodoDb(
    @Embedded
    val todoDb: TodoDb,

    @Relation(
        entity = SubTodoDb::class,
        parentColumn = "id_todo",
        entityColumn = "todoId_subTodo"
    )
    val subTodoDbs: List<SubTodoDb>
)
