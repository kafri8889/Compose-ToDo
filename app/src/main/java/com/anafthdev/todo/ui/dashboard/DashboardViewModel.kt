package com.anafthdev.todo.ui.dashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.todo.data.datasource.local.LocalCategoryDataProvider
import com.anafthdev.todo.data.model.TodoWithCategory
import com.anafthdev.todo.domain.use_case.CategoryUseCases
import com.anafthdev.todo.domain.use_case.TodoUseCases
import com.anafthdev.todo.foundation.extension.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases,
    private val todoUseCases: TodoUseCases
): ViewModel() {

    val todoWithCategory = mutableStateListOf<TodoWithCategory>()

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            categoryUseCases.getLocalCategoryUseCase().collect { categoryList ->
//                withContext(Dispatchers.Main) {
//                    categories.swap(categoryList)
//                }
//            }
//        }
//
//        viewModelScope.launch(Dispatchers.IO) {
//            todoUseCases.getLocalTodoUseCase().collect { list ->
//                withContext(Dispatchers.Main) {
//                    todoList.swap(list)
//                }
//            }
//        }

        viewModelScope.launch(Dispatchers.IO) {
            todoUseCases.getLocalTodoUseCase().combine(
                categoryUseCases.getLocalCategoryUseCase()
            ) { todoList, categoryList ->
                todoList to categoryList
            }.collect { (todoList, categoryList) ->
                val todoArrayList = arrayListOf<TodoWithCategory>()
                for (todo in todoList) {
                    val category = categoryList.find { it.id == todo.categoryId } ?: LocalCategoryDataProvider.notCategorized
                    todoArrayList.add(TodoWithCategory(todo, category))
                }

                withContext(Dispatchers.Main) { todoWithCategory.swap(todoArrayList) }
            }
        }
    }

}