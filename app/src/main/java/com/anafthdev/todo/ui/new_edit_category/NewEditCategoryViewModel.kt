package com.anafthdev.todo.ui.new_edit_category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.todo.data.CategoryColors
import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.data.model.CategoryColor
import com.anafthdev.todo.domain.use_case.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NewEditCategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
): ViewModel() {

    var name by mutableStateOf("")
    var color by mutableStateOf(CategoryColors.color_1_light)

    fun updateName(s: String) {
        name = s
    }

    fun updateColor(c: CategoryColor) {
        color = c
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
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