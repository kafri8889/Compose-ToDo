package com.anafthdev.todo.data.repository.interfaces

import com.anafthdev.todo.data.model.db.SubTodoDb
import kotlinx.coroutines.flow.Flow

interface ISubTodoRepository {

    /**
     * Get all sub to-do from database
     */
    fun getAllLocalSubTodo(): Flow<List<SubTodoDb>>

    /**
     * Get sub to-do from database by id
     */
    fun getLocalSubTodoById(id: Int): Flow<SubTodoDb?>

    /**
     * Get sub to-do from database by to-do id
     */
    fun getLocalSubTodoByTodoId(id: Int): Flow<List<SubTodoDb>>

    /**
     * Update sub to-do, if sub to-do not exists, insert
     */
    suspend fun upsertLocalSubTodo(vararg subTodoDb: SubTodoDb)

    /**
     * Update sub to-do
     */
    suspend fun updateLocalSubTodo(vararg subTodoDb: SubTodoDb)

    /**
     * Delete sub to-do
     */
    suspend fun deleteLocalSubTodo(vararg subTodoDb: SubTodoDb)

    /**
     * Insert sub to-do, if exists, replace
     */
    suspend fun insertLocalSubTodo(vararg subTodoDb: SubTodoDb)

}