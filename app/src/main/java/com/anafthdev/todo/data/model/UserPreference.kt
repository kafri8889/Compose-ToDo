package com.anafthdev.todo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserPreference(
    val isGuest: Boolean
): Parcelable
