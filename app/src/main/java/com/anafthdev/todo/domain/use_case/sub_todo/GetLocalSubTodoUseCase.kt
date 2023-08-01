package com.anafthdev.todo.domain.use_case.sub_todo

import com.anafthdev.todo.data.model.SubTodo
import com.anafthdev.todo.data.repository.interfaces.ISubTodoRepository
import com.anafthdev.todo.domain.util.GetSubTodoBy
import com.anafthdev.todo.foundation.extension.toSubTodo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

/**
 * Use case for get sub to-do from database
 */
class GetLocalSubTodoUseCase(
    private val subTodoRepository: ISubTodoRepository
) {

    /**
     * @param getSubTodoBy method to get categories
     * @return list of sub to-do, if [getSubTodoBy] is [GetSubTodoBy.ID], returned single item `subTodoList[0]`
     */
    operator fun invoke(
        getSubTodoBy: GetSubTodoBy = GetSubTodoBy.All
    ): Flow<List<SubTodo>> {
        return when (getSubTodoBy) {
            is GetSubTodoBy.TodoId -> subTodoRepository.getLocalSubTodoByTodoId(getSubTodoBy.id)
                .filterNotNull()
                .map { it.map { it.toSubTodo() } }
            is GetSubTodoBy.ID -> subTodoRepository.getLocalSubTodoById(getSubTodoBy.id)
                .filterNotNull()
                .map { listOf(it.toSubTodo()) }
            GetSubTodoBy.All -> subTodoRepository.getAllLocalSubTodo()
                .map { it.map { it.toSubTodo() } }
        }
    }

}