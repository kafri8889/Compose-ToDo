package com.anafthdev.todo.data.datasource.local

import com.anafthdev.todo.data.model.SubTodo

object LocalSubTodoDataProvider {

    val subTodo1 = SubTodo(
        id = 0,
        todoId = 0,
        title = "Sub todo 1",
        createdAt = System.currentTimeMillis(),
        finished = false
    )

    val subTodo2 = SubTodo(
        id = 1,
        todoId = 0,
        title = "Sub todo 2",
        createdAt = System.currentTimeMillis(),
        finished = true
    )

    val subTodo3 = SubTodo(
        id = 2,
        todoId = 1,
        title = "Sub todo 3",
        createdAt = System.currentTimeMillis(),
        finished = false
    )

    val subTodo4 = SubTodo(
        id = 3,
        todoId = 1,
        title = "Sub todo 4",
        createdAt = System.currentTimeMillis(),
        finished = true
    )

    val values = arrayOf(
        subTodo1,
        subTodo2,
        subTodo3,
        subTodo4,
    )

}