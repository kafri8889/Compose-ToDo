package com.anafthdev.todo.data.repository.interfaces

import com.anafthdev.todo.data.model.UserPreference
import kotlinx.coroutines.flow.Flow

interface IUserPreferenceRepository {

    fun getUserPreference(): Flow<UserPreference>

}