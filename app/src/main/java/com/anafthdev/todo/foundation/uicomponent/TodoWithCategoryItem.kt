package com.anafthdev.todo.foundation.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anafthdev.datemodule.extension.isToday
import com.anafthdev.todo.R
import com.anafthdev.todo.data.datasource.local.LocalCategoryDataProvider
import com.anafthdev.todo.data.datasource.local.LocalTodoDataProvider
import com.anafthdev.todo.data.model.CategoryColor
import com.anafthdev.todo.data.model.Todo
import com.anafthdev.todo.data.model.TodoWithCategory
import com.anafthdev.todo.foundation.common.Formatter
import com.anafthdev.todo.theme.ToDoTheme

@Preview
@Composable
private fun TodoWithCategoryItemPreview() {
    ToDoTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TodoWithCategoryItem(
                todoWithCategory = TodoWithCategory(
                    todo = LocalTodoDataProvider.todo1,
                    category = LocalCategoryDataProvider.category1
                ),
                onCheckedChange = {},
                onClick = {}
            )

            TodoWithCategoryItem(
                todoWithCategory = TodoWithCategory(
                    todo = LocalTodoDataProvider.todo2,
                    category = LocalCategoryDataProvider.category1
                ),
                onCheckedChange = {},
                onClick = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoWithCategoryItem(
    todoWithCategory: TodoWithCategory,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(25)
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.9f)
            ) {
                TopContent(
                    finished = todoWithCategory.todo.finished,
                    checked = todoWithCategory.todo.finished,
                    title = todoWithCategory.todo.title,
                    description = todoWithCategory.todo.description,
                    onCheckedChange = onCheckedChange,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )

                HorizontalDivider()

                BottomContent(
                    color = todoWithCategory.category.color,
                    todo = todoWithCategory.todo,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun TopContent(
    checked: Boolean,
    finished: Boolean,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textDecoration = if (finished) TextDecoration.LineThrough else null,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Text(
                text = description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        TodoCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
private fun BottomContent(
    color: CategoryColor,
    todo: Todo,
    modifier: Modifier = Modifier
) {
    val date = buildString {
        append(if (todo.createdAt.isToday()) stringResource(id = R.string.today) else Formatter.mediumDateFormatter.format(todo.createdAt))
        append("  ")
        append(Formatter.hourMinuteFormatter.format(todo.createdAt))
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.bodySmall
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .minimumInteractiveComponentSize()
                .clip(CircleShape)
                .background(color.secondary)
        ) {
            Text(
                text = stringResource(
                    id = R.string.n_sub_todo,
                    todo.subTodo.size
                ),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSecondary
                ),
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}
