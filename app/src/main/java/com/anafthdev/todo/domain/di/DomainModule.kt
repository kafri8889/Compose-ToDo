package com.anafthdev.todo.domain.di

import com.anafthdev.todo.data.repository.interfaces.ICategoryRepository
import com.anafthdev.todo.domain.use_case.CategoryUseCases
import com.anafthdev.todo.domain.use_case.category.GetLocalCategoryUseCase
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
        getLocalCategoryUseCases = GetLocalCategoryUseCase(categoryRepository)
    )

}