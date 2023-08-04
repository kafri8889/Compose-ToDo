package com.anafthdev.todo.foundation.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anafthdev.todo.data.datasource.local.LocalCategoryDataProvider
import com.anafthdev.todo.data.datasource.local.LocalTodoDataProvider
import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.theme.ToDoTheme

@Preview
@Composable
private fun CategoryItemPreview() {
    ToDoTheme {
        CategoryItem(
            category = LocalCategoryDataProvider.category1.copy(
                todo = LocalTodoDataProvider.values.toList()
            )
        )
    }
}

@Composable
fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .clip(CardDefaults.shape)
            .then(modifier)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(24))
                    .border(
                        width = 4.dp,
                        color = category.color.primary,
                        shape = RoundedCornerShape(8)
                    )
            )

            Text(
                text = category.name,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .weight(1f)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(24))
                    .background(category.color.surface)
            ) {
                Text(
                    text = category.todo.size.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
