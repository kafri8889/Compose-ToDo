package com.anafthdev.todo.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anafthdev.todo.data.datasource.local.AppDatabase
import com.anafthdev.todo.data.datasource.local.LocalSubTodoDataProvider
import com.anafthdev.todo.data.datasource.local.dao.SubTodoDao
import com.anafthdev.todo.foundation.extension.toSubTodoDb
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SubTodoDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var subTodoDao: SubTodoDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        subTodoDao = appDatabase.subTodoDao()
    }

    @After
    fun finish() {
        appDatabase.close()
    }

    @Test
    fun insertAndReadSubTodo() = runTest {
        val subTodoList = LocalSubTodoDataProvider.values
            .map { it.toSubTodoDb() }
            .toTypedArray()

        val subTodoListWithTodoId0 = arrayOf(
            LocalSubTodoDataProvider.subTodo1.toSubTodoDb(),
            LocalSubTodoDataProvider.subTodo2.toSubTodoDb()
        )

        subTodoDao.insertSubTodo(*subTodoList)

        subTodoDao.getAllSubTodo().firstOrNull()?.let { list ->
            list.forEach { subTodoDb ->
                assert(subTodoDb in subTodoList) { "SubTodo $subTodoDb not in subTodoList" }
            }
        }

        subTodoDao.getSubTodoByTodoId(0).firstOrNull().let { list ->
            assert(list != null) { "Sub todo list by user id null" }

            list!!.forEach { subTodoDb ->
                assert(subTodoDb in subTodoListWithTodoId0) { "SubTodo $subTodoDb not in Sub todo list with user id 0" }
            }
        }

        subTodoDao.getSubTodoById(LocalSubTodoDataProvider.subTodo1.id).firstOrNull().let { subTodoDb ->
            assert(subTodoDb != null) { "SubTodoDb null" }
        }
    }

}