package com.anafthdev.todo.domain.use_case.todo

import com.anafthdev.todo.data.model.Todo
import com.anafthdev.todo.data.repository.interfaces.ITodoRepository
import com.anafthdev.todo.domain.util.GetTodoBy
import com.anafthdev.todo.foundation.extension.toTodo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

/**
 * Use case for get to-do from database
 */
class GetLocalTodoUseCase(
    private val todoRepository: ITodoRepository
) {

    /**
     * @param getTodoBy method to get to-do
     * @return list of to-do, if [getTodoBy] is [GetTodoBy.ID] or [GetTodoBy.IDWithSubTodo], returned single item `todoList[0]`
     */
    operator fun invoke(
        getTodoBy: GetTodoBy = GetTodoBy.All
    ): Flow<List<Todo>> {
        return when (getTodoBy) {
            is GetTodoBy.CategoryId -> todoRepository.getLocalTodoByCategoryId(getTodoBy.id)
                .filterNotNull()
                .map { it.map { it.toTodo() } }
            is GetTodoBy.IDWithSubTodo -> todoRepository.getLocalTodoByIdWithSubTodo(getTodoBy.id)
                .filterNotNull()
                .map { listOf(it.toTodo()) }
            is GetTodoBy.ID -> todoRepository.getLocalTodoById(getTodoBy.id)
                .filterNotNull()
                .map { listOf(it.toTodo()) }
            GetTodoBy.All -> todoRepository.getAllLocalTodo()
                .map { it.map { it.toTodo() } }
        }
    }

}