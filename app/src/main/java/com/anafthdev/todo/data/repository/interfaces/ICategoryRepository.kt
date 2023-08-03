package com.anafthdev.todo.data.repository.interfaces

import com.anafthdev.todo.data.datasource.local.dao.CategoryDao
import com.anafthdev.todo.data.model.db.CategoryDb
import com.anafthdev.todo.data.model.db.TodoDb
import com.anafthdev.todo.data.model.db.relation.CategoryDbWithTodoDb
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {

    /**
     * Get all category from database
     */
    fun getAllLocalCategory(): Flow<List<CategoryDb>>

    /**
     * Get category from database by id
     */
    fun getLocalCategoryById(id: Int): Flow<CategoryDb?>

    /**
     * Get all category from database with to-do
     */
    fun getAllLocalCategoryWithTodo(): Flow<List<CategoryDbWithTodoDb>>

    /**
     * Get category from database by id, and also to-do if [TodoDb.categoryId] equals [CategoryDb.id].
     *
     * Check [CategoryDao.getCategoryByIdWithTodo]
     */
    fun getLocalCategoryByIdWithTodo(id: Int): Flow<CategoryDbWithTodoDb?>

    /**
     * Update category, if category not exists, insert
     */
    suspend fun upsertLocalCategory(vararg categoryDb: CategoryDb)

    /**
     * Update category
     */
    suspend fun updateLocalCategory(vararg categoryDb: CategoryDb)

    /**
     * Delete category
     */
    suspend fun deleteLocalCategory(vararg categoryDb: CategoryDb)

    /**
     * Insert category, if exists, replace
     */
    suspend fun insertLocalCategory(vararg categoryDb: CategoryDb)

}