package com.anafthdev.todo.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.anafthdev.todo.data.model.db.SubTodoDb
import kotlinx.coroutines.flow.Flow

@Dao
interface SubTodoDao {

    @Query("SELECT * FROM subTodo_table")
    fun getAllSubTodo(): Flow<List<SubTodoDb>>

    @Query("SELECT * FROM subTodo_table WHERE id_subTodo LIKE :id")
    fun getSubTodoById(id: Int): Flow<SubTodoDb?>

    @Query("SELECT * FROM subTodo_table WHERE todoId_subTodo LIKE :id")
    fun getSubTodoByTodoId(id: Int): Flow<List<SubTodoDb>>

    @Upsert
    suspend fun upsertSubTodo(vararg subTodoDb: SubTodoDb)

    @Update
    suspend fun updateSubTodo(vararg subTodoDb: SubTodoDb)

    @Delete
    suspend fun deleteSubTodo(vararg subTodoDb: SubTodoDb)

    @Insert
    suspend fun insertSubTodo(vararg subTodoDb: SubTodoDb)

}