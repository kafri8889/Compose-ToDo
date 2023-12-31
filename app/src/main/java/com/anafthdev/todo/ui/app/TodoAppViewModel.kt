package com.anafthdev.todo.ui.app

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.todo.data.datasource.local.LocalCategoryDataProvider
import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.domain.use_case.CategoryUseCases
import com.anafthdev.todo.domain.util.GetCategoryBy
import com.anafthdev.todo.foundation.extension.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TodoAppViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
): ViewModel() {

    val categories = mutableStateListOf<Category>()

    init {
        // Insert default category
        viewModelScope.launch(Dispatchers.IO) {
            categoryUseCases.insertLocalCategoryUseCase(LocalCategoryDataProvider.notCategorized)
        }

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
}