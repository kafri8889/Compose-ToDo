package com.anafthdev.todo.ui.new_todo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anafthdev.todo.R
import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.foundation.uicomponent.DragHandle

@Composable
fun NewTodoScreen(
    navController: NavController,
    viewModel: NewTodoViewModel
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        DragHandle()

        Text(
            text = stringResource(id = R.string.new_todo),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(vertical = 8.dp)
        )

        HorizontalDivider()

        TitleTextField(
            title = viewModel.title,
            onValueChange = viewModel::updateTitle,
        )

        DescriptionTextField(
            description = viewModel.description,
            onValueChange = viewModel::updateDescription
        )

        CategorySelector(
            categories = viewModel.categories,
            selectedCategory = viewModel.selectedCategory,
            onCategoryChange = viewModel::updateSelectedCategory
        )
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedButton(
                shape = RoundedCornerShape(25),
                onClick = navController::popBackStack,
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(stringResource(id = R.string.cancel))
            }

            Button(
                shape = RoundedCornerShape(25),
                onClick = {
                    viewModel.save()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(stringResource(id = R.string.save))
            }
        }
    }
}

@Composable
private fun TitleTextField(
    title: String,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Text(
        text = stringResource(id = R.string.title),
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .fillMaxWidth()
    )

    TextField(
        singleLine = true,
        value = title,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(25),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(stringResource(id = R.string.add_todo_name))
        },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(FocusRequester())
            .padding(bottom = 8.dp)
    )
}

@Composable
private fun DescriptionTextField(
    description: String,
    onValueChange: (String) -> Unit
) {
    Text(
        text = stringResource(id = R.string.title),
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .fillMaxWidth()
    )

    TextField(
        minLines = 3,
        maxLines = 5,
        value = description,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(25),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(stringResource(id = R.string.add_description))
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategorySelector(
    categories: List<Category>,
    selectedCategory: Category,
    onCategoryChange: (Category) -> Unit
) {
    Text(
        text = stringResource(id = R.string.category),
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .fillMaxWidth()
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(
            items = categories,
            key = { item -> item.id }
        ) { category ->
            FilterChip(
                selected = selectedCategory.id == category.id,
                label = {
                    Text(category.name)
                },
                onClick = {
                    onCategoryChange(category)
                }
            )
        }
    }
}
