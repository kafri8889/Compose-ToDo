package com.anafthdev.todo.ui.new_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.todo.data.datasource.local.LocalCategoryDataProvider
import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.data.model.Todo
import com.anafthdev.todo.domain.use_case.CategoryUseCases
import com.anafthdev.todo.domain.use_case.TodoUseCases
import com.anafthdev.todo.foundation.extension.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NewTodoViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases,
    private val categoryUseCases: CategoryUseCases
): ViewModel() {

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var selectedCategory by mutableStateOf(LocalCategoryDataProvider.notCategorized)

    val categories = mutableStateListOf<Category>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            categoryUseCases.getLocalCategoryUseCase().collect { list ->
                withContext(Dispatchers.Main) {
                    categories.swap(listOf(LocalCategoryDataProvider.notCategorized) +  list)
                }
            }
        }
    }

    fun updateTitle(s: String) {
        title = s
    }

    fun updateDescription(s: String) {
        description = s
    }

    fun updateSelectedCategory(category: Category) {
        selectedCategory = category
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            todoUseCases.insertLocalTodoUseCase(
                Todo(
                    id = Random.nextInt(),
                    title = title,
                    description = description,
                    categoryId = selectedCategory.id,
                    createdAt = System.currentTimeMillis(),
                    finished = false
                )
            )
        }
    }

}