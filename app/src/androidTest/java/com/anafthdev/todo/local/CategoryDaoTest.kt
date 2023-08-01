package com.anafthdev.todo.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anafthdev.todo.data.datasource.local.AppDatabase
import com.anafthdev.todo.data.datasource.local.LocalCategoryDataProvider
import com.anafthdev.todo.data.datasource.local.LocalTodoDataProvider
import com.anafthdev.todo.data.datasource.local.dao.CategoryDao
import com.anafthdev.todo.data.datasource.local.dao.TodoDao
import com.anafthdev.todo.foundation.extension.toCategoryDb
import com.anafthdev.todo.foundation.extension.toTodoDb
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var categoryDao: CategoryDao
    private lateinit var todoDao: TodoDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        todoDao = appDatabase.todoDao()
        categoryDao = appDatabase.categoryDao()
    }

    @After
    fun finish() {
        appDatabase.close()
    }

    @Test
    fun insertAndReadCategory() = runTest {
        val categories = LocalCategoryDataProvider.values
            .map { it.toCategoryDb() }
            .toTypedArray()

        val categoriesWithUserId0 = arrayOf(
            LocalCategoryDataProvider.category1.toCategoryDb(),
            LocalCategoryDataProvider.category2.toCategoryDb()
        )

        categoryDao.insertCategory(*categories)

        categoryDao.getAllCategory().firstOrNull()?.let { list ->
            list.forEach { categoryDb ->
                assert(categoryDb in categories) { "Category $categoryDb not in categories" }
            }
        }

        categoryDao.getCategoryByUserId(0).firstOrNull().let { list ->
            assert(list != null) { "Categories by user id null" }

            list!!.forEach { categoryDb ->
                assert(categoryDb in categoriesWithUserId0) { "Category $categoryDb not in Categories with user id 0" }
            }
        }

        categoryDao.getCategoryById(LocalCategoryDataProvider.category1.id).firstOrNull().let { categoryDb ->
            assert(categoryDb != null) { "CategoryDb null" }
        }
    }

    @Test
    fun getCategoryByIdWithTodo() = runTest {
        val categoryDb = LocalCategoryDataProvider.category1.toCategoryDb()
        val todoList = LocalTodoDataProvider.values
            .map { it.toTodoDb() }
            .filter { it.categoryId == categoryDb.id }
            .toTypedArray()

        categoryDao.insertCategory(categoryDb)
        todoDao.insertTodo(*todoList)

        categoryDao.getCategoryByIdWithTodo(categoryDb.id).firstOrNull().let { categoryDbWithTodoDb ->
            assert(categoryDbWithTodoDb != null) { "Category db with todo db list null" }
            assert(categoryDbWithTodoDb!!.todoList.isNotEmpty()) { "Todo db list empty" }
            assert(categoryDbWithTodoDb.todoList.size == todoList.size) {
                "Todo db list size not equals (${categoryDbWithTodoDb.todoList.size} == ${todoList.size})"
            }
        }
    }

}