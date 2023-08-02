package com.anafthdev.todo.foundation.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.anafthdev.todo.theme.ToDoTheme

class CheckablePreviewParameter: PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(true, false)
}

@Preview
@Composable
private fun TodoCheckboxPreview(
    @PreviewParameter(CheckablePreviewParameter::class) checked: Boolean
) {
    ToDoTheme {
        TodoCheckbox(
            checked = checked,
            onCheckedChange = {}
        )
    }
}

@Composable
fun TodoCheckbox(
    checked: Boolean,
    modifier: Modifier = Modifier,
    checkedContainerColor: Color = MaterialTheme.colorScheme.primary,
    onCheckedChange: (Boolean) -> Unit,
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .minimumInteractiveComponentSize()
            .toggleable(
                value = checked,
                role = Role.Checkbox,
                onValueChange = onCheckedChange
            )
            .sizeIn(minWidth = 24.dp, minHeight = 24.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = if (checked) Color.Transparent else MaterialTheme.colorScheme.outline,
                shape = CircleShape
            )
            .background(if (checked) checkedContainerColor else Color.Transparent)
            .then(modifier)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    }
}
