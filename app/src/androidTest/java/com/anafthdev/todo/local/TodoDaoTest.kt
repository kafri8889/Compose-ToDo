package com.anafthdev.todo.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anafthdev.todo.data.datasource.local.AppDatabase
import com.anafthdev.todo.data.datasource.local.LocalTodoDataProvider
import com.anafthdev.todo.data.datasource.local.dao.TodoDao
import com.anafthdev.todo.foundation.extension.toTodoDb
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var todoDao: TodoDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        todoDao = appDatabase.todoDao()
    }

    @After
    fun finish() {
        appDatabase.close()
    }

    @Test
    fun insertAndReadTodo() = runTest {
        val todoList = LocalTodoDataProvider.values
            .map { it.toTodoDb() }
            .toTypedArray()

        val todoListWithCategoryId0 = arrayOf(
            LocalTodoDataProvider.todo1.toTodoDb(),
            LocalTodoDataProvider.todo2.toTodoDb()
        )

        todoDao.insertTodo(*todoList)

        todoDao.getAllTodo().firstOrNull()?.let { list ->
            list.forEach { todoDb ->
                assert(todoDb in todoList) { "Todo $todoDb not in todoList" }
            }
        }

        todoDao.getTodoByCategoryId(0).firstOrNull().let { list ->
            assert(list != null) { "Sub todo list by category id null" }

            list!!.forEach { todoDb ->
                assert(todoDb in todoListWithCategoryId0) { "Todo $todoDb not in Sub todo list with category id 0" }
            }
        }

        todoDao.getTodoById(LocalTodoDataProvider.todo1.id).firstOrNull().let { todoDb ->
            assert(todoDb != null) { "TodoDb null" }
        }
    }

}