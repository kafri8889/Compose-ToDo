package com.anafthdev.todo.data.repository

import com.anafthdev.todo.data.datasource.local.dao.SubTodoDao
import com.anafthdev.todo.data.model.db.SubTodoDb
import com.anafthdev.todo.data.repository.interfaces.ISubTodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubTodoRepository @Inject constructor(
    private val subTodoDao: SubTodoDao
): ISubTodoRepository {

    override fun getAllLocalSubTodo(): Flow<List<SubTodoDb>> {
        return subTodoDao.getAllSubTodo()
    }

    override fun getLocalSubTodoById(id: Int): Flow<SubTodoDb?> {
        return subTodoDao.getSubTodoById(id)
    }

    override fun getLocalSubTodoByTodoId(id: Int): Flow<List<SubTodoDb>> {
        return subTodoDao.getSubTodoByTodoId(id)
    }

    override suspend fun upsertLocalSubTodo(vararg subTodoDb: SubTodoDb) {
        subTodoDao.upsertSubTodo(*subTodoDb)
    }

    override suspend fun updateLocalSubTodo(vararg subTodoDb: SubTodoDb) {
        subTodoDao.updateSubTodo(*subTodoDb)
    }

    override suspend fun deleteLocalSubTodo(vararg subTodoDb: SubTodoDb) {
        subTodoDao.deleteSubTodo(*subTodoDb)
    }

    override suspend fun insertLocalSubTodo(vararg subTodoDb: SubTodoDb) {
        subTodoDao.insertSubTodo(*subTodoDb)
    }

}