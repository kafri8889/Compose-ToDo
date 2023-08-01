package com.anafthdev.todo.domain.use_case.sub_todo

import com.anafthdev.todo.data.model.SubTodo
import com.anafthdev.todo.data.repository.interfaces.ISubTodoRepository
import com.anafthdev.todo.foundation.extension.toSubTodoDb

/**
 * Use case for upsert sub to-do to database
 */
class UpsertLocalSubTodoUseCase(
    private val subTodoRepository: ISubTodoRepository
) {

    suspend operator fun invoke(vararg subTodo: SubTodo) {
        subTodoRepository.upsertLocalSubTodo(
            *subTodo.map { it.toSubTodoDb() }.toTypedArray()
        )
    }

}
