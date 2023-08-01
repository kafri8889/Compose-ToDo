package com.anafthdev.todo.data.repository.interfaces

import com.anafthdev.todo.data.model.db.CategoryDb
import com.anafthdev.todo.data.model.db.relation.CategoryDbWithTodoDb
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {

    fun getAllLocalCategory(): Flow<List<CategoryDb>>

    fun getLocalCategoryById(id: Int): Flow<CategoryDb?>

    fun getLocalCategoryByUserId(id: Int): Flow<List<CategoryDb>>

    fun getCategoryByIdWithTodo(id: Int): Flow<CategoryDbWithTodoDb?>

    suspend fun upsertLocalCategory(vararg categoryDb: CategoryDb)

    suspend fun updateLocalCategory(vararg categoryDb: CategoryDb)

    suspend fun deleteLocalCategory(vararg categoryDb: CategoryDb)

    suspend fun insertLocalCategory(vararg categoryDb: CategoryDb)

}