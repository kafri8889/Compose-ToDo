package com.anafthdev.todo.domain.use_case

import com.anafthdev.todo.domain.use_case.category.GetLocalCategoryUseCase

/**
 * a set of use cases for a category
 */
data class CategoryUseCases(
    val getLocalCategoryUseCases: GetLocalCategoryUseCase
)
