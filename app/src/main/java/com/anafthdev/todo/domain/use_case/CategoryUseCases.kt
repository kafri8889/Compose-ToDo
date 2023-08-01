package com.anafthdev.todo.domain.use_case

import com.anafthdev.todo.domain.use_case.category.DeleteLocalCategoryUseCase
import com.anafthdev.todo.domain.use_case.category.GetLocalCategoryUseCase
import com.anafthdev.todo.domain.use_case.category.InsertLocalCategoryUseCase
import com.anafthdev.todo.domain.use_case.category.UpdateLocalCategoryUseCase
import com.anafthdev.todo.domain.use_case.category.UpsertLocalCategoryUseCase

/**
 * a set of use cases for a category
 */
data class CategoryUseCases(
    val getLocalCategoryUseCases: GetLocalCategoryUseCase,
    val upsertLocalCategoryUseCase: UpsertLocalCategoryUseCase,
    val updateLocalCategoryUseCase: UpdateLocalCategoryUseCase,
    val deleteLocalCategoryUseCase: DeleteLocalCategoryUseCase,
    val insertLocalCategoryUseCase: InsertLocalCategoryUseCase
)
