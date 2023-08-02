package com.anafthdev.todo.foundation.extension

import com.anafthdev.todo.data.CategoryColors
import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.data.model.db.CategoryDb

fun CategoryDb.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        color = CategoryColors.fromIdentifier(colorId)
    )
}
