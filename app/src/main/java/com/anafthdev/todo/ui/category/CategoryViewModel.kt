package com.anafthdev.todo.ui.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.todo.data.datasource.local.LocalCategoryDataProvider
import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.domain.use_case.CategoryUseCases
import com.anafthdev.todo.domain.use_case.TodoUseCases
import com.anafthdev.todo.domain.util.GetCategoryBy
import com.anafthdev.todo.domain.util.GetTodoBy
import com.anafthdev.todo.foundation.extension.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases,
    private val todoUseCases: TodoUseCases
): ViewModel() {

    var categoryToDelete by mutableStateOf<Category?>(null)

    val categories = mutableStateListOf<Category>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            categoryUseCases.getLocalCategoryUseCase(
                getCategoryBy = GetCategoryBy.AllWithTodo
            ).collect { list ->
                withContext(Dispatchers.Main) {
                    categories.swap(list)
                }
            }
        }
    }

    fun deleteCategory() {
        categoryToDelete?.let { categoryToDelete ->
            viewModelScope.launch(Dispatchers.IO) {
                val todoList = todoUseCases.getLocalTodoUseCase(
                    getTodoBy = GetTodoBy.CategoryId(categoryToDelete.id)
                ).firstOrNull()

                todoList?.map { it.copy(categoryId = LocalCategoryDataProvider.notCategorized.id) }?.toTypedArray()?.let { arr ->
                    todoUseCases.updateLocalTodoUseCase(*arr)
                }

                categoryUseCases.deleteLocalCategoryUseCase(categoryToDelete)
                withContext(Dispatchers.Main) {
                    this@CategoryViewModel.categoryToDelete = null
                }
            }
        }
    }

}