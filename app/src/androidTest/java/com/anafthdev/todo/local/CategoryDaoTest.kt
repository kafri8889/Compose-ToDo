package com.anafthdev.todo.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anafthdev.todo.data.datasource.local.AppDatabase
import com.anafthdev.todo.data.datasource.local.LocalCategoryDataProvider
import com.anafthdev.todo.data.datasource.local.dao.CategoryDao
import com.anafthdev.todo.foundation.extension.toCategoryDb
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

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

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

}