package com.anafthdev.todo.domain.use_case.category

import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.data.repository.interfaces.ICategoryRepository
import com.anafthdev.todo.foundation.extension.toCategoryDb

/**
 * Use case for delete category from database
 */
class DeleteLocalCategoryUseCase(
    private val categoryRepository: ICategoryRepository
) {

    suspend operator fun invoke(vararg category: Category) {
        categoryRepository.deleteLocalCategory(
            *category.map { it.toCategoryDb() }.toTypedArray()
        )
    }

}
