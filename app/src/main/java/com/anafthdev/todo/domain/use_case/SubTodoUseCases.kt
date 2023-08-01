package com.anafthdev.todo.domain.use_case

import com.anafthdev.todo.domain.use_case.sub_todo.DeleteLocalSubTodoUseCase
import com.anafthdev.todo.domain.use_case.sub_todo.GetLocalSubTodoUseCase
import com.anafthdev.todo.domain.use_case.sub_todo.InsertLocalSubTodoUseCase
import com.anafthdev.todo.domain.use_case.sub_todo.UpdateLocalSubTodoUseCase
import com.anafthdev.todo.domain.use_case.sub_todo.UpsertLocalSubTodoUseCase

/**
 * a set of use cases for sub to-do
 */
data class SubTodoUseCases(
    val getLocalSubTodoUseCase: GetLocalSubTodoUseCase,
    val upsertLocalSubTodoUseCase: UpsertLocalSubTodoUseCase,
    val updateLocalSubTodoUseCase: UpdateLocalSubTodoUseCase,
    val deleteLocalSubTodoUseCase: DeleteLocalSubTodoUseCase,
    val insertLocalSubTodoUseCase: InsertLocalSubTodoUseCase
)
