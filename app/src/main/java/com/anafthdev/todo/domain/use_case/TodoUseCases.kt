package com.anafthdev.todo.domain.use_case

import com.anafthdev.todo.domain.use_case.todo.DeleteLocalTodoUseCase
import com.anafthdev.todo.domain.use_case.todo.GetLocalTodoUseCase
import com.anafthdev.todo.domain.use_case.todo.InsertLocalTodoUseCase
import com.anafthdev.todo.domain.use_case.todo.UpdateLocalTodoUseCase
import com.anafthdev.todo.domain.use_case.todo.UpsertLocalTodoUseCase

/**
 * a set of use cases for to-do
 */
data class TodoUseCases(
    val getLocalTodoUseCase: GetLocalTodoUseCase,
    val upsertLocalTodoUseCase: UpsertLocalTodoUseCase,
    val updateLocalTodoUseCase: UpdateLocalTodoUseCase,
    val deleteLocalTodoUseCase: DeleteLocalTodoUseCase,
    val insertLocalTodoUseCase: InsertLocalTodoUseCase
)
