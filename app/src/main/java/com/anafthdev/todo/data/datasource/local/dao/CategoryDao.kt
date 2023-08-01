package com.anafthdev.todo.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.anafthdev.todo.data.model.db.CategoryDb
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_table")
    fun getAllCategory(): Flow<List<CategoryDb>>

    @Query("SELECT * FROM category_table WHERE id_category LIKE :id")
    fun getCategoryById(id: Int): Flow<CategoryDb?>

    @Query("SELECT * FROM category_table WHERE userId_category LIKE :id")
    fun getCategoryByUserId(id: Int): Flow<List<CategoryDb>>

    @Upsert
    suspend fun upsertCategory(vararg categoryDb: CategoryDb)

    @Update
    suspend fun updateCategory(vararg categoryDb: CategoryDb)

    @Delete
    suspend fun deleteCategory(vararg categoryDb: CategoryDb)

    @Insert
    suspend fun insertCategory(vararg categoryDb: CategoryDb)

}