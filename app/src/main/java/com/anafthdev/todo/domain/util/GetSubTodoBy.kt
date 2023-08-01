package com.anafthdev.todo.domain.util

/**
 * Method to get sub to-do, used in use case
 */
sealed class GetSubTodoBy {

    /**
     * Get all sub to-do
     */
    object All: GetSubTodoBy()

    /**
     * Get sub to-do by id
     */
    data class ID(val id: Int): GetSubTodoBy()

    /**
     * Get sub to-do by to-do id
     */
    data class TodoId(val id: Int): GetSubTodoBy()

}