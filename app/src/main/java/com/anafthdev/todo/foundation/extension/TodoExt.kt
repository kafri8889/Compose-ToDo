package com.anafthdev.todo.foundation.extension

import com.anafthdev.todo.data.model.Todo
import com.anafthdev.todo.data.model.db.TodoDb

fun Todo.toTodoDb(): TodoDb {
    return TodoDb(
        id = id,
        categoryId = categoryId,
        title = title,
        description = description,
        createdAt = createdAt,
        finished = finished,
    )
}
