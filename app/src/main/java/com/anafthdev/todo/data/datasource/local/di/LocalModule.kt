package com.anafthdev.todo.data.datasource.local.di

import android.content.Context
import com.anafthdev.todo.data.datasource.local.AppDatabase
import com.anafthdev.todo.data.datasource.local.dao.CategoryDao
import com.anafthdev.todo.data.datasource.local.dao.SubTodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideCategoryDao(
        appDatabase: AppDatabase
    ): CategoryDao = appDatabase.categoryDao()

    @Provides
    @Singleton
    fun provideSubTodoDao(
        appDatabase: AppDatabase
    ): SubTodoDao = appDatabase.subTodoDao()

}