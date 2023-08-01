package com.anafthdev.todo.domain.util

import com.anafthdev.todo.data.repository.interfaces.ICategoryRepository

/**
 * Method to get category, used in use case
 */
sealed class GetCategoryBy {

    /**
     * Get all category
     */
    object All: GetCategoryBy()

    /**
     * Get category by id
     */
    data class ID(val id: Int): GetCategoryBy()

    /**
     * Get category by id with to-do.
     *
     * Check [ICategoryRepository.getLocalCategoryByIdWithTodo]
     */
    data class IDWithTodo(val id: Int): GetCategoryBy()

}