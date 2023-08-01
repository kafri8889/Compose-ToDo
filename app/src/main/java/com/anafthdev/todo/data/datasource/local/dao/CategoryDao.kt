package com.anafthdev.todo.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.anafthdev.todo.data.model.db.CategoryDb
import com.anafthdev.todo.data.model.db.relation.CategoryDbWithTodoDb
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_table")
    fun getAllCategory(): Flow<List<CategoryDb>>

    @Query("SELECT * FROM category_table WHERE id_category LIKE :id")
    fun getCategoryById(id: Int): Flow<CategoryDb?>

    @Transaction
    @Query("SELECT * FROM category_table WHERE id_category LIKE :id")
    fun getCategoryByIdWithTodo(id: Int): Flow<CategoryDbWithTodoDb?>

    @Upsert
    suspend fun upsertCategory(vararg categoryDb: CategoryDb)

    @Update
    suspend fun updateCategory(vararg categoryDb: CategoryDb)

    @Delete
    suspend fun deleteCategory(vararg categoryDb: CategoryDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(vararg categoryDb: CategoryDb)

}