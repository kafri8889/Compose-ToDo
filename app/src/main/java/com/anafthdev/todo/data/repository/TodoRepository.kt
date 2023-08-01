package com.anafthdev.todo.data.repository

import com.anafthdev.todo.data.datasource.local.dao.TodoDao
import com.anafthdev.todo.data.model.db.TodoDb
import com.anafthdev.todo.data.model.db.relation.TodoDbWithSubTodoDb
import com.anafthdev.todo.data.repository.interfaces.ITodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
): ITodoRepository {

    override fun getAllLocalTodo(): Flow<List<TodoDb>> {
        return todoDao.getAllTodo()
    }

    override fun getLocalTodoById(id: Int): Flow<TodoDb?> {
        return todoDao.getTodoById(id)
    }

    override fun getLocalTodoByCategoryId(id: Int): Flow<List<TodoDb>> {
        return todoDao.getTodoByCategoryId(id)
    }

    override fun getTodoByIdWithSubTodo(id: Int): Flow<TodoDbWithSubTodoDb?> {
        return todoDao.getTodoByIdWithSubTodo(id)
    }

    override suspend fun upsertLocalTodo(vararg todoDb: TodoDb) {
        todoDao.upsertTodo(*todoDb)
    }

    override suspend fun updateLocalTodo(vararg todoDb: TodoDb) {
        todoDao.updateTodo(*todoDb)
    }

    override suspend fun deleteLocalTodo(vararg todoDb: TodoDb) {
        todoDao.deleteTodo(*todoDb)
    }

    override suspend fun insertLocalTodo(vararg todoDb: TodoDb) {
        todoDao.insertTodo(*todoDb)
    }
}