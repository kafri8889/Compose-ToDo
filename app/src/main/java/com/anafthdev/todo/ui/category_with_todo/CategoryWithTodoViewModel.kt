package com.anafthdev.todo.ui.category_with_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.todo.data.DestinationArgument
import com.anafthdev.todo.data.datasource.local.LocalCategoryDataProvider
import com.anafthdev.todo.data.model.Todo
import com.anafthdev.todo.data.model.TodoWithCategory
import com.anafthdev.todo.domain.use_case.CategoryUseCases
import com.anafthdev.todo.domain.use_case.TodoUseCases
import com.anafthdev.todo.domain.util.GetCategoryBy
import com.anafthdev.todo.foundation.extension.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CategoryWithTodoViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases,
    private val todoUseCases: TodoUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val argumentCategoryId = savedStateHandle.getStateFlow(DestinationArgument.CATEGORY_ID, -1)

    var category by mutableStateOf(LocalCategoryDataProvider.notCategorized)

    val todoWithCategory = mutableStateListOf<TodoWithCategory>()
    val completedTodoWithCategory = mutableStateListOf<TodoWithCategory>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            argumentCategoryId.flatMapLatest { id ->
                categoryUseCases.getLocalCategoryUseCase(
                    getCategoryBy = GetCategoryBy.IDWithTodo(id)
                ).map { it[0] }
            }.collect { mCategory ->
                val todoArrayList = arrayListOf<TodoWithCategory>()
                val completedTodoArrayList = arrayListOf<TodoWithCategory>()
                for (todo in mCategory.todo) {
                    val twc = TodoWithCategory(todo, mCategory)
                    if (todo.finished) completedTodoArrayList.add(twc)
                    else todoArrayList.add(twc)
                }
                withContext(Dispatchers.Main) {
                    category = mCategory
                    todoWithCategory.swap(todoArrayList)
                    completedTodoWithCategory.swap(completedTodoArrayList)
                }
            }
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoUseCases.updateLocalTodoUseCase(todo)
        }
    }

}