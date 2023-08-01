package com.anafthdev.todo.domain.util

import com.anafthdev.todo.data.repository.interfaces.ITodoRepository

/**
 * Method to get to-do, used in use case
 */
sealed class GetTodoBy {

    /**
     * Get all to-do
     */
    object All: GetTodoBy()

    /**
     * Get to-do by id
     */
    data class ID(val id: Int): GetTodoBy()

    /**
     * Get to-do by category id
     */
    data class CategoryId(val id: Int): GetTodoBy()

    /**
     * Get to-do by id with sub to-do
     *
     * Check [ITodoRepository.getLocalTodoByIdWithSubTodo]
     */
    data class IDWithSubTodo(val id: Int): GetTodoBy()

}