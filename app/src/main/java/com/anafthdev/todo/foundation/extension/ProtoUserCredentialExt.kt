package com.anafthdev.todo.foundation.extension

import com.anafthdev.todo.ProtoUserCredential
import com.anafthdev.todo.data.model.UserCredential

/**
 * Convert [ProtoUserCredential] to [UserCredential]
 */
fun ProtoUserCredential.toUserCredential(): UserCredential = UserCredential(id, name, email, password)
