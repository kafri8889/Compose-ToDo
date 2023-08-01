package com.anafthdev.todo.domain.use_case.sub_todo

import com.anafthdev.todo.data.model.SubTodo
import com.anafthdev.todo.data.repository.interfaces.ISubTodoRepository
import com.anafthdev.todo.foundation.extension.toSubTodoDb

/**
 * Use case for update sub to-do from database
 */
class UpdateLocalSubTodoUseCase(
    private val subTodoRepository: ISubTodoRepository
) {

    suspend operator fun invoke(vararg subTodo: SubTodo) {
        subTodoRepository.updateLocalSubTodo(
            *subTodo.map { it.toSubTodoDb() }.toTypedArray()
        )
    }

}
