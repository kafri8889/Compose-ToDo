package com.anafthdev.todo.data.repository.interfaces

import com.anafthdev.todo.data.model.db.SubTodoDb
import kotlinx.coroutines.flow.Flow

interface ISubTodoRepository {

    fun getAllLocalSubTodo(): Flow<List<SubTodoDb>>

    fun getLocalSubTodoById(id: Int): Flow<SubTodoDb?>

    fun getLocalSubTodoByTodoId(id: Int): Flow<List<SubTodoDb>>

    suspend fun upsertLocalSubTodo(vararg subTodoDb: SubTodoDb)

    suspend fun updateLocalSubTodo(vararg subTodoDb: SubTodoDb)

    suspend fun deleteLocalSubTodo(vararg subTodoDb: SubTodoDb)

    suspend fun insertLocalSubTodo(vararg subTodoDb: SubTodoDb)

}