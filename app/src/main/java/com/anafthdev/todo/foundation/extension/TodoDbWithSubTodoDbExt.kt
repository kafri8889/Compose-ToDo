package com.anafthdev.todo.foundation.extension

import com.anafthdev.todo.data.model.Todo
import com.anafthdev.todo.data.model.db.relation.TodoDbWithSubTodoDb

fun TodoDbWithSubTodoDb.toTodo(): Todo {
    return Todo(
        id = todoDb.id,
        categoryId = todoDb.categoryId,
        title = todoDb.title,
        description = todoDb.description,
        createdAt = todoDb.createdAt,
        finished = todoDb.finished,
    )
}
