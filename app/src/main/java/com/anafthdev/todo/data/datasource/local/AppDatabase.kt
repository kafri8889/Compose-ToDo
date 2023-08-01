package com.anafthdev.todo.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anafthdev.todo.data.datasource.local.dao.CategoryDao
import com.anafthdev.todo.data.datasource.local.dao.SubTodoDao
import com.anafthdev.todo.data.datasource.local.dao.TodoDao
import com.anafthdev.todo.data.model.db.CategoryDb
import com.anafthdev.todo.data.model.db.SubTodoDb
import com.anafthdev.todo.data.model.db.TodoDb

@Database(
    entities = [
        CategoryDb::class,
        SubTodoDb::class,
        TodoDb::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun subTodoDao(): SubTodoDao
    abstract fun todoDao(): TodoDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "app.db")
                        .build()
                }
            }

            return INSTANCE!!
        }
    }

}