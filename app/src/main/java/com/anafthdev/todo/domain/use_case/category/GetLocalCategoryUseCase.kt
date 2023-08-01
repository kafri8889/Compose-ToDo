package com.anafthdev.todo.domain.use_case.category

import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.data.repository.interfaces.ICategoryRepository
import com.anafthdev.todo.domain.util.GetCategoryBy
import com.anafthdev.todo.foundation.extension.toCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

/**
 * Use case for get category from database
 */
class GetLocalCategoryUseCase(
    private val categoryRepository: ICategoryRepository
) {

    /**
     * @param getCategoryBy method to get categories
     */
    operator fun invoke(
        getCategoryBy: GetCategoryBy = GetCategoryBy.All
    ): Flow<List<Category>> {
        return when (getCategoryBy) {
            is GetCategoryBy.All -> categoryRepository.getAllLocalCategory()
                .map { it.map { it.toCategory() } }
            is GetCategoryBy.ID -> categoryRepository.getLocalCategoryById(getCategoryBy.id)
                .filterNotNull()
                .map { listOf(it.toCategory()) }
            is GetCategoryBy.IDWithTodo -> categoryRepository.getLocalCategoryByIdWithTodo(getCategoryBy.id)
                .filterNotNull()
                .map { listOf(it.toCategory()) }
        }
    }

}