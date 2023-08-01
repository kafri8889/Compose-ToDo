package com.anafthdev.todo.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import com.anafthdev.todo.ProtoUserPreference
import com.anafthdev.todo.data.model.UserPreference
import com.anafthdev.todo.data.repository.interfaces.IUserPreferenceRepository
import com.anafthdev.todo.foundation.extension.toUserPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferenceRepository @Inject constructor(
    private val dataStore: DataStore<ProtoUserPreference>
): IUserPreferenceRepository {

    override fun getUserPreference(): Flow<UserPreference> {
        return dataStore.data
            .filterNotNull()
            .map { it.toUserPreference() }
    }

    companion object {
        val corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { ProtoUserPreference() }
        )
    }

}