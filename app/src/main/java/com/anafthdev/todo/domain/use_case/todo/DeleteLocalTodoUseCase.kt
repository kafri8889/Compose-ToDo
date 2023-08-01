package com.anafthdev.todo.domain.use_case.todo

import com.anafthdev.todo.data.model.Todo
import com.anafthdev.todo.data.repository.interfaces.ITodoRepository
import com.anafthdev.todo.foundation.extension.toTodoDb

/**
 * Use case for delete to-do from database
 */
class DeleteLocalTodoUseCase(
    private val todoRepository: ITodoRepository
) {

    suspend operator fun invoke(vararg todo: Todo) {
        todoRepository.deleteLocalTodo(
            *todo.map { it.toTodoDb() }.toTypedArray()
        )
    }

}
