package com.anafthdev.todo.data.datasource.local

import com.anafthdev.todo.data.CategoryColors
import com.anafthdev.todo.data.model.Category

/**
 * Raw data provider for [Category]
 */
object LocalCategoryDataProvider {

    /**
     * Used for uncategorized todos
     *
     * For example, a user deletes a category, the to-do that uses that category will be changed to Uncategorized To-do
     */
    val notCategorized = Category(
        id = -1,
        color = CategoryColors.color_10_light,
        name = "Not categorized",
    )

    val category1 = Category(
        id = 0,
        color = CategoryColors.color_10_light,
        name = "Category 1"
    )

    val category2 = Category(
        id = 1,
        color = CategoryColors.color_10_light,
        name = "Category 2"
    )

    val category3 = Category(
        id = 3,
        color = CategoryColors.color_10_light,
        name = "Category 3"
    )

    val values = arrayOf(
        category1,
        category2,
        category3
    )

}