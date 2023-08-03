package com.anafthdev.todo.ui.new_edit_category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.todo.data.CategoryColors
import com.anafthdev.todo.data.DestinationArgument
import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.data.model.CategoryColor
import com.anafthdev.todo.domain.use_case.CategoryUseCases
import com.anafthdev.todo.domain.util.GetCategoryBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class NewEditCategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val argumentCategoryId = savedStateHandle.getStateFlow(DestinationArgument.CATEGORY_ID, -1)
    private val argumentIsEdit = savedStateHandle.getStateFlow(DestinationArgument.IS_EDIT, false)

    var name by mutableStateOf("")
    var color by mutableStateOf(CategoryColors.color_1_light)
    var isEdit by mutableStateOf(false)

    private var categoryToEdit: Category? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            argumentCategoryId.flatMapLatest { id ->
                // Get category from database using id
                categoryUseCases.getLocalCategoryUseCase(
                    getCategoryBy = GetCategoryBy.ID(id)
                ).map { it[0] }
            }.combine(argumentIsEdit) { category, edit ->
                // Combine category and isEdit
                category to edit
            }.collect { (category, edit) ->
                withContext(Dispatchers.Main) {
                    isEdit = edit
                    categoryToEdit = category
                    if (edit) {
                        name = category.name
                        color = category.color
                    }
                }
            }
        }
    }

    fun updateName(s: String) {
        name = s
    }

    fun updateColor(c: CategoryColor) {
        color = c
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            if (isEdit) {
                categoryUseCases.updateLocalCategoryUseCase(
                    categoryToEdit!!.copy(
                        name = name,
                        color = color
                    )
                )

                return@launch
            }

            categoryUseCases.insertLocalCategoryUseCase(
                Category(
                    id = Random.nextInt(),
                    name = name,
                    color = color
                )
            )
        }
    }

}