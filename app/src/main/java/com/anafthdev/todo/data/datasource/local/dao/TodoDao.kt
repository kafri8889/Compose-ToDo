package com.anafthdev.todo.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.anafthdev.todo.data.model.db.TodoDb
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table")
    fun getAllTodo(): Flow<List<TodoDb>>

    @Query("SELECT * FROM todo_table WHERE id_todo LIKE :id")
    fun getTodoById(id: Int): Flow<TodoDb?>

    @Query("SELECT * FROM todo_table WHERE categoryId_todo LIKE :id")
    fun getTodoByCategoryId(id: Int): Flow<List<TodoDb>>

    @Upsert
    suspend fun upsertTodo(vararg todoDb: TodoDb)

    @Update
    suspend fun updateTodo(vararg todoDb: TodoDb)

    @Delete
    suspend fun deleteTodo(vararg todoDb: TodoDb)

    @Insert
    suspend fun insertTodo(vararg todoDb: TodoDb)

}