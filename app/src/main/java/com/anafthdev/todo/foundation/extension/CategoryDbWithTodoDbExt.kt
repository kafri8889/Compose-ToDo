package com.anafthdev.todo.foundation.extension

import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.data.model.db.relation.CategoryDbWithTodoDb

fun CategoryDbWithTodoDb.toCategory(): Category {
    return Category(
        id = categoryDb.id,
        userId = categoryDb.userId,
        name = categoryDb.name,
        todo = todoList.map { it.toTodo() }
    )
}
