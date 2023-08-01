package com.anafthdev.todo.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.anafthdev.todo.ProtoUserCredential
import com.anafthdev.todo.ProtoUserPreference
import com.anafthdev.todo.data.Constant
import com.anafthdev.todo.data.repository.UserCredentialRepository
import com.anafthdev.todo.data.repository.UserPreferenceRepository
import com.anafthdev.todo.data.serializer.UserCredentialSerializer
import com.anafthdev.todo.data.serializer.UserPreferenceSerializer
import com.anafthdev.todo.foundation.common.EncryptionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {

    @Provides
    @Singleton
    fun provideUserCredentialDataStore(
        @ApplicationContext context: Context,
        encryptionManager: EncryptionManager
    ): DataStore<ProtoUserCredential> = DataStoreFactory.create(
        serializer = UserCredentialSerializer(encryptionManager),
        corruptionHandler = UserCredentialRepository.corruptionHandler,
        produceFile = { context.dataStoreFile(Constant.USER_CREDENTIAL) }
    )

    @Provides
    @Singleton
    fun provideUserPreference(
        @ApplicationContext context: Context,
    ): DataStore<ProtoUserPreference> = DataStoreFactory.create(
        serializer = UserPreferenceSerializer,
        corruptionHandler = UserPreferenceRepository.corruptionHandler,
        produceFile = { context.dataStoreFile(Constant.USER_PREFERENCE) }
    )

}