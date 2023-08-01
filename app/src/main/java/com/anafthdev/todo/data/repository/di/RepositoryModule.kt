package com.anafthdev.todo.data.repository.di

import androidx.datastore.core.DataStore
import com.anafthdev.todo.ProtoUserCredential
import com.anafthdev.todo.ProtoUserPreference
import com.anafthdev.todo.data.datasource.local.dao.CategoryDao
import com.anafthdev.todo.data.datasource.local.dao.SubTodoDao
import com.anafthdev.todo.data.datasource.local.dao.TodoDao
import com.anafthdev.todo.data.repository.CategoryRepository
import com.anafthdev.todo.data.repository.SubTodoRepository
import com.anafthdev.todo.data.repository.TodoRepository
import com.anafthdev.todo.data.repository.UserCredentialRepository
import com.anafthdev.todo.data.repository.UserPreferenceRepository
import com.anafthdev.todo.data.repository.interfaces.ICategoryRepository
import com.anafthdev.todo.data.repository.interfaces.ISubTodoRepository
import com.anafthdev.todo.data.repository.interfaces.ITodoRepository
import com.anafthdev.todo.data.repository.interfaces.IUserCredentialRepository
import com.anafthdev.todo.data.repository.interfaces.IUserPreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserCredentialRepository(
        dataStore: DataStore<ProtoUserCredential>
    ): IUserCredentialRepository = UserCredentialRepository(dataStore)

    @Provides
    @Singleton
    fun provideUserPreferenceRepository(
        dataStore: DataStore<ProtoUserPreference>
    ): IUserPreferenceRepository = UserPreferenceRepository(dataStore)

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryDao: CategoryDao
    ): ICategoryRepository = CategoryRepository(
        categoryDao = categoryDao
    )

    @Provides
    @Singleton
    fun provideSubTodoRepository(
        subTodoDao: SubTodoDao
    ): ISubTodoRepository = SubTodoRepository(
        subTodoDao = subTodoDao
    )

    @Provides
    @Singleton
    fun provideTodoRepository(
        todoDao: TodoDao
    ): ITodoRepository = TodoRepository(
        todoDao = todoDao
    )

}