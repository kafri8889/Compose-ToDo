package com.anafthdev.todo.foundation.extension

import com.anafthdev.todo.data.model.SubTodo
import com.anafthdev.todo.data.model.db.SubTodoDb

fun SubTodo.toSubTodoDb(): SubTodoDb {
    return SubTodoDb(
        id = id,
        todoId = todoId,
        title = title,
        createdAt = createdAt,
        finished = finished
    )
}
