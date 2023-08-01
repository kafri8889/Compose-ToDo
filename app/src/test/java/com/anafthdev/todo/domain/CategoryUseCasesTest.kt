package com.anafthdev.todo.domain

import com.anafthdev.todo.data.datasource.local.LocalCategoryDataProvider
import com.anafthdev.todo.data.datasource.local.LocalTodoDataProvider
import com.anafthdev.todo.data.model.db.CategoryDb
import com.anafthdev.todo.data.model.db.relation.CategoryDbWithTodoDb
import com.anafthdev.todo.data.repository.interfaces.ICategoryRepository
import com.anafthdev.todo.domain.use_case.category.GetLocalCategoryUseCase
import com.anafthdev.todo.domain.util.GetCategoryBy
import com.anafthdev.todo.foundation.extension.toCategoryDb
import com.anafthdev.todo.foundation.extension.toTodoDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CategoryUseCasesTest {

    private lateinit var getLocalCategoryUseCase: GetLocalCategoryUseCase

    @BeforeEach
    fun setUp() {
        val repository = object : ICategoryRepository {
            override fun getAllLocalCategory(): Flow<List<CategoryDb>> {
                return flow {
                    emit(LocalCategoryDataProvider.values.map { it.toCategoryDb() })
                }
            }

            override fun getLocalCategoryById(id: Int): Flow<CategoryDb?> {
                return flow {
                    emit(LocalCategoryDataProvider.values.find { it.id == id }?.toCategoryDb())
                }
            }

            override fun getLocalCategoryByIdWithTodo(id: Int): Flow<CategoryDbWithTodoDb?> {
                return flow {
                    emit(
                        LocalCategoryDataProvider.values.find { it.id == id }?.toCategoryDb().let { categoryDb ->
                            if (categoryDb != null) {
                                CategoryDbWithTodoDb(
                                    categoryDb = categoryDb,
                                    todoList = LocalTodoDataProvider.values.filter { it.categoryId == categoryDb.id }.map { it.toTodoDb() }
                                )
                            } else null
                        }
                    )
                }
            }

            override suspend fun upsertLocalCategory(vararg categoryDb: CategoryDb) {

            }

            override suspend fun updateLocalCategory(vararg categoryDb: CategoryDb) {

            }

            override suspend fun deleteLocalCategory(vararg categoryDb: CategoryDb) {

            }

            override suspend fun insertLocalCategory(vararg categoryDb: CategoryDb) {

            }
        }

        getLocalCategoryUseCase = GetLocalCategoryUseCase(repository)
    }

    @Test
    fun `get all category`() = runTest {
        getLocalCategoryUseCase(
            getCategoryBy = GetCategoryBy.All
        ).firstOrNull().let { categories ->
            assert(categories != null) { "Null categories" }
            assert(categories!!.isNotEmpty()) { "Empty categories" }
            assert(categories.size == LocalCategoryDataProvider.values.size) { "Size not equals" }
        }
    }

    @Test
    fun `get category by id found`() = runTest {
        getLocalCategoryUseCase(
            getCategoryBy = GetCategoryBy.ID(LocalCategoryDataProvider.category1.id)
        ).firstOrNull().let { categories ->
            assert(categories != null) { "Null categories" }
            assert(categories!!.isNotEmpty()) { "Empty categories" }
            assert(categories[0].id == LocalCategoryDataProvider.category1.id) { "ID not equals" }
        }
    }

    @Test
    fun `get category by id not found`() = runTest {
        getLocalCategoryUseCase(
            getCategoryBy = GetCategoryBy.ID(-1)
        ).firstOrNull().let { categories ->
            assert(categories == null) { "Categories not null" }
        }
    }

    @Test
    fun `get category by id with todo`() = runTest {
        val id = LocalCategoryDataProvider.category1.id
        val todoList = LocalTodoDataProvider.values.filter { it.categoryId == id }

        getLocalCategoryUseCase(
            getCategoryBy = GetCategoryBy.IDWithTodo(id)
        ).firstOrNull().let { categories ->
            assert(categories != null) { "Null categories" }
            assert(categories!!.isNotEmpty()) { "Empty categories" }
            assert(categories[0].id == id) { "ID not equals" }
            assert(categories[0].todo.size == todoList.size) { "Todo size not equals" }
        }
    }

}