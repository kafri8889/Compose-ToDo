package com.anafthdev.todo.domain.di

import com.anafthdev.todo.data.repository.interfaces.ICategoryRepository
import com.anafthdev.todo.domain.use_case.CategoryUseCases
import com.anafthdev.todo.domain.use_case.category.DeleteLocalCategoryUseCase
import com.anafthdev.todo.domain.use_case.category.GetLocalCategoryUseCase
import com.anafthdev.todo.domain.use_case.category.InsertLocalCategoryUseCase
import com.anafthdev.todo.domain.use_case.category.UpdateLocalCategoryUseCase
import com.anafthdev.todo.domain.use_case.category.UpsertLocalCategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideCategoryUseCases(
        categoryRepository: ICategoryRepository
    ): CategoryUseCases = CategoryUseCases(
        getLocalCategoryUseCases = GetLocalCategoryUseCase(categoryRepository),
        upsertLocalCategoryUseCase = UpsertLocalCategoryUseCase(categoryRepository),
        updateLocalCategoryUseCase = UpdateLocalCategoryUseCase(categoryRepository),
        deleteLocalCategoryUseCase = DeleteLocalCategoryUseCase(categoryRepository),
        insertLocalCategoryUseCase = InsertLocalCategoryUseCase(categoryRepository)
    )

}