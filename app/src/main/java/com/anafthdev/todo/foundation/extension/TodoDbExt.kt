package com.anafthdev.todo.foundation.extension

import com.anafthdev.todo.data.model.Todo
import com.anafthdev.todo.data.model.db.TodoDb

fun TodoDb.toTodo(): Todo {
    return Todo(
        id = id,
        categoryId = categoryId,
        title = title,
        description = description,
        createdAt = createdAt,
        finished = finished,
    )
}