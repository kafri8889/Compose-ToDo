package com.anafthdev.todo.domain.use_case.sub_todo

import com.anafthdev.todo.data.model.SubTodo
import com.anafthdev.todo.data.repository.interfaces.ISubTodoRepository
import com.anafthdev.todo.foundation.extension.toSubTodoDb

/**
 * Use case for delete sub to-do from database
 */
class DeleteLocalSubTodoUseCase(
    private val subTodoRepository: ISubTodoRepository
) {

    suspend operator fun invoke(vararg subTodo: SubTodo) {
        subTodoRepository.deleteLocalSubTodo(
            *subTodo.map { it.toSubTodoDb() }.toTypedArray()
        )
    }

}
