package com.anafthdev.todo.foundation.extension

import com.anafthdev.todo.ProtoUserPreference
import com.anafthdev.todo.data.model.UserPreference

/**
 * Convert [ProtoUserPreference] to [UserPreference]
 */
fun ProtoUserPreference.toUserPreference(): UserPreference = UserPreference(isGuest)
