package com.anafthdev.todo.data.datasource.local

import com.anafthdev.todo.data.model.Todo

/**
 * Raw data provider for [Todo]
 */
object LocalTodoDataProvider {

    val todo1 = Todo(
        id = 0,
        categoryId = 0,
        title = " todo 1",
        description = "Description 1",
        createdAt = System.currentTimeMillis(),
        finished = false,
        subTodo = listOf(
            LocalSubTodoDataProvider.subTodo1,
            LocalSubTodoDataProvider.subTodo2,
        )
    )

    val todo2 = Todo(
        id = 1,
        categoryId = 0,
        title = " todo 2",
        description = "Description 2",
        createdAt = System.currentTimeMillis(),
        finished = true,
        subTodo = listOf(
            LocalSubTodoDataProvider.subTodo3,
            LocalSubTodoDataProvider.subTodo4,
        )
    )

    val todo3 = Todo(
        id = 2,
        categoryId = 1,
        title = " todo 3",
        description = "Description 3",
        createdAt = System.currentTimeMillis(),
        finished = false
    )

    val todo4 = Todo(
        id = 3,
        categoryId = 1,
        title = " todo 4",
        description = "Description 4",
        createdAt = System.currentTimeMillis(),
        finished = true
    )

    val values = arrayOf(
        todo1,
        todo2,
        todo3,
        todo4,
    )

}