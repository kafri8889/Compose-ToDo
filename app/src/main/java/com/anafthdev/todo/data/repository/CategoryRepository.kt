package com.anafthdev.todo.data.repository

import com.anafthdev.todo.data.datasource.local.dao.CategoryDao
import com.anafthdev.todo.data.model.db.CategoryDb
import com.anafthdev.todo.data.model.db.relation.CategoryDbWithTodoDb
import com.anafthdev.todo.data.repository.interfaces.ICategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
): ICategoryRepository {

    override fun getAllLocalCategory(): Flow<List<CategoryDb>> {
        return categoryDao.getAllCategory()
    }

    override fun getLocalCategoryById(id: Int): Flow<CategoryDb?> {
        return categoryDao.getCategoryById(id)
    }

    override fun getLocalCategoryByIdWithTodo(id: Int): Flow<CategoryDbWithTodoDb?> {
        return categoryDao.getCategoryByIdWithTodo(id)
    }

    override suspend fun upsertLocalCategory(vararg categoryDb: CategoryDb) {
        categoryDao.upsertCategory(*categoryDb)
    }

    override suspend fun updateLocalCategory(vararg categoryDb: CategoryDb) {
        categoryDao.updateCategory(*categoryDb)
    }

    override suspend fun deleteLocalCategory(vararg categoryDb: CategoryDb) {
        categoryDao.deleteCategory(*categoryDb)
    }

    override suspend fun insertLocalCategory(vararg categoryDb: CategoryDb) {
        categoryDao.insertCategory(*categoryDb)
    }
}