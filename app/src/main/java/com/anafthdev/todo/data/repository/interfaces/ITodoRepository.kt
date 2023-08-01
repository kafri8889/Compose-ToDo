package com.anafthdev.todo.data.repository.interfaces

import com.anafthdev.todo.data.datasource.local.dao.TodoDao
import com.anafthdev.todo.data.model.db.SubTodoDb
import com.anafthdev.todo.data.model.db.TodoDb
import com.anafthdev.todo.data.model.db.relation.TodoDbWithSubTodoDb
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {

    /**
     * Get all to-do from database
     */
    fun getAllLocalTodo(): Flow<List<TodoDb>>

    /**
     * Get to-do from database by id
     */
    fun getLocalTodoById(id: Int): Flow<TodoDb?>

    /**
     * Get to-do from database by category id
     */
    fun getLocalTodoByCategoryId(id: Int): Flow<List<TodoDb>>

    /**
     * Get to-do from database by id, and also sub to-do if [SubTodoDb.todoId] equals [TodoDb.id].
     *
     * Check [TodoDao.getTodoByIdWithSubTodo]
     */
    fun getLocalTodoByIdWithSubTodo(id: Int): Flow<TodoDbWithSubTodoDb?>

    /**
     * Update to-do, if to-do not exists, insert
     */
    suspend fun upsertLocalTodo(vararg todoDb: TodoDb)

    /**
     * Update to-do
     */
    suspend fun updateLocalTodo(vararg todoDb: TodoDb)

    /**
     * Delete to-do
     */
    suspend fun deleteLocalTodo(vararg todoDb: TodoDb)

    /**
     * Insert to-do, if exists, replace
     */
    suspend fun insertLocalTodo(vararg todoDb: TodoDb)

}
