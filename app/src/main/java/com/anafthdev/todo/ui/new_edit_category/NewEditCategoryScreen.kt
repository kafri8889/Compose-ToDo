package com.anafthdev.todo.ui.new_edit_category

import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.anafthdev.todo.data.CategoryColors
import com.anafthdev.todo.data.model.CategoryColor
import com.anafthdev.todo.foundation.uicomponent.DragHandle

@Composable
fun NewEditCategoryScreen(
    navController: NavController,
    viewModel: NewEditCategoryViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        DragHandle()

        Text(
            text = stringResource(
                id = if (viewModel.isEdit) R.string.edit_category
                else R.string.new_category
            ),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(vertical = 8.dp)
        )

        HorizontalDivider()

        NameTextField(
            name = viewModel.name,
            onValueChange = viewModel::updateName,
        )

        CategoryColorSelector(
            selectedColor = viewModel.color,
            onColorChanged = viewModel::updateColor
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
                enabled = viewModel.name.isNotBlank(),
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
private fun NameTextField(
    name: String,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Text(
        text = stringResource(id = R.string.name),
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .fillMaxWidth()
    )

    TextField(
        singleLine = true,
        value = name,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(25),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.clearFocus()
            }
        ),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(stringResource(id = R.string.add_category_name))
        },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(FocusRequester())
            .padding(bottom = 8.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CategoryColorSelector(
    selectedColor: CategoryColor,
    onColorChanged: (CategoryColor) -> Unit
) {
    Text(
        text = stringResource(id = R.string.color),
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .fillMaxWidth()
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (color in CategoryColors.valuesLight) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(color.primary)
                    .clickable {
                        onColorChanged(color)
                    }
            ) {
                androidx.compose.animation.AnimatedVisibility(
                    visible = color.id == selectedColor.id,
                    enter = scaleIn(tween(256)),
                    exit = scaleOut(tween(256))
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(color.onPrimary)
                    )
                }
            }
        }
    }
}
