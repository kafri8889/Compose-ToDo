package com.anafthdev.todo.data.datasource.local

import com.anafthdev.todo.data.model.Category

object LocalCategoryDataProvider {

    val category1 = Category(
        id = 0,
        userId = 0,
        name = "Category 1"
    )

    val category2 = Category(
        id = 1,
        userId = 0,
        name = "Category 2"
    )

    val category3 = Category(
        id = 3,
        userId = 1,
        name = "Category 3"
    )

    val values = arrayOf(
        category1,
        category2,
        category3
    )

}