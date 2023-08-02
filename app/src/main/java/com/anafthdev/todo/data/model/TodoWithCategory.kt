package com.anafthdev.todo.data.model

/**
 * Pair to-do with category by [Todo.categoryId] and [Category.id]
 */
data class TodoWithCategory(
    val todo: Todo,
    val category: Category
)