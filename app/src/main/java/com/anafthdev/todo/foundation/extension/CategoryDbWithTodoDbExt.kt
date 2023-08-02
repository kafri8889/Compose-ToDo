package com.anafthdev.todo.foundation.extension

import com.anafthdev.todo.data.CategoryColors
import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.data.model.db.relation.CategoryDbWithTodoDb

fun CategoryDbWithTodoDb.toCategory(): Category {
    return Category(
        id = categoryDb.id,
        name = categoryDb.name,
        color = CategoryColors.fromIdentifier(categoryDb.colorId),
        todo = todoList.map { it.toTodo() }
    )
}
