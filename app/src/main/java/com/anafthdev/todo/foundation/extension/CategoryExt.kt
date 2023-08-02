package com.anafthdev.todo.foundation.extension

import com.anafthdev.todo.data.model.Category
import com.anafthdev.todo.data.model.db.CategoryDb

fun Category.toCategoryDb(): CategoryDb {
    return CategoryDb(
        id = id,
        name = name,
        colorId = color.id
    )
}
