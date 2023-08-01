package com.anafthdev.todo.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import com.anafthdev.todo.ProtoUserCredential
import com.anafthdev.todo.data.model.UserCredential
import com.anafthdev.todo.data.repository.interfaces.IUserCredentialRepository
import com.anafthdev.todo.foundation.extension.toUserCredential
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserCredentialRepository @Inject constructor(
    private val dataStore: DataStore<ProtoUserCredential>
): IUserCredentialRepository {

    override fun getUserCredential(): Flow<UserCredential> {
        return dataStore.data
            .filterNotNull()
            .map { it.toUserCredential() }
    }

    companion object {
        val corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { ProtoUserCredential() }
        )
    }

}