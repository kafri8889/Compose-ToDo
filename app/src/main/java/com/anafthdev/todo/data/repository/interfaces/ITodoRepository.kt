package com.anafthdev.todo.data.repository.interfaces

import com.anafthdev.todo.data.model.db.TodoDb
import com.anafthdev.todo.data.model.db.relation.TodoDbWithSubTodoDb
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {

    fun getAllLocalTodo(): Flow<List<TodoDb>>

    fun getLocalTodoById(id: Int): Flow<TodoDb?>

    fun getLocalTodoByCategoryId(id: Int): Flow<List<TodoDb>>

    fun getTodoByIdWithSubTodo(id: Int): Flow<TodoDbWithSubTodoDb?>

    suspend fun upsertLocalTodo(vararg todoDb: TodoDb)

    suspend fun updateLocalTodo(vararg todoDb: TodoDb)

    suspend fun deleteLocalTodo(vararg todoDb: TodoDb)

    suspend fun insertLocalTodo(vararg todoDb: TodoDb)

}
