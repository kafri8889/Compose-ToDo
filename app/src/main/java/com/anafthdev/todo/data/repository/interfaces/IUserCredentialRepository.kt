package com.anafthdev.todo.data.repository.interfaces

import com.anafthdev.todo.data.model.UserCredential
import kotlinx.coroutines.flow.Flow

interface IUserCredentialRepository {

    fun getUserCredential(): Flow<UserCredential>

}