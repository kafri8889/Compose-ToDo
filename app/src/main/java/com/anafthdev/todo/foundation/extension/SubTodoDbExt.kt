package com.anafthdev.todo.foundation.extension

import com.anafthdev.todo.data.model.SubTodo
import com.anafthdev.todo.data.model.db.SubTodoDb

fun SubTodoDb.toSubTodo(): SubTodo {
    return SubTodo(
        id = id,
        todoId = todoId,
        title = title,
        createdAt = createdAt,
        finished = finished,
    )
}
